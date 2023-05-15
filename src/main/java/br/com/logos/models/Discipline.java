package br.com.logos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "disciplines")
public class Discipline {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discipline_id")
    private Integer id;

    @Column(nullable = false, name = "discipline_name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "disciplines")
    private List<Course> courses;

    @ManyToMany
    @JoinTable(name = "disciplines_teachers",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "teacher_fk"))
    private List<Teacher> teachers;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "disciplines_students",
            joinColumns = @JoinColumn(name = "discipline_fk"),
            inverseJoinColumns = @JoinColumn(name = "student_fk"))
    private List<Student> students;

    public Discipline() {
    }

    public Discipline(Integer id, String name) {
        this.id = id;
        this.name = name;
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


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
}
