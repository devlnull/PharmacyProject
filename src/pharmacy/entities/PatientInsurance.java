package pharmacy.entities;

public class PatientInsurance extends Entity{
    private final Insurance _insurance;
    private final Patient _patient;

    public PatientInsurance(Patient patient, Insurance insurance){
        this._patient = patient;
        this._insurance = insurance;
    }
}