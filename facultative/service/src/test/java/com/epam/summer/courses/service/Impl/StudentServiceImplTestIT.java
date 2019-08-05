package com.epam.summer.courses.service.Impl;

import com.epam.summer.courses.model.Student;
import com.epam.summer.courses.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class StudentServiceImplTestIT {

    @Autowired
    private StudentService studentService;

    @Test
    void getStudentsList() {
        List<Student> studentList = studentService.getStudentsList();
        assertNotNull(studentList);
        assertFalse(studentList.isEmpty());
    }

    @Test
    void getStudentById() {
        int studentId = 1;
        Student student = studentService.getStudentById(studentId);
        assertNotNull(student);
        assertEquals("maksim", student.getFirstName());
    }

    @Test
    void addStudent() {
        Student student = create("Olga");

        student = studentService.addStudent(student);
        assertNotNull(student);
        assertNotNull(student.getStudentId());
        assertEquals("Olga", student.getFirstName());
    }

    @Test
    void updateStudent() {
        int studentId = 2;
        Student student = create("dasha");
        student.setStudentId(studentId);
        studentService.updateStudent(student);
        student = studentService.getStudentById(studentId);
        assertNotNull(student);
        assertEquals("dasha", student.getFirstName());
    }

    @Test
    void deleteStudent() {
        int studentId = 3;
        studentService.deleteStudent(studentId);
        assertThrows(RuntimeException.class, () -> studentService.getStudentById(studentId));
    }

    private Student create(String name) {
        Student student = new Student();
        student.setFirstName(name);
        return student;
    }
}