package br.com.logos.service;

import br.com.logos.dtos.CourseDTO;
import br.com.logos.exceptions.CourseCoordinatorException;
import br.com.logos.exceptions.CourseDirectorNullException;
import br.com.logos.exceptions.CourseNameNullException;
import br.com.logos.exceptions.CourseNotFoundException;
import br.com.logos.models.Course;
import br.com.logos.models.Discipline;
import br.com.logos.models.Teacher;
import br.com.logos.repositories.CourseRepository;
import br.com.logos.repositories.DisciplineRepository;
import br.com.logos.repositories.TeacherRepository;
import org.springframework.http.ResponseEntity;
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



//    public Course getCourseByName(String courseName){
//
//        Optional<Course> courseOptional = courseRepository.findCourseByName();
//
//    }

    public Map<String, List<Discipline>> getCourseWithDisciplinesBySemester(Integer courseId) {

        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Course course = courseOptional.get();

        List<Discipline> disciplines = course.getDisciplines();
        List<Discipline> firstSemester = new ArrayList<>();
        List<Discipline> secondSemester = new ArrayList<>();

        if (courseOptional.isPresent()) {

            for (int i = 0; i < disciplines.size(); i++) {
                Discipline discipline = disciplines.get(i);
                Integer disciplineId = discipline.getId();
                if (disciplineId <= 6) {
                    firstSemester.add(discipline);
                } else {
                    secondSemester.add(discipline);
                }
            }

        } else {
            throw new CourseNotFoundException("This course doesn't exists");
        }

        Map<String, List<Discipline>> disciplinesSemester = new HashMap<>();
        disciplinesSemester.put("firstSemester", firstSemester);
        disciplinesSemester.put("secondSemester", secondSemester);

        return ResponseEntity.ok(disciplinesSemester).getBody();
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
