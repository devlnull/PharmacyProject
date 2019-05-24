package com.pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Script extends Entity{
    private String _patientId;
    private String _request;
    private String _response;
    private String _doctorId;
    private ScriptStatus _status;
    private List<ScriptDetail> scriptDetails;

    public Script(String patientId, String doctorId){
        this._patientId = patientId;
        this._doctorId = doctorId;
        scriptDetails = new LinkedList<>();
    }

    public Script setScriptInfo(String request, ScriptStatus status){
        this._request = request;
        this._status = status;
        return this;
    }

    public Script setResponse(String response){
        _response = response;
        _status = ScriptStatus.Responded;
        return this;
    }

    public void addScriptDetail(ScriptDetail detail){
        this.scriptDetails.add(detail);
    }

    public List<ScriptDetail> getScriptDetails(){
        return scriptDetails;
    }

    public String getPatientId() {
        return _patientId;
    }

    public String getRequest() {
        return _request;
    }

    public String getDoctorId() {
        return _doctorId;
    }

    public String getResponse() { return _response; }

    public ScriptStatus getStatus() {
        return _status;
    }
}