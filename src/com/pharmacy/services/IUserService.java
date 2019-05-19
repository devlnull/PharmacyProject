package com.pharmacy.services;

import com.pharmacy.context.UserType;
import com.pharmacy.entities.User;
import com.pharmacy.models.MemberRegisterModel;

public interface IUserService {
    User CreateUser(String username, String password);
    boolean ActivateUser(String username);
    boolean DeActivateUser(String username);
    User GetUser(String username);
    boolean LogIn(String username, String password);
    boolean LogOut(String username);
}
