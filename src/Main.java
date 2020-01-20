import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	// TODO bete konstanteak
	private static final String HOST = "localhost";
	private static final String BBDD = "prog_biblioteca";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	// TODO menuko aukerak hemen jarri
	private static final int SALIR = 0;
	private static final int LISTAR_LIBROS = 1;
	private static final int BUSCAR_LIBROS = 2;
	private static final int DELETE_LIBROS = 5;
	// .............................
	// ....................

	public static void main(String[] args) {
		
		//TODO datu basera conexioa egin
		Connection conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + BBDD, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);

		int aukera;
		do {
			menuPrincipal();

			aukera = Integer.parseInt(scan.nextLine());

			switch (aukera) {
			case LISTAR_LIBROS:
				listarLibros(conexion);
				break;
			case BUSCAR_LIBROS:
				buscarLibros(conexion);
				break;
			case DELETE_LIBROS:
				deleteLibros(conexion);
				break;
			case SALIR:
				System.out.println("El programa se ha cerrado....");
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
			}
		} while (aukera != SALIR);
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void buscarLibros(Connection conexion) {
		Statement st;
		String aukera, titulo, autor;
		int id, numPaginas;
		Scanner scan = new Scanner(System.in);
		Libro libro;
		
		try {
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from libros");
			
			while(rs.next()){
				
				libro = new Libro();
				titulo = rs.getString("nombre");
				autor = rs.getString("autor");
				id = rs.getInt("id");
				numPaginas = rs.getInt("numPaginas");
				
				libro.setTitulo(titulo);
				libro.setAutor(autor);
				libro.setId(id);
				libro.setNumPaginas(numPaginas);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	private static void deleteLibros(Connection conexion) {
		String titulo, autor;
		int id, num_pag, aukera;
		Scanner scan = new Scanner(System.in);
		
		try {
			System.out.println("Sar ezazu liburu baten id-a");
			aukera = Integer.parseInt(scan.nextLine());
			
			PreparedStatement pstDelete = conexion.prepareStatement("DELETE FROM libros WHERE id=?");
			pstDelete.setInt(1, aukera);
			pstDelete.execute();
			//delete bukaera
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	private static void listarLibros(Connection conexion) {
		Statement st;
		try {
			st = conexion.createStatement();
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
		
	private static void menuPrincipal() {
		System.out.println("*****************************************************");
		System.out.println("Aukeratu funtzio bat: ");
		System.out.println(LISTAR_LIBROS + ".- Listar libros.");
		System.out.println(DELETE_LIBROS + ".- Borrar libro");
		System.out.println(SALIR + ".- Salir");

	}

}