package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.CourseDTO;
import com.coursemanagement.CourseManagement.DTO.PaginatedResult;
import com.coursemanagement.CourseManagement.Entity.Course;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface CourseService {


    //create
    public Course addCourse(CourseDTO courseDTO) throws AccessDeniedException;

    // Get all courses (for logged-in users)
    public List<Course> getCourses() throws AccessDeniedException;

    // Get courses by instructor
    public List<Course> courseByInstructor(String instructorId) throws AccessDeniedException, EntityNotFoundException;

    // Update course
    public Course updateCourse(CourseDTO courseDTO) throws AccessDeniedException, EntityNotFoundException;

    // Delete course
    public Course deleteCourse(String id) throws AccessDeniedException, EntityNotFoundException;
}
