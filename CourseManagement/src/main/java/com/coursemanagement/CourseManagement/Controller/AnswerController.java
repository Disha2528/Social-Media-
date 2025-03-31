package com.coursemanagement.CourseManagement.Controller;

import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Service.AnswerService;
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

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private MessageUtil messageUtil;

    @PostMapping("/create")
    public ResponseEntity<ResponseHandler> createAnswer(@Validated(OnCreate.class) @RequestBody AnswerDTO answerDTO){

            try{
                ResponseHandler response=new ResponseHandler(messageUtil.getMessage("answer.create.success") , HttpStatus.OK.value(),true, "Answer", answerService.createAnswer(answerDTO));
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                ResponseHandler response=new ResponseHandler(messageUtil.getMessage("answer.create.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
                return ResponseEntity.ok(response);
            }
    }

    @GetMapping("/get/{discussionId}")
    public ResponseEntity<ResponseHandler> getAnswer(@PathVariable @Valid String discussionId)throws EntityNotFoundException {

        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("answer.get.success"), HttpStatus.OK.value(), true, "Answer", answerService.getAnswer(discussionId));
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e){
            ResponseHandler response=new ResponseHandler(e.getMessage() , HttpStatus.BAD_REQUEST.value(),false,"Discussion", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("answer.get.fail"), HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
            return ResponseEntity.ok(response);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseHandler> updateAnswer(@Validated(OnUpdate.class) @RequestBody AnswerDTO answerDTO) throws EntityNotFoundException{

        try{
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("answer.update.success") , HttpStatus.OK.value(),true, "Answer", answerService.updateAnswer(answerDTO));
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Discussion", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("answer.update.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
            return ResponseEntity.ok(response);
        }

    }

    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<ResponseHandler> deleteAnswer(@Valid @RequestParam String answerId) throws EntityNotFoundException {

        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("answer.delete.success"), HttpStatus.OK.value(), true, "Answer", answerService.deleteAnswer(answerId));
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e){
            ResponseHandler response=new ResponseHandler(e.getMessage() , HttpStatus.BAD_REQUEST.value(),false,"Discussion", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("answer.delete.fail") , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
            return ResponseEntity.ok(response);
        }

    }

}
