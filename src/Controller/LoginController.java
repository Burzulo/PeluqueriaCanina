package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class LoginController {

	public void irAPrincipal(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
			Scene scene = new Scene(root);

			// Obtener la ventana actual y cambiarle la escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Principal");
			stage.setScene(scene);
			stage.setResizable(false); // No cambia el tamaño
			stage.sizeToScene(); // Ajusta tamaño de ventana
			stage.centerOnScreen(); // Centra ventana en pantalla
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}