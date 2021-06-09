package centroVacunacion;

public class AstraZeneca extends Vacuna{

	

	public AstraZeneca(int cantidad, Fecha fechaIngreso) {
		super(cantidad, fechaIngreso);
		// TODO Auto-generated constructor stub
		final int temperatura = 3;
	}

	@Override
	public String toString() {
		return  super.toString(); //utilizamos el de la superclase
	}

}
