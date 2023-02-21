package com.gmw.reader.tos;

public record Config(DatabaseConfig databaseConfig,
                     SMTPConfig smtpConfig,
                     SecurityConfig securityConfig) {
}
