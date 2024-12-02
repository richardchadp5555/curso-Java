package modeloProductorConsumidor;

/*Autor: Richard Chadwick Plaza
 * Fecha: 30 nov 2024 
 * Curso: 1ºDAM
 * Descripcion: clase Camarero (productor) representa el hilo encargado de generar pedidos y colocarlos en el buffer.
 * El camarero es un hilo que:
 * 	- Genera pedidos
 * 	- Intenta colocar los pedidos en el buffer usando el método agregarPedido de la clase BufferPedidos
 * 	- Espera un tiempo entre cada pedido para simular el proceso real de atención a clientes
 * Lenguaje: Java
 * Asignatura: Programación de Procesos y Servicio
 */

public class Camarero implements Runnable {
	// Atributos
	private final BufferPedidos buffer;

	// Constructor
	public Camarero (BufferPedidos buffer) {
		this.buffer = buffer;
	}
	
	// Sobrescribimos el método run
	@Override
	public void run() {
		String[] pedidos = {"Hamburguesa", "Pizza", "Ensalada", "Sopa", "Pasta"};
		try {
			// Generamos 10 pedidos
			for (int i = 0; i < 10; i++) {
				int indice = (int) (Math.random() * 5);
				String pedido = pedidos[indice] + " #" + (i +1);
				this.buffer.agregarPedido(pedido); 			// Agregamos el pedido
				System.out.println("Camarero tomó el pedido: " + pedido);
				// Tiempo de espera entre pedidos, 2 sg
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Camarero interrumpido. ");
		}
		
	}
	
	
}
 