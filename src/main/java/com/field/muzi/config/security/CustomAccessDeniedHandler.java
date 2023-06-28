package com.field.muzi.config.security;

import com.field.muzi.domain.entity.MemberEntity;
import com.field.muzi.utils.EntityUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
//        response.sendRedirect("/exception/access-denied");
        if(role.contains("SUB")) {
            response.sendRedirect("/error/admin-authority");
        }else if(role.contains("USER")) {
            response.sendRedirect("/");
        }
    }
}
