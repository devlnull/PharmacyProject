package pharmacy.entities;

public class ScriptDetail extends Entity{
    private Script _script;
    private Medicine _medicine;
    private OrderDetail _orderDetail;

    public ScriptDetail(Script script, Medicine medicine, OrderDetail orderDetail)
    {
        this._script = script;
        this._medicine = medicine;
        this._orderDetail = orderDetail;
    }
}