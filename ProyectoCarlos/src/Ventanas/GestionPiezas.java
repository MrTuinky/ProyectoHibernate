package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Singleton.HibernateUtil;
import Singleton.Piezas;
import Singleton.Proveedores;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GestionPiezas extends JFrame {

	Piezas piezas = new Piezas();
	
	private JPanel contentPane;
	private JTextField textGCodigo;
	private JTextField textGNombre;
	private JTextField textGPrecio;
	private JTextField textGDescripcion;
	private JTextField textLCodigo;
	private JTextField textLPrecio;
	private JTextField textLDescripcion;
	private JTextField textLNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPiezas frame = new GestionPiezas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//
	//
	//
	//
	//
	////////////////////////////////////////////////////////////////
	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	Transaction tx = session.beginTransaction();
	
	Query q = session.createQuery("FROM Piezas");
	List<Piezas> lista = q.list();
	
	int contador = 0;
	private JTextField textUno;
	private JTextField textOtro;
	
	
	////////////////////////////////////////////////////////////////
	//
	//
	//
	//
	//
	

	/**
	 * Create the frame.
	 */
	public GestionPiezas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carlos\\Desktop\\Workspace\\bd.png"));
		setTitle("Gestion de piezas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 584, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 558, 482);
		contentPane.add(tabbedPane);
		
		JPanel panelGPiezas = new JPanel();
		panelGPiezas.setLayout(null);
		panelGPiezas.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Gestion de Piezas", null, panelGPiezas, null);
		
		JButton botonAlta = new JButton("Alta");
		botonAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);

					
				} else if(textGNombre.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Nombre no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);
					
				} else if(textGPrecio.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Precio no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);
					
					
				} else if(textGDescripcion.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "La casilla Descripción no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);
					
				} else {
					
				try{
						
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				piezas.setCodigo(textGCodigo.getText());
				piezas.setNombre(textGNombre.getText());
				piezas.setPrecio(Float.parseFloat(textGPrecio.getText()));
				piezas.setDescripcion(textGDescripcion.getText());
			
				
				
				session.save(piezas);
				tx.commit();
				
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "ConstraintViolation = Código de pieza duplicado \n El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Pieza",JOptionPane.ERROR_MESSAGE);
					
				}
				
				session.close();
				System.out.println("Se ha dado de alta una pieza");
			}
			}
		});
		botonAlta.setBounds(117, 225, 89, 37);
		panelGPiezas.add(botonAlta);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 11, 414, 48);
		panelGPiezas.add(panel_1);
		
		textGCodigo = new JTextField();
		textGCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código está vacía, para buscar una Pieza, \n  introduce un Código Pieza válido", "ERROR al intentar mostrar una Pieza",JOptionPane.INFORMATION_MESSAGE);
				
				} else {

					try {
					
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				piezas = (Piezas) session.load(Piezas.class , textGCodigo.getText());
				
				
				
				
				
				System.out.println(piezas.getCodigo()+ " - " + piezas.getNombre()+ " - " + piezas.getPrecio()+ " - " + piezas.getDescripcion());
				textGNombre.setText(piezas.getNombre());
				textGPrecio.setText(String.valueOf(piezas.getPrecio()));
				textGDescripcion.setText(piezas.getDescripcion());
				tx.commit();
				session.close();
				
					} catch(ObjectNotFoundException e) {
						
						JOptionPane.showMessageDialog(null, "El Código Pieza que ha introducido no puede mostarse debido a \n que no está en la base de datos. \n Pruebe introduciendo un Código Pieza válido. \n Ejemplo: P1","ERROR al mostrar Pieza",JOptionPane.ERROR_MESSAGE);

						
					}
					
				
				
				session.close();
				
				
				
				
				
				}
				
			}
		});
		textGCodigo.setColumns(10);
		textGCodigo.setBounds(145, 11, 200, 26);
		panel_1.add(textGCodigo);
		
		JLabel lblCdigoDePieza = new JLabel("C\u00F3digo de Pieza: (*)");
		lblCdigoDePieza.setBounds(10, 11, 125, 26);
		panel_1.add(lblCdigoDePieza);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 60, 414, 48);
		panelGPiezas.add(panel_2);
		
		textGNombre = new JTextField();
		textGNombre.setColumns(10);
		textGNombre.setBounds(145, 11, 200, 26);
		panel_2.add(textGNombre);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setBounds(10, 11, 125, 26);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(10, 104, 414, 48);
		panelGPiezas.add(panel_3);
		
		textGPrecio = new JTextField();
		textGPrecio.setColumns(10);
		textGPrecio.setBounds(145, 11, 200, 26);
		panel_3.add(textGPrecio);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 11, 125, 26);
		panel_3.add(lblPrecio);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(10, 154, 414, 48);
		panelGPiezas.add(panel_4);
		
		textGDescripcion = new JTextField();
		textGDescripcion.setColumns(10);
		textGDescripcion.setBounds(145, 11, 200, 26);
		panel_4.add(textGDescripcion);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(10, 11, 125, 26);
		panel_4.add(lblDescripcin);
		
		JButton botonLimpiar = new JButton("Limpiar");
		botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textGCodigo.setText(null);
				textGNombre.setText(null);
				textGPrecio.setText(null);
				textGDescripcion.setText(null);
				
				System.out.println("Datos limpiados");
			}
		});
		botonLimpiar.setBounds(10, 222, 89, 42);
		panelGPiezas.add(botonLimpiar);
		
		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);

					
				} else if(textGNombre.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Nombre no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);
					
				} else if(textGPrecio.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Precio no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);
					
					
				} else if(textGDescripcion.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "La casilla Descripción no puede estar vacía", "ERROR al dar de alta a una Pieza",JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					
				try {
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				piezas = (Piezas) session.load(Piezas.class , textGCodigo.getText());
				
				
				String codigoModificado = textGCodigo.getText();
				piezas.setCodigo(codigoModificado);
				
				String nombreModificado = textGNombre.getText();
				piezas.setNombre(nombreModificado);
				
				String precioModificado = textGPrecio.getText();
				piezas.setPrecio(Float.parseFloat(precioModificado));
				
				String direccionModificado = textGDescripcion.getText();
				piezas.setDescripcion(direccionModificado);
				
				session.save(piezas);
				tx.commit();
				session.close();
				
				System.out.println("La pieza se ha modificado correctamente");
				
				} catch(Exception e2){
					
					JOptionPane.showMessageDialog(null, "El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Pieza",JOptionPane.ERROR_MESSAGE);

					
				}
				System.out.println("La pieza se ha modificado correctamente");
			}
			}
		});
		botonModificar.setBounds(213, 225, 89, 37);
		panelGPiezas.add(botonModificar);
		
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				Piezas piezass = (Piezas) session.load(Piezas.class , textGCodigo.getText());
				
				session.delete(piezass);
				tx.commit();
				session.close();
				
				} catch(Exception e2) {
					
					JOptionPane.showMessageDialog(null, "El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Pieza",JOptionPane.ERROR_MESSAGE);

				}
				
				System.out.println("Pieza eliminada");
			}
		});
		botonEliminar.setBounds(308, 225, 89, 37);
		panelGPiezas.add(botonEliminar);
		
		JPanel panelLPiezas = new JPanel();
		panelLPiezas.setLayout(null);
		panelLPiezas.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Listado de Piezas", null, panelLPiezas, null);
		
		
		JButton botonBaja = new JButton("Baja");
		JButton botonUltimo = new JButton(">>");
		JButton botonSiguiente = new JButton(">");
		JButton botonAnterior = new JButton("<");
		JButton botonInicio = new JButton("<<");
		
		botonInicio.setEnabled(false);
		botonAnterior.setEnabled(false);
		botonSiguiente.setEnabled(false);
		botonUltimo.setEnabled(false);
		
		botonInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				
				if(contador > 0){
					
					botonSiguiente.setEnabled(true);
					botonUltimo.setEnabled(true);
				
				textLCodigo.setText(lista.get(0).getCodigo());
				textLNombre.setText(lista.get(0).getNombre());
				textLPrecio.setText(Float.toString(lista.get(0).getPrecio()));
				textLDescripcion.setText(lista.get(0).getDescripcion());
				
				
				contador = 0;
				
				textUno.setText(Integer.toString(contador + 1));
				
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Piezas en la base de datos" ,"ERROR al intentar pasar a otra pieza",JOptionPane.INFORMATION_MESSAGE);

					botonAnterior.setEnabled(false);
					botonInicio.setEnabled(false);
					
				}
				
				
				
				tx.commit();
				session.close();
				
				
				
				
			}
		});
		botonInicio.setBackground(Color.MAGENTA);
		botonInicio.setBounds(10, 368, 75, 48);
		panelLPiezas.add(botonInicio);
		
		// Boton anterior <
		
		botonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				if(contador > 0){
				
				botonSiguiente.setEnabled(true);
				botonUltimo.setEnabled(true);
				
				textLCodigo.setText(lista.get(contador - 1).getCodigo());
				textLNombre.setText(lista.get(contador - 1).getNombre());
				textLPrecio.setText(Float.toString(lista.get(contador - 1).getPrecio()));
				textLDescripcion.setText(lista.get(contador - 1).getDescripcion());
				
			
				
				contador --;
				
				textUno.setText(Integer.toString(contador + 1));
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Piezas en la base de datos" ,"ERROR al intentar pasar a otra pieza",JOptionPane.INFORMATION_MESSAGE);

					botonAnterior.setEnabled(false);
					botonInicio.setEnabled(false);
					
				}
				tx.commit();
				session.close();
				
				
				
				
				
			}
		});
		botonAnterior.setBounds(167, 368, 75, 48);
		panelLPiezas.add(botonAnterior);
		
		// Boton Siguiente >
		
		botonSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				if(contador < (lista.size() - 1)){
				
					botonAnterior.setEnabled(true);
					botonInicio.setEnabled(true);
				
				textLCodigo.setText(lista.get(contador+1).getCodigo());
				textLNombre.setText(lista.get(contador+1).getNombre());
				textLPrecio.setText(Float.toString(lista.get(contador+1).getPrecio()));
				textLDescripcion.setText(lista.get(contador+1).getDescripcion());
				
				
				
				contador++;
				
				textUno.setText(Integer.toString(contador + 1));
				
				
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Piezas en la base de datos" ,"ERROR al intentar pasar a otra pieza",JOptionPane.INFORMATION_MESSAGE);

					
					botonSiguiente.setEnabled(false);
					botonUltimo.setEnabled(false);
					
				}
				tx.commit();
				session.close();
				
				
				
				
			}
		});
		botonSiguiente.setBounds(269, 368, 75, 48);
		panelLPiezas.add(botonSiguiente);
		
		// Boton Ultimo >>
		botonUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				
				if(contador < (lista.size() - 1)){
					
				botonAnterior.setEnabled(true);
				botonInicio.setEnabled(true);
				
				textLCodigo.setText(lista.get(lista.size()-1).getCodigo());
				textLNombre.setText(lista.get(lista.size()-1).getNombre());
				textLPrecio.setText(Float.toString(lista.get(lista.size()-1).getPrecio()));
				textLDescripcion.setText(lista.get(lista.size()-1).getDescripcion());
				
				
				contador = (lista.size() -1);
				
				textUno.setText(Integer.toString(contador + 1));
				
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Piezas en la base de datos" ,"ERROR al intentar pasar a otra pieza",JOptionPane.INFORMATION_MESSAGE);

					
					botonSiguiente.setEnabled(false);
					botonUltimo.setEnabled(false);
					
				}
				
				tx.commit();
				session.close();
				
				
				
			}
		});
		botonUltimo.setBackground(Color.MAGENTA);
		botonUltimo.setBounds(405, 368, 75, 48);
		panelLPiezas.add(botonUltimo);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBounds(10, 11, 414, 48);
		panelLPiezas.add(panel_6);
		
		textLCodigo = new JTextField();
		textLCodigo.setColumns(10);
		textLCodigo.setBounds(145, 11, 200, 26);
		panel_6.add(textLCodigo);
		
		JLabel label = new JLabel("C\u00F3digo de Proveedor: (*)");
		label.setBounds(10, 11, 125, 26);
		panel_6.add(label);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(10, 103, 414, 48);
		panelLPiezas.add(panel_7);
		
		textLPrecio = new JTextField();
		textLPrecio.setColumns(10);
		textLPrecio.setBounds(145, 11, 200, 26);
		panel_7.add(textLPrecio);
		
		JLabel lblPrecio_1 = new JLabel("Precio");
		lblPrecio_1.setBounds(10, 11, 125, 26);
		panel_7.add(lblPrecio_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBounds(10, 150, 414, 48);
		panelLPiezas.add(panel_8);
		
		textLDescripcion = new JTextField();
		textLDescripcion.setColumns(10);
		textLDescripcion.setBounds(145, 11, 200, 26);
		panel_8.add(textLDescripcion);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 11, 125, 26);
		panel_8.add(lblDescripcion);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBounds(10, 58, 414, 48);
		panelLPiezas.add(panel_9);
		
		textLNombre = new JTextField();
		textLNombre.setColumns(10);
		textLNombre.setBounds(145, 11, 200, 26);
		panel_9.add(textLNombre);
		
		JLabel label_4 = new JLabel("Nombre");
		label_4.setBounds(10, 11, 125, 26);
		panel_9.add(label_4);
		
		JButton btnEjecutarConsulta = new JButton("Ejecutar Consulta");
		btnEjecutarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				contador = 0;
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				
					
					textLCodigo.setText(lista.get(0).getCodigo());
					textLNombre.setText(lista.get(0).getNombre());
					textLPrecio.setText(Float.toString(lista.get(0).getPrecio()));
					textLDescripcion.setText(lista.get(0).getDescripcion());
					
					// Inicializamos el 1 de x
					
					textUno.setText("1");
					textOtro.setText(Integer.toString(lista.size()));
					
					
					botonBaja.setEnabled(true);
					botonInicio.setEnabled(true);
					botonAnterior.setEnabled(true);
					botonSiguiente.setEnabled(true);
					botonUltimo.setEnabled(true);
					
				
				
				
				
				session.save(piezas);
				tx.commit();
				session.close();
				
				
			}
		});
		btnEjecutarConsulta.setBounds(10, 296, 309, 48);
		panelLPiezas.add(btnEjecutarConsulta);
		
		// Botón Baja
		botonBaja.setEnabled(false);
		botonBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resultado = JOptionPane.showConfirmDialog(null, "¿Quieres dar de baja a esta Pieza? \n \n Código: " + textLCodigo.getText() + "\n Nombre: " + textLNombre.getText() + "\n Precio: " + textLPrecio.getText() + "\n Descripción: " + textLDescripcion.getText() + "\n"  ,"Dar de baja Pieza",JOptionPane.INFORMATION_MESSAGE);
				
				if(resultado == JOptionPane.YES_OPTION){
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				Piezas piezass = (Piezas) session.load(Piezas.class , textLCodigo.getText());
				//Lo elimino también de la Lista
				lista.remove(contador);
				//Limpiar la pantalla
				textLCodigo.setText(null);
				textLNombre.setText(null);
				textLPrecio.setText(null);
				textLDescripcion.setText(null);
				
				textUno.setText(null);
				textOtro.setText(null);;
				
				session.delete(piezass);
				tx.commit();
				session.close();
				System.out.println("Pieza eliminada");
				
				
				}
				
			}
		});
		botonBaja.setBounds(347, 296, 90, 48);
		panelLPiezas.add(botonBaja);
		
		textUno = new JTextField();
		textUno.setColumns(10);
		textUno.setBounds(28, 238, 40, 20);
		panelLPiezas.add(textUno);
		
		JLabel label_2 = new JLabel("de");
		label_2.setBounds(88, 241, 23, 14);
		panelLPiezas.add(label_2);
		
		textOtro = new JTextField();
		textOtro.setColumns(10);
		textOtro.setBounds(132, 238, 32, 20);
		panelLPiezas.add(textOtro);
	}
}
