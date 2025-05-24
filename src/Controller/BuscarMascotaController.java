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

	// -- INICIALIZA LA LISTA >>>>>>
	@FXML
	public void initialize() {
		cargarTodasLasMascotas();

		// >> Si el campo queda vacio se muestran la lista completa nuevamente
		txtNombreMascota.textProperty().addListener((obs, oldText, newText) -> {
			if (newText.isEmpty()) {
				cargarTodasLasMascotas();
			}
		});
	}

	// -- BOTÓN BUSCAR MASCOTA >>>>>>
	@FXML
	private void buscarMascota() {
		String nombre = txtNombreMascota.getText().trim(); // texto que se rellena en el buscador de mascotas

		if (nombre.isEmpty()) {
			mostrarAlerta("Ingrese un nombre para buscar la mascota.");
			return;
		}

		try {
			EntityManager em = JPAUtil.getEntityManager();

			// >> CONSULTA EN LA BD DE LA BUSQUEDA
			TypedQuery<Mascota> query = em.createQuery(
					"SELECT m FROM Mascota m WHERE LOWER(m.nombreMascota) LIKE LOWER(:nombre)", Mascota.class);
			query.setParameter("nombre", "%" + nombre + "%"); // Para búsqueda parcial anotando solo una letra
			mascotasEncontradas = query.getResultList();
			em.close();

			// Alerta por si no hay coincidencia en la busqueda
			if (mascotasEncontradas.isEmpty()) {
				listaResultados.getItems().clear();
				mostrarAlerta("No se encontró ninguna mascota con el nombre ingresado.");
				return;
			}

			mostrarResultadosEnLista();

		} catch (Exception e) {
			mostrarAlerta("Error al buscar en la base de datos: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// -- CARGA TODAS LAS MASCOTAS DE LA BD >>>>>>
	private void cargarTodasLasMascotas() {
		try {
			EntityManager em = JPAUtil.getEntityManager();

			// Consulta a la BD
			TypedQuery<Mascota> query = em.createQuery("SELECT m FROM Mascota m", Mascota.class);
			mascotasEncontradas = query.getResultList();
			em.close();

			// tras la consulta a BD muestra la lista de mascotas al inicializar
			mostrarResultadosEnLista();

		} catch (Exception e) {
			mostrarAlerta("Error al cargar mascotas: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// -- MUESTRA LA LISTA EN LA INTERFAZ >>>>>>
	private void mostrarResultadosEnLista() {
		ObservableList<String> resultados = FXCollections.observableArrayList();
		for (Mascota m : mascotasEncontradas) {
			String duenio = (m.getUnDuenio() != null) ? m.getUnDuenio().getNombre() : "Sin dueño";
			resultados.add("ID: " + m.getIdMascota() + " - " + m.getNombreMascota() + " (Dueño: " + duenio + ")");
		}
		listaResultados.setItems(resultados);
	}

	// -- BOTÓN VER FICHA DE MASCOTA >>>>>>
	@FXML
	private void verFichaMascota() {

		// Comprueba que se haya hecho la busqueda de la mascota
		if (mascotasEncontradas == null || mascotasEncontradas.isEmpty()) {
			mostrarAlerta("Primero debe realizar una búsqueda y seleccionar una mascota.");
			return;
		}

		// Se obtiene el ID de la mascota
		int index = listaResultados.getSelectionModel().getSelectedIndex();
		if (index >= 0 && index < mascotasEncontradas.size()) {
			int idMascota = mascotasEncontradas.get(index).getIdMascota();

			// >> CAMBIA DE INTERFAZ
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
				Parent root = loader.load();

				// Obtengo el controlador de la nueva escena
				FichaMascotaController controller = loader.getController();
				// Carga la ficha segun la ID de la mascota
				controller.setIdMascota(idMascota);

				
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
			}
		} else {
			mostrarAlerta("Selecciona una mascota de la lista.");
		}
	}

	// -- BOTÓN SALIR >>>>>>
	public void salirPrincipal(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
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

	// -- METODO ALERTAS >>>>>>
	private void mostrarAlerta(String mensaje) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle("Aviso");
		alerta.setHeaderText(null);
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}
}
