package com.SocialMedia.SocialMedia.Util;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    @Value("${staticFolderPath}")
    private String staticFolderPath;


    public void storePfp(String username, String sourcePath, String avatarName) throws IOException {
        Path userAvatarFolder = Paths.get(staticFolderPath, "avatars", username);
        if (!Files.exists(userAvatarFolder)) {
            Files.createDirectories(userAvatarFolder);
        }

        Path sourceFile = Paths.get(sourcePath, avatarName);
        if (!Files.exists(sourceFile)) {
            throw new IOException("Avatar file does not exist at: " + sourceFile.toString());
        }

        Path destinationFile = userAvatarFolder.resolve(avatarName);
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }


    public void storePostImage(String username, String sourcePath, String imageName) throws IOException {
        Path userPostsFolder = Paths.get(staticFolderPath, "posts", username);
        if (!Files.exists(userPostsFolder)) {
            Files.createDirectories(userPostsFolder);
        }

        Path sourceFile = Paths.get(sourcePath, imageName);
        if (!Files.exists(sourceFile)) {
            throw new IOException("Post image does not exist at: " + sourceFile.toString());
        }
        Path destinationFile = userPostsFolder.resolve(imageName);
        Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }
}