package restaurant.gui.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restaurant.gui.guiUtils.Alerts;
import restaurant.gui.guiUtils.Navigation;
import restaurant.gui.guiUtils.Validations;
import restaurant.data.repositories.UserRepository;
import restaurant.models.users.UserRole;

/*
Controller for customer sign up page
 */
public class RegisterController {

    public Button signInBtn;
    public TextField fullNameTextField;
    public TextField usernameTextField;
    public PasswordField passwordTextField;


    private UserRepository userRepository = new UserRepository();

    private Validations validations = new Validations();
    private Navigation navigation = new Navigation();
    private Alerts alerts = new Alerts();

    @FXML
    private void handleSubmitActionButton(ActionEvent actionEvent) {

        if (!validateForm()) return;

        String fullName = fullNameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        var user = userRepository.signUp(username, password, fullName, UserRole.CUSTOMER);
        if (user == null)
            alerts.showErrorAlert("Existing username", "A user with the same username already exists");
        else {
            alerts.showSuccessAlert("Registered Successfully", "You have signed up successfully please login with your credentials");
            navigateToLoginPage();
        }
    }

    @FXML
    private void handleSignInActionButton(ActionEvent actionEvent) {
        navigateToLoginPage();
    }

    //after finishing sign up the customer returns to the sign up page
    private void navigateToLoginPage()
    {
        Stage stage;
        stage = (Stage)signInBtn.getScene().getWindow();
        navigation.showPageWithoutController("../pages/LoginPage.fxml", "Login", 1200, 700, stage);
    }

    //validations
    private boolean validateForm()
    {
        var v1 = validations.validateFullNameTextField(fullNameTextField);
        var v2 = validations.validateUsernameTextField(usernameTextField);
        var v3 = validations.validatePasswordField(passwordTextField);

        return v1 && v2 && v3;
    }
}
