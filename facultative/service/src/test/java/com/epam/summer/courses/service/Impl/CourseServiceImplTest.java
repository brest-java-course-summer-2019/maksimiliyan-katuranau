package com.epam.summer.courses.service.Impl;

import com.epam.summer.courses.dao.CourseDao;
import com.epam.summer.courses.model.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.JUnitException;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseDao courseDao;

    @InjectMocks
    private CourseServiceImpl courseService;

    @AfterEach
    void clearMock() {
        verifyNoMoreInteractions(courseDao);
    }

    @Test
    void getCourseById() {
        Integer courseId = 1;
        when(courseDao.getCourseById(courseId)).thenReturn(Optional.of(create("maths")));
        Course course = courseService.getCourseById(courseId);
        assertNotNull(course);
        assertEquals("maths", course.getCourseName());
        verify(courseDao).getCourseById(1);
    }

    @Test
    void getCoursesList() {
        when(courseDao.getCoursesList()).thenReturn(Collections.singletonList(create("maths")));
        List<Course> courseList = courseService.getCoursesList();

        assertNotNull(courseList);
        assertEquals(1, courseList.size());
        Course course = courseList.get(0);
        assertNotNull(course);
        assertEquals("maths", course.getCourseName());
        verify(courseDao).getCoursesList();
    }

    @Test
    void addCourse() {
        Course course = create("race");
        when(courseDao.addCourse(any(Course.class))).thenReturn(course);
        course = courseService.addCourse(course);
        assertEquals("race", course.getCourseName());
        verify(courseDao).addCourse(course);
    }

    @Test
    void updateCourse() {
        courseService.updateCourse(create("dance"));
        ArgumentCaptor<Course> captor = ArgumentCaptor.forClass(Course.class);
        verify(courseDao).updateCourse(captor.capture());
        Course course = captor.getValue();
        assertNotNull(course);
        assertEquals("dance", course.getCourseName());
    }

    @Test
    void deleteCourse() {
        int courseId = 2;
        courseService.deleteCourse(courseId);
        verify(courseDao, only()).deleteCourse(courseId);
    }

    private Course create(String name) {
        Course course = new Course(1);
        course.setCourseName(name);
        return course;
    }
}