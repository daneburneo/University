package br.com.logos.service;

import br.com.logos.exceptions.StudentNameInvalidNullException;
import br.com.logos.exceptions.StudentSnnNullException;
import br.com.logos.models.Course;
import br.com.logos.models.Discipline;
import br.com.logos.models.Student;
import br.com.logos.models.Teacher;
import br.com.logos.repositories.CourseRepository;
import br.com.logos.repositories.DisciplineRepository;
import br.com.logos.repositories.StudentRepository;
import br.com.logos.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DisciplineRepository disciplineRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, DisciplineRepository disciplineRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.disciplineRepository = disciplineRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getOneStudentById(int id) throws Exception {

        Optional<Student> validation = studentRepository.findById(id);

        if (validation.isEmpty() || validation.equals(null)) {
            throw new Exception("A student with this id doesn't exist");

        } else {
            return validation.get();
        }
    }

    public Student newStudent(@Valid Student studentDTO) {

        Student student = new Student();

        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setSsn(studentDTO.getSsn());

        Optional<Discipline> disciplineOptional = disciplineRepository.findById(2);
        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(disciplineOptional.get());
        student.setDisciplines(disciplines);

        Optional<Teacher> teacherOptional = teacherRepository.findById(17);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacherOptional.get());
        student.setTeachers(teachers);

        Optional<Course> courseOptional = courseRepository.findById(5);
        List<Course> courses = new ArrayList<>();
        courses.add(courseOptional.get());
        student.setCourses(courses);

        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student studentToBeUpdated) {

        validateStudent(studentToBeUpdated);

        Student studentUpdated = studentRepository.findById(id).get();

        studentUpdated.setFirstName(studentToBeUpdated.getFirstName());
        studentUpdated.setLastName(studentToBeUpdated.getLastName());
        studentUpdated.setDisciplines(studentToBeUpdated.getDisciplines());
        studentUpdated.setSsn(studentToBeUpdated.getSsn());
        studentUpdated.setId(studentToBeUpdated.getId());
        studentUpdated.setTeachers(studentToBeUpdated.getTeachers());
        studentUpdated.setCourses(studentToBeUpdated.getCourses());

        return studentRepository.save(studentUpdated);
    }
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
    public boolean validateStudent(Student studentValidated) {

        if (studentValidated.getFirstName().isEmpty() || studentValidated.getFirstName().equals(null)) {
            throw new StudentNameInvalidNullException("Student's name cannot be empty. Please, type a valid name");
        }
        if (studentValidated.getLastName().equals(null) || studentValidated.getLastName().isEmpty()) {
            throw new StudentNameInvalidNullException("Student's last name cannot be empty.Please, type a valid name");
        }
        if (studentValidated.getSsn().equals(null) || studentValidated.getSsn().equals(0)) {
            throw new StudentSnnNullException(" SNN invalid! Please, enter with a valid register");
        }
        return false;
    }
}
