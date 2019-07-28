package com.epam.summer.courses.dao;

import com.epam.summer.courses.model.Course;

import java.util.List;

/**
 * The interface Student has courses dao.
 */
public interface StudentHasCoursesDao {
    /**
     * Gets student courses list.
     *
     * @param studentId the student id
     * @return the student courses list
     */
    List<Course> getStudentCoursesList(final Integer studentId);

    /**
     * Delete student courses list.
     *
     * @param studentId the student id
     */
    void deleteStudentCoursesList(final Integer studentId);

    /**
     * Add student courses list.
     *
     * @param studentId  the student id
     * @param courseList the course list
     */
    void addStudentCoursesList(final Integer studentId, final List<Course> courseList);

    /**
     * Add student course.
     *
     * @param studentId the student id
     * @param course    the course
     */
    void addStudentCourse(final Integer studentId, final Course course);
}
