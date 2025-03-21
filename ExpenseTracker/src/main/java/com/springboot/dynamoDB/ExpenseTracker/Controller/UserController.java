package com.springboot.dynamoDB.ExpenseTracker.Controller;

import com.springboot.dynamoDB.ExpenseTracker.DTO.UserDTO;
import com.springboot.dynamoDB.ExpenseTracker.Entity.User;
import com.springboot.dynamoDB.ExpenseTracker.Service.UserService;
import com.springboot.dynamoDB.ExpenseTracker.Util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    //create
    @PostMapping("/add")
    public ResponseEntity<ResponseHandler> addUser(@RequestBody @Valid User user){
        User addedUser= userService.addUser(user);

        ResponseHandler response = new ResponseHandler(addedUser, messageSource.getMessage("user.create.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "User");

        return ResponseEntity.ok(response);
    }

    //retrieve
    @GetMapping("/users")
    public ResponseEntity<ResponseHandler> getUsers(){
        List<User> users= userService.getUsers();
        ResponseHandler response = new ResponseHandler(users, messageSource.getMessage("users.get.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Users");

        return ResponseEntity.ok(response);
    }

    //retrieve
    @GetMapping("/{id}")
    public ResponseEntity<ResponseHandler> getUserById(@PathVariable  String id){
        User user= userService.getUserById(id);

        ResponseHandler response = new ResponseHandler(user, messageSource.getMessage("user.get.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "User");

        return ResponseEntity.ok(response);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseHandler> updateUser( @Valid @PathVariable String id, @Valid @RequestBody UserDTO userDTO){
        User user =userService.updateUser(id,userDTO);
        ResponseHandler response = new ResponseHandler(user, messageSource.getMessage("user.update.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "User");

        return ResponseEntity.ok(response);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseHandler> delete(@PathVariable @Valid String id){

        User user= userService.deleteUser(id);

        ResponseHandler response = new ResponseHandler(user, messageSource.getMessage("user.delete.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "User");

        return ResponseEntity.ok(response);
    }


}
