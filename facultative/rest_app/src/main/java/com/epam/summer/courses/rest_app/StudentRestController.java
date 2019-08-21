package com.epam.summer.courses.rest_app;

import com.epam.summer.courses.model.Student;
import com.epam.summer.courses.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents() {
        LOGGER.debug("get all students");
        return studentService.getStudentsList();
    }

    @GetMapping(value = "/students/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        LOGGER.debug("find student by id({})", id);
        return studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        LOGGER.debug("add student({})", student);
        return studentService.addStudent(student);
    }

    @PutMapping("/students")
    public void updateStudent(@RequestBody Student student) {
        LOGGER.debug("update student ({})", student);
        studentService.updateStudent(student);
    }

    @DeleteMapping(value = "/students/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        LOGGER.debug("delete student ({})", id);
        studentService.deleteStudent(id);
    }
}
