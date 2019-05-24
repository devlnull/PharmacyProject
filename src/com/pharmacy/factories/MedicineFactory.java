package com.pharmacy.factories;

import com.pharmacy.entities.Medicine;
import com.pharmacy.services.IMedicineService;
import com.pharmacy.services.MedicineService;

public class MedicineFactory {
    public IMedicineService create(){
        return new MedicineService(new RepoFactory<Medicine>().create(Medicine.class));
    }
}
