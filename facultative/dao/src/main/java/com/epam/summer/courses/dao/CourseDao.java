package com.epam.summer.courses.dao;

import com.epam.summer.courses.model.Course;

import java.util.List;
import java.util.Optional;

/**
 * The interface Course dao.
 */
public interface CourseDao {

    /**
     * Add course course.
     *
     * @param course the course
     * @return the course
     */
    Course addCourse(Course course);

    /**
     * Update course.
     *
     * @param course the course
     */
    void updateCourse(Course course);

    /**
     * Delete course.
     *
     * @param courseId the course id
     */
    void deleteCourse(Integer courseId);

    /**
     * Gets courses list.
     *
     * @return the courses list
     */
    List<Course> getCoursesList();

    /**
     * Gets course by id.
     *
     * @param courseId the course id
     * @return the course by id
     */
    Optional<Course> getCourseById(Integer courseId);
}
