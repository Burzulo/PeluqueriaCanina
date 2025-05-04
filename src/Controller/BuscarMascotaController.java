package Controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Model.JPAUtil;
import Model.Mascota;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class BuscarMascotaController {

	@FXML
	private TextField txtNombreMascota;
	@FXML
	private ListView<String> listaResultados;

	private List<Mascota> mascotasEncontradas;

	// -- BOTON BUSCAR MASCOTA -->
	@FXML
	private void buscarMascota() {
		String nombre = txtNombreMascota.getText().trim();

		if (nombre.isEmpty()) {
			mostrarAlerta("Por favor, ingrese un nombre para buscar la mascota.");
			return;
		}

		// -- CONSULTA A BD PARA BUSCAR NOMBRES DE MASCOTA
		try {
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Mascota> query = em.createQuery(
					"SELECT m FROM Mascota m WHERE LOWER(m.nombreMascota) = LOWER(:nombre)", Mascota.class);
			query.setParameter("nombre", nombre);

			mascotasEncontradas = query.getResultList();
			em.close();

			if (mascotasEncontradas.isEmpty()) {
				mostrarAlerta("No se encontró ninguna mascota con el nombre ingresado.");
				listaResultados.getItems().clear();
				return;
			}

			// -- PROCESA Y FILTRA LA LISTA DE MASCOTAS
			ObservableList<String> resultados = FXCollections.observableArrayList();
			for (Mascota m : mascotasEncontradas) {
				String duenio = (m.getUnDuenio() != null) ? m.getUnDuenio().getNombre() : "Sin dueño";
				resultados.add("ID: " + m.getIdMascota() + " - " + m.getNombreMascota() + " (Dueño: " + duenio + ")");
			}

			// -- MUESTRA RESULTADO EN INTERFAZ GRAFICA
			listaResultados.setItems(resultados);

		} catch (Exception e) {
			mostrarAlerta("Error al buscar en la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// -- BOTON VER FICHA DE MASCOTA -->
	@FXML
	private void verFichaMascota() {
		if (mascotasEncontradas == null || mascotasEncontradas.isEmpty()) {
			mostrarAlerta("Primero debe realizar una búsqueda y seleccionar una mascota.");
			return;
		}

		int index = listaResultados.getSelectionModel().getSelectedIndex();
		if (index >= 0 && index < mascotasEncontradas.size()) {
			int idMascota = mascotasEncontradas.get(index).getIdMascota();

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
				Parent root = loader.load();

				FichaMascotaController controller = loader.getController();
				controller.cargarDatos(idMascota);

				Stage stage = new Stage();
				stage.setTitle("Ficha Mascota");
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.setOnCloseRequest(evt -> evt.consume());
				stage.show();
				stage.centerOnScreen();

				((Stage) listaResultados.getScene().getWindow()).close();
			} catch (Exception e) {
				e.printStackTrace();
				mostrarAlerta("Error al cargar la ficha de la mascota.");
			}
		} else {
			mostrarAlerta("Selecciona una mascota de la lista.");
		}
	}


	// -- BOTON SALIR -->
	public void salirPrincipal(ActionEvent event) {

		// -- CAMBIA DE INTERFAZ
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Principal");
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

	// -- ALERTA POR FALTA DE INFO -->
	private void mostrarAlerta(String mensaje) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Aviso");
		alerta.setHeaderText(null);
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}
}
