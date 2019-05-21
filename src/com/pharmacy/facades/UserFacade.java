package com.pharmacy.facades;

import com.pharmacy.entities.Person;
import com.pharmacy.entities.User;
import com.pharmacy.models.MemberRegisterModel;
import com.pharmacy.services.IDoctorService;
import com.pharmacy.services.IEmployeeService;
import com.pharmacy.services.IPatientService;
import com.pharmacy.services.IUserService;

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
        user = _userService.CreateUser(registerModel.userName,
                registerModel.password, registerModel.userType);
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
        User user = _userService.GetUser(username);
        user.setPerson(GetSubUser(user));
        return user;
    }

    @Override
    public User GetUserById(String userId) {
        User user = _userService.GetUserById(userId);
        user.setPerson(GetSubUser(user));
        return user;
    }

    @Override
    public Person GetSubUser(User user){
        switch(user.getUserType()){
            case Doctor:
                return _doctorService.GetByUserId(user.getId());
            case Patient:
                return _patientService.GetByUserId(user.getId());
            case Employee:
                return _employeeService.GetByUserId(user.getId());
        }
        return null;
    }

    @Override
    public Person GetSubUserById(String userId){
        User user = GetUserById(userId);
        return GetSubUser(user);
    }

    @Override
    public boolean LogIn(String username, String password) {
        return _userService.LogIn(username, password);
    }

    @Override
    public void LogOut(String username) {
        _userService.LogOut(username);
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
