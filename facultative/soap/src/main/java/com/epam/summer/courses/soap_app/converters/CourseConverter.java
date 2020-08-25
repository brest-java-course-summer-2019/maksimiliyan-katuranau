package com.epam.summer.courses.soap_app.converters;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.soap_app.schemas.CourseType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CourseConverter {

    public static CourseType convertToCourseType(Course course) {
        CourseType courseType = new CourseType();
        courseType.setCourseId(course.getCourseId());
        courseType.setCourseName(course.getCourseName());
        courseType.setDate(convertToXMLGregorianCalendar());
        courseType.setTeacher(course.getTeacher());
        courseType.setNumberOfStudents(course.getNumberOfStudents());
        return courseType;
    }

    public static Course convertToCourse(CourseType courseType) {
        Course course = new Course();
        course.setCourseId(courseType.getCourseId());
        course.setCourseName(courseType.getCourseName());
        course.setDate(convertToJavaDate(courseType.getDate()));
        course.setTeacher(courseType.getTeacher());
        course.setNumberOfStudents(courseType.getNumberOfStudents());
        return course;
    }

    private static XMLGregorianCalendar convertToXMLGregorianCalendar() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
             return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Date convertToJavaDate(XMLGregorianCalendar xmlGregorianCalendar) {
        if(xmlGregorianCalendar == null) {
            return null;
        }
        return xmlGregorianCalendar.toGregorianCalendar().getTime();
    }
}
