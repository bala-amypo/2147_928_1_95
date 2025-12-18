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
import com.example.demo.entity.TransactionLog;
import com.example.demo.service.TransactionService;
@RestController
public class TransactionController {
@Autowired
TransactionService service;
@PostMapping("/transaction/add")
public TransactionLog add(@RequestBody TransactionLog t) {
return service.insert(t);
}
@GetMapping("/transaction/getAll")
public List<TransactionLog> getAll() {
return service.getAll();
}
@GetMapping("/transaction/get/{id}")
public Optional<TransactionLog> get(@PathVariable Long id) {
return service.getOne(id);
}
@PutMapping("/transaction/update/{id}")
public String update(@PathVariable Long id, @RequestBody TransactionLog
3
t) {
if(service.getOne(id).isPresent()) {
t.setId(id);
service.insert(t);
return "Updated successfully";
}
return "Id not found";
}
@DeleteMapping("/transaction/delete/{id}")
public String delete(@PathVariable Long id) {
if(service.getOne(id).isPresent()) {
service.delete(id);
return "Deleted successfully";
}
return "Id not found";
}
