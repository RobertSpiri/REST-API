# REST-API

![alt text](https://user-images.githubusercontent.com/13012953/41840292-0e1d0914-786e-11e8-9235-5543607d2e8d.png)

## Install
[Java](https://java.com/en/download/)

[MongoDB Windows](https://www.mongodb.com/dr/fastdl.mongodb.org/win32/mongodb-win32-x86_64-2008plus-ssl-3.6.5-signed.msi/download})

[PostMan Windows](https://app.getpostman.com/app/download/win64?_ga=2.85332507.329633062.1529917507-36859038.1529582665)

[Maven](https://maven.apache.org/)

[Maven install guide](https://maven.apache.org/install.html)

## Usage

Download the repository to your local machine

Start the Data Base with mongod.exe

Run the folowing in bash
```
mvn clean install -U

cd "target"

java -jar robert-REST-API-0.1.0.jar
```
Now you can view your content of your DB by opening http://localhost:8080 with your favorite browser

The fun begins here

Start PostMan

#### VIEW
To view all students : GET on  http://localhost:8080/students

To view all the courses of a student: GET on http://localhost:8080/students/"student_id"/courses

To view all courses : GET on http://localhost:8080/courses

To view all the students of a course : GET on http://localhost:8080/courses/"course_id"/students

#### ADD

To add a student : POST on http://localhost:8080/students with a JSON body
```
{

    "name" : "StudentName",
    
    "lastName" : "StudentLastName"
    
}
```

To add a course : POST on http://localhost:8080/courses with a JSON body
```
{

    "course" : "CourseName"
  
}
```
To add a course to a student : POST on http://localhost:8080/students/"student_id"/addcourse with a form-data body with the key: "courseId" and the value being the id of the course

To add a student to a course : POST on http://localhost:8080/courses/"course_id"/addstudent with a form-data body with the key: "studentId" and the value being the id of the student

#### UPDATE

To update a course : PUT on http://localhost:8080/courses/"course_id" with a JSON body
```
{

    "course" : "NewCourseName"
  
}
```
To update a student : PUT on http://localhost:8080/students/"student_id" with a JSON body
```
{

    "name" : "NewName",
  
    "lastName" : "NewLastName"
  
}
```
#### REMOVE
To remove a student : DELETE on http://localhost:8080/students/"student_id"

To remove a course : DELETE on http://localhost:8080/courses/"course_id"

To remove a course to a student : POST on http://localhost:8080/students/"student_id"/removecourse with a form-data body with the key: "courseId" and the value being the id of the course

To remove a student to a course : POST on http://localhost:8080/courses/"course_id"/removestudent with a form-data body with the key: "studentId" and the value being the id of the student 

