package com.SocialMedia.SocialMedia.Controller;

import com.SocialMedia.SocialMedia.DTO.UserDTO;
import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.Exceptions.EntityAlreadyExistsException;
import com.SocialMedia.SocialMedia.Groups.*;
import com.SocialMedia.SocialMedia.Service.UserService;
import com.SocialMedia.SocialMedia.Util.MessageUtil;
import com.SocialMedia.SocialMedia.Util.ResponseHandler;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private MessageUtil messageUtil;


    @PostMapping("/register")
    public ResponseEntity<ResponseHandler> registerUser(@RequestBody @Validated(OnCreate.class) UserDTO userDTO){
        try{
            UserDTO user=userService.registerUser(userDTO);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("user.register.success"), HttpStatus.OK.value(), true, "User",user);
            return ResponseEntity.ok(response);
        } catch (EntityAlreadyExistsException ex){
            ResponseHandler response= new ResponseHandler(ex.getMessage(), HttpStatus.CONFLICT.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseHandler> login(@RequestBody @Validated(OnLogin.class) UserDTO userDTO){
        try{
            String token = userService.login(userDTO);
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("user.login.success"), HttpStatus.OK.value(), true, "User", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/password")
    public ResponseEntity<ResponseHandler> updatePassword(@RequestBody @Validated(OnUpdatePassword.class) UserDTO userDTO){
        try{

            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("user.update.success"), HttpStatus.OK.value(), true, "User",userService.update(userDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/email")
    public ResponseEntity<ResponseHandler> updateEmail(@RequestBody @Validated(OnUpdateEmail.class) UserDTO userDTO){
        try{
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("user.update.success"), HttpStatus.OK.value(), true, "User",userService.update(userDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/bio")
    public ResponseEntity<ResponseHandler> updatebio(@RequestBody @Validated(OnUpdateBio.class) UserDTO userDTO){
        try{
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("user.update.success"), HttpStatus.OK.value(), true, "User",userService.update(userDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update/pfp")
    public ResponseEntity<ResponseHandler> updatepfp(@RequestBody @Validated(OnUpdatePfp.class) UserDTO userDTO){
        try{
            ResponseHandler response= new ResponseHandler(messageUtil.getMessage("user.update.success"), HttpStatus.OK.value(), true, "User",userService.update(userDTO));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseHandler response= new ResponseHandler(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }




}
