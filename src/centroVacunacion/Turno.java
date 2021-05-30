package centroVacunacion;

public class Turno {
	private Persona persona; 

	private Vacuna vacuna; 

	private Fecha fecha;
	
	public Turno(Persona persona, Vacuna vacuna, Fecha fecha) {
		this.persona = persona;
		this.vacuna = vacuna;
		this.fecha = fecha;

	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Vacuna getVacuna() {
		return vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}





}
