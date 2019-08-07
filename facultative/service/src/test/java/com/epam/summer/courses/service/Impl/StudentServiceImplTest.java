package com.epam.summer.courses.service.Impl;

import com.epam.summer.courses.dao.StudentDao;
import com.epam.summer.courses.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Captor
    private ArgumentCaptor<Student> studentCaptor;

    @AfterEach
    void clearMock() {
        verifyNoMoreInteractions(studentDao);
    }

    @Test
    void getStudentsList() {
        when(studentDao.getStudentsList()).thenReturn(Collections.singletonList(create("Vadim")));
        List<Student> studentList = studentService.getStudentsList();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        Student student = studentList.get(0);
        assertNotNull(student);
        assertEquals("Vadim", student.getFirstName());
        verify(studentDao).getStudentsList();
    }

    @Test
    void getStudentById() {
        int studentId = 1;
        when(studentDao.getStudentById(studentId)).thenReturn(create("sasha"));
        Student student = studentService.getStudentById(studentId);
        assertNotNull(student);
        assertEquals("sasha", student.getFirstName());
        verify(studentDao).getStudentById(1);
    }

    @Test
    void addStudent() {
        Student student = create("Julia");
        when(studentDao.addStudent(any(Student.class))).thenReturn(student);
        student = studentService.addStudent(student);
        assertEquals("Julia", student.getFirstName());
        verify(studentDao).addStudent(student);
    }

    @Test
    void updateStudent() {
        studentService.updateStudent(create("Tanya"));
        verify(studentDao).updateStudent(studentCaptor.capture());
        Student student = studentCaptor.getValue();
        assertNotNull(student);
        assertEquals("Tanya", student.getFirstName());
    }

    @Test
    void deleteStudent() {
        int studentId = 2;
        studentService.deleteStudent(studentId);
        verify(studentDao).deleteStudent(studentId);
    }

    private Student create(String name) {
        Student student = new Student();
        student.setFirstName(name);
        return student;
    }
}
