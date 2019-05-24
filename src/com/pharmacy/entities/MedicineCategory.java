package com.pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class MedicineCategory extends Entity{
    private String _name;
    private List<Medicine> _medicines;

    public MedicineCategory(String name){
        setName(name);
        _medicines = new LinkedList<>();
    }

    private void setName(String name){
        this._name = name;
    }

    public List<Medicine> getMedicines(){
        return this._medicines;
    }

    public void addNewMedicine(Medicine medicine){
        this._medicines.add(medicine);
    }

    public String getName() {
        return _name;
    }
}