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
	private LinkedList<Turno> CalendarioVacunacion;
	private HashMap<Integer,String> Vacunados;
	private Vacuna vacuna;
	
	
	
	
	/*** Constructor.* recibe el nombre del centro y la capacidad de vacunacióndiaria.*
	 * Si la capacidad de vacunación no es positiva sedebe generar una excepción.*
	 * Si el nombre no está definido, se debe generar una excepción.*/
	public CentroVacunacion(String nombre, int CapacidadXdia) {
		this.Nombre = nombre;
		this.CapacidadXdia = CapacidadXdia;
		this.Inscriptos = new ArrayList();
		this.VacunasDisponibles = new ArrayList();
		this.CalendarioVacunacion = new LinkedList<Turno>();
		this.Vacunados = new HashMap();
		this.vacuna = new Vacuna(null, 0 , null);

	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	/*** Solo se pueden ingresar los tipos de vacunas planteadosen la 1ra parte.* 
	 * Si el nombre de la vacuna no coincidiera con losespecificados se debe generar* una excepción.* 
	 * También se genera excepción si la cantidad es negativa.* 
	 * La cantidad se debe* sumar al stock existente, tomando en cuenta lasvacunas ya utilizadas.*/
	public void ingresarVacunas(String nombreVacuna,int cantidad, Fecha fechaIngreso) {
		
		boolean SeGuardo = false;
		

		try {
			if (cantidad <= 0) 
			fail("No se puede ingresar valores cero o negativos");

		} catch (RuntimeException e) { }
		
		
 {
		try {
			if (!fechaIngreso.anterior(Fecha.hoy()))
			fail("La fecha es Posterior del dia de hoy");
			} catch (RuntimeException e) { }			
		}
		
		
		//if(cantidad<=0)
	//		System.out.println("No se puede ingresar valores cero o negativos");
		//if (!fechaIngreso.anterior(Fecha.hoy()))
		//	System.out.println(("La fecha es Posterior del dia de hoy"));
		
		if (nombreVacuna.equals("Sputnik")) {
			Vacuna nuevaVacuna = new Sputnik(nombreVacuna, cantidad, fechaIngreso);
			 SeGuardo = VacunasDisponibles.add(nuevaVacuna);
		}
		
		if (nombreVacuna.equals("Pfizer")) {
			Vacuna nuevaVacuna = new Pfizer(nombreVacuna, cantidad, fechaIngreso);
			SeGuardo = VacunasDisponibles.add(nuevaVacuna);
			Fecha facha = fechaIngreso;
			for(int i = 0; i <=30; i++) {
				facha.avanzarUnDia();
			}
			nuevaVacuna.setVencimiento(facha);
			
		}
		
		if (nombreVacuna.equals("Moderna")) {
			Vacuna nuevaVacuna = new Moderna(nombreVacuna, cantidad, fechaIngreso);			
			SeGuardo = VacunasDisponibles.add(nuevaVacuna);
			Fecha facha = fechaIngreso;
			for(int i = 0; i <=60; i++) {
				facha.avanzarUnDia();
			}
			nuevaVacuna.setVencimiento(facha);
		}	
		
		if (nombreVacuna.equals("Sinopharm")) {
			Vacuna nuevaVacuna = new Sinopharm(nombreVacuna, cantidad, fechaIngreso);
			SeGuardo = VacunasDisponibles.add(nuevaVacuna);

		}
		
		if (nombreVacuna.equals("AstraZeneca")) {
			Vacuna nuevaVacuna = new AstraZeneca(nombreVacuna, cantidad, fechaIngreso);
			SeGuardo = VacunasDisponibles.add(nuevaVacuna);
		}
		
		//Si el  nombre lanza una exepcion, sino agrega a la cantidad de vacunas general
		if (!SeGuardo) {
			try {
				fail("El nombre no pertenece a una vacuna admitida");
			} catch (RuntimeException e) { }
		}
		else
			vacuna.setCantidad(vacuna.getCantidad()+cantidad);
	

	}
	
	/*** total de vacunas disponibles no vencidas sin distinción por tipo.
	 *Ademas Se borrar las vacunas vencidas y se devuelve al stock las vacunas no aplicadas */
	public int vacunasDisponibles() {
		BorrarVacunasVencidas();
		BorrarTurnoVencidos();

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
		
		if (tienePadecimientos && esEmpleadoSalud) {
			try {
				fail("Personas con padecimientos no deben trabajar sector salud");
			} catch (RuntimeException e) { }
		}

				
		int edad = Fecha.diferenciaAnios(Fecha.hoy() , nacimiento);
		Persona nueva = new Persona(dni,edad,tienePadecimientos,esEmpleadoSalud);
		
/*		if (edad>= 60 && esEmpleadoSalud) {
			try {
				fail("Personas mayores 60 no deben trabajar sector salud");
			} catch (RuntimeException e) { }
		}*/
		
		try {
				if (edad< 18)
				fail("Es Menor de 18");
			} catch (RuntimeException e) { }
	

		try {
				if (Inscriptos.contains(dni))
				fail("Ya esta inscripto");
			} catch (RuntimeException e) { }
		
		int i = 0;
		boolean Inscribido = false;
		
		if (Inscriptos.size() == 0) {
			Inscriptos.add(nueva);
			Inscribido = true;
		}
		/*Se guarda por prioridad EmpleadoSalud > Mayor60 > Padecimientos > general
		  
		  Si la persona es emplado saludo busca en la lista el primer mayor De 60 reemplaza posicion
		  y el resto sde mueve a la izquierda (sino hay reemplaza al siguiente en prioridad) */
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
		
		/*Si es mayor de 60 busca el primero con Padecimientos reemplaza posicion
		  y el resto sde mueve a la izquierda (sino hay reemplaza al siguiente en prioridad) */
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
 		
 		/*Si tienePadecimientos busca el primero de la poblacion en general reemplaza posicion
		  y el resto sde mueve a la izquierda*/
		while (tienePadecimientos && !Inscribido && i < Inscriptos.size()) {
			if (Inscriptos.get(i).getEdad()>=60 || Inscriptos.get(i).isEsEmpleadoSalud() || Inscriptos.get(i).isTienePadecimientos())
				i++;
			if (!Inscriptos.get(i).isTienePadecimientos() && !Inscriptos.get(i).isEsEmpleadoSalud() && !(Inscriptos.get(i).getEdad()>=60) ) {
				Inscriptos.add(i, nueva);
				Inscribido = true;
			}
		}
		
		//Si no cumple con ningun condicional se agrega al final
		if(!Inscribido)
			Inscriptos.add(nueva);
				

	}

	/*** Devuelve una lista con los DNI de todos los inscriptos que no se vacunaron
	y que no tienen turno asignado.*
	Si no quedan inscriptos sin vacunas debe devolver una lista vacía.*/
	public LinkedList<Integer> listaDeEspera(){
		LinkedList<Integer> lista = new LinkedList<Integer>();
		for (Persona lape: Inscriptos) {
			lista.add(lape.getDni());
		}
		return lista;
	}
	
	/*** Primero se verifica si hay turnos vencidos. En caso de haber turnos
	vencidos, la persona que no asistió al turno debe ser borrada del sistema
	y la vacuna reservada debe volver a estar disponible.
	* Segundo, se deben verificar si hay vacunas vencidas y quitarlas del sistema.
	*  Por último, se procede a asignar los turnos a partirde la fecha inicial 
	recibida según lo especificado en la 1ra parte.* 
	Cada vez que se registra un nuevo turno, la vacuna destinada a esa persona 
	dejará de estar disponible. Dado que estará reservada para ser aplicada
	el día del turno. */
	public void generarTurnos(Fecha fechaInicial) {

		BorrarTurnoVencidos();

		BorrarVacunasVencidas();

		int i = 0;
		int Cupos = 0;
		while(i < Inscriptos.size() && Inscriptos.size() != 0 && VacunasDisponibles.size()!= 0) {
			if(Inscriptos.get(i).getEdad() >= 60) {
				if (TurnoMAyor60(Inscriptos.get(i), fechaInicial))
					Cupos++;
			}
			else {
					int j = 0;
					boolean NoVence = false;
					//Verifica que la fecha del turno sea menor a la del vencimiento de la vacuna Moderna
					while(j <VacunasDisponibles.size() && !NoVence && VacunasDisponibles.get(j).getNombreVacuna().equals("Moderna")) {
							if(!fechaInicial.posterior(VacunasDisponibles.get(j).getVencimiento())) {
								NoVence = true;
						}
						j++;					
					}
					
					if (j <VacunasDisponibles.size() ) {
						CalendarioVacunacion.add(new Turno(Inscriptos.get(i), VacunasDisponibles.get(0),fechaInicial));
						CambiarContVacuna(j);
						vacuna.setCantidad(vacuna.getCantidad()-1);
						Cupos++;
					}
				}
			if(Cupos == CapacidadXdia) {
				fechaInicial.avanzarUnDia();
				Cupos = 0;
			}
			
			i++;
		}
	}

	/*** Devuelve una lista con los dni de las personas quetienen turno asignado* 
	* para la fecha pasada por parámetro.* Si no hay turnos asignados para ese día,
	 se debedevolver una lista vacía.
	* La cantidad de turnos no puede exceder la capacidadpor día de la ungs.*/
	
	public ArrayList<Integer> turnosConFecha(Fecha fecha){
		ArrayList<Integer> lista = new ArrayList<Integer>();
		for(Turno f: CalendarioVacunacion) {
			if(fecha.equals(f.getFecha()) && lista.size()<=CapacidadXdia) {
				lista.add(f.getPersona().getDni());
			}
		}
		return lista;
	}

			
	/*** Dado el DNI de la persona y la fecha de vacunación*
	*se valida que esté inscripto y que tenga turno para ese dia.*
	*-Si tiene turno y está inscripto se debe registrarla persona como*
	*vacunada y la vacuna se quita del depósito.
	*-Si no está inscripto o no tiene turno ese día,se genera una Excepcion.*/
	
	public void vacunarInscripto(int dni, Fecha fechaVacunacion) {
		HashMap<Integer,String> dicc= new HashMap<Integer,String>();
		ArrayList<Integer> ca= new ArrayList<Integer>();
		ca= turnosConFecha(fechaVacunacion);
		for(Map.Entry<Integer,String> d : Vacunados.entrySet()) {
			for(Integer tur : ca) {
				if (! d.getKey().equals(tur)) {
			//		Vacunados.put(tur,NombreVacuna)
						
			}
	}
		}
	}
	/* Devuelve un Diccionario donde*-la clave es el dni de las personas vacunadas
	 *-Y, el valor es el nombre de la vacuna aplicada.*/
	public Map<Integer, String> reporteVacunacion(){
		Map<Integer,String> report = new HashMap<Integer, String>();
		return report;
	}

	/*** Devuelve en O(1) un Diccionario:*-clave: nombre de la vacuna
	 *-valor: cantidad de vacunas vencidas conocidashasta el momento.*/
	public Map<String, Integer> reporteVacunasVencidas(){
		Map<String, Integer> coso = new HashMap<String, Integer>();
		return coso;
	}

	
	
	
//---------------------------------- metodos de metodos -------------------------------------------------	
	
	// Borra el turno y agrega Vacuna al stock al principio de la lista
	private void BorrarTurnoVencidos() {
		Iterator<Turno> Iterador= CalendarioVacunacion.iterator();
		while(Iterador.hasNext()) {
			Turno T = (Turno) Iterador.next();
			if (T.getFecha().posterior(Fecha.hoy()))
				VacunasDisponibles.add(0,T.getVacuna());
				Iterador.remove();
		}
		
		
	}
	
	/*Borra las vacunas que el vencimiento es menor a la fecha del dia de hoy
	  Se disminuye el contador de las vacunas en general y la del nombre especifico*/
	private void BorrarVacunasVencidas(){
		
		Iterator<Vacuna> Iterador= VacunasDisponibles.iterator();
		
		while(Iterador.hasNext()) {
			Vacuna V = (Vacuna) Iterador.next();
			if (V.getNombreVacuna().equals("Pfizer") && V.getVencimiento().anterior(Fecha.hoy())) {
				vacuna.setCantidad(vacuna.getCantidad()- V.getCantidad());
				Iterador.remove();

			}
			if (V.getNombreVacuna().equals("Moderna") && V.getVencimiento().anterior(Fecha.hoy())) {
				vacuna.setCantidad(vacuna.getCantidad()- V.getCantidad());
				Iterador.remove();
			}
		}
	}
	
	/*-Agrega la persona si es mayor de 60
	  -verifica que la vacuna solo sea Sputnik o Pfizer
	  -Y Ademas que la fecha del turno no este vencida la vacuna
	  -Sino agrega el turno devuelve false para no sumar un cupo por dia*/
	
	private boolean TurnoMAyor60(Persona persona, Fecha fechainicial) {
		int j=0; 
		boolean SeAgrego = false;
		while(j < VacunasDisponibles.size() && !SeAgrego) {
				if(VacunasDisponibles.get(j).getNombreVacuna().equals("Pfizer") && VacunasDisponibles.get(j).getVencimiento().posterior(fechainicial)) {
					CalendarioVacunacion.add(new Turno(Inscriptos.get(j), VacunasDisponibles.get(j), fechainicial));
					CambiarContVacuna(j);
					VacunasDisponibles.get(j).setCantidad(VacunasDisponibles.get(j).getCantidad()-1);
					vacuna.setCantidad(vacuna.getCantidad()-1);
					SeAgrego = true;

				}
				if(VacunasDisponibles.get(j).getNombreVacuna().equals("Sputnik")) {
					CalendarioVacunacion.add(new Turno(Inscriptos.get(j), VacunasDisponibles.get(j),fechainicial));
					CambiarContVacuna(j);
					VacunasDisponibles.get(j).setCantidad(VacunasDisponibles.get(j).getCantidad()-1);
					vacuna.setCantidad(vacuna.getCantidad()-1);
					SeAgrego = true;
				}
				j++;
		}
		return SeAgrego;
	}
		
	
		
	// Disminuye 1 la cantidad de Vacuna.Si la cantidad es 0 borra de la lista la vacuna
	private void CambiarContVacuna(int pos) {
		VacunasDisponibles.get(pos).setCantidad(VacunasDisponibles.get(pos).getCantidad()-1);
		if (VacunasDisponibles.get(pos).getCantidad() == 0)
			VacunasDisponibles.remove(pos);
	}
	
	//Devuelve el nombre de la vacuna
	
	private String  nombreVacuna (int dni) {
		for(Turno f: CalendarioVacunacion) {
			if(f.getPersona().getDni() == dni) {
				return f.getVacuna().getNombreVacuna();
			
			
			}
		}
		return "";
	}
	
		
	
	
	
	@Override
	//a completar
	public String toString() {
		return "" + Nombre + "  Capacidad de vacunaciones por dia : " + CapacidadXdia ;
	}
}
