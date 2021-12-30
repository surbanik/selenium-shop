package models;

public class UserBuilder {

    String firstName;
    String lastName;
    String email;
    String password;
    String address;
    String zipCode;
    String city;
    String country;

    public UserBuilder firstName(String firstName){
        this.firstName = firstName;
        return this;
    }


    public UserBuilder lastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email){
        this.email = email;
        return this;
    }


    public UserBuilder password(String password){
        this.password = password;
        return this;
    }

    public UserBuilder address(String address){
        this.address = address;
        return this;
    }
    public UserBuilder zipCode(String zipCode){
        this.zipCode = zipCode;
        return this;
    }
    public UserBuilder city(String city){
        this.city = city;
        return this;
    }
    public UserBuilder country(String country){
        this.country = country;
        return this;
    }


    public User build(){
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setAddress(address);
        user.setZipCode(zipCode);
        user.setCity(city);
        user.setCountry(country);
        return user;
    }

}
