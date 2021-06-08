package centroVacunacion;

public class Pfizer extends Vacuna{
	private int temperatura;

	
	public Pfizer(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		super(nombreVacuna, cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int  temperatura= -18;
		
	}
	@Override
	public String toString() {
		return  super.toString();
	}


	
}
