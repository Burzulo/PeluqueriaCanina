package Model;

public class Duenio {

	private int idDuenio;
	private String nombre;
	private int telefono;
	private String email;
	private String direccion;

	public Duenio() {
		super();
	}

	public Duenio(int idDuenio, String nombre, int telefono, String email, String direccion) {
		super();
		this.idDuenio = idDuenio;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
	}

	public int getIdDuenio() {
		return idDuenio;
	}

	public void setIdDuenio(int idDuenio) {
		this.idDuenio = idDuenio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
