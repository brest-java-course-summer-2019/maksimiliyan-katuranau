package com.epam.summer.courses.web_app.validators;

import com.epam.summer.courses.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class CourseValidatorTest {

    Course course;

    CourseValidator courseValidator = new CourseValidator();
    BindingResult result;

    @BeforeEach
    void setup() {
        course = Mockito.mock(Course.class);
        result = new BeanPropertyBindingResult(course, "course");
    }

    @Test
    void shouldRejectNullDepartmentName() {
        // given
        Mockito.when(course.getCourseName()).thenReturn(null);

        // when
        courseValidator.validate(course, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyDepartmentName() {
        // given
        Mockito.when(course.getCourseName()).thenReturn("");

        // when
        courseValidator.validate(course, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeDepartmentName() {

        // given
        String filled = StringUtils.repeat("*", 300);
        Mockito.when(course.getCourseName()).thenReturn(filled);

        // when
        courseValidator.validate(course, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateDepartmentName() {

        // given
        String filled = StringUtils.repeat("*", 250);
        Mockito.when(course.getCourseName()).thenReturn(filled);

        // when
        courseValidator.validate(course, result);

        // then
        assertFalse(result.hasErrors());
    }
}