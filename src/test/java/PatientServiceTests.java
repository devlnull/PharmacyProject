package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.Patient;
import com.pharmacy.entities.User;
import com.pharmacy.services.IPatientService;
import com.pharmacy.services.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PatientServiceTests {
    private IContext context;
    @Before
    public void InitContext(){
        context = new InMemoryContext();
    }

    @Test
    public void Create_ExistUser_MustNotCreate(){
        IPatientService patientService = ServiceTestHelper.getPatientService(context);
        String existUserId = ServiceTestHelper.getExistUser(context,"test").getId();
        Patient result = patientService.Create(existUserId, "test", "test");
        Assert.assertNull(result);
    }

    @Test
    public void Create_NotExitUser_MustCreate(){
        IPatientService patientService = ServiceTestHelper.getPatientService(context);
        IUserService userService = ServiceTestHelper.getUserService(context);
        User user = userService.CreateUser("test9123", "P@$$W0rd");
        Patient result = patientService.Create(user.getId(), "test", "test");
        Assert.assertNotNull(result);
    }
}
