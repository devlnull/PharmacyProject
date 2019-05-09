package com.pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Doctor extends Person {
    private List<DoctorLicense> _doctorLicenses;
    private List<Script> _scripts;

    public Doctor(String firstname, String lastname){
        super(firstname, lastname);
        this._doctorLicenses = new LinkedList<DoctorLicense>();
        this._scripts = new LinkedList<>();
    }

    public List<DoctorLicense> getLicenses(){
        return this._doctorLicenses;
    }

    public void addNewLicense(DoctorLicense license){
        this._doctorLicenses.add(license);
    }

    public List<Script> getScripts(){
        return this._scripts;
    }

    public void addNewScript(Script script){
        this._scripts.add(script);
    }
}