package tel.bvm.homework1part3.postConsruct;

import tel.bvm.homework1part3.model.Faculty;

import java.util.HashMap;
import java.util.Map;

public class DefaultDataFaculties {

    private final DefaultDataStudents defaultDataStudents;

    public DefaultDataFaculties(DefaultDataStudents defaultDataStudents) {
        this.defaultDataStudents = defaultDataStudents;
    }

    public Faculty facultyInfo (long id) {

        Faculty faculty1 = new Faculty(1, "Когневран (Ravenclaw)", "Красный (Red)", null);
        faculty1.setStudents(defaultDataStudents.studentList(faculty1));
        Faculty faculty2 = new Faculty(2, "Гриффиндор (Gryffindor)", "Жёлтый (Yellow)", null);
        faculty2.setStudents(defaultDataStudents.studentList(faculty2));
        Faculty faculty3 = new Faculty(3, "Пуффендуй (Hufflepuff)", "Зелёный (Green)", null);
        faculty3.setStudents(defaultDataStudents.studentList(faculty3));
        Faculty faculty4 = new Faculty(4, "Слизерин (Slytherin)", "Фиолетовый (Purple)", null);
        faculty4.setStudents(defaultDataStudents.studentList(faculty4));

        Map<Long, Faculty> facultyMap = new HashMap<>();
        facultyMap.put(faculty1.getId(), faculty1);
        facultyMap.put(faculty2.getId(), faculty1);
        facultyMap.put(faculty3.getId(), faculty1);
        facultyMap.put(faculty4.getId(), faculty1);

        return facultyMap.get(id);
    }
}
//            (1, "Когневран (Ravenclaw)", "Красный (Red)");
//    Faculty faculty1 = new Faculty(2, "Гриффиндор (Gryffindor)", "Красный (Red)");
//    1	Когневран (Ravenclaw)	Красный (Red)
//            2	Гриффиндор (Gryffindor)	Жёлтый (Yellow)
//            3	Пуффендуй (Hufflepuff)	Зелёный (Green)
//            4	Слизерин (Slytherin)	Синий (Purple)