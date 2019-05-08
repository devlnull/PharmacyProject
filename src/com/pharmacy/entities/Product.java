package com.pharmacy.entities;

import java.util.Date;

public class Product extends Entity{
    private Medicine _medicine;
    private Company _company;
    private int _quantity;
    private double _price;
    private Date _createDate;
    private Date _expireDate;

    public Product(Medicine medicine, Company comapny){
        this._medicine = medicine;
        this._company = comapny;
    }

    public void setProuctInfo(int quantity, double price, Date createDate, Date expireDate){
        this._quantity = quantity;
        this._price = price;
        this._createDate = createDate;
        this._expireDate = expireDate;
    }
}