

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.Scanner;

public class MainDelete {
	private static final String HOST = "localhost";
	private static final String BBDD = "prog_biblioteca";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) {
		String titulo, autor;
		int id, num_pag, aukera;
		Scanner scan = new Scanner(System.in);

		Connection conexion;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);

			System.out.println("Sar ezazu liburu baten id-a");
			aukera = Integer.parseInt(scan.nextLine());
			
			PreparedStatement pstDelete = conexion.prepareStatement("DELETE FROM libros WHERE id=?");
			pstDelete.setInt(1, aukera);
			pstDelete.execute();
			//delete bukaera
			
			
			conexion.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
