package modeloProductorConsumidor;

import java.util.LinkedList;

/*Autor: Richard Chadwick Plaza
 * Fecha: 30 nov 2024 
 * Curso: 1ºDAM
 * Descripcion: esta clase representa el buffer, que será el espacio compartido entre los camareros (productores) y los cocineros (consumidores). 
 * usaremos una estructura interna, como una cola, para simultar esta lista de pedidos. El buffer debe cumplir con las siguientes condiciones:
 * 	-	Sincronización: un solo hilo podrá acceder al buffer a la vez
 * 	-	Control de límites: el buffer tendrá una capacidad máxima. Si está lleno, los productores esperan. Si está vacío, los consumidores esperan
 *  Lenguaje: Java
 * Asignatura: Programación de Procesos y servicios
 */

public class BufferPedidos {
	// Atributos
	private final int capacidad;						// Capacidad máxima del buffer
	private final LinkedList<String> pedidos;				// Buffer compartido 
	
	// Constructor
	public BufferPedidos(int capacidad) {
		this.capacidad = capacidad;
		this.pedidos = new LinkedList<>();
	}
	
	// Método para que los camareros (productores) agreguen un pedido
	public synchronized void agregarPedido(String pedido) throws InterruptedException {
		while (this.pedidos.size() == capacidad) {			// Si el buffer está lleno espera
			wait();
		}
		// Agrega el pedido al final de la cola
		this.pedidos.add(pedido);
		System.out.println("Pedido agregado: " + pedido);
		notifyAll();		// Notifica a los cocineros (consumidores) de que hay un pedido disponible
	}
	
	// Método para que los cocineros (consumidores) retiren un pedido
	public synchronized  String retirarPedido() throws InterruptedException {
		while (this.pedidos.isEmpty()) {		// SI el buffer está vacío espera
			wait();
		}
		// Retira el primer pedido de la cola
		String pedido = this.pedidos.removeFirst();
		System.out.println("Pedido retirado: " + pedido);
		notifyAll();						// Notificamos a los camareros (productores) de que hay espacio disponible
		return pedido;
	}
}
 