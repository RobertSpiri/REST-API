package me.robert;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/students/{id}/addcourse", method = RequestMethod.POST)
    public void addCourseToStudent(@PathVariable String id, @Valid String courseId) {
        Optional<Student> studentOptional = studentRepository.findById(new ObjectId(id));
        Optional<Course> courseOptional = courseRepository.findById(new ObjectId(courseId));

        studentOptional.ifPresent(student -> {
            courseOptional.ifPresent(course -> {
                student.getCourses().add(course);
            });
            studentRepository.save(student);
        });
    }
}