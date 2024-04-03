package edu.icet.trendify.security;

public class SecurityConstants {
    private SecurityConstants(){}
    public static final long JWT_EXPIRATION = 60000; //28800000; // 8 Hours
    public static final long JWT_REFRESH_EXPIRATION = 240000; //86400000; // 24 Hours
}