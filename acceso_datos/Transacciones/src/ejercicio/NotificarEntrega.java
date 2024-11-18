package ejercicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*Autor: Richard Chadwick Plaza
 * Fecha: 18 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase NotificarEntrega, implementa un hilo que simula el proceso de entrega de un pedido. Su propósito 
 * es actualizar la base de datos después de un tiempo aleatorio para reflejar la finalización de la entrega de un pedido y
 * liberar al rider asignado, marcandolo como "disponible"
 */

public class NotificarEntrega extends Thread {
	// Atributos
	private Connection conexion;
	private int codPedido;
	private int codRider;
	
	//Constructor
	public NotificarEntrega(Connection conexion, int codPedido, int codRider) {
		this.conexion = conexion;
		this.codPedido = codPedido;
		this.codRider = codRider;
	}
	
	// Método run()
	@Override
	public void run() {
		try {
			// Simulamos un tiempo de entrega aleatorio
			int tiempoEntrega = (int) (Math.random() * 5000 + 2000);				// Entre 2 y 7 segundos
			Thread.sleep(tiempoEntrega);
			System.out.println("El rider " + codRider + " está entregando el pedido " + codPedido);
			
			// Actualizamos  la tabla de pedido con la fecha-hora de entrega
			String sqlActualizarEntrega = "UPDATE pedidos SET fecha = NOW() WHERE codped = ?";
			try (PreparedStatement pstmt = conexion.prepareStatement(sqlActualizarEntrega)){
				pstmt.setInt(1, codPedido);
				pstmt.executeUpdate();
				System.out.println("Se ha marcado el pedido " + codPedido + " como entregado");
			}
			
			// Actualizamos la disponibilidad del rider
			String sqlActualizarRider = "UPDATE riders SET disponibilidad = 1 WHERE codrider = ?";
			try (PreparedStatement pstmt = conexion.prepareStatement(sqlActualizarRider)){
				pstmt.setInt(1, codRider);
				pstmt.executeUpdate();
				System.out.println("El rider " + codRider + " vuelve a estar disponible al haber terminado la entrega del pedido");
			}
		} catch (InterruptedException e) {
			System.out.println("Hilo interrumpido " + e.getMessage());
		} catch(SQLException e) {
			System.out.println("Error al actualizar la base de datos " + e.getMessage());
		}
	}
}
 