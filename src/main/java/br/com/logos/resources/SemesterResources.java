package br.com.logos.resources;

import br.com.logos.exceptions.SemesterNotFoundException;
import br.com.logos.models.Semester;
import br.com.logos.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semesters")
public class SemesterResources {

    private final SemesterService semesterService;

    @Autowired
    public SemesterResources(SemesterService semesterService){
        this.semesterService = semesterService;
    }


    @GetMapping
    public Iterable<Semester> getAllSemesters() {
        return semesterService.getAllSemesters();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Semester> getOneSemesterById(@PathVariable("id") int id) throws Exception {
        try {
            Semester semester = semesterService.getOneSemesterById(id);
            return ResponseEntity.ok(semester);

        } catch (SemesterNotFoundException SemesterNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }
//    @PostMapping
//    public Semester newSemester (@RequestBody @Valid SemesterDTO newSemester) {
//        Semester semester = semesterService.newSemester(newSemester);
//        return semester;
//
//    }

//    @PutMapping("/{id}")
//    public Semester updateSemester(@PathVariable("id") int id, @RequestBody Semester semesterToBeUpdated) {
//        return semesterService.updateSemester(id, semesterToBeUpdated);
//    }

    @DeleteMapping("/{id}")
    public void deleteSemester(@PathVariable("id") int id) throws Exception {
        semesterService.deleteSemester(id);
    }
}


