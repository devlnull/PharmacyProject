package pharmacy.entities;

import java.util.List;

public class Doctor extends Person{
    private List<DoctorLicense> _doctorLicenses;
    private List<Script> _scripts;

    public Doctor(String firstname, String lastname){
        super(firstname, lastname);
    }

    public List<DoctorLicense> getLicenses(){
        return this._doctorLicenses;
    }

    public void addNewLicense(DoctorLicense license){
        this._doctorLicenses.add(license);
    }
}