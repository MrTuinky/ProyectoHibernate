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

public class ConsultaPiezas extends JFrame {

	Piezas piezas = new Piezas();
	
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
	public ConsultaPiezas() {
		setTitle("Consulta Piezas");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carlos\\Desktop\\Workspace\\bd.png"));
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
		comboBusqueda.addItem("Precio");
		
		
		
		JButton botonBuscar = new JButton("Buscar Pieza");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if ( textCodigo.getText().equals("") ) {
					
					JOptionPane.showMessageDialog(null, "Esta casilla no puede estar vacía", "ERROR al consultar Pieza",JOptionPane.ERROR_MESSAGE);
				
				
				} else {
				
				try {
					
				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
				
				Query q = session.createQuery("FROM Piezas");
				List<Piezas> lista = q.list();
				
				
				
				
				String cBusqueda = (String) comboBusqueda.getSelectedItem();
				
				
				
				
				switch(cBusqueda){
				case "Código":
					
					piezas = (Piezas) session.load(Piezas.class , textCodigo.getText());
					comboCodigo.addItem(" CODIGO: "+piezas.getCodigo()+ "\n\n NOMBRE: "+ piezas.getNombre()+"\n\n PRECIO: " + piezas.getPrecio()+ "\n\n DESCRIPCION: " + piezas.getDescripcion());
					
					break;
					
				case "Precio":
					
					float codigo = Float.parseFloat(textCodigo.getText());
					
					for (Piezas pieza : lista) {
						
						if(pieza.getPrecio() == codigo){
						
							comboCodigo.addItem(" CODIGO: "+pieza.getCodigo()+ "\n\n NOMBRE: "+ pieza.getNombre()+"\n\n PRECIO: " + pieza.getPrecio()+ "\n\n DESCRIPCION: " + pieza.getDescripcion());

							
						}
						
						
						
					}
					
					
					tx.commit();
					session.close();
				
				
				
				
			}
				
				} catch(ObjectNotFoundException e) {
					
					JOptionPane.showMessageDialog(null, "Los datos que ha introducido no son válidos debido a  \n que no están en la base de datos. \n Pruebe introduciendo un datos válidos. \n Ejemplo: P1","ERROR al mostrar Piezas",JOptionPane.ERROR_MESSAGE);
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
