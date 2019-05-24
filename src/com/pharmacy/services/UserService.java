package com.pharmacy.services;

import com.pharmacy.context.UserType;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.User;
import com.pharmacy.entities.UserStatus;
import com.pharmacy.repo.IRepo;
import com.utils.HashHelper;

import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUserService {
    private final IRepo<User> _userRepo;

    public UserService(IRepo<User> userRepo){
        this._userRepo = userRepo;
    }

    @Override
    public User CreateUser(String username, String password, UserType userType) {
        username = username.toLowerCase();
        if(checkUserExist(username))
            return null;
        User user = new User(username, password, userType);
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
    public boolean LogIn(String username, String password) {
        User user = GetUser(username);
        return HashHelper.Equal(password, user.getHashPassword());
    }

    @Override
    public void LogOut(String username) {
        User user = GetUser(username);
        user.setLoggedIn(true);
    }

    @Override
    public User GetUser(String username) {
        return _userRepo.Get(x -> x.getUserName().toLowerCase().equals(username.toLowerCase()));
    }

    @Override
    public User GetUserById(String userId) {
        return _userRepo.Get(x -> x.getId().equals(userId));
    }

    public List<User> GetGroupOfUsers(UserType userType){
        return _userRepo.GetAll()
                .stream()
                .filter(x -> x.getUserType() == userType)
                .collect(Collectors.toList());
    }
}
