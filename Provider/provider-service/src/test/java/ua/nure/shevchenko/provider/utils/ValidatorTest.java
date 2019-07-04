package ua.nure.shevchenko.provider.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void validateUserParameters() {
        boolean actual = Validator.validateUserParameters("login1",
                "password2",
                "Andrey",
                "Shevchenko",
                "Alexs",
                "email@mail.ru",
                "0955938675");

        assertTrue(actual);
    }

    @Test
    public void validateUserParameters2() {
        boolean actual = Validator.validateUserParameters("login1",
                "password2",
                "s223ndrey",
                "Shevchenko",
                "Alexs",
                "email@mail.ru",
                "0955938675");

        assertFalse(actual);
    }



    @Test
    public void validateTariffParameters() {
        boolean actual = Validator.validateTariffParameters("Specification yes","Vippp","20.2");
        assertTrue(actual);
    }

    @Test
    public void validateTariffParameters2() {
        boolean actual = Validator.validateTariffParameters("Specification yes","Vi","20.2");
        assertFalse(actual);
    }

    @Test
    public void isNotNull() {
        boolean actual = Validator.isNotNull("String","yes",2);
        assertTrue(actual);
    }

    @Test
    public void isNotNull1() {
        boolean actual = Validator.isNotNull("String",null,2);
        assertFalse(actual);
    }

    @Test
    public void validateTariffParameters1() {
        boolean actual = Validator.validateTariffParameters("20.20");
        assertTrue(actual);
    }

    @Test
    public void validateTariffParameters3() {
        boolean actual = Validator.validateTariffParameters(".20");
        assertFalse(actual);
    }
}