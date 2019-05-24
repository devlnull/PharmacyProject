package com.pharmacy.services;

import com.pharmacy.entities.Medicine;
import com.pharmacy.repo.IRepo;

import java.util.List;

public class MedicineService implements IMedicineService {
    private IRepo<Medicine> _medRepo;

    public MedicineService(IRepo<Medicine> medRepo){
        this._medRepo = medRepo;
    }

    @Override
    public List<Medicine> GetMedicines() {
        return _medRepo.GetAll();
    }

    @Override
    public Medicine GetMedicine(String id) {
        return _medRepo.Get(id);
    }

    @Override
    public List<Medicine> GetMedicineByName(String name) {
        return _medRepo.GetAll(x -> x.getName().equals(name));
    }

    @Override
    public List<Medicine> GetMedicineByDosage(String dosage) {
        return _medRepo.GetAll(x -> x.getName().equals(dosage));
    }
}
