package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.tos.EmailDTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.user.DBUserService;
import com.gmw.smtp.service.EmailService;
import com.gmw.smtp.service.EmailServiceImpl;
import com.gmw.user.tos.ExistingUserTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ForgotPasswordActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new SecureRandom();
    private final EmailDTO emailDTO;
    private final PasswordEncoder encoder;

    @Override
    protected Void realExecute() throws ResourceNotFoundException, ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBUserService dbUserService = serviceManager.getDbUserService();
            ExistingUserTO user = dbUserService.obtainUserByEmail(emailDTO.getEmail());

            if (user == null) {
                throw new ResourceNotFoundException();
            }

            String newPassword = generateSecureRandomPassword();
            user.setPassword(encoder.encode(newPassword));

            dbUserService.updateUser(user);

            EmailService emailService = new EmailServiceImpl();
            emailService.sendEmail(user.getEmail(), "New password", "New password was generated: " + newPassword);
        } catch (Exception e) {
            LOGGER.error("Cannot update password user!");
            throw new ResourceNotUpdatedException();

        }
        return null;
    }

    private Stream<Character> getRandomSpecialChars(int count) {
        IntStream specialChars = RANDOM.ints(count, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }

    private String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(3),
                Stream.concat(getRandomSpecialChars(3),
                        Stream.concat(getRandomAlphabets(5, true), getRandomAlphabets(4, false))));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        return charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private Stream<Character> getRandomAlphabets(int count, boolean upperCase) {

        IntStream characters = null;
        if (upperCase) {
            characters = RANDOM.ints(count, 65, 90);
        } else {
            characters = RANDOM.ints(count, 97, 122);
        }
        return characters.mapToObj(data -> (char) data);
    }

    private Stream<Character> getRandomNumbers(int count) {
        IntStream numbers = RANDOM.ints(count, 48, 57);
        return numbers.mapToObj(data -> (char) data);
    }
}
