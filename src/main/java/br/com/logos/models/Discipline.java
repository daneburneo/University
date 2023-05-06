package br.com.logos.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "disciplines")
public class Discipline {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="discipline_id")
    private Integer id;

    @Column(nullable = false, name = "discipline_name")
    private String name;

    @JsonIgnore
    @ManyToMany
    private List<Course> courses;

    @JsonIgnore
    @ManyToMany
    private List<Teacher> teachers;


    @JsonIgnore
    @ManyToMany
    private List<Student> students;

    public Discipline() {
    }

    public Discipline(Integer id, String name, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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
}
