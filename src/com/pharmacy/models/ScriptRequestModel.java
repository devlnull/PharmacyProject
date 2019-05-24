package com.pharmacy.models;

import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.Patient;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class ScriptRequestModel {
    public ScriptRequestModel(Patient currentPatient,
                              Doctor targetDoctor,
                              String requestMessage){
        this.currentPatient = currentPatient;
        this.targetDoctor = targetDoctor;
        this.requestMessage = requestMessage;
    }

    @NotNull
    @Max(64)
    public Patient currentPatient;
    @NotNull @Max(64)
    public Doctor targetDoctor;
    @Max(512)
    public String requestMessage;
}
