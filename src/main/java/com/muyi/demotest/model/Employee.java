package com.muyi.demotest.model;


public class Employee {
    private Long id;

    //@NotEmpty(message = "employeeId cannot be empty")
    private String employeeId;

    //@NotBlank(message = "Name cannot be blank")
    //@NotNull(message = "Name cannot be null")
    private String name;

    //@Min(value = 1, message = "Age cannot be less than 1")
    //@Max(value = 100, message = "Age cannot be more than 100")
    private Integer age;

    public Employee(String employeeId, String name, Integer age) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
