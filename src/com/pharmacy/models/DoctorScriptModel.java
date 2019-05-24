package com.pharmacy.models;

public class DoctorScriptModel {
    private final String scriptId;
    private final String patient;
    private final String status;

    public DoctorScriptModel(String scriptId, String patient, String status){
        this.patient = patient;
        this.scriptId = scriptId;
        this.status = status;
    }


    public String getScriptId() {
        return scriptId;
    }

    public String getPatient() {
        return patient;
    }

    public String getStatus() {
        return status;
    }
}
