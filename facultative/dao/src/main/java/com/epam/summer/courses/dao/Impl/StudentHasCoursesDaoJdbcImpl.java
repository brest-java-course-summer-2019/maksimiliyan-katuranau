package com.epam.summer.courses.dao.Impl;

import com.epam.summer.courses.dao.StudentHasCoursesDao;
import com.epam.summer.courses.dao.mapper.CourseMapper;
import com.epam.summer.courses.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentHasCoursesDaoJdbcImpl implements StudentHasCoursesDao {

    @Value("${list.student.courses.get}")
    private String LIST_STUDENT_COURSES_GET_SQL;

    @Value("${list.student.courses.delete}")
    private String LIST_STUDENT_COURSES_DELETE_SQL;

    @Value("${student.course.add}")
    private String STUDENT_COURSE_ADD_SQL;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private CourseMapper courseMapper;

    @Autowired
    public StudentHasCoursesDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                        CourseMapper courseMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.courseMapper = courseMapper;
    }

    @Override
    public List<Course> getStudentCoursesList(final Integer studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource("studentId", studentId);
        return namedParameterJdbcTemplate.query(LIST_STUDENT_COURSES_GET_SQL, parameters, courseMapper);
    }

    @Override
    public void deleteStudentCoursesList(final Integer studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource("studentId", studentId);
        namedParameterJdbcTemplate.update(LIST_STUDENT_COURSES_DELETE_SQL, parameters);
    }

    @Override
    public void addStudentCoursesList(final Integer studentId, final List<Course> courseList) {
        deleteStudentCoursesList(studentId);
        courseList.forEach(course -> addStudentCourse(studentId, course));
    }

    @Override
    public void addStudentCourse(final Integer studentId, final Course course) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("studentId", studentId)
                .addValue("courseId", course.getCourseId());
        namedParameterJdbcTemplate.update(STUDENT_COURSE_ADD_SQL, parameters);
    }
}
