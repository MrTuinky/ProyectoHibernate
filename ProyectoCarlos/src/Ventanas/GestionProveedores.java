package Ventanas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.hibernate.DuplicateMappingException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Singleton.HibernateUtil;
import Singleton.Piezas;
import Singleton.Proveedores;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Toolkit;

public class GestionProveedores extends JFrame {
	
	
	
	Proveedores prov = new Proveedores();
	
	private JTextField textGCodigo;
	private JTextField textGNombre;
	private JTextField textGApellidos;
	private JTextField textGDireccion;
	private JTextField textLCodigo;
	private JTextField textLNombre;
	private JTextField textLApellidos;
	private JTextField textLDIreccion;
	private JTextField textUno;
	private JTextField textOtro;
	
	//Clase para redondear Botones
	
	/* 
	class RoundedBorder implements Border {

	    private int radius;

	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }

	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }

	    public boolean isBorderOpaque() {
	        return true;
	    }

	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
	
	*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionProveedores frame = new GestionProveedores();
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
	
	Query q = session.createQuery("FROM Proveedores");
	List<Proveedores> lista = q.list();
	
	int contador = 0;
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////
	//
	//
	//
	//
	//
	
	/**
	 * Create the frame.
	 */
	public GestionProveedores() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carlos\\Desktop\\Workspace\\bd.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Gesti\u00F3n de proveedores");
		setBounds(100, 100, 597, 487);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JTabbedPane tabbedPaneGestionProveedores = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneGestionProveedores.setBounds(0, 0, 571, 437);
		getContentPane().add(tabbedPaneGestionProveedores);
		
		JPanel panelGProvee = new JPanel();
		panelGProvee.setLayout(null);
		panelGProvee.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPaneGestionProveedores.addTab("Gestion de proveedores", null, panelGProvee, null);
		
		//Botón para dar de alta a un proveedor, es decir, añadir a la base de datos un nuevo proveedor
		
