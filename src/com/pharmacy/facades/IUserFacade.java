package com.pharmacy.facades;

import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.User;
import com.pharmacy.models.MemberRegisterModel;

public interface IUserFacade {
    boolean CreateMember(MemberRegisterModel registerModel);
    User GetUser(String username);
    Doctor GetDoctor(String userId);
    void ActivateUser(String username);
    void DeActiveUser(String username);
}
