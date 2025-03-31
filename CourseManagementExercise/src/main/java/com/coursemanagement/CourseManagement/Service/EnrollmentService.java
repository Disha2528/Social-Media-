package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.EnrollmentDTO;
import com.coursemanagement.CourseManagement.Entity.Enrollment;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface EnrollmentService {
    //create
    public Enrollment addEnrollment(EnrollmentDTO enrollmentDTO) throws AccessDeniedException;

    //retrieve
    public List<Enrollment> getEnrollment() ;
}
