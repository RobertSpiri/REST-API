package me.robert;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class Curs {
    @Id
    private String id;
    private String course;

    public Curs() {
    }

    public Curs(String id, String course) {
        this.id = id;
        this.course = course;
    }

    @DBRef
    private List<Curs> curs_ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }


}
