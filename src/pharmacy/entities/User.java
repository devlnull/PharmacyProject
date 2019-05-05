package pharmacy.entities;

public class User extends Entity{
    private String _username;
    private String _email;
    private Boolean _emailConfirmed;
    private String _passwordHash;
    private String _phoneNumber;
    private Boolean _phoneNumberConfirmed;
    private Boolean _lockoutEnabled;
    private Integer _accessFailedCount;
    private Address _address;
    private Doctor _doctor = null;
    private Employee _employee = null;
    private Patient _patient = null;
    private UserStatus _status = UserStatus.Deactive;

    public User(String username, String password){

    }

    public void setRequiredInfo(String username, String password){
        this._username = username;
    }

    public void applyStatus(UserStatus status){
        this._status = status;
    }

    public UserStatus getStatus(){
        return this._status;
    }

    public void setAddress(Address address){
        this._address = address;
    }
}