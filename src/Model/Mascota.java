package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Mascota")
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMascota;

	private String nombreMascota;
	private LocalDate fecha_nacimiento;
	private String sexo, raza, color;
	private String esterilizado, alergia, medicacion, cirugia, observaciones;
	private Double pesoActual;

	@ManyToOne
	@JoinColumn(name = "id_duenio")
	private Duenio unDuenio;

	@OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
	private List<Cita> historial = new ArrayList<>();

	public Mascota() {
	}

	public Mascota(int idMascota, String nombreMascota, LocalDate fecha_nacimiento, String sexo, String raza,
			String color, String esterilizado, String alergia, String medicacion, String cirugia, String observaciones,
			Double pesoActual, Duenio unDuenio) {
		this.idMascota = idMascota;
		this.nombreMascota = nombreMascota;
		this.fecha_nacimiento = fecha_nacimiento;
		this.sexo = sexo;
		this.raza = raza;
		this.color = color;
		this.esterilizado = esterilizado;
		this.alergia = alergia;
		this.observaciones = observaciones;
		this.medicacion = medicacion;
		this.cirugia = cirugia;
		this.pesoActual = pesoActual;
		this.unDuenio = unDuenio;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getNombreMascota() {
		return nombreMascota;
	}

	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}

	public LocalDate getFechaNacimiento() {
		return fecha_nacimiento;
	}

	public void setFechaNacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEsterilizado() {
		return esterilizado;
	}

	public void setEsterilizado(String esterilizado) {
		this.esterilizado = esterilizado;
	}

	public String getAlergia() {
		return alergia;
	}

	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}

	public String getMedicacion() {
		return medicacion;
	}

	public void setMedicacion(String medicacion) {
		this.medicacion = medicacion;
	}

	public String getCirugia() {
		return cirugia;
	}

	public void setCirugia(String cirugia) {
		this.cirugia = cirugia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Double getPesoActual() {
		return pesoActual;
	}

	public void setPesoActual(Double pesoActual) {
		this.pesoActual = pesoActual;
	}

	public Duenio getUnDuenio() {
		return unDuenio;
	}

	public void setUnDuenio(Duenio unDuenio) {
		this.unDuenio = unDuenio;
	}

	public List<Cita> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Cita> historial) {
		this.historial = historial;
	}

	public void addHistorial(Cita h) {
	    historial.add(h);
	    h.setMascota(this); // sincroniza la relación bidireccional
	}

	@Override
	public String toString() {
	    return nombreMascota;
	}

}
