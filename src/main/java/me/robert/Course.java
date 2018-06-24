package me.robert;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "courses")
public class Course {
    @Id
    private ObjectId id;
    private String course;
    @DBRef(lazy = true)
    private List<Student> students = new ArrayList<Student>();

    public Course() {
    }

    public Course(ObjectId id, String course) {
        this.id = id;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course1 = (Course) o;
        return Objects.equals(course, course1.course) &&
                Objects.equals(students, course1.students);
    }

    @Override
    public int hashCode() {

        return Objects.hash(course, students);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


}
