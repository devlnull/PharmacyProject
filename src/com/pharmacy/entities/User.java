package com.pharmacy.entities;

import com.pharmacy.context.UserType;
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
    private boolean _isLoggedIn;
    private Person _person;
    private final UserType _userType;
    private UserStatus _status = UserStatus.Active;

    public User(String username, String password, UserType userType){
        this._username = username;
        this._passwordHash = HashHelper.hashToSHA256(password);
        this._userType = userType;
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

    public boolean getLoggedIn(){
        return _isLoggedIn;
    }

    public void setLoggedIn(boolean val){
        this._isLoggedIn = val;
    }

    public String getUserName(){
        return _username;
    }

    public String getHashPassword(){
        return this._passwordHash;
    }


    public void setPerson(Person person){
        this._person = person;
    }

    public Person getPerson(){
        return this._person;
    }

    public UserType getUserType(){
        return this._userType;
    }
}