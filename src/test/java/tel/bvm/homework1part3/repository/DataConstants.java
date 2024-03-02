package tel.bvm.homework1part3.repository;

import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.util.*;

public class DataConstants {

    public static final Faculty FACULTY_1 = new Faculty(1, "Когневран (Ravenclaw)", "Красный (Red)", null);
    public static final Faculty FACULTY_2 = new Faculty(2, "Гриффиндор (Gryffindor)", "Жёлтый (Yellow)", null);
    public static final Faculty FACULTY_3 = new Faculty(3, "Пуффендуй (Hufflepuff)", "Зелёный (Green)", null);
    public static final Faculty FACULTY_4 = new Faculty(4, "Слизерин (Slytherin)", "Фиолетовый (Purple)", null);

    public static final Map<Long, Faculty> FACULTY_MAP = new HashMap<>(Map.of(
            FACULTY_1.getId(), FACULTY_1,
            FACULTY_2.getId(), FACULTY_2,
            FACULTY_3.getId(), FACULTY_3,
            FACULTY_4.getId(), FACULTY_4));

    public static final List<Faculty> FACULTY_LIST = new ArrayList<>(FACULTY_MAP.values());

    public static final List<Faculty> FACULTY_CONTAINS_NAME_1 = new ArrayList<>(List.of(FACULTY_1));
    public static final List<Faculty> FACULTY_CONTAINS_NAME_2 = new ArrayList<>(List.of(FACULTY_2));
    public static final List<Faculty> FACULTY_CONTAINS_NAME_3 = new ArrayList<>(List.of(FACULTY_3));
    public static final List<Faculty> FACULTY_CONTAINS_NAME_4 = new ArrayList<>(List.of(FACULTY_4));

    public static final List<Faculty> filterByFragmentNameFaculty(List<Faculty> faculty, String fragment) {
        List<Faculty> FILTERED_FACULTY = new ArrayList<>();
        for (Faculty s : faculty) {
            if (s.getName().toLowerCase().contains(fragment)) {
                FILTERED_FACULTY.add(s);
            }
        }
        return FILTERED_FACULTY;
    }

    public static final List<Faculty> filterByFragmentColorFaculty(List<Faculty> faculty, String fragment) {
        List<Faculty> FILTERED_FACULTY = new ArrayList<>();
        for (Faculty s : faculty) {
            if (s.getColor().toLowerCase().contains(fragment)) {
                FILTERED_FACULTY.add(s);
            }
        }
        return FILTERED_FACULTY;
    }

    public static final Student STUDENT_1 = new Student(1L, "Луна Лавгуд (Luna Lovegood)", 25, facultyInfo(1));
    public static final Student STUDENT_2 = new Student(2L, "Дин Томас (Dean Thomas)", 78, facultyInfo(1));
    public static final Student STUDENT_3 = new Student(3L, "Симус Финниган (Seamus Finnigan)", 43, facultyInfo(3));
    public static final Student STUDENT_4 = new Student(4L, "Блейз Забини (Blaise Zabini)", 67, facultyInfo(2));
    public static final Student STUDENT_5 = new Student(5L, "Миллисента Булстроуд (Millicent Bulstrode)", 89, facultyInfo(4));
    public static final Student STUDENT_6 = new Student(6L, "Панси Паркинсон (Pansy Parkinson)", 93, facultyInfo(4));
    public static final Student STUDENT_7 = new Student(7L, "Чо Чанг (Cho Chang)", 56, facultyInfo(4));
    public static final Student STUDENT_8 = new Student(8L, "Майкл Корнер (Michael Corner)", 34, facultyInfo(1));
    public static final Student STUDENT_9 = new Student(9L, "Роберт Хиллиард (Robert Hilliard)", 100, facultyInfo(1));
    public static final Student STUDENT_10 = new Student(10L, "Элеанор Брэнсток (Eleanor Branstock)", 18, facultyInfo(1));
    public static final Student STUDENT_11 = new Student(11L, "Кэти Белл (Katie Bell)", 73, facultyInfo(3));
    public static final Student STUDENT_12 = new Student(12L, "Алисия Спиннет (Alicia Spinnet)", 23, facultyInfo(2));
    public static final Student STUDENT_13 = new Student(13L, "Анджелина Джонсон (Angelina Johnson)", 64, facultyInfo(4));
    public static final Student STUDENT_14 = new Student(14L, "Кэтти Бел (Kathy Bell)", 83, facultyInfo(2));
    public static final Student STUDENT_15 = new Student(15L, "Колин Криви (Colin Creevey)", 49, facultyInfo(2));
    public static final Student STUDENT_16 = new Student(16L, "Гарри Поттер (Harry Potter)", 11, facultyInfo(2));
    public static final Student STUDENT_17 = new Student(17L, "Гермиона Грейнджер (Hermione Granger)", 11, facultyInfo(2));
    public static final Student STUDENT_18 = new Student(18L, "Рон Уизли (Ron Weasley)", 11, facultyInfo(2));
    public static final Student STUDENT_19 = new Student(19L, "Драко Малфой (Draco Malfoy)", 12, facultyInfo(2));
    public static final Student STUDENT_20 = new Student(20L, "Невилл Лонгботтом (Neville Longbottom)", 10, facultyInfo(4));
    public static final Student STUDENT_21 = new Student(21L, "Джинни Уизли (Ginny Weasley)", 7, facultyInfo(3));
    public static final Student STUDENT_22 = new Student(22L, "Альбус Дамблдор (Albus Dumbledore)", 115, facultyInfo(2));
    public static final Student STUDENT_23 = new Student(23L, "Северус Снегг (Severus Snape)", 38, facultyInfo(3));
    public static final Student STUDENT_24 = new Student(24L, "Филиус Флитвик (Filius Flitwick)", 75, facultyInfo(1));

