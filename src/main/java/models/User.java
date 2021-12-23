package models;

public class User {

    String firstName;
    String lastName;
    String email;
    String password;


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
