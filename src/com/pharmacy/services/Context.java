package com.pharmacy.services;

import com.pharmacy.entities.*;

import java.util.LinkedList;
import java.util.List;

public class Context{
    List<Address> Addresses = new LinkedList<Address>();
    List<Company> Companies = new LinkedList<Company>();
    List<Doctor> Doctors = new LinkedList<Doctor>();
    List<DoctorLicense> DoctorLicenses = new LinkedList<DoctorLicense>();
    List<Employee> Employees = new LinkedList<Employee>();
    List<Insurance> Insurances = new LinkedList<Insurance>();
    List<InsuranceSupport> InsuranceSupports = new LinkedList<InsuranceSupport>();
    List<Medicine> Medicines = new LinkedList<Medicine>();
    List<MedicineCategory> MedicineCategories = new LinkedList<MedicineCategory>();
    List<Order> Orders = new LinkedList<Order>();
    List<OrderDetail> OrderDetails = new LinkedList<OrderDetail>();
    List<Patient> Patients = new LinkedList<Patient>();
    List<PatientInsurance> PatientInsurances = new LinkedList<PatientInsurance>();
    List<Product> Products = new LinkedList<Product>();
    List<Script> Scripts = new LinkedList<Script>();
    List<ScriptDetail> ScriptDetails = new LinkedList<ScriptDetail>();
    List<User> Users = new LinkedList<User>();
}
