package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.User;
import com.pharmacy.repo.IRepo;
import com.pharmacy.repo.InMemoryRepo;
import com.pharmacy.services.IPatientService;
import com.pharmacy.services.IUserService;
import com.pharmacy.services.PatientService;
import com.pharmacy.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PatientServiceTests {
    private IContext context;
    @Before
    public void InitContext(){
        context = new InMemoryContext();
    }

    private IPatientService getService() {
        IRepo<Patient> repo = new InMemoryRepo<>(context, Patient.class);
        User existUser = getExistUser("test");
        repo.Add(new Patient(existUser.getId(), "test", "test"));
        return new PatientService(repo);
    }

    private IUserService getUserService() {
        IRepo<User> repo = new InMemoryRepo<>(context, User.class);
        repo.Add(new User("test", "P@$$W0rd"));
        return new UserService(repo);
    }

    private User getExistUser(String userName){
        IUserService userService = getUserService();
        return userService.GetUser(userName);
    }

    @Test
    public void Create_ExistUser_MustNotCreate(){
        IPatientService patientService = getService();
        String existUserId = getExistUser("test").getId();
        Patient result = patientService.Create(existUserId, "test", "test");
        Assert.assertNull(result);
    }

    @Test
    public void Create_NotExitUser_MustCreate(){
        IPatientService patientService = getService();
        IUserService userService = getUserService();
        User user = userService.CreateUser("test9123", "P@$$W0rd");
        Patient result = patientService.Create(user.getId(), "test", "test");
        Assert.assertNotNull(result);
    }
}
