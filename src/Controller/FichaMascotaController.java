package Controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Model.Cita;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class FichaMascotaController {

	private int idMascota, idMascotaActual;

	// -- MASCOTA >>>>>>
	@FXML
	private Label lblNombreMascota, lblHistoriaClinica;
	@FXML
	private Label lblFechaNacimiento, lblSexo, lblRaza, lblColor, lblEsterilizado, lblAlergia, lblMedicacion,
			lblCirugia, lblPesoActual, lblObservaciones;

	// -- DUEÑO >>>>>>
	@FXML
	private Label lblNombreDuenio, lblTelefono, lblEmail, lblDireccion;

	@FXML
	private ListView<Cita> lvHistorialCitas;

	// -- METODO ID MASCOTA Y CARGA DE DATOS >>>>>>
	public void cargarDatos(int idMascota) {
		this.idMascotaActual = idMascota;

		// Se obtiene la mascota desde BD
		EntityManager em = JPAUtil.getEntityManager();
		Mascota mascota = em.find(Mascota.class, idMascotaActual);

		// Muestra los datos de la mascota y su dueño en pantalla
		if (mascota != null) {
			lblNombreMascota.setText(mascota.getNombreMascota().toUpperCase());
			lblHistoriaClinica.setText("HC #" + mascota.getIdMascota());
			lblFechaNacimiento.setText("Fecha Nacimiento: " + mascota.getFechaNacimiento().toString());
			lblSexo.setText("Sexo: " + mascota.getSexo());
			lblRaza.setText("Raza: " + mascota.getRaza());
			lblColor.setText("Color: " + mascota.getColor());
			lblPesoActual.setText("Peso: " + mascota.getPesoActual() + " kg.");
			lblEsterilizado.setText("Esterilizado: " + mascota.getEsterilizado());
			lblAlergia.setText("Alergias: " + mascota.getAlergia());
			lblMedicacion.setText("Medicación Habitual: " + mascota.getMedicacion());
			lblCirugia.setText("Intervenciones Quirúrgicas: " + mascota.getCirugia());
			lblObservaciones.setText("Observaciones: " + mascota.getObservaciones());

			if (mascota.getUnDuenio() != null) {
				lblNombreDuenio.setText("Nombre: " + mascota.getUnDuenio().getNombre());
				lblTelefono.setText("Teléfono: " + mascota.getUnDuenio().getTelefono());
				lblEmail.setText("Email: " + mascota.getUnDuenio().getEmail());
				lblDireccion.setText("Dirección: " + mascota.getUnDuenio().getDireccion());
			}
		}
		em.close();
	}

	// -- BOTON RESERVA DE CITAS >>>>>>
	public void reservarCita(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ReservaCita.fxml"));
			Parent root = loader.load();

			// Obtengo el controlador de la nueva escena
			ReservaCitaController reservaController = loader.getController();
			// Carga la ficha segun la ID de la mascota
			reservaController.setIdMascota(idMascotaActual);

			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Reservar Cita");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarCitas(int idMascota) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			// Consulta BD buscando las citas asociadas
			TypedQuery<Cita> query = em.createQuery(
					"SELECT c FROM Cita c WHERE c.mascota.idMascota = :idMascota ORDER BY c.fecha DESC", Cita.class);
			query.setParameter("idMascota", idMascota);
			// muestra resultados
			List<Cita> citas = query.getResultList();

			cargarCitas(citas);

		} finally {
			em.close();
		}
	}

	// -- BOTON ACTUALIZACION DE DATOS >>>>>>
	public void actualizarDatos(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ActualizarFicha.fxml"));
			Parent root = loader.load();

			// Obtengo el controlador de la nueva escena
			ActualizarFichaController actualizarController = loader.getController();
			// Carga la ficha segun la ID de la mascota
			actualizarController.cargarDatos(idMascotaActual);

			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Actualizar Ficha");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -- BOTON SALIR >>>>>>
	public void salirFicha(ActionEvent event) {

		// >> CAMBIA DE INTERFAZ
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Principal");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
		cargarDatos(idMascota);
		cargarCitas(idMascota);
	}

	// Metodo auxiliar para cargar las citas
	public void cargarCitas(List<Cita> citas) {
		ObservableList<Cita> citasList = FXCollections.observableArrayList(citas);
		lvHistorialCitas.setItems(citasList);
	}
}