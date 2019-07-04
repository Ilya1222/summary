package ua.nure.shevchenko.provider.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {

    @Test
    public void isFilled() {

        boolean actual = Validation.isFilled("Arnold");

        assertTrue(actual);


    }

    @Test
    public void isFilled2() {

        boolean actual = Validation.isFilled("arnold");

        assertFalse(actual);


    }

    @Test
    public void isCorrectEmail() {

        boolean actual = Validation.isCorrectEmail("arnold.@mail.ru");

        assertTrue(actual);


    }

    @Test
    public void isCorrectEmail2() {

        boolean actual = Validation.isCorrectEmail("arnold.mail.ru");

        assertFalse(actual);


    }

    @Test
    public void isCorrectLogin() {
        boolean actual = Validation.isCorrectLogin("login3");
        assertTrue(actual);
    }

    @Test
    public void isCorrectLogin2() {
        boolean actual = Validation.isCorrectLogin("lo");
        assertFalse(actual);
    }

    @Test
    public void isCorrectPassword() {
        boolean actual = Validation.isCorrectPassword("password");
        assertTrue(actual);
    }

    @Test
    public void isCorrectPassword2() {
        boolean actual = Validation.isCorrectPassword("pas2");
        assertFalse(actual);
    }

    @Test
    public void isCorrectPhone() {
        boolean actual = Validation.isCorrectPhone("0955838605");
        assertTrue(actual);
    }

    @Test
    public void isCorrectPhone2() {
        boolean actual = Validation.isCorrectPhone("380955838605");
        assertFalse(actual);
    }

    @Test
    public void isCorrectName() {
        boolean actual = Validation.isCorrectName("VIP-I");
        assertTrue(actual);
    }

    @Test
    public void isCorrectName2() {
        boolean actual = Validation.isCorrectName("VI");
        assertFalse(actual);
    }

    @Test
    public void isCorrectPrice() {
        boolean actual = Validation.isCorrectPrice("20.1");
        assertTrue(actual);
    }

    @Test
    public void isCorrectPrice2() {
        boolean actual = Validation.isCorrectPrice("20ob");
        assertFalse(actual);
    }

    @Test
    public void isCorrectSpecification() {
        boolean actual = Validation.isCorrectSpecification("This is Specification");
        assertTrue(actual);
    }

    @Test
    public void isCorrectSpecification2() {
        boolean actual = Validation.isCorrectSpecification("this is Specification");
        assertFalse(actual);
    }
}