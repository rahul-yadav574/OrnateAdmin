package in.evolve.ornateadmin.Models;

/**
 * Created by RAJEEV YADAV on 1/9/2017.
 */
public class PgVisitInfo {

    private String name;
    private String email;
    private String phoneNumber;
    private String occupancy;
    private String date;
    private String time;

    public PgVisitInfo(String name, String email, String phoneNumber, String occupancy, String date,String time)
    {
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.occupancy=occupancy;
        this.date=date;
        this.time=time;
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

    public String getOccupancy() {
        return occupancy;
    }

    public String getDate() {
        return date;
    }

    public String getTime(){ return time;}
}
