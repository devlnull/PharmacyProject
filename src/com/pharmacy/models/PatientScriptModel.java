package com.pharmacy.models;

public class PatientScriptModel {
    private final String scriptId;
    private final String doctor;
    private final String status;

    public PatientScriptModel(String scriptId, String doctor, String status){
        this.doctor = doctor;
        this.scriptId = scriptId;
        this.status = status;
    }


    public String getScriptId() {
        return scriptId;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getStatus() {
        return status;
    }
}
