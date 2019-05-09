package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.Employee;
import com.pharmacy.entities.User;
import com.pharmacy.services.IEmployeeService;
import com.pharmacy.services.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeServiceTests {
    private IContext context;
    @Before
    public void InitContext(){
        context = new InMemoryContext();
    }

    @Test
    public void Create_ExistUser_MustNotCreate(){
        IEmployeeService empService = ServiceTestHelper.getEmployeeService(context);
        String existUserId = ServiceTestHelper.getExistUser(context, "test").getId();
        Employee result = empService.Create(existUserId, "test", "test");
        Assert.assertNull(result);
    }

    @Test
    public void Create_NotExitUser_MustCreate(){
        IEmployeeService empService = ServiceTestHelper.getEmployeeService(context);
        IUserService userService = ServiceTestHelper.getUserService(context);
        User user = userService.CreateUser("test9123", "P@$$W0rd");
        Employee result = empService.Create(user.getId(), "test", "test");
        Assert.assertNotNull(result);
    }
}
