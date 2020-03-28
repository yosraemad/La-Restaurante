package restaurant.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import restaurant.gui.utils.Alerts;
import restaurant.gui.utils.Validations;
import restaurant.services.UserRepository;

public class MainPageController {

    public TextField usernameTextField;
    public PasswordField passwordTextField;

    private Validations validations = new Validations();
    private Alerts alerts = new Alerts();

    private UserRepository userRepository = new UserRepository();

    public void handleSubmitActionButton(ActionEvent actionEvent) {

        var v1 = validations.validateEmptyTextField(usernameTextField);
        var v2 = validations.validateEmptyTextField(passwordTextField);

        if (!v1 || !v2) return;

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        var res = userRepository.login(username, password);
        if (!res) { alerts.showErrorAlert("False Credentials", "Wrong username or password"); }

        // TODO: NAVIGATE TO THE DASHBOARD

    }

    public void handleSignUpActionButton(ActionEvent actionEvent) {
        //TODO: NAVIGATE TO THE SIGN UP PAGE
    }
}
