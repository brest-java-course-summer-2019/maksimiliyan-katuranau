package com.epam.summer.courses.rest_app;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/courses/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        LOGGER.debug("find course by id({})", id);
        return courseService.getCourseById(id);
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        LOGGER.debug("add course({})", course);
        return courseService.addCourse(course);
    }

    @PutMapping("/courses")
    public void updateCourse(@RequestBody Course course) {
        LOGGER.debug("update course ({})", course);
        courseService.updateCourse(course);
    }

    @DeleteMapping(value = "/courses/{id}")
    public void deleteCourse(@PathVariable("id") int id) {
        LOGGER.debug("delete course ({})", id);
        courseService.deleteCourse(id);
    }
}
