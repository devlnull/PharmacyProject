package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.User;
import com.pharmacy.services.IDoctorService;
import com.pharmacy.services.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoctorServiceTests {
    private IContext context;
    @Before
    public void InitContext(){
        context = new InMemoryContext();
    }

    @Test
    public void Create_ExistUser_MustNotCreate(){
        IDoctorService doctorService = ServiceTestHelper.getDoctorService(context);
        String existUserId = ServiceTestHelper.getExistUser(context,"test").getId();
        Doctor result = doctorService.Create(existUserId, "test", "test");
        Assert.assertNull(result);
    }

    @Test
    public void Create_NotExitUser_MustCreate(){
        IDoctorService doctorService = ServiceTestHelper.getDoctorService(context);
        IUserService userService = ServiceTestHelper.getUserService(context);
        User user = userService.CreateUser("test9123", "P@$$W0rd");
        Doctor result = doctorService.Create(user.getId(), "test", "test");
        Assert.assertNotNull(result);
    }
}
