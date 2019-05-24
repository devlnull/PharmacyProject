package com.pharmacy.entities;

public class ScriptDetail extends Entity{
    private String _scriptId;
    private String _medicineId;
    private String _orderDetailId;

    public ScriptDetail(String scriptId, String medicineId)
    {
        this._scriptId = scriptId;
        this._medicineId = medicineId;
    }

    public void addOrderDetail(String orderDetailId){
        this._orderDetailId = orderDetailId;
    }

    public String getScriptId() {
        return _scriptId;
    }

    public String getMedicineId() {
        return _medicineId;
    }

    public String getOrderDetailId() {
        return _orderDetailId;
    }
}