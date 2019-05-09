package com.pharmacy.services;


import com.pharmacy.entities.Employee;

public interface IEmployeeService {
    Employee Create(String userId, String firstName, String lastName);
    Employee GetByUserId(String userId);
}
