package com.pharmacy.context;

import com.pharmacy.entities.User;

public class InMemoryCurrentContext implements ICurrentContext{
    private String _username;
    private UserType _userType;
    private boolean _isLoggedIn;
    private User _user;

    @Override
    public void setCurrentUser(User user){
        this._username = user.getUserName();
        this._userType = user.getUserType();
        this._isLoggedIn = true;
        this._user = user;
    }

    @Override
    public String getCurrentUsername() {
        return _username;
    }

    @Override
    public String getCurrentUserInfo() {
        if(_user.getPerson() == null)
            return _user.getUserName();
        else {
            return _user.getPerson().getName();
        }
    }

    @Override
    public UserType getCurrentUserType() {
        return this._userType;
    }

    @Override
    public void flush(){
        this._isLoggedIn = false;
        this._username = null;
        this._userType = null;
    }

    @Override
    public boolean isLoggedIn() {
        return _isLoggedIn;
    }
}
