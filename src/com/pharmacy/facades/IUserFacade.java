package com.pharmacy.facades;

import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.User;
import com.pharmacy.models.MemberRegisterModel;

public interface IUserFacade {
    boolean CreateMember(MemberRegisterModel registerModel);
    User GetUser(String username);
    Doctor GetDoctor(String userId);
    boolean LogIn(String username, String password);
    boolean LogOut(String username);
    void ActivateUser(String username);
    void DeActiveUser(String username);
}
