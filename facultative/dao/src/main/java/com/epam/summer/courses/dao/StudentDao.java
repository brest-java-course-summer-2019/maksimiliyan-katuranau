package com.epam.summer.courses.dao;

import com.epam.summer.courses.model.Student;

import java.util.List;

/**
 * The interface Student dao.
 */
public interface StudentDao {

    /**
     * Add student student.
     *
     * @param student the student
     * @return the student
     */
    Student addStudent(Student student);

    /**
     * Update student.
     *
     * @param student the student
     */
    void updateStudent(Student student);

    /**
     * Delete student.
     *
     * @param studentId the student id
     */
    void deleteStudent(Integer studentId);

    /**
     * Gets students list.
     *
     * @return the students list
     */
    List<Student> getStudentsList();

    /**
     * Gets student by id.
     *
     * @param studentId the student id
     * @return the student by id
     */
    Student getStudentById(Integer studentId);
}
