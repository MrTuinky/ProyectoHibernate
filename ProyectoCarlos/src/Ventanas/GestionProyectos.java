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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Singleton.HibernateUtil;
import Singleton.Proveedores;
import Singleton.Proyectos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Toolkit;

public class GestionProyectos extends JFrame {
	
	
	
	Proyectos proyectos = new Proyectos();
	
	private JTextField textGCodigo;
	private JTextField textGNombre;
	private JTextField textGCiudad;
	private JTextField textLCodigo;
	private JTextField textLNombre;
	private JTextField textLCiudad;
	private JTextField textUno;
	private JTextField textOtro;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionProyectos frame = new GestionProyectos();
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
	
	Query q = session.createQuery("FROM Proyectos");
	List<Proyectos> lista = q.list();
	
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
	public GestionProyectos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carlos\\Desktop\\Workspace\\bd.png"));
		setTitle("Gesti\u00F3n de Proyectos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 566);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JTabbedPane tabbedPaneGestionProveedores = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneGestionProveedores.setBounds(0, 0, 551, 527);
		getContentPane().add(tabbedPaneGestionProveedores);
		
		JPanel panelGProyecto = new JPanel();
		panelGProyecto.setLayout(null);
		panelGProyecto.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPaneGestionProveedores.addTab("Gestion de Proyectos", null, panelGProyecto, null);
		
		//Botón para dar de alta a un proyecto, es decir, añadir a la base de datos un nuevo proyecto
		
		JButton botonAlta = new JButton("Alta");
		botonAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código no puede estar vacía", "ERROR al dar de alta a un Proyecto",JOptionPane.ERROR_MESSAGE);

					
				} else if(textGNombre.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Nombre no puede estar vacía", "ERROR al dar de alta a un Proyecto",JOptionPane.ERROR_MESSAGE);
					
				} else if(textGCiudad.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Ciudad no puede estar vacía", "ERROR al dar de alta a un Proyecto",JOptionPane.ERROR_MESSAGE);
					
					
				} else {
				
					try{
					
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				
				proyectos.setCodigo(textGCodigo.getText());
				proyectos.setNombre(textGNombre.getText());
				proyectos.setCiudad(textGCiudad.getText());
				
			
				
				
				session.save(proyectos);
				tx.commit();
				
				session.close();
				
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "ConstraintViolation = Código de proyecto duplicado \n El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Proveedor",JOptionPane.ERROR_MESSAGE);
					
				}
				
