package br.com.logos.resources;

import br.com.logos.exceptions.TeacherNotFoundException;
import br.com.logos.models.Teacher;
import br.com.logos.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherResources {

    private final TeacherService teacherService;

    public TeacherResources(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public Iterable<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getOneTeacherById(@PathVariable("id") int id) throws Exception {
        try {
            Teacher teacher = teacherService.getOneTeacherById(id);
            return ResponseEntity.ok(teacher);

        } catch (TeacherNotFoundException teacherNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Teacher newTeacher(@RequestBody @Valid Teacher newTeacher) {
        return teacherService.newTeacher(newTeacher);

    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable("id") int id, @RequestBody Teacher teacherUpdated) {
        return teacherService.updateTeacher(id, teacherUpdated);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") int id) {
        teacherService.deleteTeacher(id);
    }

}

