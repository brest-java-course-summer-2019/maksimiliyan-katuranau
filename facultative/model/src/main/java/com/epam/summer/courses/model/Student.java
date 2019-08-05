package com.epam.summer.courses.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private int age;
    private int numberOfCourses;
    private List<Course> courseList;

    public Student() {
        this.courseList = new ArrayList<>();
    }

    public Student(Integer studentId, String firstName, String lastName, int age, int numberOfCourses,
                   List<Course> courseList) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.numberOfCourses = numberOfCourses;
        this.courseList = courseList;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    public void setNumberOfCourses(int numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", numberOfCourses=" + numberOfCourses +
                ", courseList=" + courseList +
                '}';
    }
}
