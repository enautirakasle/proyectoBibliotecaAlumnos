package vista.socio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controlador.ControladorSocio;
import modelo.Libro;
import modelo.Socio;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;

public class FormularioDeBusquedaSocio extends JDialog {
//	public FormularioBusquedaSocio() {
//	}
	
	private final JPanel contentPanel = new JPanel();
	private JTextField nombre;
	private JTextField apellido;

	private ControladorSocio controladorSocio;
	private JTable table;
	private JComboBox comboProvincias;
	private JComboBox comboPoblacion;

	/**
	 * Create the dialog.
	 */
	public FormularioDeBusquedaSocio(GestionSocio parent, boolean modal) {
		super(parent, modal);

		setBounds(100, 100, 679, 352);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.SOUTH);

		JLabel lblNombre = new JLabel("Nombre");

		JLabel lblApellido = new JLabel("Apellido");

		JLabel lblPoblacion = new JLabel("Poblacion");
		

		JLabel lblSelccionaElAlumno = new JLabel("Busqueda de Socio");
		lblSelccionaElAlumno.setFont(new Font("Tahoma", Font.BOLD, 12));

		nombre = new JTextField();
		nombre.setEditable(true);
		nombre.setEnabled(true);
		nombre.setColumns(10);

		apellido = new JTextField();
		apellido.setEditable(true);
		apellido.setEnabled(true);
		apellido.setColumns(10);
		
		JButton buscarNombre = new JButton("Buscar");
		buscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				controladorSocio.mostrarSociosPorNombre(nombre.getText());
			}
		});
		
		
		
		JButton buscarApellido = new JButton("Buscar");
		buscarApellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorSocio.mostrarSociosPorApellido(apellido.getText());
			}
		});
		
		
		JButton buscarPoblacion = new JButton("Buscar");
		buscarPoblacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorSocio.mostrarSociosPorPoblacion(String.valueOf(comboPoblacion.getSelectedItem()));
			}
		});
		
		JLabel lblProvincia = new JLabel("Provincia");
		
		comboProvincias = new JComboBox();
		comboProvincias.setEditable(true);
		
		comboPoblacion = new JComboBox();
		comboPoblacion.setEditable(true);
		
		JButton buscarProvincia = new JButton("Buscar");
		buscarProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorSocio.mostrarSociosPorProvincia(String.valueOf(comboProvincias.getSelectedItem()));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton limpiar = new JButton("Limpiar Criterios de B\u00FAsqueda");
		limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				limpiarCampos();
			}

			
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblApellido)
								.addComponent(lblPoblacion)
								.addComponent(lblProvincia, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(apellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboPoblacion, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboProvincias, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(buscarNombre, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(buscarApellido, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(92)
									.addComponent(limpiar))
								.addComponent(buscarPoblacion, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(buscarProvincia, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(22)
							.addComponent(lblSelccionaElAlumno))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(11)
							.addComponent(lblSelccionaElAlumno)
							.addGap(6)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNombre))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(1)
									.addComponent(nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(buscarNombre))
							.addGap(6)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(4)
									.addComponent(lblApellido))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(1)
									.addComponent(apellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(buscarApellido))
							.addGap(11)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPoblacion)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(1)
									.addComponent(comboPoblacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(buscarPoblacion))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(4)
									.addComponent(lblProvincia))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(1)
									.addComponent(comboProvincias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(buscarProvincia)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(71)
							.addComponent(limpiar)))
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addGap(12))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setToolTipText("Prueba");
		contentPanel.setLayout(gl_contentPanel);
		
	}

	
	
	

	public ControladorSocio getControladorSocio() {
		return controladorSocio;
	}

	public void setControladorSocio(ControladorSocio controladorSocio) {
		this.controladorSocio = controladorSocio;
	}

	public void rellenarTabla(ArrayList<Socio> socios) {
		//table model bat sortu
		DefaultTableModel dtm = new DefaultTableModel();

		//burukoen izenak jarri 
		Object[] burukoak = {"NOMBRE", "APELLIDO", "DIRECCION", "POBLACION", "PROVINCIA", "DNI"};
		dtm.setColumnIdentifiers(burukoak );

		//tablemodela bete
		for (Socio socio : socios) {
			Object[] lerroa = { socio.getNombre(), socio.getApellido(), socio.getDireccion(), socio.getPoblacion(), socio.getProvincia(), socio.getDni()};
			dtm.addRow(lerroa);
		}
		
		//tablemodela taulari asignatu
		table.setModel(dtm);
		
		//taula ordenagarria sortu
		TableRowSorter<DefaultTableModel> modeloOrdenado = new TableRowSorter<DefaultTableModel>(dtm);
		table.setRowSorter(modeloOrdenado);
		
	}


	public void rellenarComboDireccion(ArrayList<String> direcciones) {
		for(String direccion: direcciones){
			//TODO  no existe el comboDirecciones
		}
	}

	
	public void rellenarComboPoblacion(ArrayList<String> pueblos) {
		for(String pueblo: pueblos){
			this.comboPoblacion.addItem(pueblo);
		}
		this.comboPoblacion.setSelectedIndex(-1);
	}

	
	public void rellenarComboProvincia(ArrayList<String> provincias) {
		for(String provincia: provincias){
			this.comboProvincias.addItem(provincia);
		}
		this.comboProvincias.setSelectedIndex(-1);
		
	}
	private void limpiarCampos() {
		nombre.setText("");
		apellido.setText("");
		comboPoblacion.setSelectedIndex(-1);
		comboProvincias.setSelectedIndex(-1);
		
	}
	
}
