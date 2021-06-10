package centroVacunacion;

public class Sinopharm extends Vacuna{
	
	private int temperatura;
	
	public Sinopharm(int cantidad, Fecha fechaIngreso) {
		super(cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura = 3;
	}
	
	@Override
	public String toString() {
		return "Sinopharm se almacena a: " + temperatura;
	}

}
