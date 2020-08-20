package by.buyinghouses.service;

import by.buyinghouses.domain.User;
import org.springframework.stereotype.Service;

@Service
public class MessageCreatorService {

    public String createMessage(Messages message) {

        switch (message) {
            case EMPTY_PASSWORD_MESSAGE:
                return "Please fill field repeated password";
            case DIFFERENT_PASSWORD_MESSAGE:
                return "Passwords are different";
            case USER_EXIST_MESSAGE:
                return "User with the same email/login already exist";
            case SUCCESSFULLY_ACTIVATED_MESSAGE:
                return "User successfully activated";
            case ACTIVATION_CODE_NOT_FOUND_MESSAGE:
                return "Activation code is not found";
            case ACCOMMODATION_EXIST_MESSAGE:
                return "Accommodation with the same name already exists";
            default:
                return "Bad request";
        }

    }

    public String createEmailMessage(User user) {
        return String.format(
                "Hello, %s! "
                        + "\n"
                        + "Welcome to Buyinghouses. Please, visit our link: http://localhost:8080/activate/%s",
                user.getUserName(),
                user.getActivationCode()
        );
    }
}
