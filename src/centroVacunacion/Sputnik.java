package centroVacunacion;

public class Sputnik extends Vacuna{
	
	private int temperatura;

	public Sputnik(int cantidad, Fecha fechaIngreso) {
		super(cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura = 3;
	}

	@Override
	public String toString() {
		return "Sputnik se almacena a: " + temperatura;
	}


}
