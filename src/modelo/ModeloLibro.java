package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ModeloLibro extends Conectar {
	public ModeloLibro() {
		super();
	}

	public ArrayList<Libro> seleccionarTitulos() throws Exception {

		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery("SELECT titulo FROM LIBROS ");
		// pasar de ResultSet a ArrayList

		ArrayList<Libro> libros = new ArrayList<Libro>();
		while (rs.next()) {
			Libro libro = new Libro();
			libro.setTitulo(rs.getString(1));

			libros.add(libro);
		}
		return libros;
	}

	public ArrayList<Libro> seleccionarNoPrestados() {
		PreparedStatement pst;
		ArrayList<Libro> libros = new ArrayList<Libro>();
		try {
			pst = super.cn.prepareStatement(
					"select libros.* from libros where id not in (select libros.id from libros join prestamos on libros.id = prestamos.id_libro and (prestamos.devuelto = ?))");
			pst.setBoolean(1, false);
			ResultSet rs = pst.executeQuery();

			Libro libro;
			while (rs.next()) {
				libro = new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setNum_pag(rs.getInt("num_pag"));
				libros.add(libro);
			}
			return libros;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libros;
		// TODO egiteko dago

	}

	public void insertar(Libro libro) throws SQLException {
		// escribir la instruccion INSERT
		try {

			PreparedStatement pst = cn.prepareStatement("INSERT INTO LIBROS (titulo, autor, num_pag) VALUES (?,?,?)");

			// System.out.println(pst);

			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.setInt(3, libro.getNum_pag());

			pst.execute();// ejecuta
			// System.out.println("Libro insertado correctamente");
		} catch (SQLException ex) {
			throw ex;

		}
	}

	public void borrar(int id) throws Exception {
		try {
			PreparedStatement pst = cn.prepareStatement("DELETE FROM libros WHERE id = ?");
			pst.setInt(1, id);
			pst.execute();// ejecuta

			if (pst.getUpdateCount() == 0) {// no a borrado nada
				// System.out.println("Libro no existe");
			} else {
				// System.out.println("Libro borrado correctamente");
			}

		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void modificar(int id, String nueva_direccion) throws Exception {
		try {

			PreparedStatement pst = cn.prepareStatement("UPDATE socios SET direccion=? WHERE id=?");

			pst.setString(1, nueva_direccion);
			pst.setInt(2, id);

			pst.execute();// ejecuta

			// System.out.println("Direccion del libro " + id + " modificado
			// exitosisimamente");
		} catch (Exception ex) {
			throw ex;

		}

	}

	public String seleccionarId(int id_libro) throws Exception {

		PreparedStatement pst;
		String titulo = "";
		try {
			pst = cn.prepareStatement("SELECT TITULO FROM LIBROS WHERE id=?");
			pst.setInt(1, id_libro);

			ResultSet rs = pst.executeQuery();// ejecuta

			while (rs.next()) { // coge el titulo que es UNO SOLO

				titulo = rs.getString(1);
			}
			return titulo;

		} catch (Exception e) {
			throw e;

		}
	}

	public Libro seleccionarDatosLibro(String titulo) throws Exception {
		PreparedStatement pst;
		Libro libro = new Libro();

		try {
			pst = cn.prepareStatement("SELECT * FROM LIBROS WHERE titulo=?");
			pst.setString(1, titulo);

			ResultSet rs = pst.executeQuery();// ejecuta

			while (rs.next()) { // coge el titulo que es UNO SOLO

				libro.setId(rs.getInt(1));
				libro.setTitulo(rs.getString(2));
				libro.setAutor(rs.getString(3));
				libro.setNum_pag(rs.getInt(4));
			}
			return libro;

		} catch (Exception e) {
			throw e;

		}
	}
	public Libro seleccionarDatosTitulo(String titulo) throws Exception {
		PreparedStatement pst;
		Libro libro = new Libro();

		try {
			pst = cn.prepareStatement("SELECT * FROM LIBROS WHERE titulo=?");
			pst.setString(1, titulo);

			ResultSet rs = pst.executeQuery();// ejecuta

			while (rs.next()) { // coge el titulo que es UNO SOLO

				libro.setId(rs.getInt(1));
				libro.setTitulo(rs.getString(2));
				libro.setAutor(rs.getString(3));
				libro.setNum_pag(rs.getInt(4));
			}
			return libro;

		} catch (Exception e) {
			throw e;

		}
	}

	public void borrarLibro(String titulo) throws Exception {

		PreparedStatement pst;

		try {
			pst = cn.prepareStatement("DELETE FROM LIBROS WHERE TITULO=?");
			pst.setString(1, titulo);

			int count = pst.executeUpdate();
			// System.out.println(pst + " " + count);

			if (count >= 1) {
				JOptionPane.showMessageDialog(null, "LIBRO BORRADO");
			} else {
				JOptionPane.showMessageDialog(null, "LIBRO NO EXISTE");
			}

		} catch (Exception e) {
			throw e;

		}

	}

	public ArrayList<Libro> seleccionarTodos() throws Exception {
		PreparedStatement pst;
		Libro libro;

		try {
			pst = cn.prepareStatement("SELECT * FROM LIBROS ");

			ResultSet rs = pst.executeQuery();// ejecuta

			// pasar de ResultSet a ArrayList

			ArrayList<Libro> libros = new ArrayList<Libro>();
			while (rs.next()) {
				libro = new Libro();
				libro.setId(Integer.parseInt(rs.getString(1)));
				libro.setTitulo(rs.getString(2));
				libro.setAutor(rs.getString(3));
				libro.setNum_pag(Integer.parseInt(rs.getString(4)));
				
				libros.add(libro);
			}
			return libros;

		} catch (Exception e) {
			throw e;

		}
	}
	public ArrayList<String> seleccionarAutores() throws Exception {
		PreparedStatement pst;
		String autor;

		try {
			pst = cn.prepareStatement("SELECT DISTINCT  AUTOR FROM LIBROS ");

			ResultSet rs = pst.executeQuery();// ejecuta

			// pasar de ResultSet a ArrayList

			ArrayList<String> autores = new ArrayList<String>();
			while (rs.next()) {
				
				autor=rs.getString(1);
				autores.add(autor);
			}
			return autores;

		} catch (Exception e) {
			throw e;

		}
	}
	
	public ArrayList<Libro> seleccionarDatosLibroAutor(String autor) throws Exception {
		PreparedStatement pst;
		Libro libro = new Libro();
		ArrayList<Libro> libros=new ArrayList<Libro>();
		try {
			pst = cn.prepareStatement("SELECT * FROM LIBROS WHERE autor=?");
			pst.setString(1, autor);

			ResultSet rs = pst.executeQuery();// ejecuta

			while (rs.next()) {
				
				libro=new Libro();
				
				libro.setId(rs.getInt(1));
				libro.setTitulo(rs.getString(2));
				libro.setAutor(rs.getString(3));
				libro.setNum_pag(rs.getInt(4));
				
				libros.add(libro);
			}
			return libros;

		} catch (Exception e) {
			throw e;

		}
	}
}
