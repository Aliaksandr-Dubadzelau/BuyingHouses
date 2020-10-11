import by.buyinghouses.domain.User;
import by.buyinghouses.service.MessageCreatorService;
import by.buyinghouses.service.Messages;
import org.junit.Assert;
import org.junit.Test;

public class MessageCreatorServiceTest {

    private final MessageCreatorService creatorService = new MessageCreatorService();
    private final User user = new User();

    @Test
    public void createMessage_getEmptyPasswordMessage_PleaseFillFieldRepeatedPassword(){

        String result = creatorService.createMessage(Messages.EMPTY_PASSWORD_MESSAGE);
        String expected = "Please fill field repeated password";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createMessage_getDifferentPasswordMessage_PasswordsAreDifferent(){

        String result = creatorService.createMessage(Messages.DIFFERENT_PASSWORD_MESSAGE);
        String expected = "Passwords are different";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createMessage_getUserExistMessage_UserWithTheSameEmailLoginAlreadyExist(){

        String result = creatorService.createMessage(Messages.USER_EXIST_MESSAGE);
        String expected = "User with the same email/login already exist";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createMessage_getSuccessfullyActivatedMessage_UserSuccessfullyActivated(){

        String result = creatorService.createMessage(Messages.SUCCESSFULLY_ACTIVATED_MESSAGE);
        String expected = "User successfully activated";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createMessage_getActivationCodeNotFoundMessage_ActivationCodeIsNotFound(){

        String result = creatorService.createMessage(Messages.ACTIVATION_CODE_NOT_FOUND_MESSAGE);
        String expected = "Activation code is not found";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createMessage_getAccommodationExistMessage_AccommodationWithTheSameNameAlreadyExists(){

        String result = creatorService.createMessage(Messages.ACCOMMODATION_EXIST_MESSAGE);
        String expected = "Accommodation with the same name already exists";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createMessage_getDefaultMessage_BadRequest(){

        String result = creatorService.createMessage(Messages.DEFAULT);
        String expected = "Bad request";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createEmailMessage_sentCorrectUser_MessageWithActivatedLink(){

        user.setUserName("testUserName");
        user.setEmail("testMail@mail.com");

        String result = creatorService.createEmailMessage(user);
        String expected = String.format(
                "Hello, %s! "
                        + "\n"
                        + "Welcome to Buyinghouses. Please, visit our link: http://localhost:8080/activate/%s",
                user.getUserName(),
                user.getActivationCode());

        Assert.assertEquals(expected, result);
    }

    @Test
    public void createEmailMessage_sentWrongUser_SomethingGoWrong(){

        String result = creatorService.createEmailMessage(null);
        String expected = "Something go wrong";

        Assert.assertEquals(expected, result);
    }
}
