package SI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.awt.GridBagLayout;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldFilas;
	private JTextField txtFieldColumnas;
	private JLabel lblColumnas;
	private JLabel lblFilas;
	private JButton btnAceptar;
	private JLabel lblCrearLaberinto;
	private JLabel lblLeerLaberinto;
	private JButton btnLeer;

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setResizable(false);

		setTitle("Creador de laberintos");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 178, 124, 44, 0, 37, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 75, 0, 27, 65, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		
		lblCrearLaberinto = new JLabel("Crear laberinto");
		GridBagConstraints gbc_lblCrearLaberinto = new GridBagConstraints();
		gbc_lblCrearLaberinto.anchor = GridBagConstraints.WEST;
		gbc_lblCrearLaberinto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrearLaberinto.gridx = 1;
		gbc_lblCrearLaberinto.gridy = 1;
		contentPane.add(lblCrearLaberinto, gbc_lblCrearLaberinto);
		
		lblLeerLaberinto = new JLabel("Leer laberinto");
		GridBagConstraints gbc_lblLeerLaberinto = new GridBagConstraints();
		gbc_lblLeerLaberinto.anchor = GridBagConstraints.WEST;
		gbc_lblLeerLaberinto.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeerLaberinto.gridx = 4;
		gbc_lblLeerLaberinto.gridy = 1;
		contentPane.add(lblLeerLaberinto, gbc_lblLeerLaberinto);

		lblFilas = new JLabel("Introduce el n\u00FAmero de filas:");
		GridBagConstraints gbc_lblFilas = new GridBagConstraints();
		gbc_lblFilas.anchor = GridBagConstraints.WEST;
		gbc_lblFilas.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilas.gridx = 1;
		gbc_lblFilas.gridy = 2;
		contentPane.add(lblFilas, gbc_lblFilas);

		txtFieldFilas = new JTextField();
		txtFieldFilas.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		txtFieldFilas.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtFieldFilas = new GridBagConstraints();
		gbc_txtFieldFilas.anchor = GridBagConstraints.WEST;
		gbc_txtFieldFilas.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldFilas.gridx = 2;
		gbc_txtFieldFilas.gridy = 2;
		contentPane.add(txtFieldFilas, gbc_txtFieldFilas);
		txtFieldFilas.setColumns(10);
		
		btnLeer = new JButton("Leer");
		
		btnLeer.addActionListener(new BtnLeerActionListener());
		
		GridBagConstraints gbc_btnLeer = new GridBagConstraints();
		gbc_btnLeer.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_btnLeer.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeer.gridx = 4;
		gbc_btnLeer.gridy = 2;
		contentPane.add(btnLeer, gbc_btnLeer);

		lblColumnas = new JLabel("Introduce el n\u00FAmero de columnas:");
		GridBagConstraints gbc_lblColumnas = new GridBagConstraints();
		gbc_lblColumnas.anchor = GridBagConstraints.WEST;
		gbc_lblColumnas.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumnas.gridx = 1;
		gbc_lblColumnas.gridy = 3;
		contentPane.add(lblColumnas, gbc_lblColumnas);

		txtFieldColumnas = new JTextField();
		txtFieldColumnas.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		txtFieldColumnas.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtFieldColumnas = new GridBagConstraints();
		gbc_txtFieldColumnas.anchor = GridBagConstraints.WEST;
		gbc_txtFieldColumnas.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldColumnas.gridx = 2;
		gbc_txtFieldColumnas.gridy = 3;
		contentPane.add(txtFieldColumnas, gbc_txtFieldColumnas);
		txtFieldColumnas.setColumns(10);

		btnAceptar = new JButton("Crear");
		btnAceptar.addActionListener(new BtnAceptarActionListener());
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.anchor = GridBagConstraints.WEST;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 4;
		contentPane.add(btnAceptar, gbc_btnAceptar);
	}

	private class BtnLeerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			Gson gson = new Gson();
			int seleccion = fileChooser.showOpenDialog(contentPane);
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
				try {
			   File fichero = fileChooser.getSelectedFile();
			   // Aquí debemos abrir y leer el fichero.
			   String path = fichero.getPath();
			   BufferedReader br;
			
				br = new BufferedReader(new FileReader(path));
				PuzzleJSON leido = gson.fromJson(br, PuzzleJSON.class);
				
				//Wilson leido = gson.fromJson(br, Wilson.class);
				Formas frame = new Formas(leido.getLaberinto());
				frame.setVisible(true);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch(Exception e2) {
				JOptionPane.showMessageDialog(contentPane, "Fichero no válido.");
			}
			
			   
			}
		}
	}
	
	private class BtnAceptarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int filas = Integer.parseInt(txtFieldFilas.getText());
			int columnas = Integer.parseInt(txtFieldColumnas.getText());
			Celda[][] laberinto = new Celda[filas][columnas];
			
			Wilson.crearLaberinto(laberinto);

			Formas frame = new Formas(laberinto);
			frame.setVisible(true);
		}
	}

}
