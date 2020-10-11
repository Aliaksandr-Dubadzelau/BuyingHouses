package by.buyinghouses.service;

import by.buyinghouses.domain.Role;
import by.buyinghouses.domain.User;
import by.buyinghouses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {


    private final static boolean ACTIVATED = true;
    private final static boolean NOT_ACTIVATED = false;
    private final static boolean ADDED = true;
    private final static boolean NOT_ADDED = false;
    private final static String CONFIRMED_ACTIVATION_CODE = null;
    private final static String MAIL_SUBJECT = "Activation code";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private MessageCreatorService messageCreatorService;
    @Autowired
    private UUIDService uuidService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       MailSenderService mailSenderService,
                       MessageCreatorService messageCreatorService,
                       UUIDService uuidService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
        this.messageCreatorService = messageCreatorService;
        this.uuidService = uuidService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserService() {

    }

    public Iterable<User> findUsers() {
        return userRepository.findAll();
    }

    public User findUser(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void deleteUser(String userName) {
        userRepository.deleteByName(userName);
    }

    public boolean addUser(User user) {

        String userEmail = user.getEmail();
        String userName = user.getUserName();
        User userByEmailFromDB = userRepository.findByEmail(userEmail);
        User userByNameFromDB = userRepository.findByUserName(userName);
        boolean isAdded;

        if (userByEmailFromDB != null || userByNameFromDB != null) {
            isAdded = NOT_ADDED;
        } else {
            prepareUserToSaving(user);
            isAdded = NOT_ADDED;

            if (!StringUtils.isEmpty(user.getEmail())) {

                isAdded = ADDED;
                String message = messageCreatorService.createEmailMessage(user);

                mailSenderService.send(userEmail, MAIL_SUBJECT, message);
                save(user);
            }
        }

        return isAdded;
    }

    public boolean activateUser(String code) {

        User user = userRepository.findByActivationCode(code);
        boolean isActivated = ACTIVATED;

        if (user == null) {
            isActivated = NOT_ACTIVATED;
        } else {
            activate(user);
            save(user);
        }

        return isActivated;
    }

    private void prepareUserToSaving(User user) {

        String activationCode = uuidService.createUUID();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(NOT_ACTIVATED);
        user.setActivationCode(activationCode);
        user.setPassword(encodedPassword);
    }

    private void activate(User user) {

        user.setActive(ACTIVATED);
        user.setActivationCode(CONFIRMED_ACTIVATION_CODE);
    }

    private void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }
}
