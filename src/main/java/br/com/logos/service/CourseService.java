package br.com.logos.service;

import br.com.logos.dtos.CourseDTO;
import br.com.logos.exceptions.CourseCoordinatorException;
import br.com.logos.exceptions.CourseDirectorNullException;
import br.com.logos.exceptions.CourseNameNullException;
import br.com.logos.models.Course;
import br.com.logos.models.Discipline;
import br.com.logos.models.Semester;
import br.com.logos.models.Teacher;
import br.com.logos.repositories.CourseRepository;
import br.com.logos.repositories.DisciplineRepository;
import br.com.logos.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.*;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DisciplineRepository disciplineRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, DisciplineRepository disciplineRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.disciplineRepository = disciplineRepository;
    }

//    public Map<String, List<Discipline>> getCourseWithDisciplinesBySemester(Integer courseId) {
//
//        Course course = courseRepository.findById(courseId);
//
//        Map<String, List<Discipline>> disciplineMap = new HashMap<>();
//        List<Semester> semesters = course. ();
//
//        for (Semester semester : semesters) {
//            List<Discipline> disciplines = semester.getDisciplines();
//            disciplineMap.put(semester.getName(), disciplines);
//        }
//
//        return disciplineMap;
//    }


//}

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

    public Course updateCourse(int id, CourseDTO courseToBeUpdated) {

        Course courseUpdated = courseRepository.findById(id).get();

        courseUpdated.setName(courseToBeUpdated.getName());
        courseUpdated.setLevel(courseToBeUpdated.getLevel());
        courseUpdated.setDirector(courseToBeUpdated.getDirector());
        courseUpdated.setCoordinator(courseToBeUpdated.getCoordinator());
        courseUpdated.setId(courseUpdated.getId());
        courseUpdated.setTeachers(courseToBeUpdated.getTeachersList());
        courseUpdated.setDisciplines(courseToBeUpdated.getDisciplinesList());

        return courseRepository.save(courseUpdated);
    }

    public Course newCourse(CourseDTO courseDto) {
        validateCourse(courseDto);

        Course course = new Course();

        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDirector(courseDto.getDirector());
        course.setCoordinator(courseDto.getCoordinator());
        course.setLevel(courseDto.getLevel());
        course.setDisciplines((List<Discipline>) disciplineRepository.findAll());
        Iterable<Teacher> teachersList = teacherRepository.findAll();
        course.setTeachers(courseDto.getTeachersList());

        return courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public boolean validateCourse(@Valid CourseDTO courseValidated) {

        if (courseValidated.getName().isEmpty()) {
            throw new CourseNameNullException("Course name cannot be empty. Please, type a valid name");
        }
        if (courseValidated.getCoordinator().isEmpty()) {
            throw new CourseCoordinatorException("Course coordinator it's null! Please, type a name");
        }
        if (courseValidated.getDirector().isEmpty()) {
            throw new CourseDirectorNullException("Director's name it's empty. Please, enter a valid name");
        }
        return false;
    }
}
