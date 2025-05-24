package Controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import Model.Cita;
import Model.Mascota;
import Model.JPAUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ReservaCitaController {

	private EntityManager em;
	private Mascota mascota;
	private int idMascotaActual;

	@FXML
	private Label lblNombreMascota, lblHistoriaClinica, lblNombreDuenio;

	@FXML
	private DatePicker dpFechaCita;

	@FXML
	private TextField txtObservaciones;

	@FXML
	private ComboBox<String> cmbMotivoCita;

	@FXML
	public void initialize() {

		// Establece la fecha actual como predeterminada
		dpFechaCita.setValue(LocalDate.now());
		// Opciones del ComboBox
		cmbMotivoCita.getItems().addAll("BAÑO", "CORTE PELO", "CORTE UÑAS", "LIMPIEZA OIDOS", "FULL SERVICIOS");
	}

	// Almacena ID de la mascota
	public void setIdMascota(int idMascota) {
		this.idMascotaActual = idMascota;
	}

	// -- BOTON RESERVAR CITA >>>>>>
	@FXML
	private void reservaNuevaCita(ActionEvent event) {

		// Validar los campos obligatorios y no esten vacios
		LocalDate fecha = dpFechaCita.getValue();
		String motivo = cmbMotivoCita.getValue();
		String observaciones = txtObservaciones.getText();

		if (fecha == null || motivo == null || motivo.trim().isEmpty() || observaciones.trim().isEmpty()) {
			mostrarAlerta("Campos incompletos", "Por favor, completa todos los campos antes de reservar.");
			return;
		}

		try {
			EntityManager em = JPAUtil.getEntityManager();
			em.getTransaction().begin();

			// Crea y configura una cita
			Cita nuevaCita = new Cita();
			nuevaCita.setFecha(fecha);
			nuevaCita.setMotivo(motivo);
			nuevaCita.setObservaciones(observaciones);

			// Asocia la cita a una mascota
			Mascota mascota = em.find(Mascota.class, idMascotaActual);
			nuevaCita.setMascota(mascota);

			// Guarda, confirma y cierra en la BD
			em.persist(nuevaCita);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		mostrarAlerta("Reserva confirmada", "La cita ha sido reservada correctamente.");

		// Cierra la ventana actual
		Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ventanaActual.close();

		// Abrir la nueva ventana en la Ficha Mascota
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
			Parent root = loader.load();

			// Obtengo el controlador de la nueva escena
			FichaMascotaController controller = loader.getController();
			// Carga la ficha segun la ID de la mascota
			controller.setIdMascota(idMascotaActual);
			
			Stage nuevaVentana = new Stage();
			nuevaVentana.setTitle("Ficha Mascota");
			nuevaVentana.setScene(new Scene(root));
			nuevaVentana.setResizable(false);
			nuevaVentana.centerOnScreen();
			nuevaVentana.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -- DATOS ID MASCOTA DESDE BD >>>>>>
	public void cargarDatos(int idMascota) {
		try {
			em = JPAUtil.getEntityManager();
			mascota = em.find(Mascota.class, idMascota);
			lblNombreMascota.setText(mascota.getNombreMascota());
			lblHistoriaClinica.setText("HC #" + mascota.getIdMascota());
			lblNombreDuenio.setText("Mascota de " + mascota.getUnDuenio().getNombre());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	// -- BOTON SALIR >>>>>>
	@FXML
	public void salirReserva(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
			Parent root = loader.load();

			// Obtengo el controlador de la nueva escena
			FichaMascotaController fichaController = loader.getController();
			// Carga la ficha segun la ID de la mascota
			fichaController.cargarDatos(mascota.getIdMascota());

			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Ficha Mascota");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -- ALERTAS >>>>>>
	private void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}
