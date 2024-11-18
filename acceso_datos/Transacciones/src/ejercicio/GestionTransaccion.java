package ejercicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*Autor: Richard Chadwick Plaza
 * Fecha: 17 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: esta clase gestiona la transacción, en ella se hará lo siguiente:
 * 	- 1. Conectar a la base de datos usando la clase ConexionMySQL
 * 	- 2. Iniciar una transacci'on con setAutoCommit(false)
 * 	- 3. Ejecutar las operaciones INSERT y UPDATE
 * 	- 4. Confirmar la transacción con COMMIT() si todo sale bien
 * 	- 5. Revertir los cambios con rollback() si algo falla
 */

public class GestionTransaccion {
	// Variables globales para rastrear el último envío registrado, -1 es el valor por defecto, realmente ese -1 se interpreta luego como que no hay valor
	private static int ultimoCodPedido = -1;
	private static int ultimoCodRider = -1;

	public static void main(String[] args) {
		
		// Objeto scanner
		Scanner teclado = new Scanner(System.in);
		
		// Variable bandera para gestionar el bucle con el menu
		Boolean continuar = true;
		
		// Conexion a la base de datos	 inicialmente 'null'
		Connection conexion = null;
		
		try {
			// Establecemos la conexión con la base de datos
			conexion = ConexionMySQL.getConexion();
			
			// Desactivamos autocomit
			if(conexion != null) {
				conexion.setAutoCommit(false);
			}else {
				throw new SQLException("No se pudo establecer conexión con la base de datos ");
			}
			
			// Bucle con menu
			do {
				verMenu();
				int opcionMenu = teclado.nextInt();
				teclado.nextLine(); 					//Para evitar problemas con el buffer
				
				switch(opcionMenu) {
				case 1:
					insertarPedido(conexion, teclado);
					break;
				case 2:
					actualizarExistencias(conexion, teclado);
					break;
				case 3:
					registrarEnvio(conexion, teclado);
					break;
				case 4:
					 try {
					        conexion.commit();
					        System.out.println("Transacción confirmada exitosamente.");
					        
					        // Si se ha registrado un envío inciamos el hilo
					        if (ultimoCodPedido != -1 && ultimoCodRider != -1) {
					        	NotificarEntrega hiloEntrega = new NotificarEntrega(conexion, ultimoCodPedido, ultimoCodRider);
					        	hiloEntrega.start();
					        	System.out.println("El pedido está en reparto ");
					        	
					        	// Esperamos a que el hilo termine para volver a dejar las variables ultimoCodPedido y ultimoCodRider en -1 para evitar que se vuelva a repartir un pedido que  ya ha sido repartido
					        	try {
					        		hiloEntrega.join(); 		// Esperamos a que le hilo termine
					        		// Reiniciamos las variables
					        		ultimoCodPedido = -1;
					        		ultimoCodRider = -1;
					        	} catch(InterruptedException e) {
					        		 System.out.println("Error al esperar a que termine el hilo: " + e.getMessage());
					        	}
					        }
					    } catch (SQLException e) {
					        System.out.println("Error al confirmar la transacción: " + e.getMessage());
					    }
					break;
				case 5:
					try {
				        conexion.rollback();
				        System.out.println("Transacción revertida correctamente.");
				    } catch (SQLException e) {
				        System.out.println("Error al revertir la transacción: " + e.getMessage());
				    }
					break;
				case 6:
					System.out.println("Saliendo...");
					continuar = false;
					break;
				default:
					System.out.println("Opción introducida no válida, introduce un número del 1 al 6");
				}
			}while(continuar);
			
			
		}catch(SQLException e) {		// Si hay un error revertimos hacemos rollback()
			if(conexion != null) {
				try {
					System.out.println("Error en la transacción, haciendo rollback()...");
					conexion.rollback();
				}catch(SQLException ex) {
					System.out.println("Error al hacer rollback");
					ex.printStackTrace();
				}
			}
			
		}finally {				//Nos aseguramos de cerrar la conexión y el scanner
			teclado.close();
			try {
				conexion.close();
				System.out.println("Conexion cerrada correctamente");
			}catch (SQLException excep) {
				System.out.println("Error al cerrar la conexión: ");
				excep.printStackTrace();
			}
		}

	}
	
	// Método que muestra el menu
	public static void verMenu() {
		StringBuilder menu = new StringBuilder();
		menu.append("Introduce una opción (1 - 6): \n");
		menu.append("\t- 1. Insertar un pedido: \n");
		menu.append("\t- 2. Actualizar existencias: \n");
		menu.append("\t- 3. Registrar un envio: \n");
		menu.append("\t- 4. Confirmar (commit): \n");
		menu.append("\t- 5. Cancelar (rollback): \n");
		menu.append("\t- 6. Salir: \n");
		System.out.println(menu);
	}
	
