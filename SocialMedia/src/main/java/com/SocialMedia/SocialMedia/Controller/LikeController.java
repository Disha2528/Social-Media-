package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.Entities.Like;
import com.SocialMedia.SocialMedia.Service.LikeService;
import com.SocialMedia.SocialMedia.Util.MessageUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.SocialMedia.SocialMedia.Util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private MessageUtil messageUtil;

    //works
    @PostMapping("/addLike/{postId}")
    public ResponseEntity<ResponseHandler> addLike(@PathVariable @Valid String postId){

        try{
            Like like= likeService.addLike(postId);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("like.add.success"), HttpStatus.OK.value(), true, "Like",like);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Like",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @DeleteMapping("/removeLike/{postId}")
    public ResponseEntity<ResponseHandler> unLike(@PathVariable @Valid String postId){
        try{
            Like like= likeService.removeLike(postId);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("like.remove.success"), HttpStatus.OK.value(), true, "Like",like);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Like",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @GetMapping("/getLikes/{postId}")
    public ResponseEntity<ResponseHandler> getLikes(@PathVariable @Valid String postId, @RequestParam(defaultValue = "3") int limit, @RequestParam(required = false) String lastEvaluatedKey){

        try{
            PaginatedResult<Like> likes= likeService.getLikes(postId,limit,lastEvaluatedKey);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("like.get.success"), HttpStatus.OK.value(), true, "Like",likes);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Like",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
