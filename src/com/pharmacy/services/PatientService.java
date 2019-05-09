package com.pharmacy.services;

import com.pharmacy.entities.Patient;
import com.pharmacy.repo.IRepo;

public class PatientService implements IPatientService {
    private final IRepo<Patient> _patientRepo;

    public PatientService(IRepo<Patient> patientRepo){
        this._patientRepo = patientRepo;
    }

    @Override
    public Patient Create(String userId, String firstName, String lastName) {
        Patient target = GetByUserId(userId);
        if(target != null)
            return null;
        Patient patient = new Patient(userId, firstName, lastName);
        return _patientRepo.Add(patient);
    }

    @Override
    public Patient GetByUserId(String userId) {
        return _patientRepo.Get(x -> x.getUserId().equals(userId));
    }
}
