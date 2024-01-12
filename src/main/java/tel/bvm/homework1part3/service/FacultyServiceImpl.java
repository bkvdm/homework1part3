package tel.bvm.homework1part3.service;

import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    //    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

//    private final HashMap<Long, Faculty> faculties = new HashMap<Long, Faculty>();
//    private long count = 0;


    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
//        faculty.setId(count++);
//        faculties.put(faculty.getId(), faculty);
//        return faculty;
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
//        return faculties.get(id);
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
//        if (!faculties.containsKey(id)) {
//            return null;
//        }
//        faculties.put(id, faculty);
//        return faculty;
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
//        faculties.remove(id);
        facultyRepository.deleteById(id);

    }

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }
}
