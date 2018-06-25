package me.robert;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {
    private Map<String, Student> studentDBMock;
    private Map<String, Course> courseDBMock;

    @Before
    public void setUp() {
        studentDBMock = new HashMap<>();
        courseDBMock = new HashMap<>();
    }

    @Test
    public void addStudentToCourse() {
        Student student = new Student(new ObjectId(), "STUDENT_NAME_TEST", "STUDENT_LAST_NAME_TEST");
        Course course = new Course(new ObjectId(), "COURSE_TEST");

        studentDBMock.put(student.getId().toHexString(), student);
        courseDBMock.put(course.getId().toHexString(), course);

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.findById(Mockito.any())).then(new Answer<Optional<Student>>() {
            @Override
            public Optional<Student> answer(InvocationOnMock invocationOnMock) throws Throwable {
                ObjectId id = invocationOnMock.getArgument(0);

                return Optional.of(studentDBMock.get(id.toHexString()));
            }
        });

        Mockito.when(studentRepository.save(Mockito.any())).then(new Answer<Student>() {
            @Override
            public Student answer(InvocationOnMock invocationOnMock) throws Throwable {
                Student student = invocationOnMock.getArgument(0);

                studentDBMock.put(student.getId().toHexString(), student);
                return studentDBMock.get(student.getId().toHexString());
            }
        });

        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Mockito.when(courseRepository.findById(Mockito.any())).then(new Answer<Optional<Course>>() {
            @Override
            public Optional<Course> answer(InvocationOnMock invocationOnMock) throws Throwable {
                ObjectId id = invocationOnMock.getArgument(0);

                return Optional.of(courseDBMock.get(id.toHexString()));
            }
        });

        Mockito.when(courseRepository.save(Mockito.any())).then(new Answer<Course>() {
            @Override
            public Course answer(InvocationOnMock invocationOnMock) throws Throwable {
                Course course = invocationOnMock.getArgument(0);

                courseDBMock.put(course.getId().toHexString(), course);
                return courseDBMock.get(course.getId().toHexString());
            }
        });

        CourseController courseController = new CourseController();
        courseController.setStudentRepository(studentRepository);
        courseController.setCourseRepository(courseRepository);

        courseController.addStudentToCourse(course.getId().toHexString(), student.getId().toHexString());

        courseDBMock.get(course.getId().toHexString()).getStudents();
        assert courseDBMock.get(course.getId().toHexString()).getStudents().size() == 1;
        assert courseDBMock.get(course.getId().toHexString()).getStudents().get(0).equals(student);
    }

    @Test
    public void removeStudentFromCourse() {
        Student student = new Student(new ObjectId(), "STUDENT_NAME_TEST", "STUDENT_LAST_NAME_TEST");
        Course course = new Course(new ObjectId(), "COURSE_TEST");

        studentDBMock.put(student.getId().toHexString(), student);
        courseDBMock.put(course.getId().toHexString(), course);

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Mockito.when(studentRepository.findById(Mockito.any())).then(new Answer<Optional<Student>>() {
            @Override
            public Optional<Student> answer(InvocationOnMock invocationOnMock) throws Throwable {
                ObjectId id = invocationOnMock.getArgument(0);

                return Optional.of(studentDBMock.get(id.toHexString()));
            }
        });

        Mockito.when(studentRepository.save(Mockito.any())).then(new Answer<Student>() {
            @Override
            public Student answer(InvocationOnMock invocationOnMock) throws Throwable {
                Student student = invocationOnMock.getArgument(0);

                studentDBMock.put(student.getId().toHexString(), student);
                return studentDBMock.get(student.getId().toHexString());
            }
        });

        CourseRepository courseRepository = Mockito.mock(CourseRepository.class);
        Mockito.when(courseRepository.findById(Mockito.any())).then(new Answer<Optional<Course>>() {
            @Override
            public Optional<Course> answer(InvocationOnMock invocationOnMock) throws Throwable {
                ObjectId id = invocationOnMock.getArgument(0);

                return Optional.of(courseDBMock.get(id.toHexString()));
            }
        });

        Mockito.when(courseRepository.save(Mockito.any())).then(new Answer<Course>() {
            @Override
            public Course answer(InvocationOnMock invocationOnMock) throws Throwable {
                Course course = invocationOnMock.getArgument(0);

                courseDBMock.put(course.getId().toHexString(), course);
                return courseDBMock.get(course.getId().toHexString());
            }
        });

        CourseController courseController = new CourseController();
        courseController.setStudentRepository(studentRepository);
        courseController.setCourseRepository(courseRepository);

        course.getStudents().add(student);
        courseController.removeStudentFromCourse(course.getId().toHexString(), student.getId().toHexString());

        courseDBMock.get(course.getId().toHexString()).getStudents();
        assert courseDBMock.get(course.getId().toHexString()).getStudents().size() == 0;
        assert courseDBMock.get(course.getId().toHexString()).getStudents().isEmpty();

    }
}