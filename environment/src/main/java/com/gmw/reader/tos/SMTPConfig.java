package com.gmw.reader.tos;

public record SMTPConfig(String host,
                         boolean auth,
                         boolean tls,
                         int port,
                         String username,
                         String password) {
}
