package com.antone.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;
    private Integer age;
    private Double salary;

    public Employee(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public Employee(int age){
        this.age = age;
    }
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
