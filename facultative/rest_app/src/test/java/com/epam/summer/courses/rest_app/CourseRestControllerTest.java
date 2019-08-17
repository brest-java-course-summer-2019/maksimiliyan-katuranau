package com.epam.summer.courses.rest_app;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.service.CourseService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
class CourseRestControllerTest {

    @Autowired
    private CourseRestController controller;

    @Autowired
    private CourseService courseService;

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void after() {
        verifyNoMoreInteractions(courseService);
    }

    @Test
    void getCourses() throws Exception {

        when(courseService.getCoursesList()).thenReturn(Arrays.asList(create(0), create(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/courses")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseName", Matchers.is("def0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].courseId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].courseName", Matchers.is("def1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].courseId", Matchers.is(1)))
        ;

        verify(courseService).getCoursesList();
    }

    private Course create(int index) {
        Course course = new Course();
        course.setCourseName("def" + index);
        course.setCourseId(index);
        return course;
    }
}