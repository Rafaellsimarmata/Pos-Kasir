package dotjava.admin.controller;

import dotjava.admin.activityLog;
import dotjava.admin.ItemEntry;
import dotjava.admin.models.ItemEntry_db;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.Initializable;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class itemEntryController implements Initializable {

    @FXML
    private TextField namaTextField;

    @FXML
    private TextField hargaTextField;

    @FXML
    private Button tambahButton;

    @FXML
    private Button simpanButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TableView<ItemEntry> itemTableView;

    @FXML
    private TableColumn<ItemEntry, Integer> idColumn;

    @FXML
    private TableColumn<ItemEntry, String> namaColumn;

    @FXML
    private TableColumn<ItemEntry, Integer> hargaColumn;

    private ObservableList<ItemEntry> items;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            items = FXCollections.observableArrayList(ItemEntry_db.getAllItems());
            itemTableView.setItems(items);

            idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            namaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            hargaColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPrice()).asObject());



            tambahButton.setOnAction(event -> {
                try {
                    handleTambahButton(event);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                simpanButton.setOnAction(this::handleSimpanButton);
            });
            // Add listener for table row selection and handle text field updates
            itemTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    showItemDetails(newSelection);
                } else {
                    clearTextFields();
                }
            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void handleTambahButton(ActionEvent event) throws SQLException {
        String nama = namaTextField.getText();
        int harga = Integer.parseInt(hargaTextField.getText());

        try {
            ItemEntry_db.addItem(nama, harga);
            items.clear();
            items.addAll(ItemEntry_db.getAllItems());
            itemTableView.setItems(items);


            namaTextField.clear();
            hargaTextField.clear();
            System.out.println("Item added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding item: " + e.getMessage());
            // You can also display an error message to the user here
        }
    }
    @FXML
    public void handleSimpanButton(ActionEvent event) {

        // Get selected item from table view (assuming selection is implemented)
        ItemEntry selectedItem = itemTableView.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String newName = namaTextField.getText();
            if (hargaTextField.getText().isEmpty()) {
                System.out.println("Please enter a price for the item.");
                return;
            }
            int newPrice = Integer.parseInt(hargaTextField.getText()); // Assuming user input for price

            // Update item in database
            try {
                ItemEntry_db.updateItem(selectedItem.getId(), newName, newPrice);

                // Assuming ItemEntry has a setter method for price
                selectedItem.setPrice(newPrice);  // Update price in the selected item object

                // Refresh the specific item in the table view (assuming ObservableList)
                int itemIndex = items.indexOf(selectedItem);
                items.set(itemIndex, selectedItem);  // Update the item at the specific index

                System.out.println("Item updated successfully.");

                // Log activity for saving item
//                String activityType = "Update Item (ID: " + selectedItem.getId() + ")";
//                int activityId = activityLog.setUserActivity(activityType);
//                System.out.println("Activity log inserted with ID: " + activityId);

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error updating item: " + e.getMessage());
                // You can also display an error message to the user here
            }
        } else {
            System.out.println("Please select an item to update.");
        }
    }


    private void showItemDetails(ItemEntry selectedItem) {
        if (selectedItem != null) {
            namaTextField.setText(selectedItem.getName());
            hargaTextField.setText(String.valueOf(selectedItem.getPrice())); // Convert int to String
        } else {
            clearTextFields();
        }
    }
    private void clearTextFields() {
        namaTextField.setText("");
        hargaTextField.setText("");
    }
}
