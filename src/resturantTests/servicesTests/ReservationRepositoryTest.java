package resturantTests.servicesTests;

import org.junit.jupiter.api.Test;
import restaurant.reservation.Reservation;
import restaurant.services.ReservationRepository;

import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class ReservationRepositoryTest {
    private ReservationRepository reservationRepository = new ReservationRepository();


    @Test
    public void testAddingReservation()
    {
        int tableNumber = 5;
        String customerName = "brian";
        Date date = Calendar.getInstance().getTime();

        Reservation reservation = new Reservation(tableNumber, customerName, date);
        reservationRepository.saveReservation(reservation);
        var res = reservationRepository.getReservationByCustomerUsername(customerName);
        assertEquals(tableNumber, res.getTableNum());
    }
}