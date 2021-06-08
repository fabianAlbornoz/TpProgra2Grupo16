package centroVacunacion;

public class Sinopharm extends Vacuna{

	public Sinopharm(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		super(nombreVacuna, cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura = 3;
	}
	@Override
	public String toString() {
		return  super.toString();
	}

}
