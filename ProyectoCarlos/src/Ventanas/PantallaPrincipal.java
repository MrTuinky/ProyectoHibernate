package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Singleton.HibernateUtil;
import Singleton.Piezas;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Toolkit;
import javax.swing.JTextArea;

public class PantallaPrincipal extends JFrame {
	
	GestionProveedores gp = new GestionProveedores();
	GestionPiezas gpiezas = new GestionPiezas();
	ConsultaProveedores cp = new ConsultaProveedores();
	ConsultaPiezas cpiezas = new ConsultaPiezas();
	GestionProyectos gproyectos = new GestionProyectos();
	ConsultaProyectos cproyectos = new ConsultaProyectos();

	private JPanel contentPane;
	private final Action action = new SwingAction();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		//Inicia la base de datoas desde el principio para ahorrar tiempo
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		session.close();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setTitle("Pantalla Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carlos\\Desktop\\bd.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 582, 21);
		contentPane.add(menuBar);
		
		JMenu mnBaseDeDatos = new JMenu("Base de Datos");
		menuBar.add(mnBaseDeDatos);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		menuBar.add(mnProveedores);
		
		
		//Redirige a la ventana de gestion de proveedores
		JMenuItem menuGProveedores = new JMenuItem("Gesti\u00F3n de Proveedores");
		menuGProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				gp.setVisible(true);
				
				
			}
		});
		mnProveedores.add(menuGProveedores);
		
		JMenuItem mntmConsulta = new JMenuItem("Consulta de proveedores");
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cp.setVisible(true);
				
			}
		});
		mnProveedores.add(mntmConsulta);
		
		JMenu mnPiezas = new JMenu("Piezas");
		menuBar.add(mnPiezas);
		
		JMenuItem mntmGestionDePiezas = new JMenuItem("Gestion de Piezas");
		mntmGestionDePiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gpiezas.setVisible(true);;
				
			}
		});
		mnPiezas.add(mntmGestionDePiezas);
		
		JMenuItem mntmConsultaDePiezas = new JMenuItem("Consulta de Piezas");
		mntmConsultaDePiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cpiezas.setVisible(true);;
				
			}
		});
		mnPiezas.add(mntmConsultaDePiezas);
		
		JMenu mnProyectos = new JMenu("Proyectos");
		menuBar.add(mnProyectos);
		
		JMenuItem mntmGestion = new JMenuItem("Gestion de Proyectos");
		mntmGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gproyectos.setVisible(true);;
				
			}
		});
		mnProyectos.add(mntmGestion);
		
		JMenuItem mntmConsultaDeProyectos = new JMenuItem("Consulta de Proyectos");
		mntmConsultaDeProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cproyectos.setVisible(true);;
				
			}
		});
		mnProyectos.add(mntmConsultaDeProyectos);
		
		JMenu mnGestinGlobal = new JMenu("Gesti\u00F3n Global");
		menuBar.add(mnGestinGlobal);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JTextArea txtrCreadoPorCarlos = new JTextArea();
		txtrCreadoPorCarlos.setText("Creado por Carlos Hern\u00E1ndez");
		mnAyuda.add(txtrCreadoPorCarlos);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
