package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCita;

	private LocalDate fecha;

	private String motivo, observaciones;

	@ManyToOne
	@JoinColumn(name = "idMascota")
	private Mascota mascota;

	public Cita() {
	}

	public Cita(int idCita, LocalDate fecha, String motivo, String observaciones, Mascota mascota) {
		this.idCita = idCita;
		this.fecha = fecha;
		this.motivo = motivo;
		this.observaciones = observaciones;
		this.mascota = mascota;
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	// metodo para mostrar texto en la ListView
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return fecha.format(formatter) + " - " + motivo + " (" + observaciones + ")";
	}
}
