package br.com.logos.resources;

import br.com.logos.dtos.CourseDTO;
import br.com.logos.exceptions.CourseNotFoundException;
import br.com.logos.models.Course;
import br.com.logos.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/courses")
public class CourseResources {
    private final CourseService courseService;

    public CourseResources(CourseService courseService) {
        this.courseService = courseService;
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

    @PostMapping
    public Course newCourse(@RequestBody Course newCourse) {
        return courseService.newCourse(newCourse);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable("id") int id, @RequestBody Course courseToBeUpdated) {
        return courseService.updateCourse(id, courseToBeUpdated);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable("id") int id) throws Exception {
       courseService.deleteCourse(id);
    }
}
