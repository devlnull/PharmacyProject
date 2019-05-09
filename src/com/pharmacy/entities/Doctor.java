package com.pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Doctor extends Person {
    private List<DoctorLicense> _doctorLicenses;
    private List<Script> _scripts;
    private String _userId;

    public Doctor(String userId, String firstname, String lastname){
        super(firstname, lastname);
        this._doctorLicenses = new LinkedList<DoctorLicense>();
        this._scripts = new LinkedList<>();
        this._userId = userId;
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

    public String getUserId(){
        return _userId;
    }
}