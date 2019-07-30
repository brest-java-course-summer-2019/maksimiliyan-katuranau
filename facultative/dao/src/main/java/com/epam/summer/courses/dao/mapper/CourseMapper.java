package com.epam.summer.courses.dao.mapper;

import com.epam.summer.courses.model.Course;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CourseMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"),
                resultSet.getDate("date"), resultSet.getString("teacher"), resultSet.getInt("studentCount"));
        return course;
    }
}
