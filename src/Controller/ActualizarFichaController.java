package Controller;

import javax.persistence.EntityManager;

import Model.Duenio;
import Model.JPAUtil;
import Model.Mascota;
import javafx.application.Platform;
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

public class ActualizarFichaController {

	private int idMascotaActual;

	// -- MASCOTA >>>>>
	@FXML
	private TextField txtNombreMascota, txtRaza, txtColor, txtAlergia, txtMedicacion, txtCirugia, txtPesoActual,
			txtObservaciones;
	@FXML
	private DatePicker dpFechaNacimiento, dpUltimaVisita;
	@FXML
	private ComboBox<String> cmbSexo, cmbEsterilizado;

	// -- DUEÑO >>>>>
	@FXML
	private TextField txtNombreDuenio, txtTelefono, txtEmail, txtDireccion;

	// -- OPCIONES DEL COMBOBOX -->
	@FXML
	public void initialize() {
		cmbSexo.getItems().addAll("MACHO", "HEMBRA");
		cmbEsterilizado.getItems().addAll("SI", "NO");
	}

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void cargarDatos(int idMascota) {
		this.idMascotaActual = idMascota;

		EntityManager em = JPAUtil.getEntityManager();
		Mascota mascota = em.find(Mascota.class, idMascota);

		if (mascota != null) {
			txtNombreMascota.setText(mascota.getNombreMascota());

			dpFechaNacimiento.setValue(mascota.getFechaNacimiento());

			cmbSexo.setValue(mascota.getSexo());
			txtRaza.setText(mascota.getRaza());
			txtColor.setText(mascota.getColor());
			cmbEsterilizado.setValue(mascota.getEsterilizado());
			txtAlergia.setText(mascota.getAlergia());
			txtMedicacion.setText(mascota.getMedicacion());
			txtCirugia.setText(mascota.getCirugia());
			txtPesoActual.setText(mascota.getPesoActual() != null ? mascota.getPesoActual().toString() : "");
			txtObservaciones.setText(mascota.getObservaciones());

			txtNombreDuenio.setText(mascota.getUnDuenio().getNombre());
			txtTelefono.setText(mascota.getUnDuenio().getTelefono());
			txtEmail.setText(mascota.getUnDuenio().getEmail());
			txtDireccion.setText(mascota.getUnDuenio().getDireccion());
		}

		em.close();
	}

	// verrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
	private boolean isEmpty(TextField field) {
		return field == null || field.getText() == null || field.getText().trim().isEmpty();
	}

	// -- BOTON ACTUALIZAR >>>>>>
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FXML
	public void actualizarFicha(ActionEvent event) {
		((Node) event.getSource()).getScene().getRoot().requestFocus();

		if (isEmpty(txtNombreMascota) || dpFechaNacimiento.getValue() == null || cmbSexo.getValue() == null
				|| isEmpty(txtRaza) || isEmpty(txtColor) || // Línea 89 probablemente
				cmbEsterilizado.getValue() == null || isEmpty(txtAlergia) || isEmpty(txtMedicacion)
				|| isEmpty(txtCirugia) || isEmpty(txtObservaciones) || isEmpty(txtPesoActual)
				|| isEmpty(txtNombreDuenio) || isEmpty(txtTelefono) || isEmpty(txtEmail) || isEmpty(txtDireccion)) {

			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Campos incompletos");
			alerta.setHeaderText(null);
			alerta.setContentText("Por favor, rellena todos los campos obligatorios antes de continuar.");
			alerta.showAndWait();
			return;
		}

		// Validación email
		if (!esEmailValido(txtEmail.getText().trim())) {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Email inválido");
			alerta.setHeaderText(null);
			alerta.setContentText("Por favor, introduce un email con formato válido (ejemplo@dominio.com).");
			alerta.showAndWait();
			return;
		}

		// Validar peso, que no este vacio y sea numerico
		String pesoTexto = txtPesoActual.getText().trim();
		if (pesoTexto.isEmpty()) {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Peso vacío");
			alerta.setHeaderText(null);
			alerta.setContentText("Por favor, introduce un peso válido.");
			alerta.showAndWait();
			return;
		} else {
			try {
				Double.parseDouble(pesoTexto);
			} catch (NumberFormatException ex) {
				Alert alerta = new Alert(Alert.AlertType.ERROR);
				alerta.setTitle("Peso inválido");
				alerta.setHeaderText(null);
				alerta.setContentText("El peso introducido no es un número válido.");
				alerta.showAndWait();
				return;
			}
		}

		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();

			Mascota mascota = em.find(Mascota.class, idMascotaActual);
			if (mascota == null) {
				em.getTransaction().rollback();
				return;
			}

			// Actualizar datos mascota
			mascota.setNombreMascota(txtNombreMascota.getText().trim().toUpperCase());
			mascota.setFechaNacimiento(dpFechaNacimiento.getValue());
			mascota.setSexo(cmbSexo.getValue());
			mascota.setRaza(txtRaza.getText().trim().toUpperCase());
			mascota.setColor(txtColor.getText().trim().toUpperCase());
			mascota.setEsterilizado(cmbEsterilizado.getValue());
			mascota.setAlergia(txtAlergia.getText().trim().toUpperCase());
			mascota.setMedicacion(txtMedicacion.getText().trim().toUpperCase());
			mascota.setCirugia(txtCirugia.getText().trim().toUpperCase());
			mascota.setObservaciones(txtObservaciones.getText().trim().toUpperCase());

			if (!pesoTexto.isEmpty()) {
				mascota.setPesoActual(Double.parseDouble(pesoTexto));
			} else {
				mascota.setPesoActual(null);
			}

			// Actualizar datos dueño
			if (mascota.getUnDuenio() != null) {
				Duenio duenio = mascota.getUnDuenio();

				duenio.setNombre(txtNombreDuenio.getText().trim().toUpperCase());
				duenio.setTelefono(txtTelefono.getText().trim());
				duenio.setEmail(txtEmail.getText().trim().toLowerCase());
				duenio.setDireccion(txtDireccion.getText().trim().toUpperCase());
			}

			em.merge(mascota);
			em.getTransaction().commit();

			// >> CAMBIA DE INTERFAZ
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
			Parent root = loader.load();

			FichaMascotaController fichaController = loader.getController();
			fichaController.setIdMascota(idMascotaActual);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Ficha Mascota");
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
	}

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private boolean esEmailValido(String email) {
		if (email == null || email.isEmpty())
			return false;
		return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
	}

	// -- BOTON SALIR -->
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FXML
	public void salirActualizar(ActionEvent event) {
		try {
			// Cargar el FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
			Parent root = loader.load();

			// Obtener el controlador y pasar el ID de la mascota
			FichaMascotaController fichaController = loader.getController();
			fichaController.cargarDatos(idMascotaActual);

			// Mostrar la escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Ficha Mascota");
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}