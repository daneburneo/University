package br.com.logos.resources;


import br.com.logos.exceptions.StudentException;
import br.com.logos.models.Student;
import br.com.logos.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentResources {

    private final StudentService studentService;

    public StudentResources(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Iterable<Student> getAllStudents() {  return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getOneStudentById(@PathVariable("id") int id) throws Exception {
        try {
            Student student = studentService.getOneStudentById(id);
            return ResponseEntity.ok(student);

        } catch (StudentException studentNotFound) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Student newStudent(@RequestBody @Valid Student student) {
        return studentService.newStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") int id, @RequestBody Student studentUpdated) {

         return studentService.updateStudent(id, studentUpdated);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id) { studentService.deleteStudent(id); }
}




