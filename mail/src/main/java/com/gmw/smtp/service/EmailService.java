package com.gmw.smtp.service;

public interface EmailService {

    void sendEmail(String recipient, String subject, String message);
}
