package com.pharmacy.services;

import com.pharmacy.entities.Medicine;

import java.util.List;

public interface IMedicineService {
    List<Medicine> GetMedicines();
    Medicine GetMedicine(String id);
    List<Medicine> GetMedicineByName(String name);
    List<Medicine> GetMedicineByDosage(String dosage);
}
