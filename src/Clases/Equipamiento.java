package Clases;

public enum Equipamiento {
	ESPADA(5, 5), HACHA(6, 4), MAZA(8, 2), BASTON(4, 6), LANZA(9, 1);

	private final int fuerza;
	private final int defensa;

	// ---- CONSTRUCTOR
	Equipamiento(int fuerza, int defensa) {
		this.fuerza = fuerza;
		this.defensa = defensa;
	}

	// ---- GETTERS AND SETTERS
	public int getFuerza() {
		return fuerza;
	}

	public int getDefensa() {
		return defensa;
	}

}
