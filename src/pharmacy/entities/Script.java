package pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Script extends Entity{
    private Patient _patient;
    private String _request;
    private Doctor _doctor;
    private ScriptStatus _status;
    private List<ScriptDetail> scriptDetails;

    public Script(Patient patinet, Doctor doctor){
        this._patient = patinet;
        this._doctor = doctor;
        scriptDetails = new LinkedList<ScriptDetail>();
    }

    public void setScriptInfo(String request, ScriptStatus status){
        this._request = request;
        this._status = status;
    }

    public void addScriptDetail(ScriptDetail detail){
        this.scriptDetails.add(detail);
    }

    public List<ScriptDetail> getScriptDetails(){
        return scriptDetails;
    }
}