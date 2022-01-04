package models;

import lombok.Data;

@Data
public class User {

    String firstName;
    String lastName;
    String email;
    String password;
    String address;
    String zipCode;
    String city;
    String country;


}
