package centroVacunacion;

public class AstraZeneca extends Vacuna{

	

	public AstraZeneca(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		super(nombreVacuna, cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura = 3;
	}

	@Override
	public String toString() {
		return  super.toString();
	}

}
