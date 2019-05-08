package com.pharmacy.entities;

import java.util.Date;

public class Person extends Entity{
    private String _firstname;
    private String _lastname;
    private Date _birth;

    public Person(String firstname, String lastname){
        setName(firstname, lastname);
    }

    private void setName(String firstname, String lastname){
        this._firstname = firstname;
        this._lastname = lastname;
    }

    private void setPersonalInfo(Date birth){
        this._birth = birth;
    }

    public String getName(){
        if(_firstname == null && _lastname == null)
            return "NO NAME";
        return String.format("%s %s", _firstname, _lastname);
    }
}