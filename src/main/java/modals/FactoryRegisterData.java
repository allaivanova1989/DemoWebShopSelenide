package modals;

import com.github.javafaker.Faker;

import static utils.PropertyReader.getProperty;

public class FactoryRegisterData {

    static Faker faker = new Faker();

    public static RegisterData getRegisterData() {

        return RegisterData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.name().firstName() + "@gmail.com")
                .password(getProperty("password"))
                .confirmPassword(getProperty("password"))
                .build();

    }

    public static RegisterData getRegisterDataWithDigitsInFirstName() {

        return RegisterData.builder()
                .firstName("122345678")
                .lastName(faker.name().lastName())
                .email(faker.name().firstName() + "@gmail.com")
                .password(getProperty("password"))
                .confirmPassword(getProperty("password"))
                .build();

    }

    public static RegisterData getRegisterDataWithDigitsInLastName() {

        return RegisterData.builder()
                .firstName(faker.name().firstName())
                .lastName("1234567890")
                .email(faker.name().firstName() + "@gmail.com")
                .password(getProperty("password"))
                .confirmPassword(getProperty("password"))
                .build();

    }

    public static RegisterData getRegisterDataWithSpecSymbolsInFirstName() {

        return RegisterData.builder()
                .firstName("$%^&*()")
                .lastName(faker.name().lastName())
                .email(faker.name().firstName() + "@gmail.com")
                .password(getProperty("password"))
                .confirmPassword(getProperty("password"))
                .build();

    }

    public static RegisterData getRegisterDataWithSpecSymbolsInLastName() {

        return RegisterData.builder()
                .firstName(faker.name().firstName())
                .lastName("$%^&*()")
                .email(faker.name().firstName() + "@gmail.com")
                .password(getProperty("password"))
                .confirmPassword(getProperty("password"))
                .build();

    }

    public static RegisterData getRegisterDataWithoutSymbolInEmail() {

        return RegisterData.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.name().firstName() + "gmail.com")
                .password(getProperty("password"))
                .confirmPassword(getProperty("password"))
                .build();

    }
}
