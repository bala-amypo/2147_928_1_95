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
import com.example.demo.entity.BudgetPlan;
import com.example.demo.service.BudgetPlanService;
@RestController
public class BudgetPlanController {
@Autowired
BudgetPlanService service;
@PostMapping("/budget/add")
public BudgetPlan add(@RequestBody BudgetPlan b) {
return service.insert(b);
}
@GetMapping("/budget/getAll")
public List<BudgetPlan> getAll() {
return service.getAll();
}
@GetMapping("/budget/get/{id}")
public Optional<BudgetPlan> get(@PathVariable Long id) {
return service.getOne(id);
}
@PutMapping("/budget/update/{id}")
public String update(@PathVariable Long id, @RequestBody BudgetPlan b) {
if(service.getOne(id).isPresent()) {
b.setId(id);
service.insert(b);
return "Updated successfully";
}
return "Id not found";
}
@DeleteMapping("/budget/delete/{id}")
public String delete(@PathVariable Long id) {
if(service.getOne(id).isPresent()) {
service.delete(id);
return "Deleted successfully";
}
return "Id not found";
}
}
