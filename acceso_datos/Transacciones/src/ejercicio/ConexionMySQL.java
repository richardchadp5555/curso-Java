package ejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Autor: Richard Chadwick Plaza
 * Fecha: 11 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase ConexionMySQL, sirve para conectarnos con la base de datos en local
 */

public class ConexionMySQL {

	private static final String URL = "jdbc:mysql://localhost:3306/transaccionesbd";
	private static final String USUARIO = "root";
	private static final String PASSWORD = "(richardDAM2)";
	
	public static Connection getConexion() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			//System.out.println("Conexión exitosa");
		}catch (SQLException e){
			System.out.println("Error al conectar con la base de datos");
			e.printStackTrace();
		}
		return conexion;
	}
}
 