package tel.bvm.homework1part3.postConsruct;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import tel.bvm.homework1part3.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferDatabase {

    private DefaultDataStudents defaultDataStudents;

    public TransferDatabase(DefaultDataStudents defaultDataStudents) {
        this.defaultDataStudents = defaultDataStudents;
    }

    @Autowired
    private DataSource dataSource; // предположим, что соединение с базой уже создано

    @PostConstruct
    void init() throws SQLException {
        insertData();
    }

    void insertData() throws SQLException {
        final Connection connection = dataSource.getConnection();

        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO STUDENTS(ID, NAME, AGE) VALUES(?, ?, ?)")){

            for(Student student: defaultDataStudents.studentList){ // students - список студентов
                long id = student.getId();
                String name = student.getName();
                int age = student.getAge();

                statement.setLong(1, id);
                statement.setString(2, name);
                statement.setInt(3, age);
                statement.addBatch();
            }

            statement.executeBatch();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally{
            connection.close();
        }
    }
}
