

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class MainCreate {
	private static final String HOST = "localhost";
	private static final String BBDD = "prog_biblioteca";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) {
		String nombre, apellido, dni, password;
		int id;
		boolean esAdmin;

		Connection conexion;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);
			
			PreparedStatement pstInsert = conexion.prepareStatement("INSERT INTO usuarios(nombre, apellido, dni, admin, password) VALUES (?, ? ,?, ?, ?)");
			pstInsert.setString(1, "aitor");
			pstInsert.setString(2, "badiola");
			pstInsert.setString(3, "11223344w");
			pstInsert.setBoolean(4, false);
			pstInsert.setString(5, "badiola2020");
			pstInsert.execute();

			conexion.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
