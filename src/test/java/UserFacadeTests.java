package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.context.UserType;
import com.pharmacy.entities.Doctor;
import com.pharmacy.entities.User;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.models.MemberRegisterModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserFacadeTests {
    private IContext context;
    @Before
    public void InitCotext(){
        context = new InMemoryContext();
    }

    private MemberRegisterModel getRegisterModel(){
        return new MemberRegisterModel(
                "doc1", "P@$$W0rd", UserType.Doctor,
                "doc", "tor"
        );
    }

    @Test
    public void Create_CorrectData_MustCreateCorrectUser(){
        IUserFacade facade = FacadeTestHelper.getUserFacade(context);
        boolean result = facade.CreateMember(getRegisterModel());
        Assert.assertTrue(result);
    }

    @Test
    public void Create_CorrectData_MustCreateSubUser(){
        IUserFacade facade = FacadeTestHelper.getUserFacade(context);
        facade.CreateMember(getRegisterModel());
        User targetUser = facade.GetUser("doc1");
        Doctor targetDoc = facade.GetDoctor(targetUser.getId());
        Assert.assertNotNull(targetUser);
        Assert.assertNotNull(targetDoc);
    }
}
