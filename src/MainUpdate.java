import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class MainUpdate {
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

			PreparedStatement pstUpdate = conexion.prepareStatement("update usuarios set nombre=? where id=?");
			pstUpdate.setString(1, "bbbb");
			pstUpdate.setInt(2, 1);
			pstUpdate.executeUpdate();
			//update bukaera
		
			
			conexion.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
