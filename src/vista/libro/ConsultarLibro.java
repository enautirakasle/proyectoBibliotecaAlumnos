package vista.libro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorLibro;
import modelo.Libro;

import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;

public class ConsultarLibro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private ControladorLibro  controladorLibro;
	private JComboBox autores;
	private JComboBox titulos;
	private JComboBox num_pag;
	private JTable tablaPorAutor;
	private JTable tablaPorTitulo;
	private JTable tablaNumPag;

	
	public ConsultarLibro(JDialog parent, boolean modal) {
		
		super(parent,modal);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, "name_82353901282691");
			{
				JPanel PorAutor = new JPanel();
				tabbedPane.addTab("Por Autor", null, PorAutor, null);
				PorAutor.setLayout(null);
				
				autores = new JComboBox();
				autores.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						
						controladorLibro.seleccionarLibrosPorAutor((String)autores.getSelectedItem());
							
					}
				});
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(0, 85, 419, 138);
					PorAutor.add(scrollPane);
					{
						tablaPorAutor = new JTable();
						scrollPane.setViewportView(tablaPorAutor);
					}
				}
				autores.setBounds(126, 29, 232, 20);
				PorAutor.add(autores);
				
				JLabel autor = new JLabel("Autores");
				autor.setBounds(32, 32, 46, 14);
				PorAutor.add(autor);
			}
			{
				JPanel PorTitulo = new JPanel();
				tabbedPane.addTab("Por Titulo", null, PorTitulo, null);
				PorTitulo.setLayout(null);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(0, 52, 417, 171);
					PorTitulo.add(scrollPane);
					{
						tablaPorTitulo = new JTable();
						scrollPane.setViewportView(tablaPorTitulo);
					}
				}
				{
					JLabel lblTitulos = new JLabel("Titulos ");
					lblTitulos.setBounds(53, 27, 34, 14);
					PorTitulo.add(lblTitulos);
				}
				{
					titulos = new JComboBox();
					titulos.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							
							//controladorLibro.seleccionarLibrosPorTitulo((String)titulos.setSelectedItem());
						}
					});
					titulos.setBounds(146, 24, 228, 20);
					PorTitulo.add(titulos);
				}
			}
			{
				JPanel PorNumPag = new JPanel();
				tabbedPane.addTab("Por Num Paginas", null, PorNumPag, null);
				PorNumPag.setLayout(null);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(0, 76, 419, 147);
					PorNumPag.add(scrollPane);
					{
						tablaNumPag = new JTable();
						scrollPane.setViewportView(tablaNumPag);
					}
				}
				{
					JLabel lblNumDePginas = new JLabel("Num de P\u00E1ginas");
					lblNumDePginas.setBounds(54, 30, 76, 14);
					PorNumPag.add(lblNumDePginas);
				}
				{
					num_pag = new JComboBox();
					num_pag.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							int menores;
							int valor=num_pag.getSelectedIndex();
							
							switch(valor){
							
							case 1:
								menores=100;
								break;
							case 2:
								menores=500;
								break;
							case 5:
								menores=10000;
								break;
								
							}
						
							controladorLibro.seleccionarMenores(valor);
							
						}
					});
					num_pag.setModel(new DefaultComboBoxModel(new String[] {"<  100", " > 100  y <  500", "> 500"}));
					num_pag.setBounds(173, 27, 224, 20);
					PorNumPag.add(num_pag);
				}
			}
		}
	}


	public void rellenarTablaPorAutor(ArrayList<Libro> libros) {

		DefaultTableModel dtm=new DefaultTableModel();
		
		String[] encabezados={"ID","TITULO","AUTOR","NUM PAGINAS"};
		
		dtm.setColumnIdentifiers(encabezados);
		
		for (Libro libro:libros){
			
			String[]  fila={ String.valueOf(libro.getId()),libro.getTitulo(),libro.getAutor(),String.valueOf(libro.getNum_pag())};
			dtm.addRow(fila);
		}
		tablaPorAutor.setModel(dtm);
		
	}
	public void rellenarTablaPorTitulo(ArrayList<Libro> libros) {

		DefaultTableModel dtm=new DefaultTableModel();
				
				String[] encabezados={"ID","TITULO","AUTOR","NUM PAGINAS"};
				
				dtm.setColumnIdentifiers(encabezados);
				
				for (Libro libro:libros){
					
					String[]  fila={ String.valueOf(libro.getId()),libro.getTitulo(),libro.getAutor(),String.valueOf(libro.getNum_pag())};
					dtm.addRow(fila);
				}
				//tablaPorTitulo.setModel(dtm);
				
			}

	public ControladorLibro getControladorLibro() {
		return controladorLibro;
	}


	public void setControladorLibro(ControladorLibro controladorLibro) {
		this.controladorLibro = controladorLibro;
	}


	public void rellenarListaTitulos(ArrayList<Libro> libros) {
		titulos.removeAllItems();
		
		for (Libro libro:libros){
			titulos.addItem(libro.getTitulo());
		}
	}


	public void rellenarListaAutores(ArrayList<String> valores) {
		autores.removeAllItems();
		
		for (String autor:valores){	
			autores.addItem(autor);
		}
	}

}
