package com.example.demo.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Category;
@Service
public class CategoryServiceImpl implements CategoryService {
Map<Long, Category> data = new HashMap<>();
@Override
public Category insert(Category c) {
data.put(c.getId(), c);
return c;
}
@Override
public List<Category> getAll() {
return new ArrayList<>(data.values());
}
@Override
public Optional<Category> getOne(Long id) {
return Optional.ofNullable(data.get(id));
}
@Override
public void delete(Long id) {
data.remove(id);
}
}