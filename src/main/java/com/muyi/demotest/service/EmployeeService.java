package com.muyi.demotest.service;

import com.muyi.demotest.dao.EmployeeDao;
import com.muyi.demotest.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeDao dao;
    private ModelMapper mapper;

    @Autowired
    public EmployeeService(EmployeeDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<Employee> getAll(){
        List<com.muyi.demotest.model.entity.Employee> all = dao.findAll();
        return all.stream().map(emp -> mapper.map(emp,Employee.class)).collect(Collectors.toList());
    }

    public Employee getById(Long id){
        com.muyi.demotest.model.entity.Employee e1 = dao.findById(id).get();
        return mapper.map(e1, Employee.class);
    }

    public Employee saveEmpl(Employee e1){
        com.muyi.demotest.model.entity.Employee ee = mapper.map(e1, com.muyi.demotest.model.entity.Employee.class);
        return mapper.map(dao.save(ee), Employee.class);
    }

    public void deleteEmpById(Long id){
        com.muyi.demotest.model.entity.Employee ee = dao.findById(id).get();
        dao.delete(ee);
    }
}
