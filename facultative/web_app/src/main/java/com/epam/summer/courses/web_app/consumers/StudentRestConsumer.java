package com.epam.summer.courses.web_app.consumers;

import com.epam.summer.courses.model.Student;
import com.epam.summer.courses.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class StudentRestConsumer implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestConsumer.class);

    private String url;
    private RestTemplate restTemplate;

    public StudentRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Student> getStudentsList() {
        LOGGER.debug("getStudentsList()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url, List.class);
        return  (List<Student>) responseEntity.getBody();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        LOGGER.debug("getStudentById({})", studentId);
        ResponseEntity<Student> responseEntity = restTemplate.getForEntity(url + "/" + studentId, Student.class);
        return responseEntity.getBody();
    }

    @Override
    public Student addStudent(Student student) {
        LOGGER.debug("addStudent({})", student);
        return restTemplate.postForObject(url, student, Student.class);
    }

    @Override
    public void updateStudent(Student student) {
        LOGGER.debug("updateStudent({})", student);
        restTemplate.put(url, student);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        LOGGER.debug("deleteStudent({})", studentId);
        restTemplate.delete(url + "/" + studentId);
    }
}
