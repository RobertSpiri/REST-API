package me.robert;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "students")
public class Student {
    @Id
    private ObjectId id;
    private String name;
    private String lastName;
    @DBRef(lazy = true)
    private List<Course> courses = new ArrayList<Course>();

    public Student(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, lastName, courses);
    }

    public Student(ObjectId id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
