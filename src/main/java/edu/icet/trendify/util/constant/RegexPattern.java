package edu.icet.trendify.util.constant;

public final class RegexPattern {
    private RegexPattern(){}
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}$\n";
    public static final String TEXT = "^[a-zA-Z\\\\s]+$ ";
}
