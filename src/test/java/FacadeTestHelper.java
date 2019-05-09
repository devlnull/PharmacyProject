package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.facades.IUserFacade;
import com.pharmacy.facades.UserFacade;

public class FacadeTestHelper {

    public static IUserFacade getUserFacade(IContext context){
        return new UserFacade(ServiceTestHelper.getUserService(context),
                ServiceTestHelper.getDoctorService(context),
                ServiceTestHelper.getPatientService(context),
                ServiceTestHelper.getEmployeeService(context));
    }
}
