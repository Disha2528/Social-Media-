package com.SocialMedia.SocialMedia.Util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthenticatedUserProvider {

    private final String userName;


    public AuthenticatedUserProvider(HttpServletRequest request, JwtUtil jwtUtil) {
        String token = extractToken(request);
        this.userName = jwtUtil.extractUsername(token);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        throw new RuntimeException("Authorization token missing or invalid.");
    }


    public String getUserName() {
        return userName;
    }
}
