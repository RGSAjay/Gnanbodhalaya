package com.gnanbodhalaya.onlinelearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gnanbodhalaya.onlinelearning.model.Course;
import com.gnanbodhalaya.onlinelearning.service.CourseService;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/courses"; // This will be a Thymeleaf template for viewing courses
    }

    @GetMapping("/create")
    public String showCourseForm() {
        return "admin/create-course"; // This will be a Thymeleaf template for course creation
    }

    @PostMapping("/create")
    public String createCourse(Course course) {
        courseService.save(course);
        return "redirect:/admin/courses"; // After creation, redirect back to the course list
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id).orElse(null));
        return "admin/edit-course"; // This will be a Thymeleaf template for course editing
    }

    @PostMapping("/edit")
    public String updateCourse(Course course) {
        courseService.updateCourse(course);
        return "redirect:/admin/courses"; // After update, redirect back to the course list
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        courseService.deleteCourse(id);
        return "redirect:/admin/courses"; // After deletion, redirect back to the course list
    }
}
