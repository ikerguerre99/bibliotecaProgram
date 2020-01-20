import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

public class MainSelect {
	private static final String HOST = "localhost";
	private static final String BBDD = "prog_biblioteca";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) throws SQLException {
		String nombre, titulo, autor, apellido, dni, password;
		int id, num_pag;
		boolean esAdmin;
		Usuario usuario;

		Connection conexionLibros = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexionLibros = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);


			/*
			 * READ select guztiena eta pantailaratzea
			 */
			liburuakZerrendatu(conexionLibros);
			

			Connection conexionUsuarios;
			Class.forName("com.mysql.jdbc.Driver");
			conexionUsuarios = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);

			Statement stu = conexionUsuarios.createStatement();
			ResultSet rsu = stu.executeQuery("select * from usuarios");
			System.out.println("Erabiltzaile guztien zerrenda:");
			System.out.println("**********************************************************************************");
			while (rsu.next()) {
				id = rsu.getInt("id");
				nombre = rsu.getString("nombre");
				apellido = rsu.getString("apellido");
				dni = rsu.getString("dni");
				password = rsu.getString("password");
				esAdmin = rsu.getBoolean("admin");
				
				usuario = new Usuario();
				usuario.setId(id);
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setDni(dni);
				usuario.setPassword(password);
				

				System.out.println(usuario);
			}
			System.out.println("**********************************************************************************");
			// select bukaera

			conexionUsuarios.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	private static void liburuakZerrendatu(Connection conexionLibros) {
		Statement st;
		try {
			st = conexionLibros.createStatement();
			
			ResultSet rs = st.executeQuery("select * from libros");
			
			System.out.println("Liburu guztien zerrenda:");
			System.out.println("**********************************************************************************");
			while (rs.next()) {
				int id = rs.getInt("id");
				String titulo = rs.getString("titulo");
				String autor = rs.getString("autor");
				int num_pag = rs.getInt("num_pag");

				System.out.println(
						"id: " + id + ", titulo: " + titulo + ", autor: " + autor + "" + ", num_pag: " + num_pag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
}