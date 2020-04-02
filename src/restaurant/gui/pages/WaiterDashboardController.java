package restaurant.gui.pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import restaurant.models.reservation.Reservation;
import restaurant.data.repositories.ReservationRepository;
import restaurant.models.users.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaiterDashboardController implements Initializable {
    public Label welcomeLabel;
    public ListView<String> reservationsListView;

    private ReservationRepository reservationRepository = new ReservationRepository();
    private User user;

    public WaiterDashboardController(User user)
    {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String fullName = user.getName();
        String firstName = fullName.substring(0, fullName.indexOf(' '));
        welcomeLabel.setText(String.format("Hello, %s!", firstName));
        fillList();
    }

    private void fillList()
    {
        ArrayList<Reservation> reservations = reservationRepository.getTodayReservations();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Reservation res:reservations)
        {
            items.add(res.toString());
        }

        reservationsListView.setItems(items);
    }
}
