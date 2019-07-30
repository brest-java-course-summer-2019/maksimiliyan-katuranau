package com.epam.summer.courses.dao.Impl;

import com.epam.summer.courses.dao.CourseDao;
import com.epam.summer.courses.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-dao.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CourseDaoJdbcImplTest {

    private static final String COURSE_NAME_DEV = "maths";
    private static final String TEACHER_DEV = "Nosova";
    private static final int NUMBER_OF_STUDENTS = 4;
    private static final String COURSE_NAME_NEW = "dance";
    private static final String TEACHER_NEW = "Liborets";

    @Autowired
    CourseDao courseDao;

    @Test
    public void getCoursesList() {
        List<Course> courseList = courseDao.getCoursesList();
        assertNotNull(courseList);
        assertTrue(courseList.size() > 0);
    }

    @Test
    public void getCourseById() {
        Integer courseId = 2;
        Course course = courseDao.getCourseById(courseId).get();
        assertNotNull(course);
        assertTrue(course.getCourseId().equals(courseId));
        assertEquals(COURSE_NAME_DEV, course.getCourseName());
        assertEquals(TEACHER_DEV, course.getTeacher());
        assertEquals(NUMBER_OF_STUDENTS, course.getNumberOfStudents());
    }

    @Test
    public void addCourse() {
        Course course = new Course();
        course.setCourseName(COURSE_NAME_NEW);
        course.setTeacher(TEACHER_NEW);
        Course newCourse = courseDao.addCourse(course);
        assertNotNull(newCourse.getCourseId());
    }

    @Test
    public void updateCourse() {
        Integer courseId = 2;
        Course course = courseDao.getCourseById(courseId).get();
        course.setCourseName(COURSE_NAME_NEW);
        course.setTeacher(TEACHER_NEW);
        courseDao.updateCourse(course);
        Course updatedCourse = courseDao.getCourseById(courseId).get();
        assertEquals(courseId, updatedCourse.getCourseId());
        assertEquals(COURSE_NAME_NEW, updatedCourse.getCourseName());
        assertEquals(TEACHER_NEW, updatedCourse.getTeacher());
    }

    @Test
    public void deleteCourse() {
        int size = courseDao.getCoursesList().size();
        courseDao.deleteCourse(1);
        assertEquals(size - 1, courseDao.getCoursesList().size());
    }
}
