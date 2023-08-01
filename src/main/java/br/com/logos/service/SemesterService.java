package br.com.logos.service;

import br.com.logos.models.Semester;
import br.com.logos.repositories.SemesterRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository semesterRepository){
        this.semesterRepository = semesterRepository;
    }

    public Iterable<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Semester getOneSemesterById(int id) throws Exception {

        Optional<Semester> validation = semesterRepository.findById(id);

        if (validation.isEmpty() || validation.equals(null)) {
            throw new Exception("Semester not found by id. Please, type another id");
        } else {
            return validation.get();
        }
    }

    public void deleteSemester(int id) { semesterRepository.deleteById(id);
    }

//    public boolean validateTeacher(Semester semesterValidated) {
//
//        if (validateTeacher.getFirstName().isEmpty() || teacherValidated.getFirstName().equals(null)) {
//            throw new TeacherNameNullException("Name cannot be empty. Please, type a valid name");
//        }
//        if (teacherValidated.getLastName().equals(null) || teacherValidated.getLastName().isEmpty()) {
//            throw new TeacherNameNullException("Teacher's last name cannot be empty.Please, try again");
//        }
//        if (teacherValidated.getSsn().equals(null) || teacherValidated.getLastName().isEmpty()) {
//            throw new TeacherSnnNullException("SNN invalid! Please, enter a valid register");
//        }
//        return false;
//    }

}
