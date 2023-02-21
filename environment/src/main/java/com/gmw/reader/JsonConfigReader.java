package com.gmw.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmw.reader.tos.Config;
import com.gmw.reader.tos.DatabaseConfig;
import com.gmw.reader.tos.SMTPConfig;
import com.gmw.reader.tos.SecurityConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonConfigReader {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String CONFIG_FILE = System.getProperty("config");

    public static DatabaseConfig readDatabaseConfig() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            Config config = mapper.readValue(new File(CONFIG_FILE), Config.class);

            return config.databaseConfig();
        } catch (FileNotFoundException e) {
            LOGGER.error("Cannot find the config file!", e);
        } catch (IOException e) {
            LOGGER.error("Error during reading the config file!", e);
        }
        return null;
    }

    public static SMTPConfig readSMTPConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Config config = mapper.readValue(new File(CONFIG_FILE), Config.class);

            return config.smtpConfig();
        } catch (FileNotFoundException e) {
            LOGGER.error("Cannot find the config file!", e);
        } catch (IOException e) {
            LOGGER.error("Error during reading the config file!", e);
        }
        return null;
    }

    public static SecurityConfig readSecurityConfig() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Config config = mapper.readValue(new File(CONFIG_FILE), Config.class);

            return config.securityConfig();
        } catch (FileNotFoundException e) {
            LOGGER.error("Cannot find the config file!", e);
        } catch (IOException e) {
            LOGGER.error("Error during reading the config file!", e);
        }
        return null;
    }
}
