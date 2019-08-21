package com.epam.summer.courses.web_app;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.model.Student;
import com.epam.summer.courses.service.CourseService;
import com.epam.summer.courses.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Student controller.
 */
@Controller
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    /**
     * Goto students page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/students")
    public final String students(Model model) {
        LOGGER.debug("get list students ({})", model);
        model.addAttribute("students", studentService.getStudentsList());
        return "students";
    }

    /**
     * Goto student page.
     *
     * @param studentId the student id
     * @param model     the model
     * @return view name
     */
    @GetMapping(value = "/student/{studentId}")
    public final String gotoEditStudentPage(@PathVariable Integer studentId, Model model) {
        LOGGER.debug("gotoEditStudentPage ({}, {})", studentId, model);
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("courses", courseService.getCoursesList());
        return "student";
    }


    /**
     * Goto add student page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/student")
    public final String gotoAddStudentPage(Model model) {
        LOGGER.debug("gotoAddStudentPage ({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.getCoursesList());
        return "student";
    }

    /**
     * Persist new student into persistence storage.
     *
     * @param student new student.
     * @return view name
     */
    @PostMapping(value = "/student")
    public String addStudent(@ModelAttribute Student student,
                             @RequestParam(value = "coursesList", required = false) final int[] coursesList) {
        LOGGER.debug("addStudent({})", student);
        setCourseList(student, coursesList);
        studentService.addStudent(student);
        return "redirect:/students";
    }

    /**
     * Update student into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/student/{studentId}")
    public String updateStudent(@PathVariable Integer studentId, @ModelAttribute Student student,
                                @RequestParam(value = "coursesList", required = false) final int[] coursesList) {
        LOGGER.debug("updateStudent({}, {})", studentId, student);
        setCourseList(student, coursesList);
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    /**
     * Delete course by id.
     *
     * @return view name
     */
    @GetMapping(value = "/student/{studentId}/delete")
    public final String deleteStudent(@PathVariable Integer studentId, Model model) {
        LOGGER.debug("deleteStudent({},{})", studentId, model);
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    private void setCourseList(Student student, final int[] coursesList) {
        if (coursesList != null) {
            List<Course> courses = new ArrayList<>();
            for (int i : coursesList) {
                courses.add(new Course(i));
            }
            student.setCourseList(courses);
        }
    }
}
