package com.SocialMedia.SocialMedia.Util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserUtil {

    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    public AuthenticatedUserUtil(JwtUtil jwtUtil, HttpServletRequest request) {
        this.jwtUtil = jwtUtil;
        this.request = request;
    }


    public void ensureLoggedInUser(String requiredUserId) throws AccessDeniedException {
        String currentUserId = jwtUtil.extractUsername(extractToken());
        if (currentUserId == null || !currentUserId.equals(requiredUserId)) {
            throw new AccessDeniedException("You are not authorized to perform this action.");
        }
    }

    public String getCurrentUser(){
        String token= extractToken();
        String userName= jwtUtil.extractUsername(token);
        return userName;
    }

    private String extractToken() {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        throw new RuntimeException("Authorization token missing or invalid.");
    }

}
