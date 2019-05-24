package com.pharmacy.services;

import com.pharmacy.entities.Script;
import com.pharmacy.entities.ScriptDetail;
import com.pharmacy.models.ScriptRequestModel;
import com.pharmacy.models.ScriptResponseModel;

import java.util.List;

public interface IScriptService {
    void SubmitRequest(ScriptRequestModel model);
    void ResponseToScript(ScriptResponseModel model);
    Script GetScriptById(String scriptId);
    List<Script> GetPatientScripts(String patientId);
    List<Script> GetDoctorScripts(String doctorId);
    List<ScriptDetail> GetScriptDetails(String scriptId);
}
