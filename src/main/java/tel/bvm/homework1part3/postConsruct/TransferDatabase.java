package tel.bvm.homework1part3.postConsruct;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class TransferDatabase {

    private final DefaultDataStudents defaultDataStudents;
    private final DefaultDataFaculties defaultDataFaculties;

    private final DataSource dataSource;
    public TransferDatabase(DefaultDataStudents defaultDataStudents, DefaultDataFaculties defaultDataFaculties, DataSource dataSource) {
        this.defaultDataStudents = defaultDataStudents;
        this.defaultDataFaculties = defaultDataFaculties;
        this.dataSource = dataSource;
    }

//    @Autowired
//    private DataSource dataSource;
//    private DataSource hogwards;

    @PostConstruct
    void init() throws SQLException {
        insertDataStudents();
        insertDataFaculties();
    }

    void insertDataStudents() throws SQLException {

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO STUDENT(ID, NAME, AGE) VALUES(?, ?, ?)")) {

            for (Student student : defaultDataStudents.studentList) {
                long id = student.getId();
                String name = student.getName();
                int age = student.getAge();
//                Faculty faculty = student.getFaculty();

                statement.setLong(1, id);
                statement.setString(2, name);
                statement.setInt(3, age);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void insertDataFaculties() throws SQLException {
        final Connection connection = dataSource.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO FACULTY(ID, NAME, COLOR) VALUES(?, ?, ?)")) {

            for (Map.Entry<Long, Faculty> entry : defaultDataFaculties.facultyMap.entrySet()) {
                Faculty faculty = entry.getValue();
                long id = faculty.getId();
                String name = faculty.getName();
                String color = faculty.getColor();

                statement.setLong(1, id);
                statement.setString(2, name);
                statement.setString(3, color);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
//            for(Faculty faculty : defaultDataFaculties.facultyMap){
//                long id = faculty.getId();
//                String name = faculty.getName();
//                String color = faculty.getColor();
//
//                statement.setLong(1, id);
//                statement.setString(2, name);
//                statement.setString(3, color);
//                statement.addBatch();
//            }
