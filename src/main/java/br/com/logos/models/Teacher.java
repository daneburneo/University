package br.com.logos.models;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
