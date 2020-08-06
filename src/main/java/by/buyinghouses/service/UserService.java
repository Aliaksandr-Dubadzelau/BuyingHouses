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
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSenderService senderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }

    public boolean addUser(User user) {

        boolean isAdded = true;

        User userFromDB = userRepository.findByEmailOrUserName(user.getEmail(), user.getUserName());

        if (userFromDB != null) {
            isAdded = false;
        } else {
            user.setRoles(Collections.singleton(Role.ADMIN));
            user.setActive(true);
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "Hello, %s! \n" +
                                "Welcome to Buyinghouses. Please, visit our link: http://localhost:8080/activate/%s",
                        user.getUserName(),
                        user.getActivationCode()
                );

                senderService.send(user.getEmail(), "Activation code", message);
            }
        }

        return isAdded;
    }

    public boolean activateUser(String code) {

        boolean isActivated = true;

        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            isActivated = false;
        } else {
            user.setActivationCode(null);
            userRepository.save(user);
        }

        return isActivated;
    }


}
