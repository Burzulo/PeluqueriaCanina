package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ActualizarFichaController {

	@FXML
	private ComboBox<String> cmbSexo;

	@FXML
	private ComboBox<String> cmbEsterilizado;

	@FXML
	public void initialize() {
		cmbSexo.getItems().addAll("Macho", "Hembra");
		cmbEsterilizado.getItems().addAll("Si", "No");
	}

	public void guardarActu(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/FichaMascota.fxml"));
			Scene scene = new Scene(root);

			// Obtener la ventana actual y cambiar de escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Ficha Mascota");
			stage.setScene(scene);
			stage.setResizable(false); // No cambiar el tamaño
			stage.centerOnScreen(); // Centrar ventana en pantalla
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
