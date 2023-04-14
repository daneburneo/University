package br.com.logos.resources.service;

import br.com.logos.exceptions.*;
import br.com.logos.models.Teacher;
import br.com.logos.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getOneTeacherById(int id) throws Exception {

        Optional<Teacher> validation = teacherRepository.findById(id);

        if (validation.isEmpty() || validation.equals(null)) {
            throw new Exception("Teacher not found by id. Please, type another id");

        } else {

            return validation.get();
        }

    }


    public Teacher newTeacher(Teacher teacher) {
        validateTeacher(teacher);
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(int id, Teacher updatedTeacher) {
        Teacher teacherSet = teacherRepository.findById(id).get();
        teacherSet.setFirstName(updatedTeacher.getFirstName());
        teacherSet.setLastName(updatedTeacher.getLastName());

        return teacherRepository.save(teacherSet);
    }

    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }


    public boolean validateTeacher(Teacher teacherValidated) {


        if (teacherValidated.getFirstName().isEmpty()) {
            throw new TeacherNameNullException("Name cannot be empty. Please, type a valid name");
        }

        if (teacherValidated.getLastName().equals(null)) {
            throw new TeacherNameNullException("Teacher's last name cannot be empty.Please, try again");

        }

        if (teacherValidated.getSsn().equals(null)) {
            throw new TeacherSnnNullException("SNN invalid! Please, enter a valid register");
        }

        return false;

    }


}


