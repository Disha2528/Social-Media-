package com.SocialMedia.SocialMedia.Service;
import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.DTO.UserDTO;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UserService {
    public UserDTO  registerUser(UserDTO userDTO) throws IOException;

    public String login(UserDTO userDTO);

    public UserDTO update(UserDTO userDTO) throws EntityNotFoundException, IOException;

}
