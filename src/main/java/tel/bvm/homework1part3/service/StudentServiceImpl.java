package tel.bvm.homework1part3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public final Object flag = new Object();

    @Override
    public Student addStudent(Student student) {
        logger.info("Calling addStudent method with student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Calling findStudent method with id: {}", id);
        return studentRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Student editStudent(long id, Student student) {
        logger.info("Calling editStudent method with id: {} and student: {}", id, student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Calling deleteStudent method with id: {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAllStudents() {
        logger.info("Calling findAllStudents method");
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findByAgeBetween(Integer from, Integer to) {
        logger.info("Calling findByAgeBetween method with age range: {} to {}", from, to);
        return studentRepository.findByAgeBetween(from, to);
    }

    @Override
    public List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer from, Integer to) {
        logger.info("Calling findByAgeLessThanEqualAndGreaterThanEqual method with parameters: from = {}, to = {}", from, to);
        if (Optional.ofNullable(from).isEmpty() || Optional.ofNullable(to).isEmpty()) {
            logger.error("No students found with the specified search criteria (BAD_REQUEST): from = {}, to = {}", from, to);
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (from != 0) {
            from = from - 1;
        }
        if (to != Integer.MAX_VALUE) {
            to = to + 1;
        }
        return studentRepository.findByAgeBetween(from, to);
    }

    @Override
    public Faculty findByStudentOfFaculty(Long id, String name) {
        logger.info("Method findByStudentOfFaculty is called with student id: " + id + " and name: " + name);
        if (Optional.ofNullable(studentRepository.findByIdOrNameIgnoreCase(id, name)).isEmpty()) {
            logger.error("Faculty for student with id: " + id + " and name: " + name + " was not found (NOT_FOUND)");
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return studentRepository.findByIdOrNameIgnoreCase(id, name).getFaculty();
    }

    @Override
    public int countStudents() {
        logger.info("Method countStudents is called.");
        return studentRepository.countStudents();
    }

    @Override
    public int averageAge() {
        logger.info("Method averageAge is called.");
        return studentRepository.averageAge();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.info("Method getLastFiveStudents is called.");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public List<Student> getAllStudents(Integer pageNumber, Integer pageSize) {
        logger.info("Method getAllStudents is called with pageNumber: " + pageNumber + " and pageSize: " + pageSize);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }

    @Override
    public Optional<List<String>> getAllStudentsStartWithKey(String startWithKey) {
        List<String> studentsStartWithKey = studentRepository.findAll().stream()
                .map(s -> s.getName())
                .filter(s -> s.startsWith(startWithKey))
                .map(s -> s.toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        if (studentsStartWithKey.isEmpty()) {
            throw new RuntimeException("Students whose name begins with " + startWithKey + " are not found at Hogwarts School");
        }
        return Optional.of(studentsStartWithKey);
    }

    @Override
    public Double getAverageAgeOfStudent() {
        return studentRepository.findAll().stream()
                .mapToDouble(s -> s.getAge())
                .average()
                .orElseThrow(() -> new RuntimeException("There is no data to calculate the average age of students at Hogwarts School"));
    }

    @Override
    public String studentsPrintParallel() {
        long startTime = System.currentTimeMillis();
        studentRepository.findAll().parallelStream().forEach(student -> System.out.println(student.getName()));
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        return "Имена всех студентов школы Хогвардс выведены в консоль в параллельном режиме за: " + elapsedTime + " (мс)";
    }

    @Override
    public String studentsPrintThreadSynchronized() {
        long startTime = System.currentTimeMillis();
        List<Student> students = studentRepository.findAll();
        if (students.size() <= 1) {
            throw new RuntimeException("Количество студентов недостаточно, чтобы применение потоков было оправданным");

        } else if (students.size() % 2 == 0) {
            for (int a = 0; a < students.size(); a = a + 2) {
                int b = a;
                new Thread(() -> {
                    doPrintStudent(students.get(b).getName());
                    doPrintStudent(students.get(b + 1).getName());
                }).start();
            }
        } else {
            for (int a = 0; a < students.size(); a = a + 3) {
                int b = a;
                new Thread(() -> {
                    doPrintStudent(students.get(b).getName());
                    doPrintStudent(students.get(b + 1).getName());
                    doPrintStudent(students.get(b + 2).getName());
                }).start();
            }
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        return "Имена всех студентов школы Хогвардс выведены синхронизировано за: " + elapsedTime + " (мс)";
    }

    @Override
    public String studentsPrintParallelThread() {
        long startTime = System.currentTimeMillis();
        List<Student> students = studentRepository.findAll();
        if (students.size() <= 1) {
            throw new RuntimeException("Количество студентов недостаточно, чтобы применение потоков было оправданным");

        } else if (students.size() % 2 == 0 && students.size() == 2) {
            for (int a = 0; a < students.size(); a = a + 2) {
                int b = a;
                new Thread(() -> {
                    System.out.println(students.get(b).getName());
                    System.out.println(students.get(b + 1).getName());
                }).start();
            }
        } else if (students.size() % 2 != 0 && students.size() == 3) {
            for (int a = 0; a < students.size(); a = a + 3) {
                int b = a;
                new Thread(() -> {
                    System.out.println(students.get(b).getName());
                    System.out.println(students.get(b + 1).getName());
                    System.out.println(students.get(b + 2).getName());
                }).start();
            }
        } else if (students.size() % 2 == 0) {
            for (int a = 0; a < students.size(); a = a + 2) {
                int b = a;
                new Thread(() -> {
                    System.out.println(students.get(b).getName());
                    System.out.println(students.get(b + 1).getName());
                }).start();
                if (students.size() - a > 2) {
                    new Thread(() -> {
                        System.out.println(students.get(b + 2).getName());
                        System.out.println(students.get(b + 3).getName());
                    }).start();
                }
            }
        } else {
            for (int a = 0; a < students.size(); a = a + 3) {
                int b = a;
                new Thread(() -> {
                    System.out.println(students.get(b).getName());
                    System.out.println(students.get(b + 1).getName());
                    System.out.println(students.get(b + 2).getName());
                }).start();
                if (students.size() - a > 3) {
                    new Thread(() -> {
                        System.out.println(students.get(b + 3).getName());
                        System.out.println(students.get(b + 4).getName());
                        System.out.println(students.get(b + 5).getName());
                    }).start();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        return "Имена всех студентов школы Хогвардс выведены в консоль параллельно (используется не более 2 потоков) за: " + elapsedTime + " (мс)";
    }

    public void doPrintStudent(String studentName) {
        synchronized (flag) {
            System.out.println(studentName);
        }
    }
}