package br.com.logos.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "course_name")
    String name;
    @Column(name = "course_coordinator")
    String coordinator;
    @Column(name = "course_level")
    String level;
    @Column(name = "course_director")
    String director;


    @JsonIgnore
    @ManyToMany
    private List<Discipline> disciplines;

    @JsonIgnore
    @ManyToMany
    private List<Teacher> teachers;

    public Course() {
    }
    public Course(Integer id, String name, String coordinator, String level, String director) {
        this.id = id;
        this.name = name;
        this.coordinator = coordinator;
        this.level = level;
        this.director = director;
    }

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

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }


}
