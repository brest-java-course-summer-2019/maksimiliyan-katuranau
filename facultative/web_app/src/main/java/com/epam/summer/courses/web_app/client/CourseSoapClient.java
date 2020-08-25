package com.epam.summer.courses.web_app.client;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.service.CourseService;
import com.epam.summer.courses.soap_app.converters.CourseConverter;
import com.epam.summer.courses.soap_app.schemas.AddCourseRequest;
import com.epam.summer.courses.soap_app.schemas.AddCourseResponse;
import com.epam.summer.courses.soap_app.schemas.DeleteCourseRequest;
import com.epam.summer.courses.soap_app.schemas.GetAllCoursesRequest;
import com.epam.summer.courses.soap_app.schemas.GetAllCoursesResponse;
import com.epam.summer.courses.soap_app.schemas.GetCourseByIdRequest;
import com.epam.summer.courses.soap_app.schemas.GetCourseByIdResponse;
import com.epam.summer.courses.soap_app.schemas.UpdateCourseRequest;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;
import java.util.stream.Collectors;

public class CourseSoapClient extends WebServiceGatewaySupport implements CourseService {

    public Course addCourse(final Course course) {
        AddCourseRequest request = new AddCourseRequest();
        request.setCourse(CourseConverter.convertToCourseType(course));
        AddCourseResponse response = (AddCourseResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return CourseConverter.convertToCourse(response.getCourse());
    }

    public void updateCourse(final Course course) {
        UpdateCourseRequest request = new UpdateCourseRequest();
        System.out.println("course: " + course);
        request.setCourse(CourseConverter.convertToCourseType(course));
        getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public void deleteCourse(final Integer courseId) {
        DeleteCourseRequest request = new DeleteCourseRequest();
        request.setId(courseId);
        getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public List<Course> getCoursesList() {
        GetAllCoursesResponse response = (GetAllCoursesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(new GetAllCoursesRequest());
        return response.getCourses().stream().map(CourseConverter::convertToCourse).collect(Collectors.toList());
    }

    public Course getCourseById(final Integer courseId) {
        GetCourseByIdRequest request = new GetCourseByIdRequest();
        request.setId(courseId);
        GetCourseByIdResponse response = (GetCourseByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return CourseConverter.convertToCourse(response.getCourse());
    }
}
