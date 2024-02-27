package tel.bvm.homework1part3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable long id) {
        Student foundStudent = studentService.editStudent(id, student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/findByAgeBetween/")
    public List<Student> findByAgeBetween(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to) {
        return studentService.findByAgeBetween(from, to);
    }

    @GetMapping("/findByAgeInclusive/")
    public List<Student> findByAgeLessThanEqualAndGreaterThanEqual(@RequestParam(required = false) Integer lowerBound, @RequestParam(required = false) Integer upperBound) {
        return studentService.findByAgeLessThanEqualAndGreaterThanEqual(lowerBound, upperBound);
    }

    @GetMapping("/findByStudentOfFaculty/")
    public Faculty findByStudentOfFaculty(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        return studentService.findByStudentOfFaculty(id, name);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countStudents() {
        int count = studentService.countStudents();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/average-age")
    public ResponseEntity<Integer> averageAge() {
        int average = studentService.averageAge();
        return ResponseEntity.ok(average);
    }

    @GetMapping("/last-five")
    public ResponseEntity<List<Student>> getLastFiveStudents() {
        List<Student> lastFive = studentService.getLastFiveStudents();
        return ResponseEntity.ok(lastFive);
    }

    @GetMapping("/page-list-students")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Student> students = studentService.getAllStudents(pageNumber, pageSize);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/studentsNameStartWith")
    public Optional<List<String>> getAllStudentsNameStartWith(@RequestParam("startWithKey") String startWithKey) {
        return studentService.getAllStudentsStartWithKey(startWithKey);
    }

    @GetMapping("/getAverageAgeOfStudent")
    public ResponseEntity<Double> getAverageAgeOfStudent() {
        return ResponseEntity.ok(studentService.getAverageAgeOfStudent());
    }
}