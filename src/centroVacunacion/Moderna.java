package centroVacunacion;

public class Moderna extends Vacuna{
	private int temperatura;

	
	public Moderna(int cantidad, Fecha fechaIngreso) {
		super(cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura= -18;
		
	}
	@Override
	public String toString() {
		return  super.toString();
	}

}
