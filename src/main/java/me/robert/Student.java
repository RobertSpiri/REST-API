package me.robert;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Student {
    @Id
    private String id;
    private String name;
    private String lastName;

    public Student(){
    }

    public Student(String id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    @DBRef(db = "courses")
    private List<Curs> courses;

    public List<Curs> getCourses() {
        return courses;
    }

    public void setCourses(List<Curs> courses) {
        this.courses = courses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String studentInfo() {
        return "ID: " + id + "\nName: " + name + "\nLastName: " + lastName;
    }
}
