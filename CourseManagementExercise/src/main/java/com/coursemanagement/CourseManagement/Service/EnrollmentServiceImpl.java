package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.EnrollmentDTO;
import com.coursemanagement.CourseManagement.Entity.Enrollment;
import com.coursemanagement.CourseManagement.Repository.EnrollmentRepo;
import com.coursemanagement.CourseManagement.Util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private AuthUtil authUtil;

    //create
    @Override
    public Enrollment addEnrollment(EnrollmentDTO enrollmentDTO) throws AccessDeniedException {
        authUtil.ensureStudent();
        return enrollmentRepo.addEnrollment(enrollmentDTO);
    }

    //retrieve
    @Override
    public List<Enrollment> getEnrollment(){
        return enrollmentRepo.enrolledCourses(authUtil.currentUser());
    }

}
