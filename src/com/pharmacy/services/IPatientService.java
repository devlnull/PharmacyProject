package com.pharmacy.services;

import com.pharmacy.entities.Patient;

public interface IPatientService {
    Patient Create(String userId, String firstName, String lastName);
    Patient GetByUserId(String userId);
}
