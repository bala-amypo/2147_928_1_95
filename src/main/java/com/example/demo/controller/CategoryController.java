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
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
@RestController
public class CategoryController {
@Autowired
CategoryService service;
@PostMapping("/category/add")
public Category add(@RequestBody Category c) {
return service.insert(c);
}
@GetMapping("/category/getAll")
public List<Category> getAll() {
return service.getAll();
}
@GetMapping("/category/get/{id}")
public Optional<Category> get(@PathVariable Long id) {
return service.getOne(id);
}
@PutMapping("/category/update/{id}")
public String update(@PathVariable Long id, @RequestBody Category c) {
if(service.getOne(id).isPresent()) {
c.setId(id);
service.insert(c);
return "Updated successfully";
}
return "Id not found";
}
@DeleteMapping("/category/delete/{id}")
public String delete(@PathVariable Long id) {
if(service.getOne(id).isPresent()) {
service.delete(id);
return "Deleted successfully";
}
return "Id not found";
}
}