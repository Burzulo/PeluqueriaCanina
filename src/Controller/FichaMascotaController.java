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
    private Label lblNombreMascota, lblHistoriaClinica;
    @FXML
    private Label lblFechaNacimiento, lblUltimaVisita, lblSexo, lblRaza, lblColor,
                  lblEsterilizado, lblAlergia, lblMedicacion, lblCirugia, lblPesoActual;
    @FXML
    private Label lblNombreDuenio, lblTelefono, lblEmail, lblDireccion;
    
    private int idMascotaActual;
	
	// !!!!
 // Método que recibe el id de la mascota y carga los datos
    public void cargarDatos(int idMascota) {
        this.idMascotaActual = idMascota;

        EntityManager em = JPAUtil.getEntityManager();
        Mascota mascota = em.find(Mascota.class, idMascotaActual);

        if (mascota != null) {
            lblNombreMascota.setText(mascota.getNombreMascota());
            lblHistoriaClinica.setText("Historia Clinica #" + mascota.getIdMascota());
            lblFechaNacimiento.setText("Fecha Nacimiento: " + mascota.getFechaNacimiento().toString());
            lblUltimaVisita.setText("Última Visita: " + mascota.getUltimaVisita().toString());
            lblSexo.setText("Sexo: " + mascota.getSexo());
            lblRaza.setText("Raza: " + mascota.getRaza());
            lblColor.setText("Color: " + mascota.getColor());
            lblPesoActual.setText("Peso Actual: " + mascota.getPesoActual() + " kg.");
            lblEsterilizado.setText("Esterilizado: " + mascota.getEsterilizado());
            lblAlergia.setText("Alergias: " + mascota.getAlergia());
            lblMedicacion.setText("Medicación Habitual: " + mascota.getMedicacion());
            lblCirugia.setText("Intervenciones Quirúrgicas: " + mascota.getCirugia());

            if (mascota.getUnDuenio() != null) {
                lblNombreDuenio.setText("Nombre: " + mascota.getUnDuenio().getNombre());
                lblTelefono.setText("Teléfono: " + mascota.getUnDuenio().getTelefono());
                lblEmail.setText("Email: " + mascota.getUnDuenio().getEmail());
                lblDireccion.setText("Dirección: " + mascota.getUnDuenio().getDireccion());
            }
        }
        em.close();
    }


	// !!!!
	public void actualizarDatos(ActionEvent event) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ActualizarFicha.fxml"));
	        Parent root = loader.load();
	        
	        // Obtener el controlador de la nueva ventana
	        ActualizarFichaController actualizarController = loader.getController();
	        
	        // Pasar el idMascotaActual al controlador de la nueva ventana
	        actualizarController.cargarDatos(idMascotaActual);

	        // Configurar la nueva ventana
	        Scene scene = new Scene(root);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setTitle("Actualizar Ficha");
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.centerOnScreen();
	        stage.setOnCloseRequest(evt -> evt.consume());
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
			stage.setOnCloseRequest(evt -> evt.consume());
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}