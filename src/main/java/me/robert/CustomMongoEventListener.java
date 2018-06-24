package me.robert;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;

public class CustomMongoEventListener extends AbstractMongoEventListener<Object> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {

        Object source = event.getSource();

        if (event.getType().equals(Course.class)) {
            ObjectId courseId = (ObjectId) ((Document)source).get("_id");
            Course course = mongoOperations.findById(courseId, Course.class);
            for (Student student :
                    course.getStudents()) {
                Student loadedStudent = mongoOperations.findById(student.getId(), Student.class);
                loadedStudent.getCourses().remove(course);
                mongoOperations.save(loadedStudent);
            }
        } else if (event.getType().equals(Student.class)) {
            ObjectId studentId = (ObjectId) ((Document)source).get("_id");
            Student student = mongoOperations.findById(studentId, Student.class);
            for (Course course :
                    student.getCourses()) {
                Course loadedCourse = mongoOperations.findById(course.getId(), Course.class);
                loadedCourse.getStudents().remove(student);
                mongoOperations.save(loadedCourse);
            }
        }

    }
}
