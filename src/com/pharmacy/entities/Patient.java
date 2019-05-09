package com.pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Patient extends Person {
    public Patient(String userId, String firstname, String lastname){
        super(firstname, lastname);
        this._insurances = new LinkedList<>();
        this._orders = new LinkedList<>();
        this._script = new LinkedList<>();
        this._userId = userId;
    }

    private List<PatientInsurance> _insurances;
    private List<Script> _script;
    private List<Order> _orders;
    private String _userId;

    public List<PatientInsurance> getInsurances() {
        return _insurances;
    }

    public List<Script> getScript() {
        return _script;
    }

    public List<Order> getOrders() {
        return _orders;
    }

    public String getUserId(){
        return _userId;
    }
}