package br.com.logos.models;


import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, name = "student_firstname")
    private String firstName;

    @Column(nullable = false, name = "student_lastname")
    private String lastName;

    @Column(nullable = false, length = 12, name = "student_ssn")
    private String ssn;

    @Column(nullable = false, name = "student_course")
    private String course;


    public Student() {

    }

    public Student(Integer id, String firstName, String lastName, String ssn, String course) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.course = course;

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


}
