package com.coursemanagement.CourseManagement.Util;

import com.coursemanagement.CourseManagement.Static.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    public AuthUtil(JwtUtil jwtUtil, HttpServletRequest request) {
        this.jwtUtil = jwtUtil;
        this.request = request;
    }



    public void ensureStudent() throws AccessDeniedException {
        if ( ! Role.STUDENT.equals(jwtUtil.extractRole(extractToken()))) {
            throw new AccessDeniedException("Only students are allowed to perform this action.");
        }
    }

    public void ensureInstructor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("INSTRUCTOR"))) {
            throw new AccessDeniedException("Only instructors are allowed.");
        }
    }


    public void ensureRole() throws AccessDeniedException {
        if (! Role.INSTRUCTOR.equals(jwtUtil.extractRole(extractToken())) && ! Role.STUDENT.equals(jwtUtil.extractRole(extractToken()))) {
            throw new AccessDeniedException("Only authorized Roles are allowed to perform this action.");
        }
    }

    public String currentUser(){
        return jwtUtil.extractUserId(extractToken());
    }

    private String extractToken() {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        throw new RuntimeException("Authorization token missing or invalid.");
    }
}
