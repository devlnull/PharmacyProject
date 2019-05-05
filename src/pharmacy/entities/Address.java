package pharmacy.entities;

public class Address extends Entity{
    private String _city;
    private String _neighborhood;
    private String _country;
    private String _line1;
    private String _line2;

    public Address(String country, String city){
        setAddress(country, city);
    }

    private void setAddress(String country, String city){
        this._country = country;
        this._city = city;
    }

    public void setAddressDetail(String neighborhood, String line1, String line2){
        this._neighborhood = neighborhood;
        this._line1 = line1;
        this._line2 = line2;
    }
}