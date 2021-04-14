package se.hernebring.domain;

public class Publisher {

    private String name;
    private String email;
    private String address;

    public Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;   
    }

}
