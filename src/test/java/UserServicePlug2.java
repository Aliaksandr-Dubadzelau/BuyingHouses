import by.buyinghouses.domain.User;
import by.buyinghouses.repository.UserRepository;

import java.util.Optional;

public class UserServicePlug2 implements UserRepository {

    @Override
    public User findByUserName(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByActivationCode(String code) {
        return null;
    }

    @Override
    public void deleteByName(String userName) {

    }

    @Override
    public <S extends User> S save(S s) {
        return null;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
