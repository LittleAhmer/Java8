package com.antone.service;

import com.antone.pojo.Employee;

public interface MyPredicate<T> {

    public boolean test(Employee employee);

}
