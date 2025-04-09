package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.DTO.PostDTO;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
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

    //works
    @PostMapping("/add")
    public ResponseEntity<ResponseHandler> addPost(@RequestBody @Validated(OnCreate.class) PostDTO postDTO){
        try{
            PostDTO post =postService.addpost(postDTO);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.add.success"), HttpStatus.OK.value(), true, "Post",post);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Post",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @GetMapping("/getPost")
    public ResponseEntity<ResponseHandler> getPost(@RequestParam(defaultValue = "3") int pageSize,
                                                   @RequestParam(required = false) String lastEvaluatedKey){
        try{
            PaginatedResult<Post> posts= postService.getUserPosts(pageSize, lastEvaluatedKey);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.get.success"), HttpStatus.OK.value(), true, "Post",posts);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Post",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @PutMapping("/update")
    public ResponseEntity<ResponseHandler> updatePost(@RequestBody @Validated(OnUpdate.class) PostDTO postDTO){
        try{
            PostDTO post= postService.updatePost(postDTO);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.update.success"), HttpStatus.OK.value(), true, "Post",post);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Post", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Post",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseHandler> deletePost(@PathVariable @Valid String id){
        try{
            PostDTO post = postService.deletePost(id);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.delete.success"), HttpStatus.OK.value(), true, "Post",post);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Post", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Post",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //works
    @GetMapping("/byUsername/{userName}")
    public ResponseEntity<ResponseHandler> getPostByUserName(@PathVariable @Valid String userName, @RequestParam(defaultValue = "3") int pageSize,
                                                             @RequestParam(required = false) String lastEvaluatedKey){
        try{
            PaginatedResult<Post> posts= postService.getPostsByUserName(userName, pageSize, lastEvaluatedKey);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("post.get.success"), HttpStatus.OK.value(), true, "Post",posts);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Post",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
