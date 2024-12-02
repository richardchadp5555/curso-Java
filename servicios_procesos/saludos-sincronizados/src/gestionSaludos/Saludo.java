package gestionSaludos;

/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Descripcion: la clase Saludo gestiona la sincronización entre empleados y el jefe en un saludo
 * 	- Los empleados esperan a que el jefe salude antes de responder
 * 	- El jefe notifica a todos los empleados cuando saluda
 * Lenguaje: Java
 * Asignatura: Programacion de Procesos y Servicios
 */

public class Saludo {
	// Atributos
	private boolean jefeHaSaludado;		// Indica si el jefe ya ha saludado, inicialmente es false
	private int totalEmpleados;					// Indica el número total de empleados esperados
	private int empleadosLlegaron = 0;	// Contador que indica el número de empleados que llega a la oficina
	
	// Constructor
	public Saludo(int totalEmpleados) {
		this.jefeHaSaludado = false;
		this.totalEmpleados = totalEmpleados;
	}
	
	// Métodos para los empleados
	public synchronized void saludarJefe(String nombreEmpleado) {
		System.out.println(nombreEmpleado + " ha llegado");
		empleadosLlegaron ++;
		
		// Cuando lleguen todos los empleados notificamos al jefe
		if (empleadosLlegaron == this.totalEmpleados) {
			notifyAll();
		}
		// Espera a que el jefe salude
		while (!jefeHaSaludado) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
				Thread.currentThread().interrupt();
				System.out.println("Error " + e.getMessage());
			}
		}
		// El empleado saluda al jefe
		System.out.println(nombreEmpleado + "-: Buenos días jefe. ");
	}
	
	// Método para el jefe
	public synchronized void saludarEmpleados(String nombreJefe) {
		// El jefe espera a que todos los empleados lleguen
		while (empleadosLlegaron < this.totalEmpleados) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Error: " + e.getMessage());
			}
		}
		// Saluda a los empleados
		System.out.println("(Jefe) " + nombreJefe + " -: Buenos días empleados");
		jefeHaSaludado = true;
		notifyAll();
	}
}
 