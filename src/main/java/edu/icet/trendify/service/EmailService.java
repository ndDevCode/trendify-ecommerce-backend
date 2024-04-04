package edu.icet.trendify.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
