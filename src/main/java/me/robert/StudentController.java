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
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;
    }

    @RequestMapping(value = "/students/{id}/addcourse", method = RequestMethod.POST)
    public void addCourseToStudent(@PathVariable String id, @Valid String courseId) {
        Optional<Student> studentOptional = studentRepository.findById(new ObjectId(id));
        Optional<Course> courseOptional = courseRepository.findById(new ObjectId(courseId));

        studentOptional.ifPresent(student -> {
            courseOptional.ifPresent(course -> {
                student.getCourses().add(course);
                course.getStudents().add(student);

                courseRepository.save(course);
            });
            studentRepository.save(student);
        });
    }

    @RequestMapping(value = "/students/{id}/removecourse", method = RequestMethod.POST)
    public void removeCourseFromStudent(@PathVariable String id, @Valid String courseId) {
        Optional<Student> studentOptional = studentRepository.findById(new ObjectId(id));
        Optional<Course> courseOptional = courseRepository.findById(new ObjectId(courseId));

        studentOptional.ifPresent(student -> {
            courseOptional.ifPresent(course -> {
                student.getCourses().remove(course);
                course.getStudents().remove(student);

                courseRepository.save(course);
            });
            studentRepository.save(student);
        });
    }



}