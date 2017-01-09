package in.evolve.ornateadmin.Models;

/**
 * Created by RAJEEV YADAV on 1/9/2017.
 */
public class PgRequest {
    String name;
    private String email;
    private String type;
    private String noOfRooms;
    private String address;

    public PgRequest(String name,String email,String type,String noOfRooms,String address)
    {
       this.name=name;
        this.email=email;
        this.type=type;
        this.noOfRooms=noOfRooms;
        this.address=address;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public String getAddress() {
        return address;
    }
}
