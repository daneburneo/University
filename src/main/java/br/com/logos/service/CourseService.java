package br.com.logos.service;

import br.com.logos.exceptions.CourseCoordinatorException;
import br.com.logos.exceptions.CourseDirectorNullException;
import br.com.logos.exceptions.CourseNameNullException;
import br.com.logos.models.Course;
import br.com.logos.models.Discipline;
import br.com.logos.models.Teacher;
import br.com.logos.repositories.CourseRepository;
import br.com.logos.repositories.DisciplineRepository;
import br.com.logos.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DisciplineRepository disciplineRepository;
    private  final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, DisciplineRepository disciplineRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
    }
    public Course getOneCourseById(int id) throws Exception {

        Optional<Course> course = courseRepository.findById(id);

        if (course.isEmpty() || course.equals(null)) {
            throw new Exception("This id is invalid. Please, type another id.");
        } else {
            return course.get();
        }
    }
    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Course updateCourse(int id, Course courseToBeUpdated) {

        Course courseUpdated = courseRepository.findById(id).get();

        courseUpdated.setName(courseToBeUpdated.getName());
        courseUpdated.setLevel(courseToBeUpdated.getLevel());
        courseUpdated.setDirector(courseToBeUpdated.getDirector());
        courseUpdated.setCoordinator(courseToBeUpdated.getCoordinator());
        courseUpdated.setId(courseUpdated.getId());
        courseUpdated.setTeachers(courseToBeUpdated.getTeachers());
        courseUpdated.setDisciplines(courseToBeUpdated.getDisciplines());

       return courseRepository.save(courseUpdated);
   }
    public Course newCourse(Course courseDto) {
        validateCourse(courseDto);

        Course course = new Course();

        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDirector(courseDto.getDirector());
        course.setCoordinator(courseDto.getCoordinator());
        course.setLevel(courseDto.getLevel());

        Optional<Discipline> disciplineOptional = disciplineRepository.findById(2);
        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(disciplineOptional.get());
        course.setDisciplines(disciplines);

        Optional<Teacher> teacherOptional = teacherRepository.findById(2);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacherOptional.get());
        course.setTeachers(teachers);

        return courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public boolean validateCourse(@Valid Course courseValidated) {

        if (courseValidated.getName().isEmpty()) {
            throw new CourseNameNullException("Course name cannot be empty. Please, type a valid name");
        }
        if(courseValidated.getCoordinator().isEmpty()){
            throw  new CourseCoordinatorException("Course coordinator it's null! Please, type a name");
        }
        if(courseValidated.getDirector().isEmpty()){
            throw new CourseDirectorNullException("Director's name it's empty. Please, enter a valid name");
        }
        return false;
    }
}
