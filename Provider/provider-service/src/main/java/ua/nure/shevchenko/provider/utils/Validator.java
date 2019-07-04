package ua.nure.shevchenko.provider.utils;

import static java.util.Objects.nonNull;

public class Validator {

    public static boolean validateUserParameters(String login, String password, String firstName, String surName , String lastName ,String email , String phone) {
        return (Validation.isFilled(firstName ,lastName ,surName) &&
                Validation.isCorrectLogin(login) &&
                Validation.isCorrectPassword(password) &&
                Validation.isCorrectEmail(email) &&
                Validation.isCorrectPhone(phone));
    }

    public static boolean validateTariffParameters( String specification, String name , String price ) {
        return Validation.isCorrectSpecification(specification) && Validation.isCorrectName(name) && Validation.isCorrectPrice(price) ;
    }

    public static boolean isNotNull(Object...objects){
        for (Object value : objects) {
            if (!nonNull(value)){
                return false;
            }
        }
        return true;
    }

    public static boolean validateTariffParameters(  String price ) {
        return  Validation.isCorrectPrice(price);
    }

}
