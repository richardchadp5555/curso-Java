package modeloProductorConsumidor;

/*Autor: Richard Chadwick Plaza
 * Fecha: 30 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: la clase principal será el putno de entrada de nuestra aplicación. En esta clase inicializaremos:
 * 	-	El buffer compartido donde se almacenarán los pedidos
 * 	-	Los hilos de los camarerors (productores) y cocineros (consumidores)
 * 	-	Los ejecutaremos para que comiencen a trabajar simultáneamente
 * Lenguaje: Java
 * Asignatura: Programación de Procesos y servicios
 */

public class Principal {
	public static void main(String[] args) {
		// Buffer compartido con capacidad de cinco pedidos
		BufferPedidos buffer = new BufferPedidos(5);
		
		// Crear los hilos para dos camareros
		Thread camarero1 = new Thread(new Camarero(buffer), "Camarero 1");
		Thread camarero2 = new Thread(new Camarero(buffer), "Camarero 2");
		
		// Dos hilos para cocineros
		Thread cocinero1 = new Thread(new Cocinero(buffer), "Cocinero 1");
		Thread cocinero2 = new Thread(new Cocinero(buffer), "Cocinero 2");
		
		// Iniciamos los hilos
		camarero1.start();
		camarero2.start();
		cocinero1.start();
		cocinero2.start();
	}
}
 