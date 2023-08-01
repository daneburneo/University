package br.com.logos.dtos;

import br.com.logos.models.Course;
import br.com.logos.models.Discipline;

import java.util.List;

public class SemesterDTO {

    private Integer id;

    private String name;

    private List<Course> coursesList;

    private List<Discipline> disciplineList;

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

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public List<Discipline> getDisciplineList() {
        return disciplineList;
    }

    public void setDisciplineList(List<Discipline> disciplineList) {
        this.disciplineList = disciplineList;
    }
}
