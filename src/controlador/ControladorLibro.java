package controlador;

import vista.*;
import vista.libro.BorrarLibro;
import vista.libro.ConsultarLibro;
import vista.libro.GestionLibro;
import vista.libro.ListarLibros;
import vista.libro.NuevoLibro;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.*;

public class ControladorLibro {

	//un atributo por cada ventana de libros-->5
	//un atributo por cada Tabla de la BD-->   1
	private GestionLibro gestionLibro;
	private NuevoLibro nuevoLibro;
	private BorrarLibro borrarLibro;
	private ListarLibros listarLibros;
	private ConsultarLibro  consultarLibro;

	private ModeloLibro modeloLibro;

	public ControladorLibro() {
		super();
		
		
	}
	
	
	public void insertarLibro(String titulo,String autor,int num_pag){
		
		Libro libro=new Libro();
		
		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setNum_pag(num_pag);
		
		try {
			modeloLibro.insertar(libro);
			JOptionPane.showMessageDialog(null, "LIBRO añadido con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al insertar nuevo LIBRO");
		}
	}

	public GestionLibro getGestionLibro() {
		return gestionLibro;
	}

	public void setGestionLibro(GestionLibro gestionLibro) {
		this.gestionLibro = gestionLibro;
	}

	public NuevoLibro getNuevoLibro() {
		return nuevoLibro;
	}

	public void setNuevoLibro(NuevoLibro nuevoLibro) {
		this.nuevoLibro = nuevoLibro;
	}

	public BorrarLibro getBorrarLibro() {
		return borrarLibro;
	}

	public void setBorrarLibro(BorrarLibro borrarLibro) {
		this.borrarLibro = borrarLibro;
	}

	

	public ListarLibros getListarLibros() {
		return listarLibros;
	}


	public void setListarLibros(ListarLibros listarLibros) {
		this.listarLibros = listarLibros;
	}


	public ConsultarLibro getConsultarLibro() {
		return consultarLibro;
	}


	public void setConsultarLibro(ConsultarLibro consultarLibro) {
		this.consultarLibro = consultarLibro;
	}


	public ModeloLibro getModeloLibro() {
		return modeloLibro;
	}

	public void setModeloLibro(ModeloLibro modeloLibro) {
		this.modeloLibro = modeloLibro;
	}


	public void abrirGestionLibro() {
		
		gestionLibro.setVisible(true);
		
	}


	public void abrirNuevoLibro() {

		nuevoLibro.setVisible(true);
		
	}


	public void abrirBorrarLibro() {
		
		ArrayList<Libro> libros=new ArrayList<Libro>();
		
		try {
			libros=modeloLibro.seleccionarTitulos();
			borrarLibro.rellenarLista(libros);

			borrarLibro.setVisible(true);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR LIBRO");
		}
	}


	public void abrirConsultarLibro() {
		
		ArrayList<Libro> libros;
		ArrayList<String> autores;
		try {
			autores = modeloLibro.seleccionarAutores();
			consultarLibro.rellenarListaAutores(autores);
			libros = modeloLibro.seleccionarTodos();
			consultarLibro.rellenarListaTitulos(libros);

			consultarLibro.setVisible(true);
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR LIBRO");
		}
		
		
	}
	public void abrirListarLibros() {
		
		ArrayList<Libro> libros=new ArrayList<Libro>();

		try {
			libros=modeloLibro.seleccionarTodos();
			
			listarLibros.rellenarTabla(libros);
	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR LIBROS");
		}

		listarLibros.setVisible(true);
		
	}

	public void seleccionarDatosLibro(String titulo) {
		
		try {
			Libro libro=modeloLibro.seleccionarDatosLibro(titulo);
			borrarLibro.mostrarDatos(libro);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR LIBRO");
		}
		
	}


	public void borrarLibro(String titulo) {
		
		try {
			System.out.println("BORRARLIBRO");
			modeloLibro.borrarLibro(titulo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL BORRAR LIBRO");
		
		}
		
	}


	public void seleccionarLibrosPorAutor(String autor) {
		ArrayList<Libro> libros=new ArrayList<Libro>();
		try {
			libros=modeloLibro.seleccionarDatosLibroAutor(autor);
			consultarLibro.rellenarTablaPorAutor(libros);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL SELECCIONAR LIBROS");
		}
		
	}


	public void seleccionarMenores(int valor) {
		
		
		libros=modelo.seleccionarMenores(valor);
		consultarLibro.rellenarTablaPorNumPag(libros);
		
		
	}



	

}
