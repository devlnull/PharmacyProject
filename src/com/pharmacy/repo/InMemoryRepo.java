package com.pharmacy.repo;

import com.pharmacy.context.IContext;
import com.pharmacy.entities.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryRepo<T extends Entity> implements IRepo<T>{

    private final IContext _context;
    private final Class<?> _entity;

    public InMemoryRepo(IContext context, Class<?> entity){
        this._context = context;
        this._entity = entity;
    }

    private List<T> getEntityContext(){
        if(_entity.equals(Address.class)) {
            return (List<T>) this._context.getAddresses();
        } else if(_entity.equals(Company.class)){
            return (List<T>) this._context.getCompanies();
        } else if(_entity.equals(Doctor.class)){
            return (List<T>) this._context.getDoctors();
        } else if(_entity.equals(DoctorLicense.class)){
            return (List<T>) this._context.getDoctorLicenses();
        } else if(_entity.equals(Employee.class)){
            return (List<T>) this._context.getEmployees();
        } else if(_entity.equals(Insurance.class)){
            return (List<T>) this._context.getInsurances();
        } else if(_entity.equals(InsuranceSupport.class)){
            return (List<T>) this._context.getInsuranceSupports();
        } else if(_entity.equals(Medicine.class)){
            return (List<T>) this._context.getMedicines();
        } else if(_entity.equals(MedicineCategory.class)){
            return (List<T>) this._context.getMedicineCategories();
        } else if(_entity.equals(Order.class)){
            return (List<T>) this._context.getOrders();
        } else if(_entity.equals(OrderDetail.class)){
            return (List<T>) this._context.getOrderDetails();
        } else if(_entity.equals(Patient.class)){
            return (List<T>) this._context.getPatients();
        } else if(_entity.equals(PatientInsurance.class)){
            return (List<T>) this._context.getPatientInsurances();
        } else if(_entity.equals(Product.class)){
            return (List<T>) this._context.getProducts();
        } else if(_entity.equals(Script.class)){
            return (List<T>) this._context.getScripts();
        } else if(_entity.equals(ScriptDetail.class)){
            return (List<T>) this._context.getScriptDetails();
        } else if(_entity.equals(User.class)){
            return (List<T>) this._context.getUsers();
        }
        return null;
    }

    @Override
    public List<T> GetAll() {
        return getEntityContext();
    }

    @Override
    public List<T> GetAll(Predicate<T> predicate) {
        return getEntityContext()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public T Get(String id) {
        Optional<T> targetItem = getEntityContext()
                .stream()
                .filter(a -> a.IsEqualId(id))
                .findFirst();
        return targetItem.orElse(null);
    }

    @Override
    public boolean Add(T item) {
        try {
            List<T> entityContext = getEntityContext();
            checkIfExist(item);
            entityContext.add(item);
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private void checkIfExist(T item) throws Exception {
        T fetchedItem = Get(item.getId());
        if(fetchedItem != null)
            throw new Exception("Item already exist.");
    }

    private void checkIfNotExist(String id) throws Exception {
        T fetchedItem = Get(id);
        if(fetchedItem == null)
            throw new Exception(String.format("item not found with id -> (%d)", id));
    }

    @Override
    public boolean Delete(T item) {
        try {
            List<T> entityContext = getEntityContext();
            checkIfNotExist(item.getId());
            entityContext.remove(item);
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean Delete(String id) {
        try {
            List<T> entityContext = getEntityContext();
            checkIfNotExist(id);
            T item = Get(id);
            entityContext.remove(item);
            return false;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean Update(T item) {
        try{
            String itemId = item.getId();
            checkIfNotExist(itemId);
            Delete(itemId);
            Add(item);
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
