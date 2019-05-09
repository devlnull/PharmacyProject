package com.pharmacy.context;

import com.pharmacy.entities.*;

import java.util.LinkedList;
import java.util.List;

public class InMemoryContext implements IContext{
    private List<Address> Addresses = new LinkedList<Address>();
    private List<Company> Companies = new LinkedList<Company>();
    private List<Doctor> Doctors = new LinkedList<Doctor>();
    private List<DoctorLicense> DoctorLicenses = new LinkedList<DoctorLicense>();
    private List<Employee> Employees = new LinkedList<Employee>();
    private List<Insurance> Insurances = new LinkedList<Insurance>();
    private List<InsuranceSupport> InsuranceSupports = new LinkedList<InsuranceSupport>();
    private List<Medicine> Medicines = new LinkedList<Medicine>();
    private List<MedicineCategory> MedicineCategories = new LinkedList<MedicineCategory>();
    private List<Order> Orders = new LinkedList<Order>();
    private List<OrderDetail> OrderDetails = new LinkedList<OrderDetail>();
    private List<Patient> Patients = new LinkedList<Patient>();
    private List<PatientInsurance> PatientInsurances = new LinkedList<PatientInsurance>();
    private List<Product> Products = new LinkedList<Product>();
    private List<Script> Scripts = new LinkedList<Script>();
    private List<ScriptDetail> ScriptDetails = new LinkedList<ScriptDetail>();
    private List<User> Users = new LinkedList<User>();

    @Override
    public List<Medicine> getMedicines() {
        return Medicines;
    }

    @Override
    public List<Address> getAddresses() {
        return Addresses;
    }

    @Override
    public List<Company> getCompanies() {
        return Companies;
    }

    @Override
    public List<Doctor> getDoctors() {
        return Doctors;
    }

    @Override
    public List<DoctorLicense> getDoctorLicenses() {
        return DoctorLicenses;
    }

    @Override
    public List<Employee> getEmployees() {
        return Employees;
    }

    @Override
    public List<Insurance> getInsurances() {
        return Insurances;
    }

    @Override
    public List<InsuranceSupport> getInsuranceSupports() {
        return InsuranceSupports;
    }

    @Override
    public List<MedicineCategory> getMedicineCategories() {
        return MedicineCategories;
    }

    @Override
    public List<Order> getOrders() {
        return Orders;
    }

    @Override
    public List<OrderDetail> getOrderDetails() {
        return OrderDetails;
    }

    @Override
    public List<Patient> getPatients() {
        return Patients;
    }

    @Override
    public List<PatientInsurance> getPatientInsurances() {
        return PatientInsurances;
    }

    @Override
    public List<Product> getProducts() {
        return Products;
    }

    @Override
    public List<Script> getScripts() {
        return Scripts;
    }

    @Override
    public List<ScriptDetail> getScriptDetails() {
        return ScriptDetails;
    }

    @Override
    public List<User> getUsers() {
        return Users;
    }
}
