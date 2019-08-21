package com.epam.summer.courses.service;

import com.epam.summer.courses.dao.StudentDao;
import com.epam.summer.courses.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student addStudent(Student student) {
        LOGGER.debug("add student [{}]", student);
        return studentDao.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        LOGGER.debug("update student [{}]", student);
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        LOGGER.debug("delete student [{}]", studentId);
        studentDao.deleteStudent(studentId);
    }

    @Override
    public List<Student> getStudentsList() {
        LOGGER.debug("get student list");
        return studentDao.getStudentsList();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        LOGGER.debug("get student by id [{}]", studentId);
        return studentDao.getStudentById(studentId);
    }
}
