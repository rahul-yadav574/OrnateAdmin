package in.evolve.ornateadmin.Models;

/**
 * Created by RAJEEV YADAV on 1/9/2017.
 */
public class PgBookInfo {

    private String name;
    private String email;
    private String phoneNumber;

    public PgBookInfo(String name, String email, String phoneNumber, String occupancy, String date,String time)
    {
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
