package com.pharmacy.models;
import com.pharmacy.context.UserType;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class MemberRegisterModel {
    public MemberRegisterModel(String username,
                               String password,
                               UserType userType,
                               String firstName,
                               String lastName){
        this.userName = username;
        this.password = password;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NotNull @Max(64)
    public String userName;
    @NotNull @Max(64)
    public String password;
    @NotNull
    public UserType userType;
    @Max(32)
    public String firstName;
    @Max(32)
    public String lastName;
}
