package com.coursemanagement.CourseManagement.Service;

import com.coursemanagement.CourseManagement.DTO.UserDTO;
import com.coursemanagement.CourseManagement.Exception.BadRequestException;
import com.coursemanagement.CourseManagement.Entity.User;

public interface UserService {

    public User addUser(UserDTO userDTO) throws BadRequestException;

    public String login(UserDTO userDTO);

}
