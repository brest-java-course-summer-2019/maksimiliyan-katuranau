package com.epam.summer.courses.dao.Impl;

import com.epam.summer.courses.dao.StudentHasCoursesDao;
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
public class StudentHasCoursesDaoJdbcImplTest {

    @Autowired
    StudentHasCoursesDao studentHasCoursesDao;

    @Test
    public void getStudentCoursesList() {
        List<Course> courseList = studentHasCoursesDao.getStudentCoursesList(1);
        assertNotNull(courseList);
        assertTrue(courseList.size() > 0);
    }

    @Test
    public void deleteStudentCoursesList() {
        Integer studentId = 1;
        studentHasCoursesDao.deleteStudentCoursesList(studentId);
        assertEquals(0, studentHasCoursesDao.getStudentCoursesList(studentId).size());
    }

    @Test
    public void addStudentCoursesList() {
        Integer studentId = 4;
        List<Course> courseList = studentHasCoursesDao.getStudentCoursesList(2);
        int newSizeExpected = courseList.size();
        studentHasCoursesDao.addStudentCoursesList(studentId, courseList);
        assertEquals(newSizeExpected, studentHasCoursesDao.getStudentCoursesList(studentId).size());
    }

    @Test
    public void addStudentCourse() {
        Integer studentId = 4;
        int size = studentHasCoursesDao.getStudentCoursesList(studentId).size();
        studentHasCoursesDao.addStudentCourse(studentId, new Course(3));
        assertEquals(size + 1, studentHasCoursesDao.getStudentCoursesList(studentId).size());
    }
}
