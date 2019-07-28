package com.epam.summer.courses.dao.Impl;

import com.epam.summer.courses.dao.CourseDao;
import com.epam.summer.courses.dao.mapper.CourseMapper;
import com.epam.summer.courses.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The type Course dao jdbc.
 */
@Repository
public class CourseDaoJdbcImpl implements CourseDao {

    @Value("${list.courses.get}")
    private String LIST_COURSES_GET_SQL;

    @Value("${course.get}")
    private String COURSE_GET_SQL;

    @Value("${course.add}")
    private String COURSE_ADD_SQL;

    @Value("${course.update}")
    private String COURSE_UPDATE_SQL;

    @Value("${course.delete}")
    private String COURSE_DELETE_SQL;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private CourseMapper courseMapper;

    /**
     * Instantiates a new Course dao jdbc.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param courseMapper               the course mapper
     */
    @Autowired
    public CourseDaoJdbcImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             final CourseMapper courseMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.courseMapper = courseMapper;
    }

    @Override
    public Course addCourse(Course course) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("courseName", course.getCourseName())
                .addValue("teacher", course.getTeacher());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(COURSE_ADD_SQL, parameters, generatedKeyHolder, new String[] { "course_id" });
        course.setCourseId(generatedKeyHolder.getKey().intValue());
        return course;
    }

    @Override
    public void updateCourse(Course department) {
        Optional.of(namedParameterJdbcTemplate.update(COURSE_UPDATE_SQL,
                new BeanPropertySqlParameterSource(department)))
                .filter(numRowsUpdated -> numRowsUpdated > 0)
                .orElseThrow(() -> new RuntimeException("Failed to update course in DB"));
    }

    @Override
    public void deleteCourse(Integer courseId) {
        SqlParameterSource parameters = new MapSqlParameterSource("courseId", courseId);
        Optional.of(namedParameterJdbcTemplate.update(COURSE_DELETE_SQL, parameters))
                .filter(numRowsUpdated -> numRowsUpdated > 0)
                .orElseThrow(() -> new RuntimeException("Failed to delete course from DB"));
    }

    @Override
    public List<Course> getCoursesList() {
        return namedParameterJdbcTemplate.query(LIST_COURSES_GET_SQL, courseMapper);
    }

    @Override
    public Optional<Course> getCourseById(Integer courseId) {
        SqlParameterSource parameters = new MapSqlParameterSource("courseId", courseId);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(namedParameterJdbcTemplate.query(COURSE_GET_SQL,
                parameters, courseMapper)));
    }
}
