package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
@Service
public interface UserService {
User insertUser(User user);
List<User> getAllUsers();
Optional<User> getOneUser(Long id);
void deleteUser(Long id);
}