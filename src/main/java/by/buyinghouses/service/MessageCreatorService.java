package by.buyinghouses.service;

import by.buyinghouses.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MessageCreatorService {

    public String createMessage(Messages message) {

        String result;

        switch (message) {
            case EMPTY_PASSWORD_MESSAGE:
                result = "Please fill field repeated password";
                break;
            case DIFFERENT_PASSWORD_MESSAGE:
                result = "Passwords are different";
                break;
            case USER_EXIST_MESSAGE:
                result = "User with the same email/login already exist";
                break;
            case SUCCESSFULLY_ACTIVATED_MESSAGE:
                result = "User successfully activated";
                break;
            case ACTIVATION_CODE_NOT_FOUND_MESSAGE:
                result = "Activation code is not found";
                break;
            case ACCOMMODATION_EXIST_MESSAGE:
                result = "Accommodation with the same name already exists";
                break;
            default:
                result = "Bad request";
                break;
        }

        return result;
    }

    public String createEmailMessage(User user) {

        String emailMessage;

        if (user != null && !StringUtils.isEmpty(user.getEmail())) {
            emailMessage = String.format(
                    "Hello, %s! "
                            + "\n"
                            + "Welcome to Buyinghouses. Please, visit our link: http://localhost:8080/activate/%s",
                    user.getUserName(),
                    user.getActivationCode());
        }
        else{
            emailMessage = "Something go wrong";
        }

        return emailMessage;

    }
}
