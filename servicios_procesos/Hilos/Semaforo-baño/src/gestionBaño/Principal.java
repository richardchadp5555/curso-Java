package gestionBaño;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 1ºDAM
 * Descripcion: clase Principal que concentra la funcionalidad del programa. En este ejercicio disponemos de 3 servicios o baños cuyo acceso queremos controlar mediante un semáforo
 * El programa generará 3 hilos con un nombre de persona. El programa producirá mensajes como "pepito va al baño"
 * Lenguaje: Java
 */
public class Principal {
	public static void main(String[] args) {
		// Declaramos un semáforo
		Semaphore servicios = new Semaphore(3);
		
		// Array de personas
		Persona[] personas = new Persona[7];
		
		// Array de String con los nombres de las personas
		String[] nombres = {"Antonio", "Laura", "Pepe", "Pedro", "Ana", "Alberto", "Maria"};
		
		// Iniciamos los hilos
		for (int i = 0; i < nombres.length; i++) {
			personas[i] = new Persona(nombres[i], servicios);
			Thread hilo = new Thread(personas[i]);
			hilo.start();
		}
		
	}
}
 