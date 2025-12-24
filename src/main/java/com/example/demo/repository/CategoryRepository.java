package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}