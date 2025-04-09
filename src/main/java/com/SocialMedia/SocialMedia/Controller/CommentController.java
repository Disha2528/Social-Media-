package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.DTO.CommentDTO;
import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Groups.OnUpdate;
import com.SocialMedia.SocialMedia.Service.CommentService;
import com.SocialMedia.SocialMedia.Util.MessageUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.SocialMedia.SocialMedia.Util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageUtil messageUtil;

    //works
    @PostMapping("/addComment")
    public ResponseEntity<ResponseHandler> addComment(@RequestBody @Validated(OnCreate.class) CommentDTO commentDTO) {

        try {
            CommentDTO comment = commentService.addComment(commentDTO);
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("comment.add.success"), HttpStatus.OK.value(), true, "Comment", comment);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException ex) {
            ResponseHandler response= new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Comment",null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Comment",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<ResponseHandler> deleteComment(@Valid @PathVariable String commentId){

        try{
            CommentDTO comment = commentService.removeComment(commentId);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("comment.delete.success"), HttpStatus.OK.value(), true, "Comment",comment);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Comment", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Comment",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/viewComments/{postId}")
    public ResponseEntity<ResponseHandler> viewComments(@Valid @PathVariable String postId, @RequestParam(defaultValue = "5") int limit, @RequestParam(required = false) String lastEvaluatedKey){

        try{
            PaginatedResult<CommentDTO> comment = commentService.getComments(postId,limit,lastEvaluatedKey);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("comment.get.success"), HttpStatus.OK.value(), true, "Comment",comment);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Comment", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Comment",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/updateComment")
    public ResponseEntity<ResponseHandler> updateComment(@RequestBody @Validated(OnUpdate.class)CommentDTO commentDTO){

        try{
            CommentDTO comment = commentService.updateComment(commentDTO);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("comment.update.success"), HttpStatus.OK.value(), true, "Comment",comment);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Comment", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Comment",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
