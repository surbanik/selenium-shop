package models;

public class UserBuilder {

    String firstName;
    String lastName;
    String email;
    String password;

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


    public User build(){
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        return user;
    }

}
