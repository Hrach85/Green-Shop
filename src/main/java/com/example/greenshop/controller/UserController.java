package com.example.greenshop.controller;

import com.example.greenshop.dto.userDto.CreateUserRequestDto;
import com.example.greenshop.entity.Role;
import com.example.greenshop.entity.User;
import com.example.greenshop.mapper.UserMapper;
import com.example.greenshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute CreateUserRequestDto createUserRequestDto){
        Optional<User> userFromDB = userRepository.findByEmail(createUserRequestDto.getEmail());
        if (userFromDB.isEmpty()){
            User user = userMapper.map(createUserRequestDto);
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setRole(Role.CUSTOMER);
            userRepository.save(user);
        }
        return "redirect:/";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

}