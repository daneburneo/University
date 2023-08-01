package br.com.logos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "semesters")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "semester_id")
    private Integer id;

    @Column(name = "semester_name")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_fk")
    private Course course;

    @ManyToMany(mappedBy = "semesters")
    private List<Discipline> disciplines;


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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}