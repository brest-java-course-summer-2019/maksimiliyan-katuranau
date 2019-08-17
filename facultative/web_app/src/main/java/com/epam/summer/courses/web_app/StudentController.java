package com.epam.summer.courses.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Student controller.
 */
@Controller
public class StudentController {

    /**
     * Goto students page.
     *
     * @return view name
     */
    @GetMapping(value = "/students")
    public final String students() {
        return "students";
    }

    /**
     * Goto student page.
     *
     * @return view name
     */
    @GetMapping(value = "/student")
    public final String student() {
        return "student";
    }
}
