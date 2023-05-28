package br.com.logos.service;

import br.com.logos.dtos.DisciplineDTO;
import br.com.logos.exceptions.DiscilpineNameNullException;
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
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    private StudentRepository studentRepository;

    public DisciplineService(DisciplineRepository disciplineRepository, CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.disciplineRepository = disciplineRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }
    public Discipline getOneDisciplineById(int id) throws Exception {

        Optional<Discipline> validation = disciplineRepository.findById(id);

        if (validation.isEmpty() || validation.equals(null)) {
            throw new Exception("This id is invalid. Please, type another id.");
        } else {
            return validation.get();
        }
    }
    public Iterable<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Discipline updateDiscipline(int id, Discipline disciplineToBeUpdated) {

        Discipline disciplineUpdated = disciplineRepository.findById(id).get();

        disciplineUpdated.setId(disciplineToBeUpdated.getId());
        disciplineUpdated.setName(disciplineToBeUpdated.getName());
        disciplineUpdated.setCourses(disciplineToBeUpdated.getCourses());
        disciplineUpdated.setTeachers(disciplineToBeUpdated.getTeachers());

        return disciplineRepository.save(disciplineUpdated);
    }
    public Discipline newDiscipline(DisciplineDTO disciplineDto) {

        validateDiscipline(disciplineDto);

        Discipline discipline = new Discipline();

        discipline.setName(disciplineDto.getName());
        discipline.setId(disciplineDto.getId());
        Optional<Course> courseOptional = courseRepository.findById(1);
        List<Course> courses = new ArrayList<>();
        courses.add(courseOptional.get());
        discipline.setCourses(courses);

//        Optional<Teacher> teacherOptional = teacherRepository.findById(0);
//        List<Teacher> teachers  = new ArrayList<>();
//        teachers.add(teacherOptional.get());
//        discipline.setTeachers(teachers);
//
//        Optional< Student> studentOptional = studentRepository.findById(0);
//        List<Student> students = new ArrayList<>();
//        students.add(studentOptional.get());
//        discipline.setStudents(students);


        return disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(int id) {
        disciplineRepository.deleteById(id);
    }

    public boolean validateDiscipline(DisciplineDTO disciplineValidated) {

        if (disciplineValidated.getName().isEmpty()) {
            throw new DiscilpineNameNullException("Discipline name cannot be empty. Please, type a valid name");
        }

        return false;
    }
}
