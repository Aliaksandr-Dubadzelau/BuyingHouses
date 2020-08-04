package by.buyinghouses.repository;

import by.buyinghouses.domain.Accommodation;
import org.springframework.data.repository.CrudRepository;

public interface AccommodationRepository extends CrudRepository<Accommodation, Long> {

    Accommodation findByName(String name);

}
