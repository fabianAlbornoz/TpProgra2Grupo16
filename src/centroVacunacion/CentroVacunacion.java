package centroVacunacion;

import static org.junit.Assert.fail;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Iterator;

public class CentroVacunacion {
	private String Nombre;
	private int CapacidadXdia;
	private ArrayList<Persona> Inscriptos;
	private ArrayList<Vacuna> VacunasDisponibles;
	private HashMap<Persona, Vacuna> Turno;
	private LinkedList CalendarioVacunacion;
	private LinkedList Reporte;
	private Vacuna vacuna;
	
	
	
	
	/*** Constructor.* recibe el nombre del centro y la capacidad de vacunacióndiaria.*
	 * Si la capacidad de vacunación no es positiva sedebe generar una excepción.*
	 * Si el nombre no está definido, se debe generar una excepción.*/
	public CentroVacunacion(String nombre, int CapacidadXdia) {
		this.Nombre = nombre;
		this.CapacidadXdia = CapacidadXdia;
		this.Inscriptos = new ArrayList();
		this.VacunasDisponibles = new ArrayList();
		this.Turno = new HashMap();
		this.CalendarioVacunacion = new LinkedList();
		this.Reporte = new LinkedList();
		this.vacuna = new Vacuna(null, 0 , null);
		
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	/*** Solo se pueden ingresar los tipos de vacunas planteadosen la 1ra parte.* 
	 * Si el nombre de la vacuna no coincidiera con losespecificados se debe generar* una excepción.* 
	 * También se genera excepción si la cantidad es negativa.* 
	 * La cantidad se debe* sumar al stock existente, tomando en cuenta lasvacunas ya utilizadas.*/
	public void ingresarVacunas(String nombreVacuna,int cantidad, Fecha fechaIngreso) {
		
		boolean SeGuardo = false;
		
		if (cantidad <= 0) {
			try {
				fail("No se puede ingresar valores negativos");
			} catch (RuntimeException e) { }
		}
		
		if (nombreVacuna.equals("Sputnik")) {
			Vacuna nuevaVacuna = new Sputnik(nombreVacuna, cantidad, fechaIngreso);
			 SeGuardo = VacunasDisponibles.add(nuevaVacuna);
		}
		
		if (nombreVacuna.equals("Pzifer")) {
			Vacuna nuevaVacuna = new Pzifer(nombreVacuna, cantidad, fechaIngreso);
			SeGuardo = VacunasDisponibles.add(nuevaVacuna);
		}
		
		if (nombreVacuna.equals("Moderna")) {
			SeGuardo = VacunasDisponibles.add(new Moderna(nombreVacuna, cantidad, fechaIngreso));
		}
		
		if (nombreVacuna.equals("Sinopharm")) {
			Vacuna nuevaVacuna = new Sinopharm(nombreVacuna, cantidad, fechaIngreso);
			SeGuardo = VacunasDisponibles.add(nuevaVacuna);

		}
		
		if (nombreVacuna.equals("AstraZeneca")) {
			Vacuna nuevaVacuna = new AstraZeneca(nombreVacuna, cantidad, fechaIngreso);
			SeGuardo = VacunasDisponibles.add(nuevaVacuna);
		}
		
		if (!SeGuardo) {
			try {
				fail("El nombre no pertenece a una vacuna admitida");
			} catch (RuntimeException e) { }
		}
		
		vacuna.setCantidad(vacuna.getCantidad()+cantidad);
	

	}
	
	/*** total de vacunas disponibles no vencidas sin distinción por tipo.*/
	public int vacunasDisponibles() {
		return vacuna.getCantidad();
		
	}
	
	/* total de vacunas disponibles no vencidas que coincidacon el nombre de
	*vacuna especificado.*/
	public int vacunasDisponibles(String nombreVacuna) {
		int suma = 0;

		for(Vacuna vac: VacunasDisponibles) {
			if (vac.getNombreVacuna().equals(nombreVacuna)) {
				suma+= vac.getCantidad();
			}
		}
		return suma;
	}
	
	/*** Se inscribe una persona en lista de espera.
	 *Si la persona ya se encuentra inscripta o es menorde 18 años, se debe* generar una excepción.
	 *Si la persona ya fue vacunada, también debe generaruna excepción.*/
	public void inscribirPersona(int dni, Fecha nacimiento,boolean tienePadecimientos,boolean esEmpleadoSalud){ //lista ordenada por prioridad
		
		int edad = Fecha.diferenciaAnios(Fecha.hoy() , nacimiento);
		Persona nueva = new Persona(dni,edad,tienePadecimientos,esEmpleadoSalud);
		
		if (edad< 18 || Inscriptos.contains(nueva)) {
			try {
				fail("Es Menor de 18");
			} catch (RuntimeException e) { }
		}
		
		if (Inscriptos.contains(nueva)) {
			try {
				fail("Ya esta inscripto");
			} catch (RuntimeException e) { }
		}
		
		int i = 0;
		boolean Inscribido = false;
		
		if (Inscriptos.size() == 0) {
			Inscriptos.add(nueva);
			Inscribido = true;
		}
		
		while (esEmpleadoSalud && !Inscribido && i < Inscriptos.size()) {
			if (Inscriptos.get(i).isEsEmpleadoSalud())
				i++;
			
			if(Inscriptos.get(i).getEdad()>=60) {
				Inscriptos.add(i, nueva);
				Inscribido = true;
			}
			if (Inscriptos.get(i).isTienePadecimientos() && !Inscribido) {
				Inscriptos.add(i,nueva);
				Inscribido = true;
			}
			if (!Inscriptos.get(i).isTienePadecimientos() && !Inscriptos.get(i).isEsEmpleadoSalud() && !(Inscriptos.get(i).getEdad()>60) ) {
				Inscriptos.add(i,nueva);
				Inscribido = true;
			}
		}
		while (edad>=60 && !Inscribido && i < Inscriptos.size()) {
			if (Inscriptos.get(i).getEdad()>=60 || Inscriptos.get(i).isEsEmpleadoSalud())
				i++;
			if(Inscriptos.get(i).isTienePadecimientos()) {
				Inscriptos.add(i, new Persona (dni, edad, tienePadecimientos, esEmpleadoSalud));
				Inscribido = true;
			}
			if (!Inscriptos.get(i).isTienePadecimientos() && !Inscriptos.get(i).isEsEmpleadoSalud() && !(Inscriptos.get(i).getEdad()>=60) ) {
				Inscriptos.add(i, nueva);
				Inscribido = true;
			}
		}
		while (tienePadecimientos && !Inscribido && i < Inscriptos.size()) {
			if (Inscriptos.get(i).getEdad()>=60 || Inscriptos.get(i).isEsEmpleadoSalud() || Inscriptos.get(i).isTienePadecimientos())
				i++;
			if (!Inscriptos.get(i).isTienePadecimientos() && !Inscriptos.get(i).isEsEmpleadoSalud() && !(Inscriptos.get(i).getEdad()>=60) ) {
				Inscriptos.add(i, nueva);
				Inscribido = true;
			}
		}
		if(!Inscribido)
			Inscriptos.add(nueva);
				

	}

	/*** Devuelve una lista con los DNI de todos los inscriptos que no se vacunaron
	y que no tienen turno asignado.*
	Si no quedan inscriptos sin vacunas debe devolveruna lista vacía.*/
	public LinkedList<Integer> listaDeEspera(){
		LinkedList<Integer> lista = new LinkedList<Integer>();
		for (Persona lape: Inscriptos) {
			lista.add(lape.getDni());
		}
		return lista;
	}
	
	/*** Primero se verifica si hay turnos vencidos. En casode haber turnos* vencidos, la persona que no asistió al turno debeser borrada del sistema* y la vacuna reservada debe volver a estar disponible.*
	* Segundo, se deben verificar si hay vacunas vencidas y quitarlas del sistema.**
	*  Por último, se procede a asignar los turnos a partirde la fecha inicial* 
	*  recibida según lo especificado en la 1ra parte.* 
	*  Cada vez que se registra un nuevo turno, la vacunadestinada a esa persona* 
	*  dejará de estar disponible. Dado que estará reservadapara ser aplicada*
	*   el día del turno. */
	public void generarTurnos(Fecha fechaInicial) {
		
	}

	/*** Devuelve una lista con los dni de las personas quetienen turno asignado* 
	* para la fecha pasada por parámetro.* Si no hay turnos asignados para ese día,
	 se debedevolver una lista vacía.
	* La cantidad de turnos no puede exceder la capacidadpor día de la ungs.*/
	public ArrayList<Integer> turnosConFecha(Fecha fecha){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		return lista;
	}

	
	/*** Dado el DNI de la persona y la fecha de vacunación*
	*se valida que esté inscripto y que tenga turno para ese dia.*
	*-Si tiene turno y está inscripto se debe registrarla persona como*
	*vacunada y la vacuna se quita del depósito.
	*-Si no está inscripto o no tiene turno ese día,se genera una Excepcion.*/
	
	public void vacunarInscripto(int dni, Fecha fechaVacunacion) {
		
	}
	
	/* Devuelve un Diccionario donde*-la clave es el dni de las personas vacunadas
	 *-Y, el valor es el nombre de la vacuna aplicada.*/
	public Map<Integer, String> reporteVacunacion(){
		Map<Integer,String> coso = new HashMap<Integer, String>();
		return coso;
	}

	/*** Devuelve en O(1) un Diccionario:*-clave: nombre de la vacuna
	 *-valor: cantidad de vacunas vencidas conocidashasta el momento.*/
	public Map<String, Integer> reporteVacunasVencidas(){
		Map<String, Integer> coso = new HashMap<String, Integer>();
		return coso;
	}

}