		JButton botonAlta = new JButton("Alta");
		botonAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);

					
				} else if(textGNombre.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Nombre no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);
					
				} else if(textGApellidos.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Apellidos no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);
					
					
				} else if(textGDireccion.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "La casilla Dirección no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					try{
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				prov.setCodigo(textGCodigo.getText());
				prov.setNombre(textGNombre.getText());
				prov.setApellidos(textGApellidos.getText());
				prov.setDireccion(textGDireccion.getText());
				
				//Añadimos también a la lista
				
				
			
				
				
				session.save(prov);
				tx.commit();
				
				
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "ConstraintViolation = Código de proveedor duplicado \n El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Proveedor",JOptionPane.ERROR_MESSAGE);
					
				}
					
				session.close();
				System.out.println("Se ha dado de alta un proveedor");
			
				
				}
				
				
				
			}
		});
		botonAlta.setBounds(117, 225, 89, 37);
		panelGProvee.add(botonAlta);
		
		JPanel panelGCodigo = new JPanel();
		panelGCodigo.setLayout(null);
		panelGCodigo.setBounds(10, 11, 414, 48);
		panelGProvee.add(panelGCodigo);
		
		textGCodigo = new JTextField();
		
		textGCodigo.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código está vacía, para buscar un Proveedor, \n  introduce un Código Proveedor válido", "ERROR al intentar mostrar un Proveedor",JOptionPane.INFORMATION_MESSAGE);
				
				} else {
				
					try {
						
						SessionFactory sesion = HibernateUtil.getSessionFactory();
						Session session = sesion.openSession();
						Transaction tx = session.beginTransaction();
					
				prov = (Proveedores) session.load(Proveedores.class , textGCodigo.getText());
				
				
				
				System.out.println(prov.getCodigo()+ " - " + prov.getNombre()+ " - " + prov.getApellidos()+ " - " + prov.getDireccion());
				textGNombre.setText(prov.getNombre());
				textGApellidos.setText(prov.getApellidos());
				textGDireccion.setText(prov.getDireccion());
				
				tx.commit();
				session.close();
				
					} catch(ObjectNotFoundException e) {
						
						JOptionPane.showMessageDialog(null, "El Código Proveedor que ha introducido no puede mostarse debido a \n que no está en la base de datos. \n Pruebe introduciendo un Código Proveedor válido. \n Ejemplo: A00001","ERROR al mostrar Proveedor",JOptionPane.ERROR_MESSAGE);
					}
					
					}
				
				
				session.close();
			}
		});
		textGCodigo.setColumns(10);
		textGCodigo.setBounds(160, 11, 200, 26);
		panelGCodigo.add(textGCodigo);
		
		JLabel labelGCodigo = new JLabel("C\u00F3digo de Proveedor: (*)");
		labelGCodigo.setBounds(10, 11, 140, 26);
		panelGCodigo.add(labelGCodigo);
		
		JPanel panelGNombre = new JPanel();
		panelGNombre.setLayout(null);
		panelGNombre.setBounds(10, 60, 414, 48);
		panelGProvee.add(panelGNombre);
		
		textGNombre = new JTextField();
		textGNombre.setColumns(10);
		textGNombre.setBounds(160, 11, 200, 26);
		panelGNombre.add(textGNombre);
		
		JLabel labelGnombre = new JLabel("Nombre");
		labelGnombre.setBounds(10, 11, 125, 26);
		panelGNombre.add(labelGnombre);
		
		JPanel panelGApellidos = new JPanel();
		//Este código pertenece a CarlosHernandez
		panelGApellidos.setLayout(null);
		panelGApellidos.setBounds(10, 104, 414, 48);
		panelGProvee.add(panelGApellidos);
		
		textGApellidos = new JTextField();
		textGApellidos.setColumns(10);
		textGApellidos.setBounds(160, 11, 200, 26);
		panelGApellidos.add(textGApellidos);
		
		JLabel labelGApellidos = new JLabel("Apellidos");
		labelGApellidos.setBounds(10, 11, 125, 26);
		panelGApellidos.add(labelGApellidos);
		
		JPanel panelGDireccion = new JPanel();
		//Código de Carlos, no copiar
		panelGDireccion.setLayout(null);
		panelGDireccion.setBounds(10, 154, 414, 48);
		panelGProvee.add(panelGDireccion);
		
		textGDireccion = new JTextField();
		textGDireccion.setColumns(10);
		textGDireccion.setBounds(160, 11, 200, 26);
		panelGDireccion.add(textGDireccion);
		
		JLabel labelGDireccion = new JLabel("Direccion");
		labelGDireccion.setBounds(10, 11, 125, 26);
		panelGDireccion.add(labelGDireccion);
		
		// Este boton s eutiliza para dejar en blanco, es decir sin atributos, los textFields
		// de la pestaña Gestion de Proveedores
		
		JButton botonLimpiar = new JButton("Limpiar");
		botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				textGCodigo.setText(null);
				textGNombre.setText(null);
				textGApellidos.setText(null);
				textGDireccion.setText(null);
				
				System.out.println("Datos limpiados");
				
				
			}
		});
		botonLimpiar.setBounds(10, 222, 89, 42);
		panelGProvee.add(botonLimpiar);
		
		//Boton utilizado para modificar los datos de los proveedores
		
		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);

					
				} else if(textGNombre.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Nombre no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);
					
				} else if(textGApellidos.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Apellidos no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);
					
					
				} else if(textGDireccion.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "La casilla Dirección no puede estar vacía", "ERROR al dar de alta a un Proveedor",JOptionPane.ERROR_MESSAGE);
					
				} else {
				
				
				
				
				
				try {
					
					SessionFactory sesion = HibernateUtil.getSessionFactory();
					Session session = sesion.openSession();
					Transaction tx = session.beginTransaction();
					
					 Proveedores prove = (Proveedores) session.load(Proveedores.class , textGCodigo.getText());
					
					
					String codigoModificado = textGCodigo.getText();
					prove.setCodigo(codigoModificado);
					
					String nombreModificado = textGNombre.getText();
					prove.setNombre(nombreModificado);
					
					String apellidosModificado = textGApellidos.getText();
					prove.setApellidos(apellidosModificado);
					
					String direccionModificado = textGDireccion.getText();
					prove.setDireccion(direccionModificado);
					
					session.save(prove);
					tx.commit();
					session.close();
					System.out.println("El proveedor se ha modificado correctamente");
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Proveedor",JOptionPane.ERROR_MESSAGE);
					
				}
				
			
				
				
				
			}
			}
		});
		botonModificar.setBounds(213, 225, 89, 37);
		panelGProvee.add(botonModificar);
		
		// Boton utilizado para eliminar un proveedor
		
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				Proveedores prove = (Proveedores) session.load(Proveedores.class , textGCodigo.getText());
				
				
				
				session.delete(prove);
				tx.commit();
				session.close();
				
				System.out.println("Proveedor eliminado");
				
				} catch(Exception e2) {
					
					JOptionPane.showMessageDialog(null, "El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Proveedor",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		botonEliminar.setBounds(308, 225, 89, 37);
		panelGProvee.add(botonEliminar);
		
		JLabel lblDarleAlIntro = new JLabel("Darle al intro para que busque por c\u00F3digo");
		lblDarleAlIntro.setBounds(87, 318, 265, 23);
		panelGProvee.add(lblDarleAlIntro);
		
		JPanel panelLProveedores = new JPanel();
		panelLProveedores.setLayout(null);
		panelLProveedores.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPaneGestionProveedores.addTab("Listado de Proveedores", null, panelLProveedores, null);
		
		//Boton para volver al primer resultado 
		
		JButton botonInicio = new JButton("<<");
		JButton botonUltimo = new JButton(">>");
		JButton botonSiguiente = new JButton(">");
		JButton botonAnterior = new JButton("<");
		
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
				textLApellidos.setText(lista.get(0).getApellidos());
				textLDIreccion.setText(lista.get(0).getDireccion());
				
				
				contador = 0;
				
				textUno.setText(Integer.toString(contador + 1));
				
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Proveedores en la base de datos" ,"ERROR al intentar pasar a otro proveedor",JOptionPane.INFORMATION_MESSAGE);

					botonAnterior.setEnabled(false);
					botonInicio.setEnabled(false);
					
				}
				
				
				
				tx.commit();
				session.close();
				
				
			}
		});
		botonInicio.setBackground(Color.MAGENTA);
		botonInicio.setBounds(32, 350, 75, 48);
		panelLProveedores.add(botonInicio);
		
		// Boton anterior <
		botonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				if(contador > 0){
				
				botonSiguiente.setEnabled(true);
				botonUltimo.setEnabled(true);
				
				textLCodigo.setText(lista.get(contador - 1).getCodigo());
				textLNombre.setText(lista.get(contador - 1).getNombre());
				textLApellidos.setText(lista.get(contador - 1).getApellidos());
				textLDIreccion.setText(lista.get(contador - 1).getDireccion());
				
			
				
				contador --;
				
				textUno.setText(Integer.toString(contador + 1));
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Proveedores en la base de datos" ,"ERROR al intentar pasar a otro proveedor",JOptionPane.INFORMATION_MESSAGE);

					botonAnterior.setEnabled(false);
					botonInicio.setEnabled(false);
					
				}
				tx.commit();
				session.close();
				
				
				
				
				
			}
		});
		botonAnterior.setBounds(197, 350, 75, 48);
		panelLProveedores.add(botonAnterior);
		
		// Siguiente >
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
				textLApellidos.setText(lista.get(contador+1).getApellidos());
				textLDIreccion.setText(lista.get(contador+1).getDireccion());
				
				
				
				contador++;
				
				textUno.setText(Integer.toString(contador + 1));
				
				
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Proveedores en la base de datos" ,"ERROR al intentar pasar a otro proveedor",JOptionPane.INFORMATION_MESSAGE);

					
					botonSiguiente.setEnabled(false);
					botonUltimo.setEnabled(false);
					
				}
				tx.commit();
				session.close();
				
				
				
				
				
			}
		});
		botonSiguiente.setBounds(282, 350, 75, 48);
		panelLProveedores.add(botonSiguiente);
		
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
				textLApellidos.setText(lista.get(lista.size()-1).getApellidos());
				textLDIreccion.setText(lista.get(lista.size()-1).getDireccion());
				
				
				contador = (lista.size() -1);
				
				textUno.setText(Integer.toString(contador + 1));
				
				} else {
					JOptionPane.showMessageDialog(null, "No hay más Proveedores en la base de datos" ,"ERROR al intentar pasar a otro proveedor",JOptionPane.INFORMATION_MESSAGE);

					
					botonSiguiente.setEnabled(false);
					botonUltimo.setEnabled(false);
					
				}
				
				tx.commit();
				session.close();
				
				
				
				
				
			}
		});
		botonUltimo.setBackground(Color.MAGENTA);
		botonUltimo.setBounds(419, 350, 75, 48);
		panelLProveedores.add(botonUltimo);
		
		JPanel panelLCodigo = new JPanel();
		panelLCodigo.setLayout(null);
		panelLCodigo.setBounds(10, 11, 414, 48);
		panelLProveedores.add(panelLCodigo);
		
		textLCodigo = new JTextField();
		textLCodigo.setEditable(false);
		textLCodigo.setColumns(10);
		textLCodigo.setBounds(160, 11, 200, 26);
		panelLCodigo.add(textLCodigo);
		
		JLabel labelLCodigo = new JLabel("C\u00F3digo de Proveedor: (*)");
		labelLCodigo.setBounds(10, 11, 138, 26);
		panelLCodigo.add(labelLCodigo);
		
		JPanel panelLApellidos = new JPanel();
		panelLApellidos.setLayout(null);
		panelLApellidos.setBounds(10, 103, 414, 48);
		panelLProveedores.add(panelLApellidos);
		
		textLApellidos = new JTextField();
		textLApellidos.setEditable(false);
		textLApellidos.setColumns(10);
		textLApellidos.setBounds(160, 11, 200, 26);
		panelLApellidos.add(textLApellidos);
		
		JLabel labelLApellidos = new JLabel("Apellidos");
		labelLApellidos.setBounds(10, 11, 125, 26);
		panelLApellidos.add(labelLApellidos);
		
		JPanel panelLDireccion = new JPanel();
		panelLDireccion.setLayout(null);
		panelLDireccion.setBounds(10, 150, 414, 48);
		panelLProveedores.add(panelLDireccion);
		
		textLDIreccion = new JTextField();
		textLDIreccion.setEditable(false);
		textLDIreccion.setColumns(10);
		textLDIreccion.setBounds(160, 11, 200, 26);
		panelLDireccion.add(textLDIreccion);
		
		JLabel labelLDIreccion = new JLabel("Direccion");
		labelLDIreccion.setBounds(10, 11, 125, 26);
		panelLDireccion.add(labelLDIreccion);
		
		JPanel panelLNombre = new JPanel();
		panelLNombre.setBounds(10, 58, 414, 48);
		panelLProveedores.add(panelLNombre);
		panelLNombre.setLayout(null);
		
		textLNombre = new JTextField();
		textLNombre.setEditable(false);
		textLNombre.setColumns(10);
		textLNombre.setBounds(160, 11, 200, 26);
		panelLNombre.add(textLNombre);
		
		JLabel labelLNombre = new JLabel("Nombre");
		labelLNombre.setBounds(10, 11, 125, 26);
		panelLNombre.add(labelLNombre);
		
		//Buscar proveedores en la base de datos (por ID) y mostrarlos por pantalla
		
		
		JButton botonBaja = new JButton("Baja");
		JButton botonEjecutarConsulta = new JButton("Ejecutar Consulta");
		botonEjecutarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				contador = 0;
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				
					
					textLCodigo.setText(lista.get(0).getCodigo());
					textLNombre.setText(lista.get(0).getNombre());
					textLApellidos.setText(lista.get(0).getApellidos());
					textLDIreccion.setText(lista.get(0).getDireccion());
					
					// Inicializamos el 1 de x
					
					textUno.setText("1");
					textOtro.setText(Integer.toString(lista.size()));
					
					
				
					botonBaja.setEnabled(true);
					botonInicio.setEnabled(true);
					botonAnterior.setEnabled(true);
					botonSiguiente.setEnabled(true);
					botonUltimo.setEnabled(true);
					
				
				
				
				
				session.save(prov);
				tx.commit();
				session.close();
				
				
			}
			
			
		});
		botonEjecutarConsulta.setBounds(10, 262, 304, 48);
		panelLProveedores.add(botonEjecutarConsulta);
		
		// Boton baja
		
		botonBaja.setEnabled(false);
		botonBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resultado = JOptionPane.showConfirmDialog(null, "¿Quieres dar de baja a este Provedor? \n \n Código: " + textLCodigo.getText() + "\n Nombre: " + textLNombre.getText() + "\n Apellidos: " + textLApellidos.getText() + "\n Dirección: " + textLDIreccion.getText() + "\n"  ,"Dar de baja Proveedor",JOptionPane.INFORMATION_MESSAGE);

				if(resultado == JOptionPane.YES_OPTION){
					
					SessionFactory sesion = HibernateUtil.getSessionFactory();
					Session session = sesion.openSession();
					Transaction tx = session.beginTransaction();
					
					Proveedores prove = (Proveedores) session.load(Proveedores.class , textLCodigo.getText());
					//Lo elimino también de la Lista
					lista.remove(contador);
					//Limpiar la pantalla
					textLCodigo.setText(null);
					textLNombre.setText(null);
					textLApellidos.setText(null);
					textLDIreccion.setText(null);
					
					textUno.setText(null);
					textOtro.setText(null);;
					
					session.delete(prove);
					tx.commit();
					session.close();
					System.out.println("Proveedor eliminado");
					
					
					
				} 
				
				
				
				
				
			}
		});
		botonBaja.setBounds(335, 262, 89, 48);
		panelLProveedores.add(botonBaja);
		
		textUno = new JTextField();
		textUno.setEditable(false);
		textUno.setBounds(38, 223, 23, 20);
		panelLProveedores.add(textUno);
		textUno.setColumns(10);
		
		JLabel lblDe = new JLabel("de");
		lblDe.setBounds(81, 226, 23, 14);
		panelLProveedores.add(lblDe);
		
		textOtro = new JTextField();
		textOtro.setEditable(false);
		textOtro.setBounds(125, 223, 23, 20);
		panelLProveedores.add(textOtro);
		textOtro.setColumns(10);
		
		
		
		
		
	}
}





















