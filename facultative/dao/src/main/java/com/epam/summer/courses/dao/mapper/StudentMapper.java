package com.epam.summer.courses.dao.mapper;

import com.epam.summer.courses.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student(resultSet.getInt("student_id"), resultSet.getString("first_name"),
                resultSet.getString("last_name"), resultSet.getInt("age"), resultSet.getInt("courseCount"),
                null);
        return student;
    }
}
