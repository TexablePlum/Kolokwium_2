package org.example.kolokwium_2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryController {

    public static ObservableList<Book>Books = FXCollections.observableArrayList();

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, Long> isbnColumn;
    @FXML
    private TableColumn<Book, Integer> numberColumn;
    @FXML
    private TableColumn<Book, String> actual_readerColumn;


    public void initialize() {
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        isbnColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getIsbn()).asObject());
        numberColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumber()).asObject());
        actual_readerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReader()));

        booksTable.setItems(Books);
    }

    public void AddMenuItemClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Dodaj nową książkę");
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RemoveMenuItemClick() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Czy na pewno chcesz usunąć wybraną książkę?", ButtonType.YES, ButtonType.NO);
            confirmationAlert.setTitle("Potwierdzenie usunięcia");
            confirmationAlert.showAndWait();

            if (confirmationAlert.getResult() == ButtonType.YES) {
                Books.remove(selectedBook);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak zaznaczenia");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć książkę do usunięcia.");
            alert.showAndWait();
        }
    }

    public void SortByTitleMenuItemClick() {
        Books.sort((book1, book2) -> book1.getTitle().compareToIgnoreCase(book2.getTitle()));
    }

    public void SortByAuthorMenuItemClick() {
        Books.sort((book1, book2) -> book1.getAuthor().compareToIgnoreCase(book2.getAuthor()));
    }

    public void RentMenuItemClick() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            if (selectedBook.getReader() == null || selectedBook.getReader().isEmpty()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("rent_view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    RentController controller = fxmlLoader.getController();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Wypożyczenie książki");
                    stage.setScene(scene);
                    stage.showAndWait();

                    String firstName = controller.getFirstName();
                    String lastName = controller.getLastName();

                    if (firstName != null && lastName != null) {
                        selectedBook.setReader(firstName + " " + lastName);
                        booksTable.refresh();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                showAlert("Książka jest już wypożyczona", "Wybrana książka jest już wypożyczona.");
            }
        } else {
            showAlert("Brak zaznaczenia", "Proszę zaznaczyć książkę do wypożyczenia.");
        }
    }

    public void ReleaseMenuItemClick() {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            if (selectedBook.getReader() != null && !selectedBook.getReader().isEmpty()) {
                selectedBook.setReader(null);
                booksTable.refresh();
            } else {
                showAlert("Książka nie jest wypożyczona", "Wybrana książka nie jest obecnie wypożyczona.");
            }
        } else {
            showAlert("Brak zaznaczenia", "Proszę zaznaczyć książkę do zwolnienia.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void CloseApplication() {

    }

}