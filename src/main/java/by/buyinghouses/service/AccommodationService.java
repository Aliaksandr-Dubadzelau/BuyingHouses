package by.buyinghouses.service;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.domain.User;
import by.buyinghouses.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {

    @Autowired
    AccommodationRepository accommodationRepository;

    public boolean addAccommodation(Accommodation accommodation){

        boolean isAdded = true;

        Accommodation accommodationFromDB = accommodationRepository.findByName(accommodation.getName());

        if(accommodationFromDB != null){
            isAdded = false;
        }
        else{
            accommodationRepository.save(accommodation);
        }

        return isAdded;

    }

    public Iterable<Accommodation> findAccommodations() {
        return accommodationRepository.findAll();
    }

    public void acceptAccommodation(String accommodationName) {

        Accommodation accommodation = accommodationRepository.findByName(accommodationName);
        accommodation.setWaited(false);
        accommodationRepository.save(accommodation);

    }

    public void deleteAccommodation(String accommodationName) {

        accommodationRepository.deleteByName(accommodationName);

    }

    public void fillAccommodation(Accommodation accommodation, User user, Boolean isFurniture, Boolean isInternet){

        accommodation.setOwner(user);
        accommodation.setInternet(isInternet);
        accommodation.setFurniture(isFurniture);
        accommodation.setWaited(true);
        accommodation.setRate(0.0f);

    }

}
