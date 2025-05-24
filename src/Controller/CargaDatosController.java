package Controller;

import Model.Duenio;
import Model.JPAUtil;
import Model.Mascota;

import java.io.IOException;

import javax.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CargaDatosController {

	// -- MASCOTA >>>>>>
	@FXML
	private TextField txtNombreMascota, txtRaza, txtColor, txtPesoActual;
	@FXML
	private DatePicker dpFechaNacimiento, dpUltimaVisita;

	@FXML
	private ComboBox<String> cmbSexo, cmbEsterilizado;

	// -- DUEÑO >>>>>>
	@FXML
	private TextField txtNombreDuenio, txtTelefono, txtEmail;
	@FXML
	private TextArea txtDireccion;

	// -- OPCIONES COMBOBOX >>>>>>
	@FXML
	private void initialize() {
		cmbSexo.getItems().addAll("MACHO", "HEMBRA");
		cmbEsterilizado.getItems().addAll("SI", "NO");
	}

	// -- FORMATO EMAIL >>>>>>
	@FXML
	private void validarEmail() {
		String correo = txtEmail.getText();
		if (correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			System.out.println("Correo electrónico válido");
		} else {
			System.out.println("Correo electrónico inválido");
		}
	}

	// -- BOTON LIMPIEZA >>>>>>
	public void limpiarDatos(ActionEvent event) {
		txtNombreMascota.clear();
		dpFechaNacimiento.setValue(null);
		txtRaza.clear();
		txtColor.clear();
		txtNombreDuenio.clear();
		txtTelefono.clear();
		txtEmail.clear();
		txtDireccion.clear();
		cmbSexo.getSelectionModel().clearSelection();
		dpUltimaVisita.setValue(null);
	}

	// -- ALERTAS POR FALTA DE INFO >>>>>>
	private void mostrarAlerta(String titulo, String contenido) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle(titulo);
		alerta.setHeaderText(null);
		alerta.setContentText(contenido);
		alerta.showAndWait();
	}

	// -- BOTON GUARDAR DATOS >>>>>>
	@FXML
	private void guardarDatos(ActionEvent event) throws IOException {

		// Valida que los campos no esten vacios antes de guardar
		if (txtNombreDuenio.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty()
				|| txtEmail.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty()
				|| txtNombreMascota.getText().trim().isEmpty() || txtRaza.getText().trim().isEmpty()
				|| txtPesoActual.getText().trim().isEmpty() || cmbSexo.getValue() == null
				|| cmbEsterilizado.getValue() == null || dpFechaNacimiento.getValue() == null
				|| dpUltimaVisita.getValue() == null) {

			mostrarAlerta("Campos incompletos", "Por favor, rellena todos los campos antes de guardar.");
			return;
		}

		// Valida formato del email
		String correo = txtEmail.getText().trim();
		if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
			mostrarAlerta("Correo inválido", "Por favor, introduce un correo electrónico válido.");
			return;
		}

		// Valida formato del peso
		double peso;
		try {
			peso = Double.parseDouble(txtPesoActual.getText().trim());
		} catch (NumberFormatException e) {
			mostrarAlerta("Peso inválido", "El campo 'Peso Actual' debe ser un número válido.");
			return;
		}

		// >> GUARDA INFO EN BD Y OBTIENE ID DE MASCOTA NUEVA
		Mascota mascota = null; // Declaro mascota fuera del try asi esta disponible para el cambio de interfaz
		EntityManager em = JPAUtil.getEntityManager();

		try {
			Duenio duenio = new Duenio();
			duenio.setNombre(txtNombreDuenio.getText().toUpperCase().trim());
			duenio.setTelefono(txtTelefono.getText().trim());
			duenio.setEmail(txtEmail.getText().toUpperCase().trim());
			duenio.setDireccion(txtDireccion.getText().toUpperCase().trim());

			mascota = new Mascota();
			mascota.setNombreMascota(txtNombreMascota.getText().toUpperCase().trim());
			mascota.setFechaNacimiento(dpFechaNacimiento.getValue());
			mascota.setSexo(cmbSexo.getValue());
			mascota.setEsterilizado(cmbEsterilizado.getValue());
			String raza = txtRaza.getText();
			mascota.setRaza((raza != null && !raza.isBlank()) ? raza.toUpperCase().trim() : null);
			String color = txtColor.getText();
			mascota.setColor((color != null && !color.isBlank()) ? color.toUpperCase().trim() : null);
			mascota.setPesoActual(peso);
			mascota.setUnDuenio(duenio);

			em.getTransaction().begin(); // abre la transaccion para sincronizar
			em.persist(duenio);
			em.persist(mascota);
			em.flush(); // Sincroniza con BD antes del commit y genera el id de la mascota nueva
			em.getTransaction().commit(); // Confirma los cambios en BD

			Alert alerta = new Alert(Alert.AlertType.INFORMATION);
			alerta.setTitle("Registro guardado");
			alerta.setHeaderText(null);
			alerta.setContentText("Los datos se han guardado correctamente.");
			alerta.showAndWait();

		} catch (Exception e) {
			e.printStackTrace();
			mostrarAlerta("Error", "No se pudo guardar la mascota.");
			return;
		} finally {
			em.close();
		}

		// >> CAMBIA DE INTERFAZ
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
			Parent root = loader.load();
			FichaMascotaController controller = loader.getController(); // Obtengo el controlador de la nueva escena
			controller.cargarDatos(mascota.getIdMascota()); // Paso el ID de la mascota recién creada
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

	// -- BOTON SALIR >>>>>>
	public void salirNuevaMascota(ActionEvent event) {

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
}
