package com.epam.summer.courses.web_app.client;

import com.epam.summer.courses.model.Student;
import com.epam.summer.courses.service.StudentService;
import com.epam.summer.courses.soap_app.converters.StudentConverter;
import com.epam.summer.courses.soap_app.schemas.AddStudentRequest;
import com.epam.summer.courses.soap_app.schemas.AddStudentResponse;
import com.epam.summer.courses.soap_app.schemas.DeleteStudentRequest;
import com.epam.summer.courses.soap_app.schemas.GetAllStudentsRequest;
import com.epam.summer.courses.soap_app.schemas.GetAllStudentsResponse;
import com.epam.summer.courses.soap_app.schemas.GetStudentByIdRequest;
import com.epam.summer.courses.soap_app.schemas.GetStudentByIdResponse;
import com.epam.summer.courses.soap_app.schemas.UpdateStudentRequest;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.List;
import java.util.stream.Collectors;

public class StudentSoapClient extends WebServiceGatewaySupport implements StudentService {

    public Student addStudent(final Student student) {
        AddStudentRequest request = new AddStudentRequest();
        request.setStudent(StudentConverter.convertToStudentType(student));
        AddStudentResponse response = (AddStudentResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return StudentConverter.convertToStudent(response.getStudent());
    }

    public void updateStudent(final Student student) {
        UpdateStudentRequest request = new UpdateStudentRequest();
        request.setStudent(StudentConverter.convertToStudentType(student));
        getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public void deleteStudent(final Integer studentId) {
        DeleteStudentRequest request = new DeleteStudentRequest();
        request.setId(studentId);
        getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public List<Student> getStudentsList() {
        GetAllStudentsResponse response = (GetAllStudentsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(new GetAllStudentsRequest());
        return response.getStudents().stream().map(StudentConverter::convertToStudent).collect(Collectors.toList());
    }

    public Student getStudentById(final Integer studentId) {
        GetStudentByIdRequest request = new GetStudentByIdRequest();
        request.setId(studentId);
        GetStudentByIdResponse response = (GetStudentByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return StudentConverter.convertToStudent(response.getStudent());
    }
}
