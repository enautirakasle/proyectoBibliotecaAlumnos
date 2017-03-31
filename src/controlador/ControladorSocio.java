package controlador;

import modelo.Socio;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.ModeloSocio;
import vista.*;
import vista.socio.*;

public class ControladorSocio {

	// un atributo por cada ventana de Socio
	// un atributo para el modelo Socio

	private ModeloSocio modeloSocio;

	private GestionSocio gestionSocio;
	private Principal principal;
	private FormularioSocio formularioSocio;
	private FormularioDeBusquedaSocio formularioBusquedaSocio;
	private FormularioDeBorrado formularioDeBorrado;
	private FormularioDeModificado formDeModificado;
	private FormularioTodosSocios formTodosSocios;


	public FormularioTodosSocios getFormTodosSocios() {
		return formTodosSocios;
	}

	public void setFormTodosSocios(FormularioTodosSocios formTodosSocios) {
		this.formTodosSocios = formTodosSocios;
	}


	public FormularioDeBusquedaSocio getFormularioBusquedaSocio() {
		return formularioBusquedaSocio;
	}

	public void setFormularioBusquedaSocio(FormularioDeBusquedaSocio formularioBusquedaSocio) {
		this.formularioBusquedaSocio = formularioBusquedaSocio;
	}

	public FormularioDeModificado getFormDeModificado() {
		return formDeModificado;
	}

	public void setFormDeModificado(FormularioDeModificado formDeModificado) {
		this.formDeModificado = formDeModificado;
	}

	/**
	 * socio bat sortu eta modeloari insert egiteko esaten dio
	 * 
	 * @param nombre
	 *            nombre del socio
	 * @param apellido
	 *            apellido del socio
	 * @param direccion
	 *            direccion del socio
	 * @param poblacion
	 *            poblacion del socio
	 * @param provincia
	 *            provincia del socio
	 * @param dni
	 *            dni del socio
	 * 
	 */

	public void insertarSocio(String nombre, String apellido, String direccion, String poblacion, String provincia,
			String dni) {

		Socio socio = new Socio();

		socio.setNombre(nombre);
		socio.setApellido(apellido);
		socio.setDireccion(direccion);
		socio.setPoblacion(poblacion);
		socio.setProvincia(provincia);
		socio.setDni(dni);

		try {
			this.modeloSocio.insertar(socio);

			JOptionPane.showMessageDialog(null, "SOCIO INSERTADO EN LA BD");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL INSERTAR SOCIO");
		}
	}

	public void abrirGestionSocio() {
		this.gestionSocio.setVisible(true);
	}

	public void abrirFormularioSocio() {
		this.formularioSocio.setVisible(true);
	}

	public void abrirBusquedaSocio() {
		// ArrayList<String> direcciones = this.modeloSocio.selectDirecciones();
		ArrayList<String> pueblos = this.modeloSocio.selectPoblaciones();
		ArrayList<String> provincias = this.modeloSocio.selectProvincias();

		// this.formularioBusquedaSocio.rellenarComboDireccion(direcciones);
		this.formularioBusquedaSocio.rellenarComboPoblacion(pueblos);
		this.formularioBusquedaSocio.rellenarComboProvincia(provincias);

		this.formularioBusquedaSocio.setVisible(true);

	}

	// borrado de socio
	public void abrirFormDeBorrado() {
		ArrayList<Socio> socios = this.modeloSocio.seleccionarTodos();

		this.formularioDeBorrado.rellenarComboSocios(socios);
		this.formularioDeBorrado.setVisible(true);
	}

	public void abrirFormDeModificar() {
		ArrayList<Socio> socios = this.modeloSocio.seleccionarTodos();

		this.formDeModificado.rellenarComboSocios(socios);
		this.formDeModificado.setVisible(true);
	}

	public void rellenarFormDeBorrado(int idSocio) {
		Socio socio = this.modeloSocio.select(idSocio);
		this.formularioDeBorrado.rellenarFormulario(socio);
	}

	public void rellenarFormDeModificado(int idSocio) {
		Socio socio = this.modeloSocio.select(idSocio);
		this.formDeModificado.rellenarFormulario(socio);
	}

	public void eliminarSocio(int idSocio) {
		this.modeloSocio.borrar(idSocio);
		this.formularioDeBorrado.mostrarMensaje("socio borrado");
		this.formularioDeBorrado.clearForm();

		// formularioa eguneratu
		ArrayList<Socio> socios = this.modeloSocio.seleccionarTodos();
		this.formularioDeBorrado.rellenarComboSocios(socios);
	}

	public void cerrarFormularioDeBorrado() {
		this.formularioDeBorrado.clearForm();
		this.formularioDeBorrado.dispose();
	}
	// borrado de socio FIN

	public void cerrarFormularioDeModificado() {
		this.formDeModificado.clear();
		this.formDeModificado.dispose();
	}

	public ModeloSocio getModeloSocio() {
		return modeloSocio;
	}

	public void setModeloSocio(ModeloSocio modeloSocio) {
		this.modeloSocio = modeloSocio;
	}

	public GestionSocio getGestionSocio() {
		return gestionSocio;
	}

	public void setGestionSocio(GestionSocio gestionSocio) {
		this.gestionSocio = gestionSocio;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public FormularioSocio getFormularioSocio() {
		return formularioSocio;
	}

	public void setFormularioSocio(FormularioSocio formularioSocio) {
		this.formularioSocio = formularioSocio;
	}

	public FormularioDeBorrado getFormularioDeBorrado() {
		return formularioDeBorrado;
	}

	public void setFormularioDeBorrado(FormularioDeBorrado formularioDeBorrado) {
		this.formularioDeBorrado = formularioDeBorrado;
	}

	public void cerrarFormularioSocio() {
		this.formularioSocio.clear();
		this.formularioSocio.dispose();
	}

	public void modificarSocio(int parseInt, String nombre, String apellido, String direccion, String poblacion,
			String provincia, String dni) {
		Socio socio = new Socio();
		socio.setNombre(nombre);
		socio.setApellido(apellido);
		socio.setDireccion(direccion);
		socio.setPoblacion(poblacion);
		socio.setProvincia(provincia);
		socio.setDni(dni);

		this.modeloSocio.modificar(socio);
	}


	public void abrirFormListar() {
		
		ArrayList<Socio> socios=new ArrayList<Socio>(); 
		
		socios=modeloSocio.seleccionarTodos();
		
		formTodosSocios.rellenarTabla(socios);
		
		formTodosSocios.setVisible(true);
		
}
	public void mostrarSociosPorNombre(String nombre) {
		ArrayList<Socio> socios = modeloSocio.selectLikeNombre(nombre);
		this.formularioBusquedaSocio.rellenarTabla(socios);
	}

	public void mostrarSociosPorApellido(String apellido) {
		ArrayList<Socio> socios = this.modeloSocio.selectLikeApellido(apellido);
		this.formularioBusquedaSocio.rellenarTabla(socios);
	}

	public void mostrarSociosPorDireccion(String direccion) {
		ArrayList<Socio> socios = this.modeloSocio.selectPorDireccion(direccion);
		this.formularioBusquedaSocio.rellenarTabla(socios);
	}

	public void mostrarSociosPorPoblacion(String poblacion) {
		ArrayList<Socio> socios = this.modeloSocio.selectPorPoblacion(poblacion);
		this.formularioBusquedaSocio.rellenarTabla(socios);
	}

	public void mostrarSociosPorProvincia(String provincia) {
		ArrayList<Socio> socios = this.modeloSocio.selectPorProvincia(provincia);
		this.formularioBusquedaSocio.rellenarTabla(socios);

	}

}
