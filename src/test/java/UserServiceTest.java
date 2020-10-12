import by.buyinghouses.domain.User;
import by.buyinghouses.repository.UserRepository;
import by.buyinghouses.service.MailSenderService;
import by.buyinghouses.service.MessageCreatorService;
import by.buyinghouses.service.UUIDService;
import by.buyinghouses.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    UserRepository userRepository1 = new UserServicePlug1();
    UserRepository userRepository2 = new UserServicePlug2();
    JavaMailSender sender = new MailSenderPlug();
    MailSenderService mailSenderService = new MailSenderService(sender);
    MessageCreatorService messageCreatorService = new MessageCreatorService();
    UUIDService uuidService = new UUIDService();
    PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

    private final User user = new User();

    @Test
    public void addUser_existedUser_false() {

        UserService userService = new UserService(userRepository1, mailSenderService, messageCreatorService, uuidService, passwordEncoder);
        user.setUserName("test");
        user.setEmail("test@mail.ru");

        boolean result = userService.addUser(user);

        Assert.assertFalse(result);
    }

    @Test
    public void addUser_userWithoutEmail_false() {

        UserService userService = new UserService(userRepository2, mailSenderService, messageCreatorService, uuidService, passwordEncoder);
        user.setUserName("test");
        user.setPassword("123");

        boolean result = userService.addUser(user);

        Assert.assertFalse(result);
    }

    @Test
    public void addUser_user_true() {

        UserService userService = new UserService(userRepository2, mailSenderService, messageCreatorService, uuidService, passwordEncoder);
        user.setUserName("testName");
        user.setEmail("testName@mail.com");
        user.setPassword("test");

        boolean result = userService.addUser(user);

        Assert.assertTrue(result);
    }

    @Test
    public void activateUser_putExistingActivationCode_true() {

        UserService userService = new UserService(userRepository1, mailSenderService, messageCreatorService, uuidService, passwordEncoder);
        String testCode = "9dec9dd5-f8cf-4a1e-8181-5d606e3c692e";

        boolean result = userService.activateUser(testCode);

        Assert.assertTrue(result);
    }

    @Test
    public void activateUser_putNotEditingActivationCode_false() {

        UserService userService = new UserService(userRepository2, mailSenderService, messageCreatorService, uuidService, passwordEncoder);
        String wrongCode = "wrong code";

        boolean result = userService.activateUser(wrongCode);

        Assert.assertFalse(result);
    }
}
