package dotjava.admin.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;

public class mainController implements Initializable {

    @FXML
    private Button itemEntryButton;

    @FXML
    private Button activityLogButton;

    @FXML
    private Button transactionLogButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //button untuk menuju fungsi admin, menggunakan fxmlloader
        itemEntryButton.setOnAction(event -> switchToItemEntry(event));
        activityLogButton.setOnAction(event -> switchToActivityLog(event));
        transactionLogButton.setOnAction(event -> switchToTransactionLog(event));
    }


    public void switchToItemEntry(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(mainController.class.getResource("/dotjava/admin/itemEntry.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            System.out.println("Admin clicked Item Entry button");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin: Item Entry");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle the IOException
            e.printStackTrace();  // For debugging purposes
            System.out.println("Error loading itemEntry.fxml: " + e.getMessage());
        }
    }

    public void switchToActivityLog(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(mainController.class.getResource("/dotjava/admin/activityLog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            System.out.println("Admin clicked Activity Log button");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin: Activity Log");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Handle the IOException
            System.out.println("Disini Error");
            e.printStackTrace();

            System.out.println("Error loading activityLog.fxml: "  + e.getMessage());
        }
    }

    public void switchToTransactionLog(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(mainController.class.getResource("/dotjava/admin/transactionLog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            System.out.println("Admin clicked Transaction Log button");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Admin: Transaction Log");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading transactionLog.fxml: "  + e.getMessage());
        }
    }
}