    public static Faculty facultyInfo(long id) {
        return FACULTY_MAP.get(id);
    }

    public static final List<Student> STUDENT_LIST = new ArrayList<>(Arrays.asList
            (STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4, STUDENT_5, STUDENT_6,
                    STUDENT_7, STUDENT_8, STUDENT_9, STUDENT_10, STUDENT_11,
                    STUDENT_12, STUDENT_13, STUDENT_14, STUDENT_15, STUDENT_16,
                    STUDENT_17, STUDENT_18, STUDENT_19, STUDENT_20, STUDENT_21,
                    STUDENT_22, STUDENT_23, STUDENT_24));

    public List<Student> studentList(Faculty faculty) {
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
        FACULTY_1.setStudents(studentList(FACULTY_1));
        FACULTY_2.setStudents(studentList(FACULTY_2));
        FACULTY_3.setStudents(studentList(FACULTY_3));
        FACULTY_4.setStudents(studentList(FACULTY_4));
    }

    public static List<Student> studentsFaculty(Faculty faculty) {

        List<Student> STUDENT_LIST = new ArrayList<>(Arrays.asList
                (STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4, STUDENT_5, STUDENT_6,
                        STUDENT_7, STUDENT_8, STUDENT_9, STUDENT_10, STUDENT_11,
                        STUDENT_12, STUDENT_13, STUDENT_14, STUDENT_15, STUDENT_16,
                        STUDENT_17, STUDENT_18, STUDENT_19, STUDENT_20, STUDENT_21,
                        STUDENT_22, STUDENT_23, STUDENT_24));
        List<Student> STUDENTS_FACULTY = new ArrayList<>();

        for (Student student : STUDENT_LIST) {
            if (student.getFaculty().equals(faculty)) {
                STUDENTS_FACULTY.add(student);
            }
        }
        return STUDENTS_FACULTY;
    }

    public static List<Student> studentAgeBetween(int from, int to) {

        List<Student> STUDENT_LIST = new ArrayList<>(Arrays.asList
                (STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4, STUDENT_5, STUDENT_6,
                        STUDENT_7, STUDENT_8, STUDENT_9, STUDENT_10, STUDENT_11,
                        STUDENT_12, STUDENT_13, STUDENT_14, STUDENT_15, STUDENT_16,
                        STUDENT_17, STUDENT_18, STUDENT_19, STUDENT_20, STUDENT_21,
                        STUDENT_22, STUDENT_23, STUDENT_24));
        List<Student> STUDENTS_AGE_BETWEEN = new ArrayList<>();

        for (Student student : STUDENT_LIST) {
            if (student.getAge() > from && student.getAge() < to) {
                STUDENTS_AGE_BETWEEN.add(student);
            }
        }
        return STUDENTS_AGE_BETWEEN;
    }

    //1, 2, 8, 9, 10, 24
    public static final Faculty FACULTY_1_WITH_STUDENT_INFO = new Faculty(FACULTY_1.getId(), FACULTY_1.getName(), FACULTY_1.getColor(), List.of(
            new Student(STUDENT_1.getId(), STUDENT_1.getName(), STUDENT_1.getAge(), null),
            new Student(STUDENT_2.getId(), STUDENT_2.getName(), STUDENT_2.getAge(), null),
            new Student(STUDENT_8.getId(), STUDENT_8.getName(), STUDENT_8.getAge(), null),
            new Student(STUDENT_9.getId(), STUDENT_9.getName(), STUDENT_9.getAge(), null),
            new Student(STUDENT_10.getId(), STUDENT_10.getName(), STUDENT_10.getAge(), null),
            new Student(STUDENT_24.getId(), STUDENT_24.getName(), STUDENT_24.getAge(), null)));

