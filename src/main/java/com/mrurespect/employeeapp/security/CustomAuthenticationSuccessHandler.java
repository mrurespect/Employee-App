package com.mrurespect.employeeapp.security;

import com.mrurespect.employeeapp.entity.User;
import com.mrurespect.employeeapp.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService ;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("on CustomAuthenticationSuccessHandler ");
        String username =authentication.getName();
        System.out.println("username ="+username);
        User theUser =userService.findByUserName(username);

        //now place it in the session
        HttpSession session =request.getSession();
        session.setAttribute("user",theUser);

        //forward to homepage
        response.sendRedirect(request.getContextPath()+"/");
    }
}
