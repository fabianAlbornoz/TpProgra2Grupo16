package centroVacunacion;

public class Moderna extends Vacuna{
	private int temperatura;

	
	public Moderna(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		super(nombreVacuna, cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura= -18;
		
	}

}
