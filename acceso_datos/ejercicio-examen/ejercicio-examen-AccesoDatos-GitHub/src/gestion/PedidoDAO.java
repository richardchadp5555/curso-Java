package gestion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*Autor: Richard Chadwick Plaza
 * Fecha: 24 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase PedidoDAO. En esta clase se manejan los pedidos de la base de datos, se registran nuevos pedidos y se actualiza el stock de los productos,
 * todo se hace en una transacción, permitiendo hacer rollback si algo va mal
 * 
 */
public class PedidoDAO {
	// Atributos
	 private int codUser;
	 private int idProducto;
	 private int cantidad;
	 
	 // Constructor
	 public PedidoDAO(int codUser, int idProducto, int cantidad) {
	        this.codUser = codUser;
	        this.idProducto = idProducto;
	        this.cantidad = cantidad;
	 }
	public void registrarPedido() {
		//Conexión
		Connection connection = ConexionBD.getConnection();
		
		// Consultas
		String insertPedidoSQL = "INSERT INTO pedidos (coduser, idproducto, cantidad) VALUES (?, ?, ?)";
		String updateStockSQL = "UPDATE productos SET stock = stock - ? WHERE idproducto = ? AND stock >= ?";
		
		PreparedStatement insertPedidosSentencia = null;
		PreparedStatement updateStockSentencia = null;
		
		try {
			//Inicio la transacción
			connection.setAutoCommit(false);
			
			// Inserto el pedido
			insertPedidosSentencia = connection.prepareStatement(insertPedidoSQL);
			insertPedidosSentencia.setInt(1, this.codUser);
			insertPedidosSentencia.setInt(2, this.idProducto);
			insertPedidosSentencia.setInt(3,  this.cantidad);
			insertPedidosSentencia.executeUpdate();
			
			// Actualizo el stock
			updateStockSentencia = connection.prepareStatement(updateStockSQL);
			updateStockSentencia.setInt(1, this.cantidad);
			updateStockSentencia.setInt(2, this.idProducto);
			updateStockSentencia.setInt(3, this.cantidad);
			
			// Capturo el número de filas actualizadas y ejecuto la sentencia. Si el stock es menor a la cantidad del pedido esta variable valdrá cero
			int filasActualizadas = updateStockSentencia.executeUpdate();
			
			// Compruebo que se ha actualizado el stock es decir si se ha hecho bien el pedido (si hay insuficiente stock las filas actualizadas son 0)
			if (filasActualizadas == 0) {
				throw new SQLException("El stock del producto ID:" + this.idProducto + " es insuficiente.");
			}
			
			// Confirmo la transacción
			connection.commit();
			System.out.println("Pedido registrado correctamente. ");
		}catch(SQLException  e) {
			try {
				// Revertir la transacción en caso de error
				connection.rollback();
				System.out.println("Transacción revertida: " + e.getMessage());
			}catch (SQLException rollbackException) {
				System.out.println("Error al hacer rollback: " + rollbackException.getMessage());
			}
		}finally {
			// Cierro los PreparedStatement
			try {
				if(insertPedidosSentencia != null) insertPedidosSentencia.close();
				if (updateStockSentencia != null) updateStockSentencia.close();
			}catch (SQLException sentenciaCloseException) {
				System.out.println("Error al cerrar los PreparedStatement: " + sentenciaCloseException.getMessage());
			}
		}
	}
}
 