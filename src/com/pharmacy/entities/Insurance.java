package com.pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Insurance extends Entity{
    private String _name;
    private List<InsuranceSupport> _supports;

    public Insurance(String name){
        setName(name);
        this._supports = new LinkedList<>();
    }

    private void setName(String name){
        this._name = name;
    }

    public void addNewSupport(InsuranceSupport support){
        this._supports.add(support);
    }

    public List<InsuranceSupport> getSupports(){
        return _supports;
    }

    public String getName() {
        return _name;
    }
}