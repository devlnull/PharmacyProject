package com.pharmacy.entities;

public class Employee extends Person {
    private String _userId;
    public Employee(String userId, String firstname, String lastname){
        super(firstname, lastname);
        this._userId = userId;
    }

    public String getUserId(){
        return _userId;
    }
}