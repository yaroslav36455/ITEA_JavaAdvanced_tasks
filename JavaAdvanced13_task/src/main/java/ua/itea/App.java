package ua.itea;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ua.itea.db.DBConnector;
import ua.itea.db.Database;

public class App extends Application implements Initializable {
	@FXML private TextField loginField;
	@FXML private PasswordField passwordField;
	@FXML private Button confirmButton;

	@Override
	public void start(Stage stage) {		
		try {
			GridPane root = FXMLLoader.load(App.class.getClassLoader().getResource("app.fxml"));

			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		confirmButton.setOnAction(event -> {
			Database database = new Database();
			DBConnector connector = new DBConnector();
			boolean present = false;
			
			try {
				present = database.isPresent(connector.getConnection(),
								             loginField.getText(),
								             passwordField.getText());
				
				Alert alert = new Alert(AlertType.INFORMATION, present ? "Success" : "Failed");
				alert.showAndWait();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
