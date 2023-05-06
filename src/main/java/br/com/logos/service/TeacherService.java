package br.com.logos.service;

import br.com.logos.exceptions.*;
import br.com.logos.models.Course;
import br.com.logos.models.Discipline;
import br.com.logos.models.Student;
import br.com.logos.models.Teacher;
import br.com.logos.repositories.CourseRepository;
import br.com.logos.repositories.DisciplineRepository;
import br.com.logos.repositories.StudentRepository;
import br.com.logos.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final DisciplineRepository disciplineRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository, StudentRepository studentRepository, DisciplineRepository disciplineRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getOneTeacherById(int id) throws Exception {

        Optional<Teacher> validation = teacherRepository.findById(id);

        if (validation.isEmpty() || validation.equals(null)) {
            throw new Exception("Teacher not found by id. Please, type another id");
        } else {
            return validation.get();
        }
    }

    public Teacher newTeacher(Teacher teacherDTO) {
        validateTeacher(teacherDTO);

        Teacher newTeacher = new Teacher();

        newTeacher.setFirstName(teacherDTO.getFirstName());
        newTeacher.setLastName(teacherDTO.getLastName());
        newTeacher.setEmail(teacherDTO.getEmail());
        newTeacher.setSsn(teacherDTO.getSsn());
        newTeacher.setGraduation(teacherDTO.getGraduation());

        Optional<Course> courseOptional = courseRepository.findById(5);
        List<Course> courses = new ArrayList<>();
        courses.add(courseOptional.get());
        newTeacher.setCourses(courses);

        Optional<Student> studentOptional = studentRepository.findById(4);
        List<Student> students = new ArrayList<>();
        students.add(studentOptional.get());
        newTeacher.setStudents(students);

        Optional<Discipline> disciplineOptional = disciplineRepository.findById(1);
        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(disciplineOptional.get());
        newTeacher.setDisciplines(disciplines);

        return teacherRepository.save(newTeacher);
    }

    public Teacher updateTeacher(int id, Teacher teacherToBeUpdated) {
        validateTeacher(teacherToBeUpdated);

        Teacher updatedTeacher = teacherRepository.findById(id).get();
        updatedTeacher.setFirstName(teacherToBeUpdated.getFirstName());
        updatedTeacher.setLastName(teacherToBeUpdated.getLastName());
        updatedTeacher.setSsn(teacherToBeUpdated.getSsn());
        updatedTeacher.setEmail(teacherToBeUpdated.getEmail());
        updatedTeacher.setGraduation(teacherToBeUpdated.getGraduation());

        return teacherRepository.save(updatedTeacher);
    }
    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }

    public boolean validateTeacher(Teacher teacherValidated) {

        if (teacherValidated.getFirstName().isEmpty() || teacherValidated.getFirstName().equals(null)) {
            throw new TeacherNameNullException("Name cannot be empty. Please, type a valid name");
        }
        if (teacherValidated.getLastName().equals(null) || teacherValidated.getLastName().isEmpty()) {
            throw new TeacherNameNullException("Teacher's last name cannot be empty.Please, try again");
        }
        if (teacherValidated.getSsn().equals(null) || teacherValidated.getLastName().isEmpty()) {
            throw new TeacherSnnNullException("SNN invalid! Please, enter a valid register");
        }
        return false;
    }
}


