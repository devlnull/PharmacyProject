package com.pharmacy.facades;

import com.pharmacy.entities.Person;
import com.pharmacy.entities.User;
import com.pharmacy.models.MemberRegisterModel;

public interface IUserFacade {
    boolean CreateMember(MemberRegisterModel registerModel);
    User GetUser(String username);
    User GetUserById(String userId);
    Person GetSubUserById(String userId);
    Person GetSubUser(User user);
    boolean LogIn(String username, String password);
    void LogOut(String username);
    void ActivateUser(String username);
    void DeActiveUser(String username);
}
