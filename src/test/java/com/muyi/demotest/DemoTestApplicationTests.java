package com.muyi.demotest;

import com.muyi.demotest.controller.EmployeeController;
import com.muyi.demotest.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoTestApplicationTests {

	@Autowired
	private EmployeeController controller;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testGetOneEmployee(){
		ResponseEntity<Employee> re = restTemplate.getForEntity("http://localhost:"+port+"/v1/employees/11",Employee.class);
		assertEquals(200,re.getStatusCodeValue());
		assertEquals("emp101",re.getBody().getEmployeeId());
	}

	@Test
	public void testGetAllEmployees(){
		ResponseEntity<List> re = restTemplate
				.getForEntity("http://localhost:"+port+"/v1/employees",List.class);

		assertEquals(200,re.getStatusCodeValue());
		assertEquals(3,re.getBody().size());
	}

	@Test
	public void testPostEmployee(){
		Employee ee = new Employee("eee123","Boluk Babs",36); ee.setId(1L);
		ResponseEntity<Employee> re = restTemplate
				.postForEntity("http://localhost:"+port+"/v1/employees",ee,Employee.class);

		assertEquals(201,re.getStatusCodeValue());
		assertNotNull(re.getBody().getId());
	}

}
