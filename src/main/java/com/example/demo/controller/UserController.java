package com.example.demo.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
@RestController
public class UserController {
@Autowired
UserService service;
@PostMapping("/user/add")
public User add(@RequestBody User user) {
return service.insertUser(user);
}
@GetMapping("/user/getAll")
public List<User> getAll() {
return service.getAllUsers();
}
@GetMapping("/user/get/{id}")
public Optional<User> getOne(@PathVariable Long id) {
return service.getOneUser(id);
}
@PutMapping("/user/update/{id}")
public String update(@PathVariable Long id, @RequestBody User newUser) {
Optional<User> user = service.getOneUser(id);
if(user.isPresent()) {
newUser.setId(id);
service.insertUser(newUser);
return "Updated successfully";
}
return "Id not found";
}
@DeleteMapping("/user/delete/{id}")
public String delete(@PathVariable Long id) {
Optional<User> user = service.getOneUser(id);
if(user.isPresent()) {
service.deleteUser(id);
return "Deleted successfully";
}
return "Id not found";
}
}