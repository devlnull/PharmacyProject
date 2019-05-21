package com.pharmacy.context;

import com.pharmacy.entities.User;

public interface ICurrentContext {
    void setCurrentUser(User user);
    String getCurrentUsername();
    String getCurrentUserInfo();
    UserType getCurrentUserType();
    boolean isLoggedIn();
    void flush();
}
