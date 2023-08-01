package br.com.logos.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="teacher_id")
    private Integer id;
    @Column(nullable = false, name = "teacher_firstname")
    private String firstName;
    @Column(nullable = false, name = "teacher_lastname")
    private String lastName;
    @Column(nullable = false, length = 12, name = "teacher_ssn")
    private String ssn;
    @Column(nullable = false, length = 12, name = "teacher_graduation")
    private String graduation;
    @Column(nullable = false, length = 40, name = "teacher_email")
    private String email;

    @ManyToMany(mappedBy = "teachers")
    private List<Course> courses;

    @ManyToMany(mappedBy = "teachers")
    private List<Student> students;

    @ManyToMany(mappedBy = "teachers")
    private List<Discipline> disciplines;

    public Teacher() {
    }

    public Teacher(Integer id, String firstName, String lastName, String ssn, String graduation, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.graduation = graduation;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

}