				session.close();
				System.out.println("Se ha dado de alta un proyecto");
			}
			}
		});
		botonAlta.setBounds(117, 225, 89, 37);
		panelGProyecto.add(botonAlta);
		
		JPanel panelGCodigo = new JPanel();
		panelGCodigo.setLayout(null);
		panelGCodigo.setBounds(10, 11, 414, 48);
		panelGProyecto.add(panelGCodigo);
		
		textGCodigo = new JTextField();
		textGCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código está vacía, para buscar un Proyecto, \n  introduce un Código Proyecto válido", "ERROR al intentar mostrar un Proyecto",JOptionPane.INFORMATION_MESSAGE);
				
				} else {
				
				try {
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				
				proyectos = (Proyectos) session.load(Proyectos.class , textGCodigo.getText());
				
				
				
				
				
				System.out.println(proyectos.getCodigo()+ " - " + proyectos.getNombre()+ " - " + proyectos.getCiudad());
				textGNombre.setText(proyectos.getNombre());
				textGCiudad.setText(proyectos.getCiudad());
				tx.commit();
				session.close();
				
				} catch(Exception e2) {
					
					JOptionPane.showMessageDialog(null, "El Código Proyecto que ha introducido no puede mostarse debido a \n que no está en la base de datos. \n Pruebe introduciendo un Código Proyecto válido. \n Ejemplo: A00001","ERROR al mostrar Proyecto",JOptionPane.ERROR_MESSAGE);

				}
					
				}
			}
		});
		textGCodigo.setColumns(10);
		textGCodigo.setBounds(145, 11, 200, 26);
		panelGCodigo.add(textGCodigo);
		
		JLabel labelGCodigo = new JLabel("C\u00F3digo de Proyecto: (*)");
		labelGCodigo.setBounds(10, 11, 125, 26);
		panelGCodigo.add(labelGCodigo);
		
		JPanel panelGNombre = new JPanel();
		panelGNombre.setLayout(null);
		panelGNombre.setBounds(10, 60, 414, 48);
		panelGProyecto.add(panelGNombre);
		
		textGNombre = new JTextField();
		textGNombre.setColumns(10);
		textGNombre.setBounds(145, 11, 200, 26);
		panelGNombre.add(textGNombre);
		
		JLabel labelGnombre = new JLabel("Nombre");
		labelGnombre.setBounds(10, 11, 125, 26);
		panelGNombre.add(labelGnombre);
		
		JPanel panelGCiudad = new JPanel();
		panelGCiudad.setLayout(null);
		panelGCiudad.setBounds(10, 104, 414, 48);
		panelGProyecto.add(panelGCiudad);
		
		textGCiudad = new JTextField();
		textGCiudad.setColumns(10);
		textGCiudad.setBounds(145, 11, 200, 26);
		panelGCiudad.add(textGCiudad);
		
		JLabel labelGCiudad = new JLabel("Ciudad");
		labelGCiudad.setBounds(10, 11, 125, 26);
		panelGCiudad.add(labelGCiudad);
		
		// Este boton se utiliza para dejar en blanco, es decir sin atributos, los textFields
		// de la pestaña Gestion de Proyectos
		
		JButton botonLimpiar = new JButton("Limpiar");
		botonLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				textGCodigo.setText(null);
				textGNombre.setText(null);
				textGCiudad.setText(null);
				
				
				System.out.println("Datos limpiados");
				
				
			}
		});
		botonLimpiar.setBounds(10, 222, 89, 42);
		panelGProyecto.add(botonLimpiar);
		
		//Boton utilizado para modificar los datos de los proyectos
		
		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if ( textGCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "La casilla Código no puede estar vacía", "ERROR al dar de alta a un Proyecto",JOptionPane.ERROR_MESSAGE);

					
				} else if(textGNombre.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Nombre no puede estar vacía", "ERROR al dar de alta a un Proyecto",JOptionPane.ERROR_MESSAGE);
					
				} else if(textGCiudad.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "La casilla Ciudad no puede estar vacía", "ERROR al dar de alta a un Proyecto",JOptionPane.ERROR_MESSAGE);
					
					
				} else {
			
					try {
					
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				
				proyectos = (Proyectos) session.load(Proyectos.class , textGCodigo.getText());
				
				
				String codigoModificado = textGCodigo.getText();
				proyectos.setCodigo(codigoModificado);
				
				String nombreModificado = textGNombre.getText();
				proyectos.setNombre(nombreModificado);
				
				String ciudadModificado = textGCiudad.getText();
				proyectos.setCiudad(ciudadModificado);
				
				
				
				session.save(proyectos);
				tx.commit();
				session.close();System.out.println("El proyecto se ha modificado correctamente");
				
					} catch(Exception e2) {
						
						JOptionPane.showMessageDialog(null, "El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Proyecto",JOptionPane.ERROR_MESSAGE);

						
					}
				}
				
				
			}
		});
		botonModificar.setBounds(213, 225, 89, 37);
		panelGProyecto.add(botonModificar);
		
		// Boton utilizado para eliminar un proyecto
		
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				Proyectos proyectoss = (Proyectos) session.load(Proyectos.class , textGCodigo.getText());
				
				session.delete(proyectoss);
				tx.commit();
				session.close();
				System.out.println("Proyecto eliminado");
				
				} catch(Exception e2) {
					
					JOptionPane.showMessageDialog(null, "El error ha sido el siguiente: \n" + e2.getMessage(),"ERROR al insertar Proyecto",JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		botonEliminar.setBounds(308, 225, 89, 37);
		panelGProyecto.add(botonEliminar);
		
		JLabel lblDarleAlIntro = new JLabel("Darle al intro para que busque por c\u00F3digo");
		lblDarleAlIntro.setBounds(87, 318, 265, 23);
		panelGProyecto.add(lblDarleAlIntro);
		
		JPanel panelLProyectos = new JPanel();
		panelLProyectos.setLayout(null);
		panelLProyectos.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPaneGestionProveedores.addTab("Listado de Proyectos", null, panelLProyectos, null);
		
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
						textLCiudad.setText(lista.get(0).getCiudad());
						
						
						
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
				panelLProyectos.add(botonInicio);
				
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
						textLCiudad.setText(lista.get(contador - 1).getCiudad());
						
						
					
						
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
				panelLProyectos.add(botonAnterior);
				
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
						textLCiudad.setText(lista.get(contador+1).getCiudad());
						
						
						
						
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
				panelLProyectos.add(botonSiguiente);
				
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
						textLCiudad.setText(lista.get(lista.size()-1).getCiudad());
						
						
						
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
				panelLProyectos.add(botonUltimo);
				
				JPanel panelLCodigo = new JPanel();
				panelLCodigo.setLayout(null);
				panelLCodigo.setBounds(10, 11, 414, 48);
				panelLProyectos.add(panelLCodigo);
				
				textLCodigo = new JTextField();
				textLCodigo.setEditable(false);
				textLCodigo.setColumns(10);
				textLCodigo.setBounds(160, 11, 200, 26);
				panelLCodigo.add(textLCodigo);
				
				JLabel labelLCodigo = new JLabel("C\u00F3digo de Proyecto: (*)");
				labelLCodigo.setBounds(10, 11, 138, 26);
				panelLCodigo.add(labelLCodigo);
				
				JPanel panelLApellidos = new JPanel();
				panelLApellidos.setLayout(null);
				panelLApellidos.setBounds(10, 103, 414, 48);
				panelLProyectos.add(panelLApellidos);
				
				textLCiudad = new JTextField();
				textLCiudad.setEditable(false);
				textLCiudad.setColumns(10);
				textLCiudad.setBounds(160, 11, 200, 26);
				panelLApellidos.add(textLCiudad);
				
				JLabel labelLCiudad = new JLabel("Ciudad");
				labelLCiudad.setBounds(10, 11, 125, 26);
				panelLApellidos.add(labelLCiudad);
				
				JPanel panelLNombre = new JPanel();
				panelLNombre.setBounds(10, 58, 414, 48);
				panelLProyectos.add(panelLNombre);
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
							textLCiudad.setText(lista.get(0).getCiudad());
							
							
							// Inicializamos el 1 de x
							
							textUno.setText("1");
							textOtro.setText(Integer.toString(lista.size()));
							
							
						
							botonBaja.setEnabled(true);
							botonInicio.setEnabled(true);
							botonAnterior.setEnabled(true);
							botonSiguiente.setEnabled(true);
							botonUltimo.setEnabled(true);
							
						
						
						
						
						session.save(proyectos);
						tx.commit();
						session.close();
						
						
					}
				});
				botonEjecutarConsulta.setBounds(10, 262, 304, 48);
				panelLProyectos.add(botonEjecutarConsulta);
				
				// Boton baja
				
				botonBaja.setEnabled(false);
				botonBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int resultado = JOptionPane.showConfirmDialog(null, "¿Quieres dar de baja a este Provedor? \n \n Código: " + textLCodigo.getText() + "\n Nombre: " + textLNombre.getText() + "\n Ciudad: " + textGCiudad.getText() + "\n"  ,"Dar de baja Proyecto",JOptionPane.INFORMATION_MESSAGE);

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
							textLCiudad.setText(null);
							
							
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
				panelLProyectos.add(botonBaja);
				
				textUno = new JTextField();
				textUno.setEditable(false);
				textUno.setBounds(38, 223, 23, 20);
				panelLProyectos.add(textUno);
				textUno.setColumns(10);
				
				JLabel lblDe = new JLabel("de");
				lblDe.setBounds(81, 226, 23, 14);
				panelLProyectos.add(lblDe);
				
				textOtro = new JTextField();
				textOtro.setEditable(false);
				textOtro.setBounds(125, 223, 23, 20);
				panelLProyectos.add(textOtro);
				textOtro.setColumns(10);
				
				
				
				
		
	}
}
