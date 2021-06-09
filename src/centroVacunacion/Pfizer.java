package centroVacunacion;

public class Pfizer extends Vacuna{
	private int temperatura;

	
	public Pfizer(int cantidad, Fecha fechaIngreso) {
		super(cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int  temperatura= -18;
		
	}
	@Override
	public String toString() {
		return  super.toString();
	}


	
}
