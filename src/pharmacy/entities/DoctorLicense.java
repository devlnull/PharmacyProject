package pharmacy.entities;

public class DoctorLicense extends Entity{
    private Doctor _doctor;
    private String _title;
    private DoctorLicenseStatus _status = DoctorLicenseStatus.InQueue;

    public DoctorLicense(Doctor doctor, String title){
        this._doctor = doctor;
        this._title = title;
    }

    public void applyStatus(DoctorLicenseStatus status){
        this._status = status;
    }
}