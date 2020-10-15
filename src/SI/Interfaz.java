package SI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldFilas;
	private JTextField txtFieldColumnas;
	private JLabel lblColumnas;
	private JLabel lblFilas;
	private JButton btnAceptar;
	


	/**
	 * Create the frame.
	 */
	public Interfaz() {
		
		setTitle("Creador de laberintos");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 209, 124, 44, 0, 37, 0};
		gbl_contentPane.rowHeights = new int[]{0, 188, 0, 27, 192, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblFilas = new JLabel("Introduce el n\u00FAmero de filas:");
		GridBagConstraints gbc_lblFilas = new GridBagConstraints();
		gbc_lblFilas.anchor = GridBagConstraints.EAST;
		gbc_lblFilas.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilas.gridx = 1;
		gbc_lblFilas.gridy = 2;
		contentPane.add(lblFilas, gbc_lblFilas);
		
		txtFieldFilas = new JTextField();
		txtFieldFilas.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldFilas.setText("0");
		GridBagConstraints gbc_txtFieldFilas = new GridBagConstraints();
		gbc_txtFieldFilas.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldFilas.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldFilas.gridx = 2;
		gbc_txtFieldFilas.gridy = 2;
		contentPane.add(txtFieldFilas, gbc_txtFieldFilas);
		txtFieldFilas.setColumns(10);
		
		lblColumnas = new JLabel("Introduce el n\u00FAmero de columnas:");
		GridBagConstraints gbc_lblColumnas = new GridBagConstraints();
		gbc_lblColumnas.anchor = GridBagConstraints.EAST;
		gbc_lblColumnas.insets = new Insets(0, 0, 5, 5);
		gbc_lblColumnas.gridx = 1;
		gbc_lblColumnas.gridy = 3;
		contentPane.add(lblColumnas, gbc_lblColumnas);
		
		txtFieldColumnas = new JTextField();
		txtFieldColumnas.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldColumnas.setText("0");
		GridBagConstraints gbc_txtFieldColumnas = new GridBagConstraints();
		gbc_txtFieldColumnas.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldColumnas.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldColumnas.gridx = 2;
		gbc_txtFieldColumnas.gridy = 3;
		contentPane.add(txtFieldColumnas, gbc_txtFieldColumnas);
		txtFieldColumnas.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new BtnAceptarActionListener());
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 4;
		contentPane.add(btnAceptar, gbc_btnAceptar);
	}

	private class BtnAceptarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int filas = Integer.parseInt(txtFieldFilas.getText());
			int columnas = Integer.parseInt(txtFieldColumnas.getText());
			Celda[][] laberinto = new Celda[filas][columnas];

			for (int i = 0; i < laberinto.length; i++) {
				for (int j = 0; j < laberinto[0].length; j++) {

					Celda c = new Celda(i, j);

					laberinto[i][j] = c;
				}
			}
			

			Formas frame = new Formas(laberinto);
			frame.setVisible(true);
		}
	}

	
	

}
