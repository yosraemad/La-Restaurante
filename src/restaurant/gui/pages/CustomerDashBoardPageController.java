package restaurant.gui.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import restaurant.appUtils.AppUtilities;
import restaurant.gui.utils.Alerts;
import restaurant.gui.utils.Validations;
import restaurant.services.ReservationRepository;
import restaurant.users.User;

import java.time.LocalDate;

public class CustomerDashBoardPageController {

    public ComboBox numberOfSeatsComboBox;
    public DatePicker datePicker;
    public ComboBox fromHoursComboBox;
    public ComboBox fromMinsComboBox;
    public ComboBox toHoursComboBox;
    public ComboBox toMinsComboBox;
    public Button smokingBtn;
    public Button nonSmokingBtn;
    public Button reserveBtn;

    private Boolean isSmoking = null;

    private Validations validations = new Validations();
    private Alerts alerts = new Alerts();
    private ReservationRepository reservationRepository = new ReservationRepository();

    private User user;

    public CustomerDashBoardPageController(User user)
    {
        this.user = user;
    }

    @FXML
    private void handleSubmitActionButton(ActionEvent actionEvent) {
        if (!validateInput()) return;

        String username = user.getUsername();
        int numberOfSeats = Integer.parseInt(numberOfSeatsComboBox.getValue().toString());
        int fromHours = Integer.parseInt(fromHoursComboBox.getValue().toString());
        int fromMinutes = Integer.parseInt(fromMinsComboBox.getValue().toString());
        int toHours = Integer.parseInt(toHoursComboBox.getValue().toString());
        int toMinutes = Integer.parseInt(toMinsComboBox.getValue().toString());

        LocalDate localDate = datePicker.getValue();

        var reservationDate = AppUtilities.getFullDate(localDate, fromHours, fromMinutes);
        var endReservationDate = AppUtilities.getFullDate(localDate, toHours, toMinutes);

        var reservation = reservationRepository.makeReservation
                (username, numberOfSeats, reservationDate, endReservationDate, isSmoking);
        if (reservation == null) {
            alerts.showErrorAlert("FULLY BOOKED", "We apologize for not having a table that meets this requirements");
            return;
        }
        reservationRepository.saveReservation(reservation);
        alerts.showSuccessAlert("Booking Completed", "You've booked successfully!");
    }

    @FXML
    private void handleOnSmokingBtnClick(ActionEvent actionEvent) {
        isSmoking = true;
    }

    @FXML
    private void handleOnNonSmokingBtnClick(ActionEvent actionEvent) {
        isSmoking = false;
    }



    private boolean validateInput()
    {
        var v1 = validations.validateEmptyComboBox(fromHoursComboBox);
        var v2 = validations.validateEmptyComboBox(fromMinsComboBox);
        var v3 = validations.validateEmptyComboBox(toHoursComboBox);
        var v4 = validations.validateEmptyComboBox(toMinsComboBox);
        var v5 = validations.validateSmokingType(isSmoking);
        var v6 = validations.validateDate(datePicker);


        return v1 && v2 && v3 && v4 && v5 && v6;

    }

}
