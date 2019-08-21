package com.epam.summer.courses.service;

import com.epam.summer.courses.dao.CourseDao;
import com.epam.summer.courses.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course addCourse(Course course) {
        LOGGER.debug("add course [{}]", course);
        return courseDao.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        LOGGER.debug("update course [{}]", course);
        courseDao.updateCourse(course);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        LOGGER.debug("delete course [{}]", courseId);
        courseDao.deleteCourse(courseId);
    }

    @Override
    public List<Course> getCoursesList() {
        LOGGER.debug("get course list");
        return courseDao.getCoursesList();
    }

    @Override
    public Course getCourseById(Integer courseId) {
        LOGGER.debug("get course by id [{}]", courseId);
        return courseDao.getCourseById(courseId)
                .orElseThrow(() -> new RuntimeException("Failed to get course from DB"));
    }
}
