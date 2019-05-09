package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.Employee;
import com.pharmacy.entities.User;
import com.pharmacy.repo.IRepo;
import com.pharmacy.repo.InMemoryRepo;
import com.pharmacy.services.EmployeeService;
import com.pharmacy.services.IEmployeeService;
import com.pharmacy.services.IUserService;
import com.pharmacy.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeServiceTests {
    private IContext context;
    @Before
    public void InitContext(){
        context = new InMemoryContext();
    }

    private IEmployeeService getService() {
        IRepo<Employee> repo = new InMemoryRepo<>(context, Employee.class);
        User existUser = getExistUser("test");
        repo.Add(new Employee(existUser.getId(), "test", "test"));
        return new EmployeeService(repo);
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
        IEmployeeService empService = getService();
        String existUserId = getExistUser("test").getId();
        Employee result = empService.Create(existUserId, "test", "test");
        Assert.assertNull(result);
    }

    @Test
    public void Create_NotExitUser_MustCreate(){
        IEmployeeService empService = getService();
        IUserService userService = getUserService();
        User user = userService.CreateUser("test9123", "P@$$W0rd");
        Employee result = empService.Create(user.getId(), "test", "test");
        Assert.assertNotNull(result);
    }
}
