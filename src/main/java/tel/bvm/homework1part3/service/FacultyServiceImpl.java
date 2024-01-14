package tel.bvm.homework1part3.service;

import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private FacultyRepository facultyRepository;

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
//        return facultyRepository.findById(id).get();
//        return facultyRepository.getReferenceById(id);
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {
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
    public List<Faculty> findByNameContaining(String name) {
        return facultyRepository.findByNameContaining(name);
    }

    @Override
    public List<Faculty> findByColorContaining(String color) {
        return facultyRepository.findByColorContaining(color);
    }

    @Override
    public List<Faculty> findByNameAndColorContaining(String name, String color) {
        return facultyRepository.findByNameAndColorContaining(name, color);
    }
}