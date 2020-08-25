package com.epam.summer.courses.soap_app.endpoint;

import com.epam.summer.courses.service.StudentService;
import com.epam.summer.courses.soap_app.converters.StudentConverter;
import com.epam.summer.courses.soap_app.schemas.AddStudentRequest;
import com.epam.summer.courses.soap_app.schemas.AddStudentResponse;
import com.epam.summer.courses.soap_app.schemas.DeleteStudentRequest;
import com.epam.summer.courses.soap_app.schemas.DeleteStudentResponse;
import com.epam.summer.courses.soap_app.schemas.GetAllStudentsRequest;
import com.epam.summer.courses.soap_app.schemas.GetAllStudentsResponse;
import com.epam.summer.courses.soap_app.schemas.GetStudentByIdRequest;
import com.epam.summer.courses.soap_app.schemas.GetStudentByIdResponse;
import com.epam.summer.courses.soap_app.schemas.UpdateStudentRequest;
import com.epam.summer.courses.soap_app.schemas.UpdateStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.stream.Collectors;

@Profile(value = "soap")
@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://courses.summer.epam.com/soap_app/schemas";

    private final StudentService studentService;

    @Autowired
    public StudentEndpoint(final StudentService studentService) {
        this.studentService = studentService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllStudentsRequest")
    @ResponsePayload
    public GetAllStudentsResponse getAllStudents(@RequestPayload GetAllStudentsRequest request) {
        GetAllStudentsResponse response = new GetAllStudentsResponse();
        response.getStudents().addAll(studentService.getStudentsList()
                .stream().map(StudentConverter::convertToStudentType).collect(Collectors.toList()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStudentByIdRequest")
    @ResponsePayload
    public GetStudentByIdResponse getStudentById(@RequestPayload GetStudentByIdRequest request) {
        GetStudentByIdResponse response = new GetStudentByIdResponse();
        response.setStudent(StudentConverter.convertToStudentType(studentService.getStudentById(request.getId())));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddStudentRequest")
    @ResponsePayload
    public AddStudentResponse addStudent(@RequestPayload AddStudentRequest request) {
        AddStudentResponse response = new AddStudentResponse();
        response.setStudent(StudentConverter.convertToStudentType(
                studentService.addStudent(StudentConverter.convertToStudent(request.getStudent()))));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateStudentRequest")
    @ResponsePayload
    public UpdateStudentResponse updateStudent(@RequestPayload UpdateStudentRequest request) {
        studentService.updateStudent(StudentConverter.convertToStudent(request.getStudent()));
        return new UpdateStudentResponse();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteStudentRequest")
    @ResponsePayload
    public DeleteStudentResponse deleteStudent(@RequestPayload DeleteStudentRequest request) {
        studentService.deleteStudent(request.getId());
        return new DeleteStudentResponse();
    }
}
