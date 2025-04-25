package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrincipalController {

	public void IrANuevaMascota(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/CargaDatos.fxml"));
			Scene scene = new Scene(root);

			// Obtener la ventana actual y cambiar de escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Cargar Nueva Mascota");
			stage.setScene(scene);
			stage.sizeToScene(); // Ajusta tamaño de ventana
			stage.setResizable(false); // No cambiar el tamaño
			stage.centerOnScreen(); // Centrar ventana en pantalla
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void VerMascota(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/FichaMascota.fxml"));
			Scene scene = new Scene(root);

			// Obtener la ventana actual y cambiar de escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Ficha Mascota");
			stage.setScene(scene);
			stage.sizeToScene(); // Ajusta tamaño de ventana
			stage.setResizable(false); // No cambiar el tamaño
			stage.centerOnScreen(); // Centrar ventana en pantalla
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salirPrincipal(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
			Scene scene = new Scene(root);

			// Obtener la ventana actual y cambiar de escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Principal");
			stage.setScene(scene);
			stage.sizeToScene(); // Ajusta tamaño de ventana
			stage.setResizable(false); // No cambiar el tamaño
			stage.centerOnScreen(); // Centrar ventana en pantalla
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}