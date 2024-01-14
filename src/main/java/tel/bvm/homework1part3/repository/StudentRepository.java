package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Student;

import java.sql.PreparedStatement;

public interface StudentRepository extends JpaRepository<Student, Long> {
////
////    String sql = "INSERT INTO table_name (column1, column2, column3) VALUES (?, ?, ?)";
//////    PreparedStatement preparedStmt = conn.prepareStatement(sql);
////
////            preparedStmt.setString(1, "Id");
////            preparedStmt.setString(2, "Luna Lovegood");
////            preparedStmt.setInt(3, 25);
////
////
////    String[] data = new String[] {
////“Cogneewran”, “Luna Lovegood”, “25”,
////            “Cogneewran”, “Dean Thomas”, “78”,
////            …
////};
//
//    String[] data = new String[]{
//            1, "Luna Lovegood", 25,
//            2, "Dean Thomas", 78};
//
//    Connection connection = dataSource.getConnection();
//try(
//    Statement statement = connection.createStatement())
//
//    {
//        for (String[] values : data) {
//            statement.executeUpdate(
//                    "INSERT INTO students (id, lastName, age) " +
//                    “VALUES('” + values[0] + “', '” + values[1] + “',” + values[2] + “ ')”);
//        }
//    } catch(
//    Exception e)
//
//    {
//        e.printStackTrace();
//    }
}