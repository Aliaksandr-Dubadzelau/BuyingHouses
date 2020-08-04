package by.buyinghouses.service;

import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {

    @Autowired
    AccommodationRepository accommodationRepository;

    public boolean add(Accommodation accommodation){

        boolean isAdded = true;

        Accommodation accommodationFromDB = accommodationRepository.findByName(accommodation.getName());

        if(accommodationFromDB != null){
            isAdded = false;
        }
        else{
            accommodation.setWaited(true);
            accommodation.setRate(0.0f);

            accommodationRepository.save(accommodation);
        }

        return isAdded;

    }

    //delete accommodation
    //buy accommodation

}
