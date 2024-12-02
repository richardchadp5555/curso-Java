package gestionSaludos;

/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Descripción: Clase principal que inicializa el sistema.
 *              - Crea los hilos de los empleados y el jefe.
 *              - Gestiona el saludo grupal mediante la sincronización de hilos.
 * Lenguaje: Java
 * Asignatura: Programación de Procesos y Servicios
 */

public class Principal {
	public static void main(String[] args) {
		Saludo saludo = new Saludo(3);			// Se esperan 3 empleados
		
		// Crear empleados
		Thread empleado1 = new Thread(new Personal("Pepe", saludo, false));
		Thread empleado2 = new Thread(new Personal("Laura", saludo, false));
		Thread empleado3 = new Thread(new Personal("Jose", saludo, false));
		
		// Crear jefe
		Thread jefe = new Thread(new Personal("Adolfo", saludo, true));
		
		//  Iniciamos los hilos
		empleado1.start();
		empleado2.start();
		empleado3.start();
		jefe.start();
	}
}
 