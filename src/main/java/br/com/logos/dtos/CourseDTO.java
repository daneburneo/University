package br.com.logos.dtos;

import br.com.logos.models.Discipline;
import br.com.logos.models.Teacher;

import java.util.List;

public class CourseDTO {

    private Integer id;
    private String name;
    private String coordinator;
    private String level;
    private String director;
    private List<Discipline> disciplinesList;
    private List<Teacher> teachersList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Discipline> getDisciplinesList() {
        return disciplinesList;
    }

    public void setDisciplinesList(List<Discipline> disciplinesList) {
        this.disciplinesList = disciplinesList;
    }

    public List<Teacher> getTeachersList() {
        return teachersList;
    }

    public void setTeachersList(List<Teacher> teachersList) {
        this.teachersList = teachersList;
    }
}

