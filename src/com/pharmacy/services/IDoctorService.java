package com.pharmacy.services;

import com.pharmacy.entities.Doctor;

public interface IDoctorService {
    Doctor Create(String userId, String firstName, String lastName);
    Doctor GetByUserId(String userId);
    Doctor GetById(String doctorId);
}
