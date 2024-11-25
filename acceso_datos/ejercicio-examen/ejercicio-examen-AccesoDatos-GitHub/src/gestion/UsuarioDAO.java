package gestion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/*Autor: Richard Chadwick Plaza
 * Fecha: 24 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: Esta clase sigue el patrón DAO (Data Access Object), que encapsula el acceso
 * a la base de datos para la tabla `usuarios`. Su propósito es gestionar 
 * operaciones como insertar, actualizar y consultar usuarios, separando la 
 * lógica de acceso a datos de la lógica principal del programa.
 */
public class UsuarioDAO {
	public void insertarUsuario(String login, String password, String nombreCompleto) {
		Connection connection = ConexionBD.getConnection();
		CallableStatement smt = null;
		
		try {
			// Llamar al procedimiento almacenado de la base de datos
			String sql = "{CALL insertUser(?, ?, ?)}";	
			smt = connection.prepareCall(sql);
			
			smt.setString(1, login);
			smt.setString(2, password);
			smt.setString(3, nombreCompleto);
			
			// Ejecutar el procedimiento
			smt.execute();
			System.out.println("Usuario insertado correctamente: " + login);
		}catch(SQLException e) {
			System.out.println("Error al insertar el usuario " + e.getMessage());
		}finally {
			// Cerrar el CallableStatement 
			if(smt != null) {
				try {
					smt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
 