package com.createq.curlsie.controller;

import com.createq.curlsie.dto.UserDTO;
import com.createq.curlsie.service.impl.DefaultUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class AuthenticationController {

    private final DefaultUserService userService;

    public AuthenticationController(DefaultUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {

        userService.registerNewUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, Integer> checkEmail(@RequestParam String email) {
        int count = userService.countUsersByEmail(email);
        return Map.of("count", count);
    }

    @GetMapping("/check-username")
    @ResponseBody
    public Map<String, Integer> checkUsername(@RequestParam String username) {
        int count = userService.countUsersByUsername(username);
        return Map.of("count", count);
    }
}
