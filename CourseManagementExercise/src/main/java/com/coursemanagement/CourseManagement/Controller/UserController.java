package com.coursemanagement.CourseManagement.Controller;

import com.coursemanagement.CourseManagement.DTO.UserDTO;
import com.coursemanagement.CourseManagement.Exception.BadRequestException;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Service.UserService;
import com.coursemanagement.CourseManagement.Util.MessageUtil;
import com.coursemanagement.CourseManagement.Util.ResponseHandler;
import com.coursemanagement.CourseManagement.group.OnCreate;
import com.coursemanagement.CourseManagement.group.OnLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUtil messageUtil;

    @PostMapping("/register")
    public ResponseEntity<ResponseHandler> addUser(@RequestBody @Validated(OnCreate.class) UserDTO userDTO) {
        try {
            ResponseHandler response = new ResponseHandler(messageUtil.getMessage("user.register.success"), HttpStatus.OK.value(), true, "User", userService.addUser(userDTO));
            return ResponseEntity.ok(response);
        }catch (BadRequestException e){
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "User", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }catch (Exception e ) {
            ResponseHandler response = new ResponseHandler(/*messageUtil.getMessage("user.register.fail")*/ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "User", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseHandler> login(@RequestBody @Validated(OnLogin.class) UserDTO userDTO) {
        try {
            String token = userService.login(userDTO);
            ResponseHandler response = new ResponseHandler(
                    messageUtil.getMessage("user.login.successful"),
                    HttpStatus.OK.value(),
                    true,
                    "User",
                    token
            );
            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            ResponseHandler response = new ResponseHandler(
                    messageUtil.getMessage("user.login.invalid.credentials"),
                    HttpStatus.UNAUTHORIZED.value(),
                    false,
                    "User",
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }catch (EntityNotFoundException e){
            ResponseHandler response = new ResponseHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value(), false, "User", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e) {
            ResponseHandler response = new ResponseHandler(
                    messageUtil.getMessage("user.login.fail"),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    false,
                    "User",
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
