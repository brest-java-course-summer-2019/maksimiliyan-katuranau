package com.epam.summer.courses.web_app.validators;

import com.epam.summer.courses.model.Course;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CourseValidator implements Validator {

    public static final int COURSE_NAME_MAX_SIZE = 255;


    @Override
    public boolean supports(Class<?> aClass) {
        return Course.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "courseName", "courseName.empty");
        Course course = (Course) obj;

        if (StringUtils.hasLength(course.getCourseName())
                && course.getCourseName().length() > COURSE_NAME_MAX_SIZE) {
            errors.rejectValue("courseName", "courseName.maxSize255");
        }
    }
}
