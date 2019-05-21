package test.java;

import com.pharmacy.context.UserType;
import org.junit.Assert;
import org.junit.Test;
import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.User;
import com.pharmacy.repo.IRepo;
import com.pharmacy.repo.InMemoryRepo;
import com.pharmacy.services.IUserService;
import com.pharmacy.services.UserService;

public class UserServiceTests {

    private IUserService getService() {
        IContext context = new InMemoryContext();
        IRepo<User> repo = new InMemoryRepo<>(context, User.class);
        repo.Add(new User("test", "P@$$W0rd", UserType.Patient));
        return new UserService(repo);
    }

    @Test
    public void Activate_CorrectUser_MustActivate(){
        IUserService service = getService();
        String username = "test";
        boolean result = service.ActivateUser(username);
        Assert.assertTrue(result);
    }

    @Test
    public void Activate_InCorrectUser_MustNotActivate(){
        IUserService service = getService();
        String username = "test124";
        boolean result = service.ActivateUser(username);
        Assert.assertFalse(result);
    }

    @Test
    public void Create_ExistUser_MustNotCreate(){
        IUserService service = getService();
        User result = service.CreateUser("test","P@$$W0rd", UserType.Patient);
        Assert.assertNull(result);
    }


    @Test
    public void Create_NotExistUser_MustCreate(){
        IUserService service = getService();
        User result = service.CreateUser("test1","P@$$W0rd", UserType.Patient);
        Assert.assertNotNull(result);
    }
}
