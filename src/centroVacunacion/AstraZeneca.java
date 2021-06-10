package centroVacunacion;

public class AstraZeneca extends Vacuna{

	private int temperatura;

	public AstraZeneca(int cantidad, Fecha fechaIngreso) {
		super(cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura = 3;
	}

	@Override
	public String toString() {
		return "AstraZeneca se almacena a: " + temperatura;
	}

}
