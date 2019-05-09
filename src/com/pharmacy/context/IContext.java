package com.pharmacy.context;

import com.pharmacy.entities.*;

import java.util.List;

public interface IContext {
    List<Medicine> getMedicines();
    List<Address> getAddresses();
    List<Company> getCompanies();
    List<Doctor> getDoctors();
    List<DoctorLicense> getDoctorLicenses();
    List<Employee> getEmployees();
    List<Insurance> getInsurances();
    List<InsuranceSupport> getInsuranceSupports();
    List<MedicineCategory> getMedicineCategories();
    List<Order> getOrders();
    List<OrderDetail> getOrderDetails();
    List<Patient> getPatients();
    List<PatientInsurance> getPatientInsurances();
    List<Product> getProducts();
    List<Script> getScripts();
    List<ScriptDetail> getScriptDetails();
    List<User> getUsers();
}
