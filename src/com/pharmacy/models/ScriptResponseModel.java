package com.pharmacy.models;

import com.pharmacy.entities.Medicine;
import com.pharmacy.entities.Script;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ScriptResponseModel {
    public ScriptResponseModel(Script script,
                              String responseMessage,
                              List<Medicine> medicines){
        this.medicines = medicines;
        this.script = script;
        this.responseMessage = responseMessage;
    }

    @NotNull
    @Max(64)
    public List<Medicine> medicines;
    @NotNull @Max(64)
    public Script script;
    @Max(512)
    public String responseMessage;
}
