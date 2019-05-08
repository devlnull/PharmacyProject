package com.pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Company extends Entity{
    private final String _name;
    public List<Product> products;

    public Company(String name){
        this._name = name;
        products = new LinkedList<>();
    }

    public List<Product> getProducts(){
        return this.products;
    }

    public String getName() {
        return _name;
    }
}