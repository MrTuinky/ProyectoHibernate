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
import Singleton.Proyectos;
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

public class ConsultaProyectos extends JFrame {

	Proyectos proyectos = new Proyectos();
	
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
					ConsultaProyectos frame = new ConsultaProyectos();
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
	public ConsultaProyectos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carlos\\Desktop\\Workspace\\bd.png"));
		setTitle("Consulta Proyectos");
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
		comboBusqueda.addItem("Ciudad");
		
		
		JButton botonBuscar = new JButton("Buscar Proyecto");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if ( textCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "Esta casilla no puede estar vacía", "ERROR al consultar Proyectos",JOptionPane.ERROR_MESSAGE);
				
				
				} else {
					
					try {
				
				
				
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				Query q = session.createQuery("FROM Proyectos");
				List<Proyectos> lista = q.list();
				
				
				
				
				String cBusqueda = (String) comboBusqueda.getSelectedItem();
				String codigo = textCodigo.getText();
				
				
				
				switch(cBusqueda){
				case "Código":
					
					proyectos = (Proyectos) session.load(Proyectos.class , textCodigo.getText());
					comboCodigo.addItem(" CODIGO: "+proyectos.getCodigo()+ "\n\n NOMBRE: "+ proyectos.getNombre()+"\n\n CIUDAD: " + proyectos.getCiudad());
					
					break;
					
				case "Nombre":
					
					for (Proyectos proyectos : lista) {
						
						if(proyectos.getNombre().equals(codigo)){
							
							comboCodigo.addItem(" CODIGO: "+proyectos.getCodigo()+ "\n\n NOMBRE: "+ proyectos.getNombre()+"\n\n CIUDAD: " + proyectos.getCiudad());

							
						}
						
						
						
					}
					
					
					
					
					break;
				
				case "Ciudad":
					
					
					for (Proyectos proyectos : lista) {
						
						if(proyectos.getCiudad().equals(codigo)){
							comboCodigo.addItem(" CODIGO: "+proyectos.getCodigo()+ "\n\n NOMBRE: "+ proyectos.getNombre()+"\n\n CIUDAD: " + proyectos.getCiudad());

							
						}
					
				}
				
				
				
				
				
				
				tx.commit();
				session.close();
				
				
			}
					} catch(ObjectNotFoundException e) {
						
						JOptionPane.showMessageDialog(null, "Los datos que ha introducido no son válidos debido a  \n que no están en la base de datos. \n Pruebe introduciendo un datos válidos. \n Ejemplo: PR1","ERROR al mostrar Proyectos",JOptionPane.ERROR_MESSAGE);
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
		
		
		
		
		
		
	}
}
