package centroVacunacion;

public class Vacuna {
	private String nombreVacuna;
	private int cantidad;
	private Fecha fechaIngreso;
	
	
	public Vacuna(String nombreVacuna, int cantidad, Fecha fechaIngreso) {
		this.nombreVacuna = nombreVacuna;
		this.cantidad = cantidad;
		this.fechaIngreso = fechaIngreso;

	
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
	

}

