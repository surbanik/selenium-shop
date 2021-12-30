package models;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.Locale;

public class UserFactory {

    public User getRandomUser() {
        Faker faker = new Faker(new Locale("pl-PL"));
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("pl-PL"), new RandomService());
        User user = new UserBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(fakeValuesService.bothify("??????##@test.pl"))
                .password(faker.bothify("??????"))
                .address(faker.address().streetName())
                .city(faker.address().cityName())
                .zipCode(faker.address().zipCode())
                .country("Poland")
                .build();
        return user;
    }


    public User getAlreadyRegisteredUser() { //do poprawy(yaml), lombok java
        User user = new UserBuilder()
                .firstName("Test")
                .lastName("Test")
                .email(System.getProperty("userEmail"))
                .password(System.getProperty("userPassword"))
                .build();
        return user;
    }
}
