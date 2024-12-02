package gestionBiblioteca;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 2ºDAM
 * Descripcion: clase Principal, que contiene la función main
 * enunciado ejercicio : en una biblioteca, hay un único estante con capacidad para 3 libros. Los usuarios de la biblioteca pueden:
 * 	- dejar un libro en el estante si hay espacio suficiente (empleados)
 * 	- coger un libro del estante si hay un libro disponible (clientes)
 * Lenguaje: Java
 * Asignatura: Programación de Procesos y Servicios
 */

public class Principal {
	public static void main(String[] args) {
		// Buffer compartido entre los clientes y los empleados
		Estante estante = new Estante(3);
		
		// Crear empleados (productores)
		Empleado empleado1 = new Empleado("Antonio", estante);
		Empleado empleado2 = new Empleado("Adolfo", estante);
		
		// Crear clientes (consumidores)
		Cliente cliente1 = new Cliente("Richard", estante);
		Cliente cliente2 = new Cliente("Belinda",estante);
		
		// Iniciamos los hilos
		new Thread(empleado1).start();
		new Thread(empleado2).start();
		new Thread(cliente1).start();
		new Thread(cliente2).start();
	}
}
 