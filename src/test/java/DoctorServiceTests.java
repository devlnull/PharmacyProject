package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.User;
import com.pharmacy.repo.IRepo;
import com.pharmacy.repo.InMemoryRepo;
import com.pharmacy.services.DoctorService;
import com.pharmacy.services.IDoctorService;
import com.pharmacy.services.IUserService;
import com.pharmacy.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoctorServiceTests {
    private IContext context;
    @Before
    public void InitContext(){
        context = new InMemoryContext();
    }

    private IDoctorService getService() {
        IRepo<Doctor> repo = new InMemoryRepo<>(context, Doctor.class);
        User existUser = getExistUser("test");
        repo.Add(new Doctor(existUser.getId(), "test", "test"));
        return new DoctorService(repo);
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
        IDoctorService doctorService = getService();
        String existUserId = getExistUser("test").getId();
        Doctor result = doctorService.Create(existUserId, "test", "test");
        Assert.assertNull(result);
    }

    @Test
    public void Create_NotExitUser_MustCreate(){
        IDoctorService doctorService = getService();
        IUserService userService = getUserService();
        User user = userService.CreateUser("test9123", "P@$$W0rd");
        Doctor result = doctorService.Create(user.getId(), "test", "test");
        Assert.assertNotNull(result);
    }
}
