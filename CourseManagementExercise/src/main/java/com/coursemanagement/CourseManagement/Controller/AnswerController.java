package com.coursemanagement.CourseManagement.Controller;

import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.DTO.PaginatedResult;
import com.coursemanagement.CourseManagement.Entity.Answer;
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

            try {
                Answer answer= answerService.createAnswer(answerDTO);
                ResponseHandler response = new ResponseHandler(messageUtil.getMessage("answer.create.success"), HttpStatus.OK.value(), true, "Answer", answer);
                return ResponseEntity.ok(response);
            }catch (EntityNotFoundException e){
                ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Answer", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } catch (Exception e) {
                ResponseHandler response=new ResponseHandler("answer.create.fail" , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
    }

    @GetMapping("/get/{discussionId}")
    public ResponseEntity<ResponseHandler> getAnswer(@PathVariable @Valid String discussionId, @RequestParam(defaultValue = "3") int limit,
                                                     @RequestParam(required = false) String lastEvaluatedKey) {

        try {
            PaginatedResult<Answer> answer= answerService.getAnswer(discussionId,limit,lastEvaluatedKey);
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("answer.get.success"), HttpStatus.OK.value(), true, "Answer", answer);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e){
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Answer", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler("answer.get.fail" , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseHandler> updateAnswer(@Validated(OnUpdate.class) @RequestBody AnswerDTO answerDTO) {

        try{
            Answer answer=answerService.updateAnswer(answerDTO);
            ResponseHandler response=new ResponseHandler(messageUtil.getMessage("answer.update.success") , HttpStatus.OK.value(),true, "Answer", answer);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Answer", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler("answer.update.fail" , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<ResponseHandler> deleteAnswer(@Valid @RequestParam String answerId) {

        try {
            Answer answer= answerService.deleteAnswer(answerId);
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("answer.delete.success"), HttpStatus.OK.value(), true, "Answer", answer);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException e){
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "Answer", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response=new ResponseHandler("answer.delete.fail" , HttpStatus.INTERNAL_SERVER_ERROR.value(),false,"Answer", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

}
