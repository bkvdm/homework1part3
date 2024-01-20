package tel.bvm.homework1part3.constants;

import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.util.*;

public class DataTests {

    Faculty faculty1 = new Faculty(1, "Когневран (Ravenclaw)", "Красный (Red)", null);
    Faculty faculty2 = new Faculty(2, "Гриффиндор (Gryffindor)", "Жёлтый (Yellow)", null);
    Faculty faculty3 = new Faculty(3, "Пуффендуй (Hufflepuff)", "Зелёный (Green)", null);
    Faculty faculty4 = new Faculty(4, "Слизерин (Slytherin)", "Фиолетовый (Purple)", null);

    Map<Long, Faculty> facultyMap = new HashMap<>(Map.of(
            faculty1.getId(), faculty1,
            faculty2.getId(), faculty2,
            faculty3.getId(), faculty3,
            faculty4.getId(), faculty4));

    Student student1 = new Student(1L, "Луна Лавгуд (Luna Lovegood)", 25, facultyInfo(1));
    Student student2 = new Student(2L, "Дин Томас (Dean Thomas)", 78, facultyInfo(1));
    Student student3 = new Student(3L, "Симус Финниган (Seamus Finnigan)", 43, facultyInfo(3));
    Student student4 = new Student(4L, "Блейз Забини (Blaise Zabini)", 67, facultyInfo(2));
    Student student5 = new Student(5L, "Миллисента Булстроуд (Millicent Bulstrode)", 89, facultyInfo(4));
    Student student6 = new Student(6L, "Панси Паркинсон (Pansy Parkinson)", 93, facultyInfo(4));
    Student student7 = new Student(7L, "Чо Чанг (Cho Chang)", 56, facultyInfo(4));
    Student student8 = new Student(8L, "Майкл Корнер (Michael Corner)", 34, facultyInfo(1));
    Student student9 = new Student(9L, "Роберт Хиллиард (Robert Hilliard)", 100, facultyInfo(1));
    Student student10 = new Student(10L, "Элеанор Брэнсток (Eleanor Branstock)", 18, facultyInfo(1));
    Student student11 = new Student(11L, "Кэти Белл (Katie Bell)", 73, facultyInfo(3));
    Student student12 = new Student(12L, "Алисия Спиннет (Alicia Spinnet)", 23, facultyInfo(2));
    Student student13 = new Student(13L, "Анджелина Джонсон (Angelina Johnson)", 64, facultyInfo(4));
    Student student14 = new Student(14L, "Кэтти Бел (Kathy Bell)", 83, facultyInfo(2));
    Student student15 = new Student(15L, "Колин Криви (Colin Creevey)", 49, facultyInfo(2));
    Student student16 = new Student(16L, "Гарри Поттер (Harry Potter)", 11, facultyInfo(2));
    Student student17 = new Student(17L, "Гермиона Грейнджер (Hermione Granger)", 11, facultyInfo(2));
    Student student18 = new Student(18L, "Рон Уизли (Ron Weasley)", 11, facultyInfo(2));
    Student student19 = new Student(19L, "Драко Малфой (Draco Malfoy)", 12, facultyInfo(2));
    Student student20 = new Student(20L, "Невилл Лонгботтом (Neville Longbottom)", 10, facultyInfo(4));
    Student student21 = new Student(21L, "Джинни Уизли (Ginny Weasley)", 7, facultyInfo(3));
    Student student22 = new Student(22L, "Альбус Дамблдор (Albus Dumbledore)", 115, facultyInfo(2));
    Student student23 = new Student(23L, "Северус Снегг (Severus Snape)", 38, facultyInfo(3));
    Student student24 = new Student(24L, "Филиус Флитвик (Filius Flitwick)", 75, facultyInfo(1));

    public Faculty facultyInfo(long id) {
        return facultyMap.get(id);
    }

    List<Student> studentList = new ArrayList<>(Arrays.asList
            (student1, student2, student3, student4, student5, student6,
                    student7, student8, student9, student10, student11,
                    student12, student13, student14, student15, student16,
                    student17, student18, student19, student20, student21,
                    student22, student23, student24));

    public List<Student> studentList(Faculty faculty) {
        List<Student> studentsFaculty = new ArrayList<>();

        for (Student student : studentList) {
            if (student.getFaculty().equals(faculty)) {
                studentList.add(student);
            }
        }
        facultyRegistry();
        return studentsFaculty;
    }

    public void facultyRegistry() {
        faculty1.setStudents(studentList(faculty1));
        faculty2.setStudents(studentList(faculty2));
        faculty3.setStudents(studentList(faculty3));
        faculty4.setStudents(studentList(faculty4));
    }
}
