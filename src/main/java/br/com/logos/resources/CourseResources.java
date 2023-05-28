package br.com.logos.resources;

import br.com.logos.dtos.CourseDTO;
import br.com.logos.exceptions.CourseNotFoundException;
import br.com.logos.models.Course;
import br.com.logos.models.Discipline;
import br.com.logos.models.DisciplineSemester;
import br.com.logos.service.CourseService;
import br.com.logos.service.DisciplineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/courses")
public class CourseResources {
    private final CourseService courseService;

    private final DisciplineService disciplineService;

    public CourseResources(CourseService courseService, DisciplineService disciplineService) {
        this.courseService = courseService;
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public Iterable<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getOneCourseById(@PathVariable("id") int id) throws Exception {
        try {
            Course course = courseService.getOneCourseById(id);
            return ResponseEntity.ok(course);

        } catch (CourseNotFoundException courseNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/disciplines")
    public ResponseEntity<DisciplineSemester> getCourseWithDisciplinesBySemester(@PathVariable ("id") int courseId){
          try{
            DisciplineSemester disciplineSemester = courseService.getCourseWithDisciplinesBySemester(courseId);
            return ResponseEntity.ok(disciplineSemester);

        }catch (CourseNotFoundException courseNotFoundException){
              return ResponseEntity.notFound().build();
          }

    }

    @PostMapping
    public Course newCourse(@RequestBody CourseDTO newCourse) {
        return courseService.newCourse(newCourse);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") int id, @RequestBody CourseDTO courseToBeUpdated) {
        return courseService.updateCourse(id, courseToBeUpdated);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") int id) throws Exception {
       courseService.deleteCourse(id);
    }
}
