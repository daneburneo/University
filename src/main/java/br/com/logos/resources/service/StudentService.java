package br.com.logos.resources.service;


import br.com.logos.exceptions.StudentCourseNullException;
import br.com.logos.exceptions.StudentNameInvalidNullException;
import br.com.logos.exceptions.StudentSnnNullException;
import br.com.logos.models.Student;
import br.com.logos.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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


    public Student newStudent(Student student) {
        validateStudent(student);
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student studentUpdated) {
        Student student1 = studentRepository.findById(id).get();
        student1.setFirstName(studentUpdated.getFirstName());
        student1.setLastName(student1.getLastName());

        return studentRepository.save(student1);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }


    public boolean validateStudent(Student studentValidated) {


        if (studentValidated.getFirstName().isEmpty()) {
            throw new StudentNameInvalidNullException("Student's name cannot be empty.Please, type a valid name");
        }

        if (studentValidated.getLastName().equals(null)) {
            throw new StudentNameInvalidNullException("Student's last name cannot be empty.Please, type a valid name");

        }

        if (studentValidated.getSsn().equals(null)) {
            throw new StudentSnnNullException(" SNN invalid! Please, enter with a valid register");
        }

        if (studentValidated.getDiscipline().equals(null)) {
            throw new StudentCourseNullException("The Student cannot be empty. Please, type a valid course");
        }


        return false;
    }

}
