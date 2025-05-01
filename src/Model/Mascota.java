package Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mascota")
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_mascota;

	private String nombreMascota;
	private LocalDate fecha_nacimiento, ultima_visita;
	private String sexo, raza, color;
	private String esterilizado, alergia, medicacion, cirugia;
	private Double pesoActual;

	@ManyToOne
	@JoinColumn(name = "id_duenio")
	private Duenio unDuenio;

	public Mascota() {
	}

	public Mascota(int id_mascota, String nombreMascota, LocalDate fecha_nacimiento, LocalDate ultima_visita,
			String sexo, String raza, String color, String esterilizado, String alergia, String medicacion,
			String cirugia, Double pesoActual, Duenio unDuenio) {
		this.id_mascota = id_mascota;
		this.nombreMascota = nombreMascota;
		this.fecha_nacimiento = fecha_nacimiento;
		this.ultima_visita = ultima_visita;
		this.sexo = sexo;
		this.raza = raza;
		this.color = color;
		this.esterilizado = esterilizado;
		this.alergia = alergia;
		this.medicacion = medicacion;
		this.cirugia = cirugia;
		this.pesoActual = pesoActual;
		this.unDuenio = unDuenio;
	}

	public int getIdMascota() {
		return id_mascota;
	}

	public void setIdMascota(int id_mascota) {
		this.id_mascota = id_mascota;
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

	public LocalDate getUltimaVisita() {
		return ultima_visita;
	}

	public void setUltimaVisita(LocalDate ultima_visita) {
		this.ultima_visita = ultima_visita;
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

}
