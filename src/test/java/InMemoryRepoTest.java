package test.java;

import com.pharmacy.context.IContext;
import com.pharmacy.context.InMemoryContext;
import com.pharmacy.entities.Address;
import com.pharmacy.repo.IRepo;
import com.pharmacy.repo.InMemoryRepo;
import org.junit.Assert;
import org.junit.Test;

public class InMemoryRepoTest {

    private IRepo<Address> getRepo() {
        IContext context = new InMemoryContext();
        return new InMemoryRepo<Address>(context, Address.class);
    }

    @Test
    public void Add_NotExistItem_WillBeAdd() throws Exception {
        IRepo<Address> repo = getRepo();
        Address address = new Address("Iran", "Tehran");
        boolean addResult = repo.Add(address);
        Address selectedAddress = repo.Get(address.getId());
        Assert.assertEquals(selectedAddress.getId(), address.getId());
        Assert.assertTrue(addResult);
    }

    @Test
    public void Delete_NotExistItem_WillThrow() throws Exception {
        IRepo<Address> repo = getRepo();
        Address address = new Address("Iran", "Tehran");
        boolean delResult = repo.Delete(address);
        Assert.assertFalse(delResult);
    }

    @Test
    public void Update_NotExistItem_WillThrow() throws Exception {
        IRepo<Address> repo = getRepo();
        Address address = new Address("Iran", "Tehran");
        boolean updateResult = repo.Update(address);
        Assert.assertFalse(updateResult);
    }

    @Test
    public void Update_ExistItem_UpdateCorrect() throws Exception {
        IRepo<Address> repo = getRepo();
        Address address = new Address("Iran", "Tehran");
        repo.Add(address);
        address.setAddressDetail("TehranPars", "","");
        boolean updateResult = repo.Update(address);
        Assert.assertTrue(updateResult);
    }

    @Test
    public void Update_ExistItem_SameIdWithDiffProps() throws Exception {
        IRepo<Address> repo = getRepo();
        Address address = new Address("Iran", "Tehran");
        repo.Add(address);
        address.setAddressDetail("TehranPars", "","");
        repo.Update(address);
        Address selectedAddress = repo.Get(address.getId());
        Assert.assertNotNull(selectedAddress);
        Assert.assertEquals(selectedAddress.getCountry(), "Iran");
    }
}