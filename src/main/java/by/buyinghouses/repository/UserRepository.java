package by.buyinghouses.repository;

import by.buyinghouses.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
