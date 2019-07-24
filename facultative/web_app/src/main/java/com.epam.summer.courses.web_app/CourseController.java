package com.epam.summer.courses.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Course controller.
 */
@Controller
public class CourseController {

    /**
     * Goto courses list page.
     *
     * @return view name
     */
    @GetMapping(value = "/courses")
    public final String departments(Model model) {
        return "courses";
    }

    /**
     * Goto courses page.
     *
     * @return view name
     */
    @GetMapping(value = "/course")
    public final String gotoAddDepartmentPage(Model model) {
        return "course";
    }
}
