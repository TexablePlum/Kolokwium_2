package org.example.kolokwium_2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {
    @FXML
    private TextField authorField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField isbnField;
    @FXML
    private Button applyButton;
    @FXML
    private Button cancelButton;

    public void ApplyButtonClicked() {
        try {
            // Pobranie i konwersja danych
            String author = authorField.getText();
            String title = titleField.getText();
            long isbn = Long.parseLong(isbnField.getText());
            int number = LibraryController.Books.size() + 1;

            // Tworzenie obiektu Book
            Book book = new Book(author, title, isbn, number);

            // Zapisanie obiektu do listy
            LibraryController.Books.add(book);

            //Zamykanie okna
            Stage stage = (Stage) applyButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void CencelButtonClicked() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

}
