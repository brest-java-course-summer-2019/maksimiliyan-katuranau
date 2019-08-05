package com.epam.summer.courses.service.Impl;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class CourseServiceImplTestIT {

    @Autowired
    private CourseService courseService;

    @Test
    void getCoursesList() {
        List<Course> courseList = courseService.getCoursesList();
        assertNotNull(courseList);
        assertFalse(courseList.isEmpty());
    }

    @Test
    void getCourseById() {
        int courseId = 1;
        Course course = courseService.getCourseById(courseId);
        assertNotNull(course);
        assertEquals("Vasilievna", course.getTeacher());
    }

    @Test
    void addCourse() {
        Course course = create("box");
        course = courseService.addCourse(course);
        assertNotNull(course);
        assertNotNull(course.getCourseId());
        assertEquals("box", course.getCourseName());
    }

    @Test
    void updateCourse() {
        int courseId = 2;
        Course course = create("swimming");
        course.setCourseId(courseId);
        courseService.updateCourse(course);
        course = courseService.getCourseById(courseId);
        assertNotNull(course);
        assertEquals("swimming", course.getCourseName());
    }

    @Test
    void deleteCourse() {
        int courseId = 3;
        courseService.deleteCourse(courseId);
        assertThrows(RuntimeException.class, () -> courseService.getCourseById(courseId));
    }

    private Course create(String name) {
        Course course = new Course();
        course.setCourseName(name);
        return course;
    }
}