    //4, 12, 14, 15, 16, 17, 18, 19, 22
    public static final Faculty FACULTY_2_WITH_STUDENT_INFO = new Faculty(FACULTY_2.getId(), FACULTY_2.getName(), FACULTY_2.getColor(), List.of(
            new Student(STUDENT_4.getId(), STUDENT_4.getName(), STUDENT_4.getAge(), null),
            new Student(STUDENT_12.getId(), STUDENT_12.getName(), STUDENT_12.getAge(), null),
            new Student(STUDENT_14.getId(), STUDENT_14.getName(), STUDENT_14.getAge(), null),
            new Student(STUDENT_15.getId(), STUDENT_15.getName(), STUDENT_15.getAge(), null),
            new Student(STUDENT_16.getId(), STUDENT_16.getName(), STUDENT_16.getAge(), null),
            new Student(STUDENT_17.getId(), STUDENT_17.getName(), STUDENT_17.getAge(), null),
            new Student(STUDENT_18.getId(), STUDENT_18.getName(), STUDENT_18.getAge(), null),
            new Student(STUDENT_19.getId(), STUDENT_19.getName(), STUDENT_19.getAge(), null),
            new Student(STUDENT_22.getId(), STUDENT_22.getName(), STUDENT_22.getAge(), null)));

    //3, 11, 21, 23
    public static final Faculty FACULTY_3_WITH_STUDENT_INFO = new Faculty(FACULTY_3.getId(), FACULTY_3.getName(), FACULTY_3.getColor(), List.of(
            new Student(STUDENT_3.getId(), STUDENT_3.getName(), STUDENT_3.getAge(), null),
            new Student(STUDENT_11.getId(), STUDENT_11.getName(), STUDENT_11.getAge(), null),
            new Student(STUDENT_21.getId(), STUDENT_21.getName(), STUDENT_21.getAge(), null),
            new Student(STUDENT_23.getId(), STUDENT_23.getName(), STUDENT_23.getAge(), null)));

    //5,6,7,13,20
    public static final Faculty FACULTY_4_WITH_STUDENT_INFO = new Faculty(FACULTY_4.getId(), FACULTY_4.getName(), FACULTY_4.getColor(), List.of(
            new Student(STUDENT_5.getId(), STUDENT_5.getName(), STUDENT_5.getAge(), null),
            new Student(STUDENT_6.getId(), STUDENT_6.getName(), STUDENT_6.getAge(), null),
            new Student(STUDENT_7.getId(), STUDENT_7.getName(), STUDENT_7.getAge(), null),
            new Student(STUDENT_13.getId(), STUDENT_13.getName(), STUDENT_13.getAge(), null),
            new Student(STUDENT_20.getId(), STUDENT_20.getName(), STUDENT_20.getAge(), null)));

    public static final List<Faculty> LIST_FACULTY_WITH_STUDENT_INFO = new ArrayList<>(List.of(
            FACULTY_1_WITH_STUDENT_INFO,
            FACULTY_2_WITH_STUDENT_INFO,
            FACULTY_3_WITH_STUDENT_INFO,
            FACULTY_4_WITH_STUDENT_INFO));

    public static final List<Faculty> EXPECTED_LIST_FACULTY_WITH_STUDENT_INFO = new ArrayList<>(List.of(
            FACULTY_1_WITH_STUDENT_INFO,
            FACULTY_2_WITH_STUDENT_INFO,
            FACULTY_3_WITH_STUDENT_INFO,
            FACULTY_4_WITH_STUDENT_INFO));

    public static final List<Faculty> LIST_FACULTY_1 = new ArrayList<>(List.of(FACULTY_1_WITH_STUDENT_INFO));
    public static final List<Faculty> LIST_FACULTY_2 = new ArrayList<>(List.of(FACULTY_2_WITH_STUDENT_INFO));
    public static final List<Faculty> LIST_FACULTY_3 = new ArrayList<>(List.of(FACULTY_3_WITH_STUDENT_INFO));
    public static final List<Faculty> LIST_FACULTY_4 = new ArrayList<>(List.of(FACULTY_4_WITH_STUDENT_INFO));

    public static final List<Student> studentFacultyTemplate(Faculty faculty) {

        List<Student> STUDENT_LIST = new ArrayList<>(Arrays.asList
                (STUDENT_1, STUDENT_2, STUDENT_3, STUDENT_4, STUDENT_5, STUDENT_6,
                        STUDENT_7, STUDENT_8, STUDENT_9, STUDENT_10, STUDENT_11,
                        STUDENT_12, STUDENT_13, STUDENT_14, STUDENT_15, STUDENT_16,
                        STUDENT_17, STUDENT_18, STUDENT_19, STUDENT_20, STUDENT_21,
                        STUDENT_22, STUDENT_23, STUDENT_24));
        List<Student> STUDENTS_FACULTY = new ArrayList<>();

        for (Student student : STUDENT_LIST) {
            if (student.getFaculty().equals(faculty)) {
                student.setFaculty(null);
                STUDENTS_FACULTY.add(student);
            }
        }
        return STUDENTS_FACULTY;
    }

    public static final List<Student> STUDENT_LIST_TEMPLATE(Faculty faculty) {
        List<Student> studentsFaculty = new ArrayList<>();

        for (Student student : STUDENT_LIST) {
            if (student.getFaculty().equals(faculty)) {
                studentsFaculty.add(student);
                STUDENT_LIST.add(student);
            }
        }
        return studentsFaculty;
    }

    public static final Faculty FACULTY_5 = new Faculty(5, "TestNameFaculty", "TestColorFaculty", null);
    public static final Student STUDENT_25 = new Student(25L, "TestNameStudent", 75, facultyInfo(3));
}