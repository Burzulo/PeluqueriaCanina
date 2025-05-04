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
 
    // -- DUEÑO -->
    @FXML
    private Label lblNombreDuenio, lblTelefono, lblEmail, lblDireccion;
    
    private int idMascotaActual;
	
 // -- METODO ID MASCOTA Y CARGA SUS DATOS -->
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


 // -- BOTON ACTUALIZACION DE DATOS -->
	public void actualizarDatos(ActionEvent event) {

		// -- CAMBIA DE INTERFAZ
		try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ActualizarFicha.fxml"));
	        Parent root = loader.load();
	        
	        ActualizarFichaController actualizarController = loader.getController();
	        
	        // -- PASAR ID A CONTROLADOR DE NUEVA VENTANA
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

	// -- BOTON SALIR -->
	public void salirFicha(ActionEvent event) {

		// -- CAMBIA DE INTERFAZ
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