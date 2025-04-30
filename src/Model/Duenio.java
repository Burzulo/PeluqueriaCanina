package Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Duenio")
public class Duenio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_duenio;
	
	private String nombre;
	private String telefono;
	private String email;
	private String direccion;

	@OneToMany(mappedBy = "unDuenio")  // Relación inversa con Mascota
    private List<Mascota> mascotas;
	
	public Duenio() {
	}

	public Duenio(int id_duenio, String nombre, String telefono, String email, String direccion) {
		this.id_duenio = id_duenio;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
	}

	public int getIdDuenio() {
		return id_duenio;
	}

	public void setIdDuenio(int id_duenio) {
		this.id_duenio = id_duenio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
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
