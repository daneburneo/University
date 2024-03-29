package br.com.logos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    Integer id;
    @Column(name = "course_name")
    String name;
    @Column(name = "course_coordinator")
    String coordinator;
    @Column(name = "course_level")
    String level;
    @Column(name = "course_director")
    String director;

    @ManyToMany
    @JoinTable(
            name = "courses_disciplines",
            joinColumns = @JoinColumn(name = "course_fk"),
            inverseJoinColumns = @JoinColumn(name = "discipline_fk")
    )
    private List<Discipline> disciplines;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "courses_teachers",
            joinColumns = @JoinColumn(name = "course_fk"),
            inverseJoinColumns = @JoinColumn(name = "teacher_fk"))
    private List<Teacher> teachers;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Semester> list;


    public Course() {
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Semester> getList() {
        return list;
    }

    public void setList(List<Semester> list) {
        this.list = list;
    }


}
