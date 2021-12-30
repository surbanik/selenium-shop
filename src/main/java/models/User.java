package models;

public class User {

    String firstName;
    String lastName;
    String email;
    String password;
    String address;
    String zipCode;
    String city;
    String country;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstNameInput) {
        this.firstName = firstNameInput;
    }

    public void setLastName(String lastNameInput) {
        this.lastName = lastNameInput;
    }

    public void setEmail(String emailInput) {
        this.email = emailInput;
    }

    public void setPassword(String passwordInput) {
        this.password = passwordInput;
    }


}
