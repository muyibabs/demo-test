package com.muyi.demotest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muyi.demotest.controller.EmployeeController;
import com.muyi.demotest.model.Employee;
import com.muyi.demotest.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class DemoEmployeeUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployee() throws Exception {
        when(employeeService.getById(1L)).thenReturn(new Employee("emp1","Muyi Babs",21));

        mockMvc.perform(get("/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value("emp1"))
                .andExpect(jsonPath("$.name").value("Muyi Babs"))
                .andExpect(jsonPath("$.age").value(21));
                //.andReturn();
    }

    @Test
    public void testPostEmpl() throws Exception{
        Employee ee = new Employee("emp1","Muyi Babs",21);
        ee.setId(1L);
        when(employeeService.saveEmpl(any(Employee.class))).thenReturn(ee);
        mockMvc.perform(post("/v1/employees")
                .content(new ObjectMapper().writeValueAsString(ee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testGetAllEmployee() throws Exception{
        Employee e1= new Employee("emp1","Muyi Babs",21);
        Employee e2= new Employee("emp2","Lara Soft",38);
        when(employeeService.getAll()).thenReturn(Arrays.asList(e1,e2));

        MvcResult mvcResult = mockMvc.perform(get("/v1/employees"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].employeeId").value("emp1"))
                .andExpect(jsonPath("$[0].employeeId").isNotEmpty())
                .andExpect(jsonPath("$[0].age").isNumber())
                .andReturn();

//        List<Employee> employees = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsByteArray(),
//                new TypeReference<List<Employee>>() {
//                });
//        assertEquals(2,employees.size());

    }
}
