package centroVacunacion;

public class Vacuna {
	private int cantidad;
	private Fecha fechaIngreso;
	private Fecha Vencimiento;
	
	
	public Vacuna(int cantidad, Fecha fechaIngreso) {
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.Vencimiento = null;
		
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Fecha getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Fecha fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public Fecha getVencimiento() {
		return Vencimiento;
	}

	public void setVencimiento(Fecha vencimiento) {
		Vencimiento = vencimiento;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return this.getClass().getSimpleName().equals(obj);
	}
	
	
	@Override
	public String toString() {
		return "Esta vacuna es : " + this.getClass().getSimpleName() + " Contamos con un total de: " + cantidad + "ingresaron :" + fechaIngreso
				+ "su fecha de vencimiento es : " + Vencimiento  ;
	}
	

}

