import by.buyinghouses.domain.Accommodation;
import by.buyinghouses.service.AccommodationService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AccommodationServiceTest {

    private final Accommodation accommodation = new Accommodation();
    private final List<Accommodation> accommodationList = new ArrayList<>();
    private final AccommodationService accommodationService = new AccommodationService();

    @Test
    public void getWaitedAccommodation_putListWithWaitedAccommodation_waitedAccommodationList() {

        accommodation.setWaited(true);
        accommodationList.add(accommodation);

        List<Accommodation> result = accommodationService.getWaitedAccommodation(accommodationList);
        List<Accommodation> expected = accommodationList;

        Assert.assertArrayEquals(result.toArray(), expected.toArray());
    }

    @Test
    public void getWaitedAccommodation_putListWithoutWaitedAccommodation_emptyAccommodationList() {

        accommodation.setWaited(false);
        accommodationList.add(accommodation);

        List<Accommodation> result = accommodationService.getWaitedAccommodation(accommodationList);
        List<Accommodation> expected = new ArrayList<>();

        Assert.assertArrayEquals(result.toArray(), expected.toArray());
    }

    @Test
    public void getWaitedAccommodation_putEmptyAccommodationsList_emptyAccommodationList() {

        List<Accommodation> result = accommodationService.getWaitedAccommodation(new ArrayList<>());
        List<Accommodation> expected = new ArrayList<>();

        Assert.assertArrayEquals(result.toArray(), expected.toArray());
    }
}
