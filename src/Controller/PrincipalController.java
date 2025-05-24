package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrincipalController {

	// -- BOTON NUEVA MASCOTA >>>>>>
	public void irNuevaMascota(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/CargaDatos.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Cargar Nueva Mascota");
			stage.setScene(scene);
			stage.sizeToScene();
			stage.setResizable(false);
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -- BOTON BUSCAR >>>>>>
	public void buscarMascota(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/BuscarMascota.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Buscar Mascota");
			stage.setScene(scene);
			stage.sizeToScene();
			stage.setResizable(false);
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -- BOTON SALIR >>>>>>
	public void salirPrincipal(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Login");
			stage.setScene(scene);
			stage.sizeToScene();
			stage.setResizable(false);
			if (stage.getOnCloseRequest() != null) {
				stage.setOnCloseRequest(null);
			}
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}