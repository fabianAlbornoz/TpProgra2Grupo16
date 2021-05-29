package centroVacunacion;

public class Persona {
	private int dni;
	private int edad;
	private boolean tienePadecimientos;
	private boolean esEmpleadoSalud;
	
	public Persona(int dni, int edad, boolean tienePadecimientos,boolean esEmpleadoSalud) {
		this.dni = dni;
		this.edad = edad;
		this.tienePadecimientos = tienePadecimientos;
		this.esEmpleadoSalud = esEmpleadoSalud;
	}
	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public boolean isTienePadecimientos() {
		return tienePadecimientos;
	}


	public void setTienePadecimientos(boolean tienePadecimientos) {
		this.tienePadecimientos = tienePadecimientos;
	}


	public boolean isEsEmpleadoSalud() {
		return esEmpleadoSalud;
	}


	public void setEsEmpleadoSalud(boolean esEmpleadoSalud) {
		this.esEmpleadoSalud = esEmpleadoSalud;
	}


	
	
	

	
	
}
