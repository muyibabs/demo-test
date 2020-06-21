package com.muyi.demotest.controller;

import com.muyi.demotest.model.Employee;
import com.muyi.demotest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {

    private EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee e){
        return service.saveEmpl(e);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        service.deleteEmpById(id);
    }
}
