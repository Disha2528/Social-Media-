package com.coursemanagement.CourseManagement.Controller;

import com.coursemanagement.CourseManagement.DTO.DiscussionDTO;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Service.DiscussionService;
import com.coursemanagement.CourseManagement.Util.MessageUtil;
import com.coursemanagement.CourseManagement.Util.ResponseHandler;
import com.coursemanagement.CourseManagement.group.OnCreate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private MessageUtil messageUtil;

    @PostMapping("/create")
    public ResponseEntity<ResponseHandler> createDiscussion(@RequestBody @Validated(OnCreate.class) DiscussionDTO discussionDTO){

        try {
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("discussion.create.success"), HttpStatus.OK.value(), true, "Discussion", discussionService.createDiscussion(discussionDTO));
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("discussion.create.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Discussion", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getDiscussions")
    public ResponseEntity<ResponseHandler> getDiscussions(){

        try {
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("discussion.get.success"), HttpStatus.OK.value(), true, "Discussion", discussionService.getDiscussions());
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("discussion.get.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Discussion", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/getDiscussion/{id}")
    public ResponseEntity<ResponseHandler> getDiscussionsByCourseId(@Valid @PathVariable String id) throws EntityNotFoundException {

        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("discussion.get.success"), HttpStatus.OK.value(), true, "Discussion", discussionService.getDiscussionsByCourseId(id));
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e){
            ResponseHandler response=new ResponseHandler(e.getMessage() , HttpStatus.BAD_REQUEST.value(),false,"Discussion", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("discussion.get.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Discussion", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
