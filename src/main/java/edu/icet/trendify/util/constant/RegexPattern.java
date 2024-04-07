package edu.icet.trendify.util.constant;

public final class RegexPattern {
    private RegexPattern(){}
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])^\\S{8,30}$";
    public static final String TEXT = "^[a-zA-Z]+$";
    public static final String PHONE_NUMBER = "^0[0-9]{9}$";
}
