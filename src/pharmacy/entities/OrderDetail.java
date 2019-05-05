package pharmacy.entities;

public class OrderDetail extends Entity{
    private Order _order;
    private ScriptDetail _scriptDetail;
    private Product _product;
    private OrderStatus _status = OrderStatus.Preorder;

    public OrderDetail(Order order, ScriptDetail scriptDetail, Product product)
    {
        this._order = order;
        this._scriptDetail = scriptDetail;
        this._product = product;
    }

    public void applyStatus(OrderStatus orderStatus){
        this._status = orderStatus;
    }

    public OrderStatus getStatus(){
        return this._status;
    }
}