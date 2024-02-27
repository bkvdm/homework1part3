package tel.bvm.homework1part3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.FacultyRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Adding faculty: " + faculty.getName());
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(long id) {
        logger.info("Finding faculty with id: " + id);
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty editFaculty(Long id, Faculty faculty) {
        logger.info("Editing faculty with id: " + id);
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        logger.info("Deleting faculty with id: " + id);
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> findAllFaculties() {
        logger.info("Finding all faculties");
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> findByNameContainingIgnoreCase(String name) {
        logger.info("Finding faculties by name containing: " + name);
        return facultyRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Faculty> findByColorContainingIgnoreCase(String color) {
        logger.info("Finding faculties by color containing: " + color);
        return facultyRepository.findByColorContainingIgnoreCase(color);
    }

    @Override
    public ResponseEntity<List<Faculty>> findByNameAndColorContainingIgnoreCase(String name, String color) {
        logger.info("Method findByNameAndColorContainingIgnoreCase called");
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
        } else
            logger.error("Faculty not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public List<Student> findByFacultyOfStudent(Long id, String name, String color) {
        logger.info("Method findByFacultyOfStudent is called with parameters id: {}, name: {}, color: {}", id, name, color);
        if (Optional.ofNullable(facultyRepository.findByIdOrNameOrColorContainingIgnoreCase(id, name, color)).isEmpty()) {
            logger.error("Students of the specified faculty were not found");
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return facultyRepository.findByIdOrNameOrColorContainingIgnoreCase(id, name, color).getStudents();
    }

    @Override
    public Integer longestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(f -> f.getName().length())
                .max(Comparator.naturalOrder())
                .orElse(0);
    }
}
