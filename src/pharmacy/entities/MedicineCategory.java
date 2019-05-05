package pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class MedicineCategory extends Entity{
    private String _name;
    private List<Medicine> _medicines;

    MedicineCategory(String name){
        setName(name);
        _medicines = new LinkedList<Medicine>();
    }

    public void setName(String name){
        this._name = name;
    }

    public List<Medicine> getMedicines(){
        return this._medicines;
    }

    public void addNewMedicine(Medicine medicine){
        this._medicines.add(medicine);
    }
}