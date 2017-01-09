package in.evolve.ornateadmin.Models;

/**
 * Created by RAJEEV YADAV on 1/9/2017.
 */
public class GuestHouseBookInfo {

    private String name;
    private String email;
    private String phoneNumber;
    private String noOfPeople;
    private String date;
    private String noOfRooms;

    public GuestHouseBookInfo(String name, String email, String phoneNumber, String noOfPeople, String date,String noOfRooms)
    {
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.noOfPeople=noOfPeople;
        this.date=date;
        this.noOfRooms=noOfRooms;
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

    public String getNoOfPeople() {
        return noOfPeople;
    }

    public String getDate() {
        return date;
    }

    public String getNoOfRooms(){ return noOfRooms;}
}
