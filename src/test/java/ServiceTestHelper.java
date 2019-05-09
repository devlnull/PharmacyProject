package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.Employee;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.User;
import com.pharmacy.repo.IRepo;
import com.pharmacy.repo.InMemoryRepo;
import com.pharmacy.services.*;

public class ServiceTestHelper {

    public static IUserService getUserService(IContext context){
        IRepo<User> repo = new InMemoryRepo<>(context, User.class);
        repo.Add(new User("test", "P@$$W0rd"));
        return new UserService(repo);
    }

    public static IPatientService getPatientService(IContext context) {
        IRepo<Patient> repo = new InMemoryRepo<>(context, Patient.class);
        User existUser = getExistUser(context,"test");
        repo.Add(new Patient(existUser.getId(), "test", "test"));
        return new PatientService(repo);
    }

    public static IDoctorService getDoctorService(IContext context) {
        IRepo<Doctor> repo = new InMemoryRepo<>(context, Doctor.class);
        User existUser = getExistUser(context, "test");
        repo.Add(new Doctor(existUser.getId(), "test", "test"));
        return new DoctorService(repo);
    }

    public static IEmployeeService getEmployeeService(IContext context) {
        IRepo<Employee> repo = new InMemoryRepo<>(context, Employee.class);
        User existUser = getExistUser(context, "test");
        repo.Add(new Employee(existUser.getId(), "test", "test"));
        return new EmployeeService(repo);
    }

    public static User getExistUser(IContext context, String userName){
        IUserService userService = getUserService(context);
        return userService.GetUser(userName);
    }
}
