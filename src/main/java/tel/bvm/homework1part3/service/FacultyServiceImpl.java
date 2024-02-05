package tel.bvm.homework1part3.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.FacultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> findAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> findByNameContainingIgnoreCase(String name) {
        return facultyRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Faculty> findByColorContainingIgnoreCase(String color) {
        return facultyRepository.findByColorContainingIgnoreCase(color);
    }

//    @Override
//    public ResponseEntity<List<Faculty>> findByNameAndColorContainingIgnoreCase(String name, String color) {
//        if (name != null && color != null) {
//            if (color.isBlank() && name.isBlank()) {
//                return ResponseEntity.ok(facultyRepository.findAll());
//            } else if (color.isBlank()) {
//                return ResponseEntity.ok(facultyRepository.findByNameContainingIgnoreCase(name));
//            } else if (name.isBlank()) {
//                return ResponseEntity.ok(facultyRepository.findByColorContainingIgnoreCase(color));
//            } else {
//                return ResponseEntity.ok(facultyRepository.findByNameAndColorContainingIgnoreCase(name, color));
//            }
//        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
    @Override
    public ResponseEntity<List<Faculty>> findByNameAndColorContainingIgnoreCase(String name, String color) {
        if (name != null && color != null) {
            if (color.isBlank() && name.isBlank()) {
                return ResponseEntity.ok(facultyRepository.findAll());
            } else if (color.isBlank()) {
                return ResponseEntity.ok(facultyRepository.findByNameContainingIgnoreCase(name));
            } else if (name.isBlank()) {
                return ResponseEntity.ok(facultyRepository.findByColorContainingIgnoreCase(color));
            } else {
                return ResponseEntity.ok(facultyRepository.findByNameAndColorContainingIgnoreCase(name, color));
            }
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public List<Student> findByFacultyOfStudent(Long id, String name, String color) {
        if (Optional.ofNullable(facultyRepository.findByIdOrNameOrColorContainingIgnoreCase(id, name, color)).isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return facultyRepository.findByIdOrNameOrColorContainingIgnoreCase(id, name, color).getStudents();
    }
}