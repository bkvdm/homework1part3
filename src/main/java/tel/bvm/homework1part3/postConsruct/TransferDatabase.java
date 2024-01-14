package tel.bvm.homework1part3.postConsruct;

public class TransferDatabase {

//    @Autowired
//    private DataSource dataSource; // предположим, что соединение с базой уже создано
//
//
//    @PostConstruct
//    void init() {
//        insertData();
//    }
//
//
//    void insertData() {
//        final Connection connection = dataSource.getConnection();
//        try(PreparedStatement statement = connection.prepareStatement(
//                "INSERT INTO STUDENTS(ID, NAME, AGE) VALUES(?, ?, ?)")){
//
//            for(Student student: students){ // students - список студентов
//                int id = student.getId();
//                String name = student.getName();
//                int age = student.getAge();
//
//                statement.setInt(1, id);
//                statement.setString(2, name);
//                statement.setInt(3, age);
//                statement.addBatch();
//            }
//
//            statement.executeBatch();
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }finally{
//            connection.close();
//        }
//    }
}
