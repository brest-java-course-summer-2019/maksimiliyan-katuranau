package com.epam.summer.courses.rest_app;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseRestController {

    private static final Logger LOGGER  = LoggerFactory.getLogger(CourseRestController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        LOGGER.debug("get all courses");
        return courseService.getCoursesList();
    }
}
