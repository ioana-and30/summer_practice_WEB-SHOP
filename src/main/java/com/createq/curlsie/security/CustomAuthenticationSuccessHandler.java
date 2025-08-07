package com.createq.curlsie.security;

import com.createq.curlsie.facades.CartFacade;
import com.createq.curlsie.model.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    private final CartFacade cartFacade;

    public CustomAuthenticationSuccessHandler(@Lazy CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            request.getSession().setAttribute("loggedUser", userDetails.getUser());

            logger.info("User {} logged in successfully", userDetails.getUsername());
        }

        response.sendRedirect("/categories");
    }
}

