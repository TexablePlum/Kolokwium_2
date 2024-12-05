package org.example.kolokwium_2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RentController {
    private String firstName;
    private String lastName;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;

    public void ConfirmButtonClicked() {
        firstName = firstNameField.getText();
        lastName = lastNameField.getText();
        closeWindow();
    }

    public void CancelButtonClicked() {
        firstName = null;
        lastName = null;
        closeWindow();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
