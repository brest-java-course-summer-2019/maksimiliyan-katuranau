package com.epam.summer.courses.web_app;

import com.epam.summer.courses.model.Course;
import com.epam.summer.courses.service.CourseService;
import com.epam.summer.courses.web_app.validators.CourseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Course controller.
 */
@Controller
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseValidator courseValidator;

    /**
     * Goto courses list page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/courses")
    public final String departments(Model model) {
        LOGGER.debug("get list courses ({})", model);
        model.addAttribute("courses", courseService.getCoursesList());
        return "courses";
    }

    /**
     * Goto courses page.
     *
     * @param courseId course id to get
     * @param model    model
     * @return view name
     */
    @GetMapping(value = "/course/{courseId}")
    public final String gotoEditCoursePage(@PathVariable Integer courseId, Model model) {
        LOGGER.debug("gotoEditCoursePage ({}, {})", courseId, model);
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "course";
    }

    /**
     * Goto courses page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/course")
    public final String gotoAddCoursePage(Model model) {
        LOGGER.debug("gotoAddCoursePage ({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("course", new Course());
        return "course";
    }

    /**
     * Persist new course into persistence storage.
     *
     * @param course new course with filled data.
     * @return view name
     */
    @PostMapping(value = "/course")
    public String addCourse(@Valid Course course, BindingResult result) {
        LOGGER.debug("addCourse({}, {})", course, result);
        courseValidator.validate(course, result);
        if (result.hasErrors()) {
            return "course";
        } else {
            this.courseService.addCourse(course);
            return "redirect:/courses";
        }
    }

    /**
     * Update course into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/course/{courseId}")
    public String updateCourse(@PathVariable Integer courseId, @Valid Course course, BindingResult result) {
        LOGGER.debug("updateCourse({}, {}, {})", courseId, course, result);
        courseValidator.validate(course, result);
        if (result.hasErrors()) {
            return "course";
        } else {
            this.courseService.updateCourse(course);
            return "redirect:/courses";
        }
    }

    /**
     * Delete course by id.
     *
     * @return view name
     */
    @GetMapping(value = "/course/{courseId}/delete")
    public final String deleteCourseById(@PathVariable Integer courseId, Model model) {
        LOGGER.debug("deleteCourseById({},{})", courseId, model);
        courseService.deleteCourse(courseId);
        return "redirect:/courses";
    }
}
