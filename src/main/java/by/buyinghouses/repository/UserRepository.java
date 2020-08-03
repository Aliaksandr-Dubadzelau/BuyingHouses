package by.buyinghouses.repository;

import by.buyinghouses.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String username);

    User findByEmailOrUserName(String email, String username);

    User findByActivationCode(String code);
}
