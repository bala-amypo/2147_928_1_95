package com.example.demo.service;
import java.util.List;
import java.util.Optional;
import com.example.demo.entity.Category;
public interface CategoryService {
Category insert(Category c);
List<Category> getAll();
Optional<Category> getOne(Long id);
void delete(Long id);
}
