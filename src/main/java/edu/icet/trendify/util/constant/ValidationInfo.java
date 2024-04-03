package edu.icet.trendify.util.constant;

public final class ValidationInfo {
    private ValidationInfo(){}
    public static final String PASSWORD_PATTERN = "Password criteria:" +
                        "-> Character limit (8-30)" +
                        "-> Must contain at least one lowercase letter (a-z)" +
                        "-> Must contain at least one uppercase letter (A-Z)" +
                        "-> Must contain at least one digit (0-9)" +
                        "-> Must contain at least one special character (@#$%^&+=)" +
                        "-> No whitespace characters allowed";

    public static final String PHONE_NUMBER_PATTERN = "Please enter a valid phone number (ex: 0712345678)";
}
