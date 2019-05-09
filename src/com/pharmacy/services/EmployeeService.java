package com.pharmacy.services;

import com.pharmacy.entities.Employee;
import com.pharmacy.repo.IRepo;

public class EmployeeService implements IEmployeeService {
    private IRepo<Employee> _employeeRepo;

    public EmployeeService(IRepo<Employee> employeeRepo){
        this._employeeRepo = employeeRepo;
    }

    @Override
    public Employee Create(String userId, String firstName, String lastName) {
        Employee target = GetByUserId(userId);
        if(target != null)
            return null;
        Employee emp = new Employee(userId, firstName, lastName);
        return _employeeRepo.Add(emp);
    }

    @Override
    public Employee GetByUserId(String userId) {
        return _employeeRepo.Get(x -> x.getUserId().equals(userId));
    }
}
