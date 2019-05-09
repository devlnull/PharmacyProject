package com.pharmacy.services;

import com.pharmacy.entities.Doctor;
import com.pharmacy.repo.IRepo;

public class DoctorService implements IDoctorService {
    private IRepo<Doctor> _docRepo;

    public DoctorService(IRepo<Doctor> docRepo){
        this._docRepo = docRepo;
    }

    @Override
    public Doctor Create(String userId, String firstName, String lastName) {
        Doctor target = GetByUserId(userId);
        if(target != null)
            return null;
        Doctor doc = new Doctor(userId, firstName, lastName);
        return _docRepo.Add(doc);
    }

    @Override
    public Doctor GetByUserId(String userId) {
        return _docRepo.Get(x -> x.getUserId().equals(userId));
    }
}
