package br.com.logos.resources;

import br.com.logos.dtos.DisciplineDTO;
import br.com.logos.exceptions.TeacherNotFoundException;
import br.com.logos.models.Discipline;
import br.com.logos.service.DisciplineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disciplines")
public class DisciplineResources {

    private final DisciplineService disciplineService;

    public DisciplineResources(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public Iterable<Discipline> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getOneDisciplineById(@PathVariable("id") int id) throws Exception {
        try {
            Discipline discipline = disciplineService.getOneDisciplineById(id);
            return ResponseEntity.ok(discipline);

        } catch (TeacherNotFoundException teacherNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Discipline newDiscipline(@RequestBody @Valid DisciplineDTO newDiscipline) {
        Discipline discipline = disciplineService.newDiscipline(newDiscipline);
        return discipline;

    }

    @PutMapping("/{id}")
    public Discipline updateDiscipline(@PathVariable("id") int id, @RequestBody Discipline disciplineToBeUpdated) {
        return disciplineService.updateDiscipline(id, disciplineToBeUpdated);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscipline(@PathVariable("id") int id) throws Exception {
        disciplineService.deleteDiscipline(id);
    }
}

