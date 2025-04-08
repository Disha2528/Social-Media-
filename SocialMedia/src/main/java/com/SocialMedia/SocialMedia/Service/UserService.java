package com.SocialMedia.SocialMedia.Service;
import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.DTO.UserDTO;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User  registerUser(UserDTO userDTO);

    public String login(UserDTO userDTO);

    public User update(UserDTO userDTO) throws EntityNotFoundException;

}
