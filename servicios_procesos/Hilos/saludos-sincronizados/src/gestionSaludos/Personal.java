package gestionSaludos;

/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Descripción: Representa un hilo que puede ser un empleado o un jefe.
 *              - Los empleados esperan a que el jefe salude antes de responder.
 *              - El jefe espera a que lleguen los empleados y los saluda a todos  y los notifica para que continúen.
 * Lenguaje: Java
 * Asignatura: Programación de Procesos y Servicios
 */
public class Personal implements Runnable{
	//Atributos
	private String nombre;
	private Saludo saludo;
	private boolean esJefe;
	
	// Constructor
	public Personal(String nombre, Saludo saludo, boolean esJefe) {
		this.nombre = nombre;
		this.saludo = saludo;
		this.esJefe = esJefe;
	}
	
	@Override
	public void run() {
		if (esJefe) {
			// Si es jefe saluda a los empleados
			saludo.saludarEmpleados(nombre);
		} else {
			// Si es empleado saluda al jefe
			saludo.saludarJefe(nombre);
		}
		
	}

	

}
 