package pharmacy.entities;

import java.util.LinkedList;
import java.util.List;

public class Order extends Entity{
    private Patient _patient;
    private Script _script;
    private OrderStatus _status = OrderStatus.Preorder;
    private List<OrderDetail> _orderDetails;

    public Order(Patient patient, Script script){
        this._patient = patient;
        this._script = script;
        _orderDetails = new LinkedList<OrderDetail>();
    }

    public void addDetail(OrderDetail detail){
        _orderDetails.add(detail);
    }

    public List<OrderDetail> getDetails(){
        return _orderDetails;
    }

    public void applyStatus(OrderStatus status){
        this._status = status;
    }
}