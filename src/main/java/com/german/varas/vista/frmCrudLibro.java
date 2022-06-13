package com.german.varas.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.german.varas.dao.LibroDao;
import com.german.varas.dao.TemaDao;
import com.german.varas.model.Libro;
import com.german.varas.model.Tema;




public class frmCrudLibro extends JFrame implements ActionListener, MouseListener{


	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTextField txtOrigen;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JTable table;
	private JComboBox<Tema> cboTema;
	List<Tema> temas = null;
	
	
	LibroDao daoLibro = new LibroDao();
	
	
	int idSeleccionado = -1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCrudLibro frame = new frmCrudLibro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmCrudLibro() {
		setForeground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblMttoLibro = new JLabel("Libreria De Libros");
		lblMttoLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblMttoLibro.setForeground(Color.BLUE);
		lblMttoLibro.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMttoLibro.setBounds(10, 11, 503, 35);
		contentPane.add(lblMttoLibro);

		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(40, 57, 84, 26);
		contentPane.add(lblTitulo);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(40, 88, 84, 26);
		contentPane.add(lblPrecio);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(40, 119, 84, 26);
		contentPane.add(lblCantidad);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(40, 150, 84, 26);
		contentPane.add(lblOrigen);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setBounds(40, 181, 84, 26);
		contentPane.add(lblTema);

		

		txtTitulo = new JTextField();
		txtTitulo.setBounds(152, 60,114, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(152, 91,114, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(152, 122,114, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		txtOrigen = new JTextField();
		txtOrigen.setBounds(152, 153,114, 20);
		contentPane.add(txtOrigen);
		txtOrigen.setColumns(10);
		
		
		cboTema = new JComboBox();
		cboTema.setBounds(152, 181, 114, 20);
		contentPane.add(cboTema);

		

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(293, 57, 114, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 503, 184);
		contentPane.add(scrollPane);
		
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					
				},
				new String [] {
						"ID", "TITULO", "PRECIO", "CANTIDAD", "ORIGEN", "TEMA"
				}
				));
		scrollPane.setViewportView(table);
		
		
		cargaComboTema();
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(293, 90, 114, 23);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(293, 124, 114, 23);
		contentPane.add(btnEliminar);
		
		
		listaLibros();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnRegistrar) {
			
			do_btnRegistrar_actionPerformed(e);
		}
		
		if(e.getSource()==btnActualizar) {
			
			do_btnActualizar_actionPerformed(e);
		}
		
		if(e.getSource()==btnEliminar) {
			
			do_btnEliminar_actionPerformed(e);
		}
	}
	
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		
		int cantidad = Integer.parseInt(txtCantidad.getText());
		String origen = txtOrigen.getText().trim();
		Double precio = Double.parseDouble(txtPrecio.getText());
		String titulo = txtTitulo.getText().trim();
		Tema tema = (Tema)cboTema.getSelectedItem();
		
		
		Libro libro = new Libro();
		libro.setTitulo(titulo);
		libro.setPrecio(precio);
		libro.setCantEjemplares(cantidad);
		libro.setOrigen(origen);
		libro.setTema(tema);

	
		daoLibro.guardarLibro(libro);
		System.out.println("Registrado");
		
		
		listaLibros();
		
		
		limpiarControles();
	}
	
	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
		
		int cantidad = Integer.parseInt(txtCantidad.getText());
		String origen = txtOrigen.getText().trim();
		Double precio = Double.parseDouble(txtPrecio.getText());
		String titulo = txtTitulo.getText().trim();
		Tema tema = (Tema)cboTema.getSelectedItem();		
				
				
		Libro libro = new Libro();
		libro.setIdlibro(idSeleccionado);
		libro.setTitulo(titulo);
		libro.setPrecio(precio);
		libro.setCantEjemplares(cantidad);
		libro.setOrigen(origen);
		libro.setTema(tema);

		
		daoLibro.actualizarLibro(libro);
		System.out.println("Actualizado");
				
		
		listaLibros();
				
		
		limpiarControles();
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		daoLibro.eliminarLibro(idSeleccionado);
		listaLibros();
		limpiarControles();
	}
	
	
	private void listaLibros() {
		
		List<Libro> data = daoLibro.obtTodosLibros();
		
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		
		for(Libro e: data) {
			Object[] fila = {
					e.getIdlibro(),
					e.getTitulo(),
					e.getPrecio(),
					e.getCantEjemplares(),
					e.getOrigen(), 
					e.getTema().getNombre()
			};
			dtm.addRow(fila);
		}
		
		dtm.fireTableDataChanged();
	}
	
	private void limpiarControles() {
		txtTitulo.requestFocus();
		txtTitulo.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		txtOrigen.setText("");
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==table) {
			
			do_table_mouseClicked(e);
		}
	}
	
	protected void  do_table_mouseClicked(MouseEvent e) {
		int fila = table.getSelectedRow();
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		idSeleccionado = (int) dtm.getValueAt(fila, 0);
		String tit = (String) dtm.getValueAt(fila, 1);
		Double pre = (Double) dtm.getValueAt(fila, 2);
		Integer can = (int) dtm.getValueAt(fila, 3);
		String ori = (String) dtm.getValueAt(fila, 4);
		String tem = (String) dtm.getValueAt(fila, 5);
		
		txtTitulo.setText(tit);
		txtPrecio.setText(pre+"");
		txtCantidad.setText(can+"");
		txtOrigen.setText(ori);
		cboTema.setSelectedItem(obtTemaDeCombo(tem)); 
		
	}

	private Tema obtTemaDeCombo(String s) {
		Tema tema = null;
		for(Tema tem: temas) {
			if(tem.getNombre().equals(s)) {
				tema = tem;
			}
		}
		return tema;
	}
	
	private void cargaComboTema() {
		Tema x1 = new Tema("Drama");
		Tema x2 = new Tema("Cuento");
		Tema x3 = new Tema("Novela");
		Tema x4 = new Tema ("Suspenso");
		Tema x5 = new Tema ("Dorama");
		
		TemaDao temaDao = new TemaDao();
		
		
		temaDao.guardarTema(x1);
		temaDao.guardarTema(x2);
		temaDao.guardarTema(x3);
		temaDao.guardarTema(x4);
		temaDao.guardarTema(x5);
		
		temas = temaDao.obtTodosTemas();
		
		for(Tema x: temas) {
			cboTema.addItem(x);
		}
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}