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
import Singleton.Proveedores;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ConsultaProveedores extends JFrame {

	Proveedores prov = new Proveedores();
	
	private JPanel contentPane;
	private JTextField textCodigo;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaProveedores frame = new ConsultaProveedores();
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
	public ConsultaProveedores() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carlos\\Desktop\\Workspace\\bd.png"));
		setTitle("Consulta proveedores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 644, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		textCodigo = new JTextField();
		textCodigo.setBounds(223, 36, 116, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		JComboBox comboCodigo = new JComboBox();
		comboCodigo.setBounds(22, 104, 584, 23);
		contentPane.add(comboCodigo);
		
		JTextArea textDatos = new JTextArea();
		textDatos.setBounds(96, 153, 285, 174);
		contentPane.add(textDatos);
		
		JComboBox comboBusqueda = new JComboBox();
		comboBusqueda.setBounds(118, 36, 86, 20);
		contentPane.add(comboBusqueda);
		
		comboBusqueda.addItem("Código");
		comboBusqueda.addItem("Nombre");
		comboBusqueda.addItem("Apellido");
		
		
		JButton botonBuscar = new JButton("Buscar Proveedor");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if ( textCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "Esta casilla no puede estar vacía", "ERROR al consultar Proveedor",JOptionPane.ERROR_MESSAGE);
				
				
				} else {
					
					try {
					
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				Query q = session.createQuery("FROM Proveedores");
				List<Proveedores> lista = q.list();
				
				
				
				
				String cBusqueda = (String) comboBusqueda.getSelectedItem();
				String codigo = textCodigo.getText();
				
				
				
				switch(cBusqueda){
				case "Código":
					
					prov = (Proveedores) session.load(Proveedores.class , textCodigo.getText());
					//comboCodigo.addItem(prov.getCodigo());
					comboCodigo.addItem(" CODIGO: "+prov.getCodigo()+ "\n\n NOMBRE: "+ prov.getNombre()+"\n\n APELLIDO: " + prov.getApellidos()+ "\n\n DIRECCION: " + prov.getDireccion());
					
					break;
					
				case "Nombre":
					
					for (Proveedores proveedores : lista) {
						
						if(proveedores.getNombre().equals(codigo)){
							//textDatos.setText("CODIGO:     "+proveedores.getCodigo()+ "\n\nNOMBRE:     " + proveedores.getNombre()+ "\n\nAPELLIDOS:     " + proveedores.getApellidos()+ "\n\nDIRECCION:     " + proveedores.getDireccion());
							//comboCodigo.addItem(proveedores.getCodigo()+ " - " + proveedores.getNombre()+ " - " + proveedores.getApellidos()+ " - " + proveedores.getDireccion());
							//comboCodigo.addItem(proveedores.getNombre());
							comboCodigo.addItem("  CODIGO: "+proveedores.getCodigo()+ "\n\n  NOMBRE: "+ proveedores.getNombre()+"\n\n  APELLIDO: " + proveedores.getApellidos()+ "\n\n  DIRECCION: " + proveedores.getDireccion());

							
						}
						
					
						
					}
					
					
					
					
					break;
				
				case "Apellido":
					
					
					for (Proveedores proveedores : lista) {
						
						if(proveedores.getApellidos().equals(codigo)){
							//textDatos.setText("CODIGO:     "+proveedores.getCodigo()+ "\n\nNOMBRE:     " + proveedores.getNombre()+ "\n\nAPELLIDOS:     " + proveedores.getApellidos()+ "\n\nDIRECCION:     " + proveedores.getDireccion());
							//comboCodigo.addItem(proveedores.getCodigo()+ " - " + proveedores.getNombre()+ " - " + proveedores.getApellidos()+ " - " + proveedores.getDireccion());
							//comboCodigo.addItem(proveedores.getNombre());
							comboCodigo.addItem("  CODIGO: "+proveedores.getCodigo()+ "\n\n  NOMBRE: "+ proveedores.getNombre()+"\n\n  APELLIDO: " + proveedores.getApellidos()+ "\n\n  DIRECCION: " + proveedores.getDireccion());

							
						}
					
				}
				
				
				
				
				
				
				tx.commit();
				session.close();
				
				
			}
				
				
				} catch(ObjectNotFoundException e) {
					
					JOptionPane.showMessageDialog(null, "Los datos que ha introducido no son válidos debido a  \n que no están en la base de datos. \n Pruebe introduciendo un datos válidos. \n Ejemplo: A00001","ERROR al mostrar Proveedor",JOptionPane.ERROR_MESSAGE);
				}
				
			}
			}
		});
		
	
	
		botonBuscar.setBounds(360, 35, 142, 23);
		contentPane.add(botonBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboCodigo.removeAllItems();
				textCodigo.setText(null);
				textDatos.setText(null);
				
			}
		});
		btnLimpiar.setBounds(438, 304, 142, 23);
		contentPane.add(btnLimpiar);
		
		JButton botonAnadir = new JButton("A\u00F1adir");
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textDatos.setText((String) comboCodigo.getSelectedItem());;
				
				
			}
		});
		botonAnadir.setBounds(437, 269, 143, 23);
		contentPane.add(botonAnadir);
		
		JLabel lblBuscarPor = new JLabel("Buscar por: ");
		lblBuscarPor.setBounds(22, 36, 86, 20);
		contentPane.add(lblBuscarPor);
		
		
		

		
		
		
		/*
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		
		//Sirve para añadir TODOS los codigos de proveedor al combobox (no filtra)
		 * 
		Query q = session.createQuery("FROM Proveedores");
		List<Proveedores> lista = q.list();
		
		
		for (Proveedores proveedores : lista) {
			comboCodigo.addItem(proveedores.getCodigo());
		}
			
		System.out.println("Se han añadido los códigos al combobox");
		*/
		
		
		
		
	}
}
