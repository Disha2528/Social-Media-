package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.DTO.PostDTO;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Groups.OnUpdate;
import com.SocialMedia.SocialMedia.Service.PostService;
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
@RequestMapping("/post")
public class PostController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private PostService postService;

    //create

    @PostMapping("/add")
    public ResponseEntity<ResponseHandler> addPost(@RequestBody @Validated(OnCreate.class) PostDTO postDTO){
        try{
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.add.success"), HttpStatus.OK.value(), true, "Post",postService.addpost(postDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //retrieve

    @GetMapping("/getPost") // add pagination
    public ResponseEntity<ResponseHandler> getPost(@RequestParam(defaultValue = "3") int pageSize,
                                                   @RequestParam(required = false) String lastEvaluatedKey){
        try{
            PaginatedResult<Post> posts= postService.getUserPosts(pageSize, lastEvaluatedKey);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.get.success"), HttpStatus.OK.value(), true, "Post",posts);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //getPosts of a user who is friend --> need to implement


    //update

    @PutMapping("/update")
    public ResponseEntity<ResponseHandler> updatePost(@RequestBody @Validated(OnUpdate.class) PostDTO postDTO){
        try{
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.update.success"), HttpStatus.OK.value(), true, "Post",postService.updatePost(postDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //delete

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseHandler> deletePost(@PathVariable @Valid String id){
        try{
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.delete.success"), HttpStatus.OK.value(), true, "Post",postService.deletePost(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
