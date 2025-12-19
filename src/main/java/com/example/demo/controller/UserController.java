package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
