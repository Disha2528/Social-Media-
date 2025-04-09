package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.DTO.FriendDTO;
import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Service.FriendService;
import com.SocialMedia.SocialMedia.Util.MessageUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.SocialMedia.SocialMedia.Util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private MessageUtil messageUtil;


    @GetMapping("/view")
    public ResponseEntity<ResponseHandler> viewFriends(@RequestParam(defaultValue = "3") int pageSize, @RequestParam(required = false) String lastEvaluatedKey){
        try{
            PaginatedResult<Friend> friends= friendService.viewFriends(pageSize, lastEvaluatedKey);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("friend.get.success"), HttpStatus.OK.value(), true, "Friend",friends);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.OK.value(), true, "Friend",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/removeFriend/{friendName}")
    public ResponseEntity<ResponseHandler> removeFriend(@PathVariable @Valid String friendName){
        try{
            FriendDTO friend= friendService.unfriend(friendName);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("friend.delete.success"), HttpStatus.OK.value(), true, "Friend",friend);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            ResponseHandler response = new ResponseHandler(ex.getMessage(), HttpStatus.NOT_FOUND.value(), false, "Friend", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.OK.value(), true, "Friend",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
