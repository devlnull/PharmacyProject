package com.pharmacy.facades;

import com.pharmacy.context.UserType;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.Person;
import com.pharmacy.entities.User;
import com.pharmacy.models.MemberRegisterModel;

import java.util.List;

public interface IUserFacade {
    boolean CreateMember(MemberRegisterModel registerModel);
    User GetUser(String username);
    User GetUserById(String userId);
    List<User> GetGroupOfUsers(UserType userType);
    Person GetSubUserById(String userId);
    Person GetSubUser(User user);
    boolean LogIn(String username, String password);
    void LogOut(String username);
    void ActivateUser(String username);
    void DeActiveUser(String username);
    Doctor GetDoctorById(String doctorId);
    Patient GetPatientById(String patientId);
}
