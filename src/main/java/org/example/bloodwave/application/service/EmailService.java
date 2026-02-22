package org.example.bloodwave.application.service;

public interface EmailService {

    public void sendEmail(String to, String subject, String body);

    public void sendResetPasswordEmail(String email, String token);
}