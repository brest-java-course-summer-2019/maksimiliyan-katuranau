package com.epam.summer.courses.dao.Impl;

import com.epam.summer.courses.dao.StudentDao;
import com.epam.summer.courses.dao.StudentHasCoursesDao;
import com.epam.summer.courses.dao.mapper.StudentMapper;
import com.epam.summer.courses.model.Student;
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

@Repository
public class StudentDaoJdbcImpl implements StudentDao {

    @Value("${list.students.get}")
    private String LIST_STUDENTS_GET_SQL;

    @Value("${student.get}")
    private String STUDENT_GET_SQL;

    @Value("${student.add}")
    private String STUDENT_ADD_SQL;

    @Value("${student.update}")
    private String STUDENT_UPDATE_SQL;

    @Value("${student.delete}")
    private String STUDENT_DELETE_SQL;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private StudentMapper studentMapper;
    private StudentHasCoursesDao studentHasCoursesDao;

    /**
     * Instantiates a new Course dao jdbc.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     * @param courseMapper               the course mapper
     */
    @Autowired
    public StudentDaoJdbcImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                             final StudentMapper courseMapper, final StudentHasCoursesDao studentHasCoursesDao) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.studentMapper = courseMapper;
        this.studentHasCoursesDao = studentHasCoursesDao;
    }

    @Override
    public Student addStudent(Student student) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", student.getLastName())
                .addValue("lastName", student.getLastName())
                .addValue("age", student.getAge());
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(STUDENT_ADD_SQL, parameters, generatedKeyHolder);
        int id = generatedKeyHolder.getKey().intValue();
        student.setStudentId(id);
        studentHasCoursesDao.addStudentCoursesList(student.getStudentId(), student.getCourseList());
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        Optional.of(namedParameterJdbcTemplate.update(STUDENT_UPDATE_SQL,
                new BeanPropertySqlParameterSource(student)))
                .filter(numRowsUpdated -> numRowsUpdated > 0)
                .orElseThrow(() -> new RuntimeException("Failed to update student in DB"));
        studentHasCoursesDao.addStudentCoursesList(student.getStudentId(), student.getCourseList());
    }

    @Override
    public void deleteStudent(Integer studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource("studentId", studentId);
        Optional.of(namedParameterJdbcTemplate.update(STUDENT_DELETE_SQL, parameters))
                .filter(numRowsUpdated -> numRowsUpdated > 0)
                .orElseThrow(() -> new RuntimeException("Failed to delete student from DB"));
    }

    @Override
    public List<Student> getStudentsList() {
        List<Student> studentList = namedParameterJdbcTemplate.query(LIST_STUDENTS_GET_SQL, studentMapper);
        studentList.forEach(student ->
                student.setCourseList(studentHasCoursesDao.getStudentCoursesList(student.getStudentId()))
        );
        return studentList;
    }

    @Override
    public Student getStudentById(Integer studentId) {
        SqlParameterSource parameters = new MapSqlParameterSource("studentId", studentId);
        Student student = DataAccessUtils.uniqueResult(namedParameterJdbcTemplate.query(STUDENT_GET_SQL,
                parameters, studentMapper));
        student.setCourseList(studentHasCoursesDao.getStudentCoursesList(student.getStudentId()));
        return student;
    }
}
