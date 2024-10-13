package Clases;

public class Enemigo {

// ---- ATRIBUTOS

	private String nombre;
	private String raza;
	private int fuerza;
	private int defensa;

// ---- CONSTRUCTORES

	public Enemigo(String nombre, String raza, int fuerza, int defensa) {
		this.nombre = nombre;
		this.raza = raza;
		this.fuerza = fuerza;
		this.defensa = defensa;
	}

// ---- GETTERS & SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

}