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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizarFichaController {

	private int idMascotaActual;

	// -- Mascota
	@FXML
	private Label lblNombreMascota, lblHistoriaClinica;
	@FXML
	private TextField txtRaza, txtColor, txtAlergia, txtMedicacion, txtCirugia, txtPesoActual;
	@FXML
	private DatePicker dpFechaNacimiento, dpUltimaVisita;
	@FXML
	private ComboBox<String> cmbSexo, cmbEsterilizado;

	// -- Dueño
	@FXML
	private TextField txtNombreDuenio, txtTelefono, txtEmail;
	@FXML
	private TextArea txtDireccion;

	@FXML
	public void initialize() {
		cmbSexo.getItems().addAll("Macho", "Hembra");
		cmbEsterilizado.getItems().addAll("Si", "No");
	}

	@FXML
	public void actualizarFicha(ActionEvent event) {
		EntityManager em = JPAUtil.getEntityManager();

		try {
			em.getTransaction().begin();

			Mascota mascota = em.find(Mascota.class, idMascotaActual);

			if (mascota != null) {
				// Solo actualizar si hay una fecha nueva
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
						// Puedes mostrar un mensaje de error al usuario si quieres
					}
				}

				em.merge(mascota);
				em.getTransaction().commit();
			}

			// Cambiar a la siguiente escena
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FichaMascota.fxml"));
			Parent root = loader.load();
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
}