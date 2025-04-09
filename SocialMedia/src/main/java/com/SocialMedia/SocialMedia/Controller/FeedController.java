package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Service.FeedServiceImpl;
import com.SocialMedia.SocialMedia.Util.MessageUtil;
import com.SocialMedia.SocialMedia.Util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedServiceImpl feedService;

    @Autowired
    private MessageUtil messageUtil;


    @GetMapping("/friends/posts")
    public ResponseEntity<ResponseHandler> getFriendsPosts(@RequestParam(defaultValue = "5") int limitPerFriend) {
        try {
            List<Post> posts = feedService.getAllFriendsPosts(limitPerFriend);
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("feed.fetch.success"), HttpStatus.OK.value(), true, "Post", posts);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Post", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
