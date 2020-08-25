package com.epam.summer.courses.web_app.client;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CourseRestClient implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRestClient.class);

    private String url;
    private RestTemplate restTemplate;

    public CourseRestClient(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Course> getCoursesList() {
        LOGGER.debug("getCoursesList()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return  (List<Course>) responseEntity.getBody();
    }

    @Override
    public Course getCourseById(Integer courseId) {
        LOGGER.debug("getCourseById({})", courseId);
        ResponseEntity<Course> responseEntity = restTemplate.getForEntity(url + "/" + courseId, Course.class);
        return responseEntity.getBody();
    }

    @Override
    public Course addCourse(Course course) {
        LOGGER.debug("addCourse({})", course);
        return restTemplate.postForObject(url, course, Course.class);
    }

    @Override
    public void updateCourse(Course course) {
        LOGGER.debug("updateCourse({})", course);
        restTemplate.put(url, course);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        LOGGER.debug("deleteCourse({})", courseId);
        restTemplate.delete(url + "/" + courseId);
    }
}
