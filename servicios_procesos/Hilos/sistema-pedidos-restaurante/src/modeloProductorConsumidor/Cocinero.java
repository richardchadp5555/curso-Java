package modeloProductorConsumidor;

/*Autor: Richard Chadwick Plaza
 * Fecha: 30 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase Cocinero (consumidor), el cocinero es un hilo que:
 * 	- Retira los pedidos del buffer usando el método retirarPedido de la clase BufferPedidos
 * 	- Prcoesa los pedidos (simulado con un mensaje en la consola).
 * 	- Espera un tiempo entre la preparación de un pedido y el siguiente.
 * Lenguaje: Java
 * Asignautra: Programación de Procesos y Servicios
 */

public class Cocinero implements Runnable{
	// Atributos
	private final BufferPedidos buffer;
	
	// Constructor
	public Cocinero (BufferPedidos buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		try {
			while(true) {			// El cocinero trabaja trabaja de forma continua
				String pedido = buffer.retirarPedido();		// Retira el pedido del buffer
				System.out.println("El cocinero está trabajando en el pedido: " + pedido);
				
				// Simula el tiempo de preparación del pedido, 3 segundos
				Thread.sleep(3000);
				System.out.println("El cocinero terminó el pedido: " + pedido);
				
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
 