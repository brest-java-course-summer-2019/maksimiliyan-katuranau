package com.epam.summer.courses.dao.Impl;

import com.epam.summer.courses.dao.StudentDao;
import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-dao.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentDaoJdbcImplTest {

    private static final String STUDENT_FIRST_NAME_DEV = "david";
    private static final int STUDENT_AGE_DEV = 20;
    private static final int NUMBER_OF_COURSES_DEV = 2;
    private static final String STUDENT_FIRST_NAME_NEW = "veronika";
    private static final String STUDENT_LAST_NAME_NEW = "monit";
    private static final int STUDENT_AGE_NEW = 22;
    private static final int NUMBER_OF_COURSES_NEW = 3;


    @Autowired
    StudentDao studentDao;

    @Test
    public void getStudentsList() {
        List<Student> courseList = studentDao.getStudentsList();
        assertNotNull(courseList);
        assertTrue(courseList.size() > 0);
    }

    @Test
    public void getStudentById() {
        Integer studentId = 2;
        Student student = studentDao.getStudentById(studentId);
        assertNotNull(student);
        assertTrue(student.getStudentId().equals(studentId));
        assertEquals(STUDENT_FIRST_NAME_DEV, student.getFirstName());
        assertEquals(STUDENT_AGE_DEV, student.getAge());
        assertEquals(NUMBER_OF_COURSES_DEV, student.getNumberOfCourses());
    }

    @Test
    public void addStudent() {
        Integer studentId = 3;
        Student student = studentDao.getStudentById(studentId);
        Student newStudent = studentDao.addStudent(student);
        assertFalse(studentId.equals(newStudent.getStudentId()));
        assertEquals(student.getLastName(), newStudent.getLastName());
        assertEquals(student.getAge(), newStudent.getAge());
    }

    @Test
    public void updateStudent() {
        Integer studentId = 2;
        List<Course> courseList = new ArrayList<>() {{
            add(new Course(1));
            add(new Course(2));
            add(new Course(3));
        }};
        Student student = studentDao.getStudentById(studentId);
        student.setFirstName(STUDENT_FIRST_NAME_NEW);
        student.setLastName(STUDENT_LAST_NAME_NEW);
        student.setAge(STUDENT_AGE_NEW);
        student.setCourseList(courseList);
        studentDao.updateStudent(student);
        Student updatedStudent = studentDao.getStudentById(studentId);
        assertEquals(studentId, updatedStudent.getStudentId());
        assertEquals(STUDENT_FIRST_NAME_NEW, updatedStudent.getFirstName());
        assertEquals(STUDENT_AGE_NEW, updatedStudent.getAge());
        assertEquals(NUMBER_OF_COURSES_NEW, updatedStudent.getNumberOfCourses());
    }

    @Test
    public void deleteStudent() {
        int size = studentDao.getStudentsList().size();
        studentDao.deleteStudent(1);
        assertEquals(size - 1, studentDao.getStudentsList().size());
    }
}
