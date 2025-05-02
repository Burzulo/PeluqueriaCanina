package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class LoginController {

	// -- BOTON LOGIN -->
	public void irAPrincipal(ActionEvent event) {

		// -- CAMBIA DE INTERFAZ
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Principal");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.sizeToScene();
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}