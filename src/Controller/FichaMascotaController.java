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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FichaMascotaController {

	// -- MASCOTA -->
	@FXML
	private Label lblHC, lblNombreMascota, lblFechaNacimiento, lblUltimaVisita, lblSexo, lblRaza, lblColor,
			lblEsterilizado, lblAlergia, lblMedicacion, lblCirugia, lblPesoActual;

	// -- DUEÑO -->
	@FXML
	private Label lblNombreDuenio, lblTelefono, lblEmail, lblDireccion;

	// !!!!!!
	private int idMascotaActual;

	// !!!!
	public void cargarDatos(int id_mascota) {
		this.idMascotaActual = id_mascota;

		EntityManager em = JPAUtil.getEntityManager();
		Mascota mascota = em.find(Mascota.class, idMascotaActual);

		if (mascota != null) {
			lblHC.setText(String.valueOf(mascota.getIdCliente()));
			lblNombreMascota.setText(mascota.getNombreMascota());
			lblFechaNacimiento.setText(mascota.getFechaNacimiento().toString());
			lblUltimaVisita.setText(mascota.getUltimaVisita().toString());
			lblSexo.setText(mascota.getSexo());
			lblRaza.setText(mascota.getRaza());
			lblColor.setText(mascota.getColor());
			lblEsterilizado.setText(mascota.getEsterilizado());
			lblAlergia.setText(mascota.getAlergia());
			lblMedicacion.setText(mascota.getMedicacion());
			lblCirugia.setText(mascota.getCirugia());
			lblPesoActual.setText(String.valueOf(mascota.getPesoActual()));

			if (mascota.getUnDuenio() != null) {
				lblNombreDuenio.setText(mascota.getUnDuenio().getNombre());
				lblTelefono.setText(mascota.getUnDuenio().getTelefono());
				lblEmail.setText(mascota.getUnDuenio().getEmail());
				lblDireccion.setText(mascota.getUnDuenio().getDireccion());
			}
		}
		em.close();
	}

	// !!!!
	public void actualizarDatos(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/ActualizarFicha.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Actualizar Ficha");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// !!!!
	public void salirFicha(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/Principal.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Principal");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}