	// Método para insertar un nuevo pedido con los datos introducidos por el usuario
	public static void insertarPedido(Connection conexion, Scanner teclado) throws SQLException{
		System.out.println("\n-------- Insertar Pedido --------");
		System.out.println("Introduce el nombre del cliente:");
		String nombreCliente = teclado.nextLine().trim().toLowerCase();
		System.out.println("Introduce la dirección del pedido: ");
		String direccion = teclado.nextLine();
		System.out.println("Introduce el nombre del artículo: ");
		String nombreArticulo = teclado.nextLine().trim().toLowerCase();
		
		try {
			// Obtenemos el código del cliente
			int codCliente = obtenerCodigoCliente(conexion, nombreCliente);
			if(codCliente == -1) {
				System.out.println("Cliente no encontrado ");
				return;
			}
			
			// Obtenemos el código del artículo
			int codArticulo = obtenerCodigoArticulo(conexion, nombreArticulo);
			if(codArticulo == -1) {
				System.out.println("Artículo no encontrado ");
				return;
			}
			
			// Insertamos el pedido
			String sql = "INSERT INTO pedidos (fecha, codcli, direccion, codart) VALUES (CURDATE(), ?, ?, ?)";
			try (PreparedStatement pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
				pstmt.setInt(1,  codCliente);
				pstmt.setString(2, direccion);
				pstmt.setInt(3, codArticulo);
				pstmt.executeUpdate();
				
				// Obtengo el codigo de este pedido
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    ultimoCodPedido = rs.getInt(1);
	                    System.out.println("Pedido registrado con código " + ultimoCodPedido + ".");
	                }
	            }
			}
			
			// Actualizamos las existencias del artículo, por defecto en cada pedido se pide 1 artículo por lo que las existencias de este disminuyen en 1
			String sqlActualizarExistencias = "UPDATE articulos SET existencias = existencias - 1 WHERE codart = ?";
			try(PreparedStatement pstmt = conexion.prepareStatement(sqlActualizarExistencias)){
				pstmt.setInt(1, codArticulo);
				pstmt.executeUpdate();
				System.out.println("Se han actualizado las existencias del artículo. ");
				
			}
			
		}catch(SQLException e) {
			System.out.println("Error al registrar el pedido, haciendo rollback" + e.getMessage());
			conexion.rollback();
		}
	}
	
	//Método para actualizar las existencias
	public static void actualizarExistencias(Connection conexion, Scanner teclado) throws SQLException{
		System.out.println("\n-------- Actualizar existencias --------");
		System.out.println("Introduce el nombre del artículo: ");
		String nombreArticulo = teclado.nextLine();
		
		// Comprobamos que el artículo existe
		int codArticulo = obtenerCodigoArticulo(conexion, nombreArticulo);
		if(codArticulo == -1) {
			System.out.println("Artículo no encontrado: ");
			return;
		}
		
		// Si existe solicitamos las nuevas existencias
		System.out.println("Introduce el nuevo valor para las existencias del producto: ");
		int nuevasExistencias = teclado.nextInt(); 
		teclado.nextLine(); //Buffer
		
		// Actualizamos las existencias
		String sql = "UPDATE articulos SET existencias = ? WHERE codart = ?";
		try(PreparedStatement pstmt = conexion.prepareStatement(sql)){
			pstmt.setInt(1, nuevasExistencias);
			pstmt.setInt(2, codArticulo);
			pstmt.executeUpdate();
			System.out.println("Existencias actualizadas correctamente. ");
		}
	}
	
	// Método para registrar el envío
	public static void registrarEnvio(Connection conexion, Scanner teclado) throws SQLException {
		if (ultimoCodPedido == -1) {			//Quiere decir que no se ha insertado ningún pedido aún
			System.out.println("No hay un pedido registrado en la transacción actual.");
	        return;
		}
		System.out.println("\n-------- Registrar envío --------");
		// Buscamos un rider disponible
		int  codRider = obtenerRiderDisponible(conexion);
		if (codRider == -1) {
			System.out.println("No hay riders disponibles ");
			return;
		}
		
		// Ahora que hemos comprobado que el pedido existe y que hay Rider disponible registramos el envío
		String sqlEnvio = "INSERT INTO envios (codped, codrider, terminado) VALUES (?, ?, NULL)";
		try (PreparedStatement pstmt = conexion.prepareStatement(sqlEnvio)){
			pstmt.setInt(1, ultimoCodPedido);
			pstmt.setInt(2, codRider);
			pstmt.executeUpdate();
			System.out.println("Envío registrado correctamente. ");
		}
		
		// Actualizamos la disponibilidad del rider a "no disponible"
		String sqlActualizarDisponibilidadRider = "UPDATE riders SET disponibilidad = 0 WHERE codrider = ?";
		try(PreparedStatement pstmt = conexion.prepareStatement(sqlActualizarDisponibilidadRider)){
			pstmt.setInt(1,  codRider);
			pstmt.executeUpdate();
			System.out.println("Se ha actualizado el rider a no disponible");
		}
		
		// Guardamos el código del rider actual
		ultimoCodRider = codRider;
	}
	
	// Método para buscar un rider disponible
	private static int obtenerRiderDisponible(Connection conexion) throws SQLException{
		String sql = "SELECT codrider FROM riders WHERE disponibilidad = 1 LIMIT 1";
		try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return rs.getInt("codrider");
				}else {
					return -1;
				}
			}
		}
	}
	// Método para obtener el código del artículo
	private static int obtenerCodigoArticulo(Connection conexion, String nombreArticulo) throws SQLException{
		String sql = "SELECT codart FROM articulos WHERE descripcion = ?";
		try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
			pstmt.setString(1, nombreArticulo);
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					return rs.getInt("codart");
				}
			}
		}
		return -1;		//Artículo no encontrado
	}

	// Método para obtener el código del cliente
	private static int obtenerCodigoCliente(Connection conexion, String nombreCliente) throws SQLException{
		String sql = "SELECT codcli FROM clientes WHERE nombre = ?";
		try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
			pstmt.setString(1, nombreCliente);
			try (ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return rs.getInt("codcli");
				}
			}
		}
		return -1;
	}
}
 