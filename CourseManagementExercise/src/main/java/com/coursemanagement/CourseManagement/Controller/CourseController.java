package com.coursemanagement.CourseManagement.Controller;

import com.coursemanagement.CourseManagement.DTO.CourseDTO;
import com.coursemanagement.CourseManagement.DTO.PaginatedResult;
import com.coursemanagement.CourseManagement.Entity.Course;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Repository.CourseRepo;
import com.coursemanagement.CourseManagement.Service.CourseService;
import com.coursemanagement.CourseManagement.Util.AuthUtil;
import com.coursemanagement.CourseManagement.Util.MessageUtil;
import com.coursemanagement.CourseManagement.Util.ResponseHandler;
import com.coursemanagement.CourseManagement.group.OnCreate;
import com.coursemanagement.CourseManagement.group.OnUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private AuthUtil authUtil;

    @PostMapping("/addCourse")
    public ResponseEntity<ResponseHandler> addCourse(@RequestBody @Validated(OnCreate.class) CourseDTO courseDTO)  {

        try {
            authUtil.ensureInstructor();
            Course course =courseService.addCourse(courseDTO);
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("course.create.success"), HttpStatus.OK.value(), true, "Course", course);
            return ResponseEntity.ok(response);
        }catch (AccessDeniedException e){
                ResponseHandler response=new ResponseHandler(e.getMessage() , HttpStatus.BAD_REQUEST.value(),false,"Course", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("course.create.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseHandler> getCourses() {

        try {
            List<Course> courses=courseService.getCourses();
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("course.get.success"), HttpStatus.OK.value(), true, "Course", courses);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("course.get.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping("/courseByInstructor/{id}")
    public ResponseEntity<ResponseHandler> courseByInstructor(
            @Valid @PathVariable String id) throws EntityNotFoundException {

        try {
            List<Course> course= courseService.courseByInstructor(id);
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("course.get.success"), HttpStatus.OK.value(), true, "Course", course);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e){
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("course.get.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/updateCourse")
    public ResponseEntity<ResponseHandler> updateCourse(@RequestBody @Validated(OnUpdate.class) CourseDTO courseDTO) throws EntityNotFoundException {

        try {
            authUtil.ensureInstructor();
            Course course=courseService.updateCourse(courseDTO);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("course.update.success"), HttpStatus.OK.value(), true, "Course", course );
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response = new ResponseHandler("course.update.fail", HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<ResponseHandler> deleteCourse(@Valid @PathVariable String id) throws AccessDeniedException, EntityNotFoundException {

        try {
            authUtil.ensureInstructor();
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("course.delete.success"), HttpStatus.OK.value(), true, "Course", courseService.deleteCourse(id));
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response = new ResponseHandler("course.delete.fail", HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }




}
