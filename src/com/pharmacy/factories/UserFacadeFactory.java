package com.pharmacy.factories;

import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.Employee;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.User;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.facades.UserFacade;
import com.pharmacy.services.*;

public class UserFacadeFactory {
    public IUserFacade create(){
        return new UserFacade(
                createUserService(),
                createDoctorService(),
                createPatientService(),
                createEmployeeService()
        );
    }

    private IUserService createUserService(){
        return new UserService(new RepoFactory<User>().create(User.class));
    }

    private IDoctorService createDoctorService(){
        return new DoctorService(new RepoFactory<Doctor>().create(Doctor.class));
    }

    private IPatientService createPatientService(){
        return new PatientService(new RepoFactory<Patient>().create(Patient.class));
    }

    private IEmployeeService createEmployeeService(){
        return new EmployeeService(new RepoFactory<Employee>().create(Employee.class));
    }
}
