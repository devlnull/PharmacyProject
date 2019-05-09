package com.pharmacy.services;

import com.pharmacy.entities.User;
import com.pharmacy.entities.UserStatus;
import com.pharmacy.repo.IRepo;

public class UserService implements IUserService {
    private final IRepo<User> _userRepo;

    public UserService(IRepo<User> userRepo){
        this._userRepo = userRepo;
    }

    @Override
    public User CreateUser(String username, String password) {
        username = username.toLowerCase();
        if(checkUserExist(username))
            return null;
        User user = new User(username, password);
        return _userRepo.Add(user);
    }

    private boolean checkUserExist(String username){
        User targetUser = GetUser(username);
        return targetUser != null;
    }

    @Override
    public boolean ActivateUser(String username) {
        User user = GetUser(username);
        if(user == null) return false;
        user.applyStatus(UserStatus.Active);
        return true;
    }

    @Override
    public boolean DeActivateUser(String username) {
        User user = GetUser(username);
        if(user == null) return false;
        user.applyStatus(UserStatus.Deactive);
        return true;
    }

    @Override
    public User GetUser(String username) {
        return _userRepo.Get(x -> x.getUserName().toLowerCase().equals(username.toLowerCase()));
    }
}
