package com.coursemanagement.CourseManagement.Controller;

import com.coursemanagement.CourseManagement.DTO.EnrollmentDTO;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Service.EnrollmentService;
import com.coursemanagement.CourseManagement.Util.AuthUtil;
import com.coursemanagement.CourseManagement.Util.MessageUtil;
import com.coursemanagement.CourseManagement.Util.ResponseHandler;
import com.coursemanagement.CourseManagement.group.OnCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/addEnrollment")
    public ResponseEntity<ResponseHandler> addEnrollment(@RequestBody @Validated(OnCreate.class) EnrollmentDTO enrollmentDTO) {
        try {
            authUtil.ensureStudent();
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("enrollment.create.success"), HttpStatus.OK.value(), true, "Enrollment", enrollmentService.addEnrollment(enrollmentDTO));
            return ResponseEntity.ok(response);
        } catch (AccessDeniedException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("enrollment.create.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Enrollment", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getEnrollment")
    public ResponseEntity<ResponseHandler> getEnrollment() {
        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("enrollment.get.success"), HttpStatus.OK.value(), true, "Enrollment", enrollmentService.getEnrollment());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("enrollment.get.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Enrollment", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
