package Controller;

import javax.persistence.EntityManager;

import Model.JPAUtil;
import Model.Mascota;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizarFichaController {

	private int idMascotaActual;

	// -- MASCOTA -->
	@FXML
	private TextField txtNombreMascota, txtRaza, txtColor, txtAlergia, txtMedicacion, txtCirugia, txtPesoActual;
	@FXML
	private DatePicker dpFechaNacimiento, dpUltimaVisita;
	@FXML
	private ComboBox<String> cmbSexo, cmbEsterilizado;

	// -- DUEÑO -->
	@FXML
	private TextField txtNombreDuenio, txtTelefono, txtEmail;
	@FXML
	private TextArea txtDireccion;

	@FXML
	public void initialize() {
		cmbSexo.getItems().addAll("Macho", "Hembra");
		cmbEsterilizado.getItems().addAll("Si", "No");
	}

	public void cargarDatos(int idMascota) {
		this.idMascotaActual = idMascota;

		EntityManager em = JPAUtil.getEntityManager();
		Mascota mascota = em.find(Mascota.class, idMascota);

		if (mascota != null) {
			txtNombreMascota.setText(mascota.getNombreMascota());

			dpFechaNacimiento.setValue(mascota.getFechaNacimiento());
			dpUltimaVisita.setValue(mascota.getUltimaVisita());

			cmbSexo.setValue(mascota.getSexo());
			txtRaza.setText(mascota.getRaza());
			txtColor.setText(mascota.getColor());
			cmbEsterilizado.setValue(mascota.getEsterilizado());
			txtAlergia.setText(mascota.getAlergia());
			txtMedicacion.setText(mascota.getMedicacion());
			txtCirugia.setText(mascota.getCirugia());
			txtPesoActual.setText(mascota.getPesoActual() != null ? mascota.getPesoActual().toString() : "");

			txtNombreDuenio.setText(mascota.getUnDuenio().getNombre());
			txtTelefono.setText(mascota.getUnDuenio().getTelefono());
			txtEmail.setText(mascota.getUnDuenio().getEmail());
			txtDireccion.setText(mascota.getUnDuenio().getDireccion());
		}

		em.close();
	}

	@FXML
	public void actualizarFicha(ActionEvent event) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();

			// Buscar la mascota en la base de datos
			Mascota mascota = em.find(Mascota.class, idMascotaActual);

			if (mascota != null) {
				// Actualizar los valores si no son nulos ni vacíos
				if (dpFechaNacimiento.getValue() != null) {
					mascota.setFechaNacimiento(dpFechaNacimiento.getValue());
				}
				if (dpUltimaVisita.getValue() != null) {
					mascota.setUltimaVisita(dpUltimaVisita.getValue());
				}
				if (cmbSexo.getValue() != null && !cmbSexo.getValue().trim().isEmpty()) {
					mascota.setSexo(cmbSexo.getValue());
				}
				if (!txtRaza.getText().trim().isEmpty()) {
					mascota.setRaza(txtRaza.getText());
				}
				if (!txtColor.getText().trim().isEmpty()) {
					mascota.setColor(txtColor.getText());
				}
				if (cmbEsterilizado.getValue() != null && !cmbEsterilizado.getValue().trim().isEmpty()) {
					mascota.setEsterilizado(cmbEsterilizado.getValue());
				}
				if (!txtAlergia.getText().trim().isEmpty()) {
					mascota.setAlergia(txtAlergia.getText());
				}
				if (!txtMedicacion.getText().trim().isEmpty()) {
					mascota.setMedicacion(txtMedicacion.getText());
				}
				if (!txtCirugia.getText().trim().isEmpty()) {
					mascota.setCirugia(txtCirugia.getText());
				}
				if (!txtPesoActual.getText().trim().isEmpty()) {
					try {
						mascota.setPesoActual(Double.parseDouble(txtPesoActual.getText()));
					} catch (NumberFormatException ex) {
						System.out.println("Peso inválido: " + txtPesoActual.getText());
					}
				}

				// Guardar los cambios en la base de datos
				em.merge(mascota);
				em.getTransaction().commit();
			}

			// Cargar la ventana FichaMascota después de actualizar
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
			Parent root = loader.load();

			FichaMascotaController fichaController = loader.getController();
			fichaController.cargarDatos(idMascotaActual);

			// Configurar la nueva ventana
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Ficha Mascota");
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
			stage.centerOnScreen();

		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
	}
}