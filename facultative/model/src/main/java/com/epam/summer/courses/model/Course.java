package com.epam.summer.courses.model;

import java.util.Date;

public class Course {
    private Integer courseId;
    private String courseName;
    private Date date;
    private String teacher;

    public Course() {
    }

    public Course(Integer courseId) {
        this.courseId = courseId;
    }

    public Course(Integer courseId, String courseName, Date date, String teacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.date = date;
        this.teacher = teacher;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", date=" + date +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
