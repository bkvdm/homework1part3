package tel.bvm.homework1part3.postConsruct;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

@Component
public class TransferDatabase {

    private final DefaultDataStudents defaultDataStudents;
    private final DefaultDataFaculties defaultDataFaculties;

    private final DataSource dataSource;

    public TransferDatabase(DefaultDataStudents defaultDataStudents, DefaultDataFaculties defaultDataFaculties, DataSource dataSource) {
        this.defaultDataStudents = defaultDataStudents;
        this.defaultDataFaculties = defaultDataFaculties;
        this.dataSource = dataSource;
    }

    @PostConstruct
    void init() throws SQLException {
        insertDataFaculties();
        insertDataStudents();
    }

//    void insertDataFaculties() throws SQLException {
//        final Connection connection = dataSource.getConnection();
//
//        try (PreparedStatement statement = connection.prepareStatement(
//                "INSERT INTO FACULTY(ID, NAME, COLOR) VALUES(?, ?, ?)")) {
//
//            for (Map.Entry<Long, Faculty> entry : defaultDataFaculties.facultyMap.entrySet()) {
//                Faculty faculty = entry.getValue();
//                long id = faculty.getId();
//                String name = faculty.getName();
//                String color = faculty.getColor();
//
//                statement.setLong(1, id);
//                statement.setString(2, name);
//                statement.setString(3, color);
//                statement.addBatch();
//            }
//
//            statement.executeBatch();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            connection.close();
//        }
//    }

    void insertDataFaculties() throws SQLException {
        final Connection connection = dataSource.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
//                "INSERT INTO FACULTY(ID, NAME, COLOR) VALUES(?, ?, ?)")) {
                "INSERT INTO FACULTY(NAME, COLOR) VALUES(?, ?)")) {

            for (Map.Entry<Long, Faculty> entry : defaultDataFaculties.facultyMap.entrySet()) {
                Faculty faculty = entry.getValue();
                long id = faculty.getId();
                String name = faculty.getName();
                String color = faculty.getColor();

//                statement.setLong(1, id);
                statement.setString(1, name);
                statement.setString(2, color);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

//    void insertDataStudents() throws SQLException {
//
//        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
//                "INSERT INTO STUDENT(ID, NAME, AGE, IdFACULTY) VALUES(?, ?, ?, ?)")) {
//
//            for (Student student : defaultDataStudents.studentList) {
//                long id = student.getId();
//                String name = student.getName();
//                int age = student.getAge();
//                long idFaculty = student.getFaculty().getId();
//
//                statement.setLong(1, id);
//                statement.setString(2, name);
//                statement.setInt(3, age);
//                statement.setLong(4, idFaculty);
//                statement.addBatch();
//            }
//
//            statement.executeBatch();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    void insertDataStudents() throws SQLException {

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(
//                "INSERT INTO STUDENT(ID, NAME, AGE, IdFACULTY) VALUES(?, ?, ?, ?)")) {
                "INSERT INTO STUDENT(NAME, AGE, IdFACULTY) VALUES(?, ?, ?)")) {

            for (Student student : defaultDataStudents.studentList) {
                long id = student.getId();
                String name = student.getName();
                int age = student.getAge();
                long idFaculty = student.getFaculty().getId();

//                statement.setLong(1, id);
                statement.setString(1, name);
                statement.setInt(2, age);
                statement.setLong(3, idFaculty);
                statement.addBatch();
            }

            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}