package com.pharmacy.entities;

import com.utils.HashHelper;

public class User extends Entity{
    private String _username;
    private String _email;
    private Boolean _emailConfirmed;
    private String _passwordHash;
    private String _phoneNumber;
    private Boolean _phoneNumberConfirmed;
    private Boolean _lockoutEnabled;
    private Integer _accessFailedCount;
    private Address _address;
    private boolean _isLogedIn;
    private UserStatus _status = UserStatus.Deactive;

    public User(String username, String password){
        this._username = username;
        this._passwordHash = HashHelper.hashToSHA256(password);
    }

    public void setRequiredInfo(String username, String password){
        this._username = username;
    }

    public void applyStatus(UserStatus status){
        this._status = status;
    }

    public UserStatus getStatus(){
        return this._status;
    }

    public void setAddress(Address address){
        this._address = address;
    }

    public boolean getLogedIn(){
        return _isLogedIn;
    }

    public void setLogedIn(boolean val){
        this._isLogedIn = val;
    }

    public String getUserName(){
        return _username;
    }

    public String getHashPassword(){
        return this._passwordHash;
    }
}