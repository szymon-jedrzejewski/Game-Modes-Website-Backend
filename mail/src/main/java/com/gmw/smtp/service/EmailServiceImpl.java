package com.gmw.smtp.service;

import com.gmw.reader.JsonConfigReader;
import com.gmw.reader.tos.SMTPConfig;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void sendEmail(String recipient, String subject, String msg) {
        SMTPConfig smtpConfig = JsonConfigReader.readSMTPConfig();
        Properties properties = prepareProperties(smtpConfig);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpConfig.username(), smtpConfig.password());
            }
        });

        try {
            LOGGER.info("Sending an email...");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(smtpConfig.username()));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            LOGGER.info("Email sent!");
        } catch (MessagingException e) {
            LOGGER.error("Cannot send email to: " + recipient);
        }
    }

    private Properties prepareProperties(SMTPConfig smtpConfig) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", smtpConfig.auth());
        properties.put("mail.smtp.starttls.enable", smtpConfig.tls());
        properties.put("mail.smtp.host", smtpConfig.host());
        properties.put("mail.smtp.port", smtpConfig.port());
        properties.put("mail.smtp.ssl.trust", smtpConfig.host());

        return properties;
    }
}
