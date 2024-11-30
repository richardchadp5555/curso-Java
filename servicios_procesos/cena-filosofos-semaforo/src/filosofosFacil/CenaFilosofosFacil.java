package filosofosFacil;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 29 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase CenaFilosofosFacil, en esta clase resolvemos un ejercicio de gestión de hilos en Java. El ejercicio simula cinco filósofos chinos que se reunen a cenar en una mesa redonda con 
 * 5 palillos, para comer cada filósofo necesita tener 2 palillos. Para representar los palillos usaremos un semáforo, este garantiza que cómo máximo 5 recursos (palillos) puedan ser tomados a la vez
 * Asignatura: Programación de procesos y servicios
 * Lenguaje: Java
 */

public class CenaFilosofosFacil {
	public static void main(String[] args) {
		// Declaramos un semáforo 
		Semaphore palillos = new Semaphore(5);		// 5 pallillos
		
		// Array para los filósofos
		Filosofo[] filosofos = new Filosofo[5];
		String [] nombresFilosofos = {"Sócrates", "Platón", "Aristóteles", "Descartes", "Nietzsche"};
		
		// Inicializamos los filósofos y empezamos los hilos
		for (int i = 0; i < 5; i++) {
			filosofos[i] = new Filosofo(nombresFilosofos[i], palillos);
			filosofos[i].start();
		}
	}
}
 