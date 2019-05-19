package com.pharmacy.facades;

import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.Person;
import com.pharmacy.entities.User;
import com.pharmacy.models.MemberRegisterModel;
import com.pharmacy.services.*;

public class UserFacade implements IUserFacade {

    private final IEmployeeService _employeeService;
    private final IUserService _userService;
    private final IDoctorService _doctorService;
    private final IPatientService _patientService;

    public UserFacade(IUserService userService,
                      IDoctorService doctorService,
                      IPatientService patientService,
                      IEmployeeService employeeService){
        this._userService = userService;
        this._doctorService = doctorService;
        this._patientService = patientService;
        this._employeeService = employeeService;
    }


    @Override
    public boolean CreateMember(MemberRegisterModel registerModel) {
        Person subUser = null;
        User user = null;
        user = _userService.CreateUser(registerModel.userName, registerModel.password);
        switch (registerModel.userType) {
            case Employee:
                subUser = _employeeService.Create(user.getId(), registerModel.firstName,
                        registerModel.lastName);
                break;
            case Doctor:
                subUser = _doctorService.Create(user.getId(), registerModel.firstName,
                        registerModel.lastName);
                break;
            case Patient:
                subUser = _patientService.Create(user.getId(), registerModel.firstName,
                        registerModel.lastName);
                break;
        }
        return user != null & subUser != null;
    }

    @Override
    public User GetUser(String username) {
        return _userService.GetUser(username);
    }

    @Override
    public Doctor GetDoctor(String userId) {
        return _doctorService.GetByUserId(userId);
    }

    @Override
    public boolean LogIn(String username, String password) {
        return _userService.LogIn(username, password);
    }

    @Override
    public boolean LogOut(String username) {
        return _userService.LogOut(username);
    }

    @Override
    public void ActivateUser(String username) {
        _userService.ActivateUser(username);
    }

    @Override
    public void DeActiveUser(String username) {
        _userService.ActivateUser(username);
    }
}
