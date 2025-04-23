package Model;

import java.time.LocalDate;

public class Mascota {

	private int idCliente;
	private String nombreCliente;
	private LocalDate fechaNac;
	private String sexo;
	private String raza;
	private String color;
	private String observaciones;

	public Mascota() {
		super();
	}

	public Mascota(int idCliente, String nombreCliente, LocalDate fechaNac, String sexo, String raza, String color,
			String observaciones) {
		super();
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.fechaNac = fechaNac;
		this.sexo = sexo;
		this.raza = raza;
		this.color = color;
		this.observaciones = observaciones;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
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

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
