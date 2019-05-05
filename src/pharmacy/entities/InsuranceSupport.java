package pharmacy.entities;

public class InsuranceSupport extends Entity{
    private final Insurance _insurance;
    private final Medicine _medicine;

    public InsuranceSupport(Insurance insurance, Medicine medicine){
        this._medicine = medicine;
        this._insurance = insurance;
    }
}