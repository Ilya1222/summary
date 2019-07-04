package ua.nure.shevchenko.provider.utils;

import static java.util.Objects.nonNull;

public class Validation {
    private static final String FILLED_REGEX = "^[А-ЯЁ]{1}[а-яё]{2,20}|[A-Z]{1}[a-z]{2,20}$";
    private static final String LOGIN_REGEX = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{4,20}$";
    private static final String PASSWORD_REGEX = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{6,16}$";
    private static final String PHONE_REGEX = "^0[3-9][0-9][0-9\\s\\(\\)]{7}$";
    private static final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String SPECIFICATION_REGEX = "^[A-ZА-Я].{10,}$";
    private static final String PRICE_REGEX = "^[0-9]{1,3}\\.[0-9]+|[0-9]{1,3}$";
    private static final String NAME_REGEX = "^[А-ЯЁA-Z]{1}[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{2,16}$";

    static boolean isFilled(String... values) {

        for (String value : values) {
            if (!value.matches(FILLED_REGEX)) {
                return false;
            }
        }
        return true;
    }

    static boolean isCorrectEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    static boolean isCorrectLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    static boolean isCorrectPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    static boolean isCorrectPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    static boolean isCorrectName(String tariffName) {
        return tariffName.matches(NAME_REGEX);
    }

    public static boolean isCorrectPrice(String price) {
        return price.matches(PRICE_REGEX);
    }

    public static boolean isCorrectSpecification(String spec) {
        return spec.matches(SPECIFICATION_REGEX);
    }

}
