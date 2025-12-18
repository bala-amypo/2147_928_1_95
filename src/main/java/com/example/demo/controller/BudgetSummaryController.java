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
import com.example.demo.entity.BudgetSummary;
import com.example.demo.service.BudgetSummaryService;
@RestController
public class BudgetSummaryController {
@Autowired
BudgetSummaryService service;
@PostMapping("/summary/add")
public BudgetSummary add(@RequestBody BudgetSummary s) {
return service.insert(s);
}
@GetMapping("/summary/getAll")
public List<BudgetSummary> getAll() {
return service.getAll();
}
@GetMapping("/summary/get/{id}")
public Optional<BudgetSummary> get(@PathVariable Long id) {
return service.getOne(id);
}
@PutMapping("/summary/update/{id}")
public String update(@PathVariable Long id, @RequestBody BudgetSummary s) {
if(service.getOne(id).isPresent()) {
s.setId(id);
service.insert(s);
return "Updated successfully";
}
return "Id not found";
}
@DeleteMapping("/summary/delete/{id}")
public String delete(@PathVariable Long id) {
if(service.getOne(id).isPresent()) {
service.delete(id);
return "Deleted successfully";
}
return "Id not found";
}
}
