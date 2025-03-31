package com.coursemanagement.CourseManagement.Controller;

import com.coursemanagement.CourseManagement.DTO.CourseDTO;
import com.coursemanagement.CourseManagement.DTO.PaginatedResult;
import com.coursemanagement.CourseManagement.Entity.Course;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Service.CourseService;
import com.coursemanagement.CourseManagement.Util.MessageUtil;
import com.coursemanagement.CourseManagement.Util.ResponseHandler;
import com.coursemanagement.CourseManagement.group.OnCreate;
import com.coursemanagement.CourseManagement.group.OnUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private MessageUtil messageUtil;

    @PostMapping("/addCourse")
    public ResponseEntity<ResponseHandler> addCourse(@RequestBody @Validated(OnCreate.class) CourseDTO courseDTO) throws AccessDeniedException {

        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("course.create.success"), HttpStatus.OK.value(), true, "Course", courseService.addCourse(courseDTO));
            return ResponseEntity.ok(response);
        }catch (AccessDeniedException e){
                ResponseHandler response=new ResponseHandler(e.getMessage() , HttpStatus.BAD_REQUEST.value(),false,"Course", null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("course.create.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.ok(response);
        }

    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseHandler> getCourses() throws AccessDeniedException {

        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("course.get.success"), HttpStatus.OK.value(), true, "Course", courseService.getCourses());
            return ResponseEntity.ok(response);
        }catch (AccessDeniedException e){
            ResponseHandler response=new ResponseHandler(e.getMessage() , HttpStatus.BAD_REQUEST.value(),false,"Course", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("course.get.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @GetMapping("/courseByInstructor/{id}")
    public ResponseEntity<ResponseHandler> courseByInstructor(
            @Valid @PathVariable String id) throws AccessDeniedException, EntityNotFoundException {

        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("course.get.success"), HttpStatus.OK.value(), true, "Course", courseService.courseByInstructor(id));
            return ResponseEntity.ok(response);
        } catch (AccessDeniedException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (EntityNotFoundException e){
            ResponseHandler response=new ResponseHandler(e.getMessage() , HttpStatus.BAD_REQUEST.value(),false,"Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("course.get.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/updateCourse")
    public ResponseEntity<ResponseHandler> updateCourse(@RequestBody @Validated(OnUpdate.class) CourseDTO courseDTO) throws AccessDeniedException, EntityNotFoundException {

        try {
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("course.update.success"), HttpStatus.OK.value(), true, "Course", courseService.updateCourse(courseDTO));
            return ResponseEntity.ok(response);
        } catch (AccessDeniedException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (EntityNotFoundException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("course.update.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.ok(response);
        }

    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<ResponseHandler> deleteCourse(@Valid @PathVariable String id) throws AccessDeniedException, EntityNotFoundException {

        try {
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("course.delete.success"), HttpStatus.OK.value(), true, "Course", courseService.deleteCourse(id));
            return ResponseEntity.ok(response);
        }catch (AccessDeniedException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (EntityNotFoundException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Course", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler( messageUtil.getMessage("course.delete.fail"), HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Course", null);
            return ResponseEntity.ok(response);
        }

    }




}
