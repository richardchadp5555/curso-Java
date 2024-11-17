package conexion;

/*Autor: Richard Chadwick Plaza
 * Fecha: 17 nov 2024 
 * Curso: 1ºDAM
 * Descripcion: clase ConexionJDBC. En esta clase probamos a conectarnos a una base de datos en MySQL en local con JDBC
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
	public static void main(String[] args) {
		//Configuración de la base de datos
		String url = "jdbc:mysql://localhost:3306/mi_base_datos";			//Dirección a la base de datos		
		String user = "root";																					//Usuario
		String password = "(richardDAM2)";														//Contraseña
		
		//Intentamos conectarnos con la base de datos
		try(Connection connection = DriverManager.getConnection(url, user, password)){
			if(connection != null) {
				System.out.println("Conexión exitosa! ");
			}
		}catch(SQLException e) {
			System.out.println("Error al conectar a la base de datos " + e.getMessage());
		}
	}
	
}
 