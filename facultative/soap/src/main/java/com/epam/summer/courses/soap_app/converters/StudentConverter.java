package com.epam.summer.courses.soap_app.converters;

import com.epam.summer.courses.model.Student;
import com.epam.summer.courses.soap_app.schemas.StudentType;

import java.util.stream.Collectors;

public class StudentConverter {

    public static StudentType convertToStudentType(Student student) {
        StudentType studentType = new StudentType();
        studentType.setStudentId(student.getStudentId());
        studentType.setFirstName(student.getFirstName());
        studentType.setLastName(student.getLastName());
        studentType.setAge(student.getAge());
        studentType.setNumberOfCourses(student.getNumberOfCourses());
        studentType.getCourseList().addAll(student.getCourseList()
                .stream().map(CourseConverter::convertToCourseType).collect(Collectors.toList()));
        return studentType;
    }

    public static Student convertToStudent(StudentType studentType) {
        Student student = new Student();
        student.setStudentId(studentType.getStudentId());
        student.setFirstName(studentType.getFirstName());
        student.setLastName(studentType.getLastName());
        student.setAge(studentType.getAge());
        student.setNumberOfCourses(studentType.getNumberOfCourses());
        student.setCourseList(studentType.getCourseList()
                .stream().map(CourseConverter::convertToCourse).collect(Collectors.toList()));
        return student;
    }
}
