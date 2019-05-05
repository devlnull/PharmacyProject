package pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Medicine extends Entity{
    private String _name;
    private MedicineCategory _category;
    private String _dosage;
    private List<Product> _products;
    private List<InsuranceSupport> _insuranceSupports;

    public Medicine(String name, MedicineCategory category, String dosage){
        setMedicineInfo(name, category, dosage);
        this._products = new LinkedList<Product>();
        this._insuranceSupports = new LinkedList<InsuranceSupport>();
    }

    public void setMedicineInfo(String name, MedicineCategory category, String dosage) {
        this._name = name;
        this._dosage = dosage;
        this._category = category;
    }

    public List<Product> getProducts(){
        return _products;
    }

    public void addNewProduct(Product product){
        this._products.add(product);
    }

    public List<InsuranceSupport> getInsuranceSupports(){
        return _insuranceSupports;
    }

    public void addNewInsuranceSupport(InsuranceSupport support){
        this._insuranceSupports.add(support);
    }
}