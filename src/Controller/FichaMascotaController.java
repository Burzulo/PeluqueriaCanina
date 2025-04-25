package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FichaMascotaController {

	public void actualizarDatos(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/ActualizarFicha.fxml"));
			Scene scene = new Scene(root);

			// Obtener la ventana actual y cambiar de escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Actualizar Ficha");
			stage.setScene(scene);
			stage.setResizable(false); // No cambiar el tamaño
			stage.centerOnScreen(); // Centrar ventana en pantalla
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salirFicha(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
			Scene scene = new Scene(root);

			// Obtener la ventana actual y cambiar de escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Principal");
			stage.setScene(scene);
			stage.setResizable(false); // No cambiar el tamaño
			stage.centerOnScreen(); // Centrar ventana en pantalla
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
