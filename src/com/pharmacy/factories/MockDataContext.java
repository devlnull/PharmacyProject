package com.pharmacy.factories;

import com.pharmacy.context.IContext;
import com.pharmacy.context.UserType;
import com.pharmacy.entities.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MockDataContext {

    public static IContext MockContext(IContext context){
        context.getMedicineCategories().addAll(Arrays.stream(MedicineCategories()).collect(Collectors.toList()));
        context.getMedicines().addAll(Arrays.stream(Medicines()).collect(Collectors.toList()));
        context.getInsurances().addAll(Arrays.stream(Insurances()).collect(Collectors.toList()));
        context.getInsuranceSupports().addAll(Arrays.stream(InsuranceSupports()).collect(Collectors.toList()));
        context.getCompanies().addAll(Arrays.stream(Companies()).collect(Collectors.toList()));
        context.getProducts().addAll(Arrays.stream(Products()).collect(Collectors.toList()));
        User[] users = Users();
        Doctor[] doctors = Doctors(users);
        Patient[] patients = Patients(users);

        context.getUsers().addAll(Arrays.stream(users).collect(Collectors.toList()));
        context.getDoctors().addAll(Arrays.stream(doctors).collect(Collectors.toList()));
        context.getEmployees().addAll(Arrays.stream(Employees(users)).collect(Collectors.toList()));
        context.getPatients().addAll(Arrays.stream(patients).collect(Collectors.toList()));
        context.getScripts().addAll(Arrays.stream(Scripts(doctors, patients)).collect(Collectors.toList()));

        return context;
    }

    private static MedicineCategory[] MedicineCategories(){
        return new MedicineCategory[] {
                new MedicineCategory("cholesterol-lowering"),
                new MedicineCategory("antacid"),
                new MedicineCategory("blood-thinner"),
                new MedicineCategory("asthma-inhaler"),
                new MedicineCategory("anti-psychotic"),
                new MedicineCategory("oral-asthma"),
                new MedicineCategory("diabetes"),
                new MedicineCategory("injectable-anemia"),
        };
    }

    private static Company[] Companies(){
        return new Company[]{
                new Company("RAZI"),
                new Company("PARSIAN"),
                new Company("ABOALI"),
                new Company("SINA"),
        };
    }

    private static Insurance[] Insurances(){
        return new Insurance[]{
                new Insurance("IRAN"),
                new Insurance("SALAMAT"),
                new Insurance("BANK"),
                new Insurance("ARMY"),
        };
    }

    private static Medicine[] Medicines(){
        return new Medicine[]{
                new Medicine("Lipitor", MedicineCategories()[0], "500 mg"),
                new Medicine("Lipitor", MedicineCategories()[0], "200 mg"),
                new Medicine("Nexium", MedicineCategories()[1], "500 mg"),
                new Medicine("Nexium", MedicineCategories()[1], "200 mg"),
                new Medicine("Plavix", MedicineCategories()[2], "500 mg"),
                new Medicine("Plavix", MedicineCategories()[2], "300 mg"),
                new Medicine("Advair Diskus", MedicineCategories()[3], "500 mg"),
                new Medicine("Advair Diskus", MedicineCategories()[3], "500 mg"),
                new Medicine("Abilify", MedicineCategories()[4], "500 mg"),
                new Medicine("Abilify", MedicineCategories()[4], "100 mg"),
                new Medicine("Abilify", MedicineCategories()[4], "300 mg"),
                new Medicine("Seroquel", MedicineCategories()[5], "500 mg"),
                new Medicine("Seroquel", MedicineCategories()[5], "200 mg"),
                new Medicine("Seroquel", MedicineCategories()[5], "100 mg"),
                new Medicine("Singulair", MedicineCategories()[6], "500 mg"),
                new Medicine("Singulair", MedicineCategories()[6], "300 mg"),
                new Medicine("Singulair", MedicineCategories()[6], "500 mg"),
                new Medicine("Crestor", MedicineCategories()[1], "500 mg"),
                new Medicine("Crestor", MedicineCategories()[1], "200 mg"),
                new Medicine("Actos", MedicineCategories()[7], "500 mg"),
                new Medicine("Actos", MedicineCategories()[7], "200 mg"),
                new Medicine("Actos", MedicineCategories()[7], "100 mg"),
                new Medicine("Epogen", MedicineCategories()[2], "500 mg"),
                new Medicine("Epogen", MedicineCategories()[2], "200 mg"),
                new Medicine("Epogen", MedicineCategories()[2], "100 mg"),
        };
    }

    private static Product[] Products(){
        return new Product[]{
                new Product(Medicines()[0], Companies()[0])
                        .setProuctInfo(100,250,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[0], Companies()[1])
                        .setProuctInfo(200,550,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[0], Companies()[3])
                        .setProuctInfo(250,100,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[1], Companies()[0])
                        .setProuctInfo(250,300,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(1))),
                new Product(Medicines()[1], Companies()[1])
                        .setProuctInfo(500,50,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[1], Companies()[3])
                        .setProuctInfo(500,300,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(1))),
                new Product(Medicines()[2], Companies()[2])
                        .setProuctInfo(100,250,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[2], Companies()[3])
                        .setProuctInfo(600,200,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[3], Companies()[0])
                        .setProuctInfo(100,250,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[4], Companies()[2])
                        .setProuctInfo(100,250,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
                new Product(Medicines()[4], Companies()[1])
                        .setProuctInfo(100,250,
                        Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusYears(5))),
        };
    }

    private static InsuranceSupport[] InsuranceSupports(){
        return new InsuranceSupport[]{
                new InsuranceSupport(Insurances()[0], Medicines()[0]),
                new InsuranceSupport(Insurances()[0], Medicines()[1]),
                new InsuranceSupport(Insurances()[0], Medicines()[2]),
                new InsuranceSupport(Insurances()[0], Medicines()[3]),
                new InsuranceSupport(Insurances()[0], Medicines()[4]),
                new InsuranceSupport(Insurances()[0], Medicines()[5]),
                new InsuranceSupport(Insurances()[0], Medicines()[6]),
                new InsuranceSupport(Insurances()[0], Medicines()[7]),
                new InsuranceSupport(Insurances()[1], Medicines()[0]),
                new InsuranceSupport(Insurances()[1], Medicines()[1]),
                new InsuranceSupport(Insurances()[1], Medicines()[2]),
                new InsuranceSupport(Insurances()[1], Medicines()[3]),
                new InsuranceSupport(Insurances()[1], Medicines()[4]),
                new InsuranceSupport(Insurances()[1], Medicines()[5]),
                new InsuranceSupport(Insurances()[2], Medicines()[0]),
                new InsuranceSupport(Insurances()[2], Medicines()[1]),
                new InsuranceSupport(Insurances()[2], Medicines()[2]),
                new InsuranceSupport(Insurances()[2], Medicines()[3]),
                new InsuranceSupport(Insurances()[2], Medicines()[4]),
                new InsuranceSupport(Insurances()[2], Medicines()[5]),
        };
    }

    private static User[] Users(){
        return new User[]{
                new User("patient", "123", UserType.Patient),
                new User("doctor", "123", UserType.Doctor),
                new User("employee", "123", UserType.Employee),
        };
    }

    private static Patient[] Patients(User[] users){
        return new Patient[]{
                new Patient(users[0].getId(), "patient", "sick"),
        };
    }

    private static Script[] Scripts(Doctor[] doctors, Patient[] patients){
        return new Script[]{
                new Script(patients[0].getId(), doctors[0].getId())
                        .setScriptInfo("Test1",ScriptStatus.Pending)
                        .setResponse("Tested!"),
                new Script(patients[0].getId(), doctors[0].getId())
                        .setScriptInfo("Test2",ScriptStatus.Pending),
        };
    }

    private static Doctor[] Doctors(User[] users){
        return new Doctor[]{
                new Doctor(users[1].getId(), "doctor", "cure"),
        };
    }

    private static Employee[] Employees(User[] users){
        return new Employee[]{
                new Employee(users[2].getId(), "employee", "worker"),
        };
    }
}
