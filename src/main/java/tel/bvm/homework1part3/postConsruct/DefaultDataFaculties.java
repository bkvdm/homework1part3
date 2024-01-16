package tel.bvm.homework1part3.postConsruct;

import tel.bvm.homework1part3.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDataFaculties {

    public Faculty facultyInfo (long id) {

        Faculty faculty1 = new Faculty(1, "Когневран (Ravenclaw)", "Красный (Red)", );
        Faculty faculty2 = new Faculty(2, "Гриффиндор (Gryffindor)", "Жёлтый (Yellow)", "й");
        Faculty faculty3 = new Faculty(3, "Пуффендуй (Hufflepuff)", "Зелёный (Green)", "й");
        Faculty faculty4 = new Faculty(4, "Слизерин (Slytherin)", "Фиолетовый (Purple)", "й");

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