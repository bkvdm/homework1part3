package tel.bvm.homework1part3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable long id) {
        Student foundStudent = studentService.editStudent(id, student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("{findByAgeBetween}")
    public List<Student> findByAgeBetween(@RequestParam Integer from, @RequestParam Integer to) {
        return studentService.findByAgeBetween(from, to);
    }

    @GetMapping("{findByAgeInclusive}")
    public List<Student> findByAgeLessThanEqualAndGreaterThanEqual(@RequestParam Integer lowerBound, @RequestParam Integer upperBound) {
        return studentService.findByAgeLessThanEqualAndGreaterThanEqual(lowerBound, upperBound);
    }

//    @GetMapping()
//    public ResponseEntity<List<Student>> findByAgeIncludeBoundariesInSearchOrNo(@RequestParam Integer from, @RequestParam Integer to, @RequestParam String signInclusionBorders) {
//        return studentService.findByAgeIncludeBoundariesInSearchOrNo(from, to, signInclusionBorders);
//    }
}
