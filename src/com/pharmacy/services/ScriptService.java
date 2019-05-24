package com.pharmacy.services;

import com.pharmacy.entities.*;
import com.pharmacy.models.ScriptRequestModel;
import com.pharmacy.models.ScriptResponseModel;
import com.pharmacy.repo.IRepo;

import java.util.List;

public class ScriptService implements IScriptService{
    private IRepo<Script> _scriptRepo;
    private IRepo<ScriptDetail> _scriptDetailRepo;

    public ScriptService(IRepo<Script> scriptRepo,
                         IRepo<ScriptDetail> scriptDetailRepo){
        this._scriptRepo = scriptRepo;
        this._scriptDetailRepo = scriptDetailRepo;
    }

    @Override
    public void SubmitRequest(ScriptRequestModel model) {
        Script script = new Script(model.currentPatient.getId(), model.targetDoctor.getId());
        script.setScriptInfo(model.requestMessage, ScriptStatus.Pending);
        _scriptRepo.Add(script);
    }

    @Override
    public void ResponseToScript(ScriptResponseModel model) {
        Script script = _scriptRepo.Get(model.script.getId());
        script.setResponse(model.responseMessage);
        for(Medicine med : model.medicines){
            createScriptDetail(script.getId(), med.getId());
        }
        _scriptRepo.Update(script);
    }

    @Override
    public Script GetScriptById(String scriptId) {
        return _scriptRepo.Get(scriptId);
    }

    @Override
    public List<Script> GetPatientScripts(String patientId) {
        return _scriptRepo.GetAll(x-> x.getPatientId().equals(patientId));
    }

    @Override
    public List<Script> GetDoctorScripts(String doctorId) {
        return _scriptRepo.GetAll(x-> x.getDoctorId().equals(doctorId));
    }

    @Override
    public List<ScriptDetail> GetScriptDetails(String scriptId) {
        return _scriptDetailRepo.GetAll(x -> x.getScriptId().equals(scriptId));
    }

    private void createScriptDetail(String scriptId, String medicineId){
        _scriptDetailRepo.Add(new ScriptDetail(scriptId, medicineId));
    }
}
