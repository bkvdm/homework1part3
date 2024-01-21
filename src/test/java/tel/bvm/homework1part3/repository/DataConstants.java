package tel.bvm.homework1part3.repository;

import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.util.*;


public class DataConstants {

    public static Faculty FACULTY_1 = new Faculty(1, "Когневран (Ravenclaw)", "Красный (Red)", null);
    public static Faculty FACULTY_2 = new Faculty(2, "Гриффиндор (Gryffindor)", "Жёлтый (Yellow)", null);
    public static Faculty FACULTY_3 = new Faculty(3, "Пуффендуй (Hufflepuff)", "Зелёный (Green)", null);
    public static Faculty FACULTY_4 = new Faculty(4, "Слизерин (Slytherin)", "Фиолетовый (Purple)", null);

    public static Map<Long, Faculty> FACULTY_MAP = new HashMap<>(Map.of(
            FACULTY_1.getId(), FACULTY_1,
            FACULTY_2.getId(), FACULTY_2,
            FACULTY_3.getId(), FACULTY_3,
            FACULTY_4.getId(), FACULTY_4));

    public static List<Faculty> FACULTY_LIST = new ArrayList<>(FACULTY_MAP.values());

    public static List<Faculty> FACULTY_CONTAINS_NAME = new ArrayList<>(List.of(FACULTY_3));

    public static List<Faculty> filterByFragmentNameFaculty(List<Faculty> faculty, String fragment) {
        List<Faculty> FILTERED_FACULTY = new ArrayList<>();
        for (Faculty s : faculty) {
            if (s.getName().toLowerCase().contains(fragment)) {
                FILTERED_FACULTY.add(s);
            }
        }
        return FILTERED_FACULTY;
    }

    public static List<Faculty> filterByFragmentColorFaculty(List<Faculty> faculty, String fragment) {
        List<Faculty> FILTERED_FACULTY = new ArrayList<>();
        for (Faculty s : faculty) {
            if (s.getColor().toLowerCase().contains(fragment)) {
                FILTERED_FACULTY.add(s);
            }
        }
        return FILTERED_FACULTY;
    }

    public static Student STUDENT_1 = new Student(1L, "Луна Лавгуд (Luna Lovegood)", 25, facultyInfo(1));
    public static Student STUDENT_2 = new Student(2L, "Дин Томас (Dean Thomas)", 78, facultyInfo(1));
    public static Student STUDENT_3 = new Student(3L, "Симус Финниган (Seamus Finnigan)", 43, facultyInfo(3));
    public static Student STUDENT_4 = new Student(4L, "Блейз Забини (Blaise Zabini)", 67, facultyInfo(2));
    public static Student STUDENT_5 = new Student(5L, "Миллисента Булстроуд (Millicent Bulstrode)", 89, facultyInfo(4));
    public static Student STUDENT_6 = new Student(6L, "Панси Паркинсон (Pansy Parkinson)", 93, facultyInfo(4));
    public static Student STUDENT_7 = new Student(7L, "Чо Чанг (Cho Chang)", 56, facultyInfo(4));
    public static Student STUDENT_8 = new Student(8L, "Майкл Корнер (Michael Corner)", 34, facultyInfo(1));
    public static Student STUDENT_9 = new Student(9L, "Роберт Хиллиард (Robert Hilliard)", 100, facultyInfo(1));
    public static Student STUDENT_10 = new Student(10L, "Элеанор Брэнсток (Eleanor Branstock)", 18, facultyInfo(1));
    public static Student STUDENT_11 = new Student(11L, "Кэти Белл (Katie Bell)", 73, facultyInfo(3));
    public static Student STUDENT_12 = new Student(12L, "Алисия Спиннет (Alicia Spinnet)", 23, facultyInfo(2));
    public static Student STUDENT_13 = new Student(13L, "Анджелина Джонсон (Angelina Johnson)", 64, facultyInfo(4));
    public static Student STUDENT_14 = new Student(14L, "Кэтти Бел (Kathy Bell)", 83, facultyInfo(2));
    public static Student STUDENT_15 = new Student(15L, "Колин Криви (Colin Creevey)", 49, facultyInfo(2));
    public static Student STUDENT_16 = new Student(16L, "Гарри Поттер (Harry Potter)", 11, facultyInfo(2));
    public static Student STUDENT_17 = new Student(17L, "Гермиона Грейнджер (Hermione Granger)", 11, facultyInfo(2));
    public static Student STUDENT_18 = new Student(18L, "Рон Уизли (Ron Weasley)", 11, facultyInfo(2));
    public static Student STUDENT_19 = new Student(19L, "Драко Малфой (Draco Malfoy)", 12, facultyInfo(2));
    public static Student STUDENT_20 = new Student(20L, "Невилл Лонгботтом (Neville Longbottom)", 10, facultyInfo(4));
    public static Student STUDENT_21 = new Student(21L, "Джинни Уизли (Ginny Weasley)", 7, facultyInfo(3));
    public static Student STUDENT_22 = new Student(22L, "Альбус Дамблдор (Albus Dumbledore)", 115, facultyInfo(2));
    public static Student STUDENT_23 = new Student(23L, "Северус Снегг (Severus Snape)", 38, facultyInfo(3));
    public static Student STUDENT_24 = new Student(24L, "Филиус Флитвик (Filius Flitwick)", 75, facultyInfo(1));

    public static Faculty facultyInfo(long id) {
        return FACULTY_MAP.get(id);
    }

    public List<Student> STUDENT_LIST = new ArrayList<>(Arrays.asList
            (STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4, STUDENT_5, STUDENT_6,
                    STUDENT_7, STUDENT_8, STUDENT_9, STUDENT_10, STUDENT_11,
                    STUDENT_12, STUDENT_13, STUDENT_14, STUDENT_15, STUDENT_16,
                    STUDENT_17, STUDENT_18, STUDENT_19, STUDENT_20, STUDENT_21,
                    STUDENT_22, STUDENT_23, STUDENT_24));

    public List<Student> STUDENT_LIST(Faculty faculty) {
        List<Student> studentsFaculty = new ArrayList<>();

        for (Student student : STUDENT_LIST) {
            if (student.getFaculty().equals(faculty)) {
                STUDENT_LIST.add(student);
            }
        }
        facultyRegistry();
        return studentsFaculty;
    }

    public void facultyRegistry() {
        FACULTY_1.setStudents(STUDENT_LIST(FACULTY_1));
        FACULTY_2.setStudents(STUDENT_LIST(FACULTY_2));
        FACULTY_3.setStudents(STUDENT_LIST(FACULTY_3));
        FACULTY_4.setStudents(STUDENT_LIST(FACULTY_4));
    }
}
