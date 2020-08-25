package com.epam.summer.courses.soap_app.endpoint;

import com.epam.summer.courses.service.CourseService;
import com.epam.summer.courses.soap_app.converters.CourseConverter;
import com.epam.summer.courses.soap_app.schemas.AddCourseRequest;
import com.epam.summer.courses.soap_app.schemas.AddCourseResponse;
import com.epam.summer.courses.soap_app.schemas.DeleteCourseRequest;
import com.epam.summer.courses.soap_app.schemas.DeleteCourseResponse;
import com.epam.summer.courses.soap_app.schemas.GetAllCoursesRequest;
import com.epam.summer.courses.soap_app.schemas.GetAllCoursesResponse;
import com.epam.summer.courses.soap_app.schemas.GetCourseByIdRequest;
import com.epam.summer.courses.soap_app.schemas.GetCourseByIdResponse;
import com.epam.summer.courses.soap_app.schemas.UpdateCourseRequest;
import com.epam.summer.courses.soap_app.schemas.UpdateCourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

@Profile(value = "soap")
@Endpoint
public class CourseEndpoint {

    private static final String NAMESPACE_URI = "http://courses.summer.epam.com/soap_app/schemas";

    private final CourseService courseService;

    @Autowired
    public CourseEndpoint(final CourseService courseService) {
        this.courseService = courseService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCoursesRequest")
    @ResponsePayload
    public GetAllCoursesResponse getAllStudents(@RequestPayload GetAllCoursesRequest request) {
        GetAllCoursesResponse response = new GetAllCoursesResponse();
        response.getCourses().addAll(courseService.getCoursesList()
                .stream().map(CourseConverter::convertToCourseType).collect(Collectors.toList()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCourseByIdRequest")
    @ResponsePayload
    public GetCourseByIdResponse getStudentById(@RequestPayload GetCourseByIdRequest request) {
        GetCourseByIdResponse response = new GetCourseByIdResponse();
        response.setCourse(CourseConverter.convertToCourseType(courseService.getCourseById(request.getId())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddCourseRequest")
    @ResponsePayload
    public AddCourseResponse addStudent(@RequestPayload AddCourseRequest request) {
        AddCourseResponse response = new AddCourseResponse();
        response.setCourse(CourseConverter.convertToCourseType(
                courseService.addCourse(CourseConverter.convertToCourse(request.getCourse()))));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateCourseRequest")
    @ResponsePayload
    public UpdateCourseResponse updateStudent(@RequestPayload UpdateCourseRequest request) {
        courseService.updateCourse(CourseConverter.convertToCourse(request.getCourse()));
        return new UpdateCourseResponse();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteCourseRequest")
    @ResponsePayload
    public DeleteCourseResponse deleteStudent(@RequestPayload DeleteCourseRequest request) {
        courseService.deleteCourse(request.getId());
        return new DeleteCourseResponse();
    }
}
