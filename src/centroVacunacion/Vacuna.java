package centroVacunacion;

public class Vacuna {
	private String nombreVacuna;
	private int cantidad;
	private Fecha fechaIngreso;
	private Fecha Vencimiento;
	
	
	public Vacuna(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		this.nombreVacuna = nombreVacuna;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;
		this.Vencimiento = null;
		
	}
		
	public String getNombreVacuna() {
		return nombreVacuna;
	}

	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
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
	public boolean equals(Object objets) {

		return false;
	}
	

}

