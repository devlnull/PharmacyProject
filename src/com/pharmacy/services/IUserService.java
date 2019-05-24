package com.pharmacy.services;

import com.pharmacy.context.UserType;
import com.pharmacy.entities.User;

import java.util.List;

public interface IUserService {
    User CreateUser(String username, String password, UserType userType);
    boolean ActivateUser(String username);
    boolean DeActivateUser(String username);
    User GetUser(String username);
    User GetUserById(String userId);
    boolean LogIn(String username, String password);
    void LogOut(String username);
    List<User> GetGroupOfUsers(UserType userType);
}
