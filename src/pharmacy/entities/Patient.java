package pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Patient extends Person{

    public Patient(String firstname, String lastname){
        super(firstname, lastname);
        this._insurances = new LinkedList<PatientInsurance>();
        this._orders = new LinkedList<Order>();
        this._script = new LinkedList<Script>();
    }

    private List<PatientInsurance> _insurances;
    private List<Script> _script;
    private List<Order> _orders;
}