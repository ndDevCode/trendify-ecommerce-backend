package edu.icet.trendify.util.constant;

public final class ValidationInfo {
    private ValidationInfo(){}

    public static final String PASSWORD_PATTERN = """
    
                    This regex enforces the following password criteria:
                
                        Minimum length of 8 characters
                        Must contain at least one lowercase letter
                        Must contain at least one uppercase letter
                        Must contain at least one digit
                        Must contain at least one special character
                        No whitespace characters allowed
                        
                        """;
}
