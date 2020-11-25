package SI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SI.Interfaz.Formas;
import SI.Wilson.Celda;
import SI.Wilson.Wilson;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ElegirEstrategia extends JFrame {

	private JPanel contentPane;
	private GridBagLayout gbl_panel;
	private JLabel lblEstrategia;
	private JRadioButton rdbtnAnchura;
	private JPanel panel;
	private GridBagConstraints gbc_rdbtnAnchura;
	private JRadioButton rdbtnProfundidad;
	private GridBagConstraints gbc_rdbtnProfundidad;
	private JRadioButton rdbtnCosteUniforme;
	private GridBagConstraints gbc_rdbtnCosteUniforme;
	private JRadioButton rdbtnVoraz;
	private GridBagConstraints gbc_rdbtnVoraz;
	private JRadioButton rdbtnA;
	private GridBagConstraints gbc_rdbtnA;
	private JButton btnResolver;
	private GridBagConstraints gbc_btnResolver;
	private ButtonGroup bg;
	private Celda[][] laberinto;
	private ElegirEstrategia self;

	/**
	 * Create the frame.
	 * 
	 * @param objetivo
	 * @param inicio
	 * @param laberinto
	 */
	public ElegirEstrategia(Celda[][] laberinto) {

		self = this;
		this.laberinto = laberinto;

		setResizable(false);
		setTitle("Elegir Estrategia");
		setLocationRelativeTo(null);

		bg = new ButtonGroup();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblEstrategia = new JLabel("Elige una estrategia:");
		contentPane.add(lblEstrategia, BorderLayout.NORTH);

		panel = new JPanel();
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.add(panel, BorderLayout.CENTER);
		gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 89, 89, 89, 0 };
		gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		rdbtnAnchura = new JRadioButton("Anchura");
		rdbtnAnchura.setActionCommand("Anchura");
		rdbtnAnchura.setSelected(true);
		gbc_rdbtnAnchura = new GridBagConstraints();
		gbc_rdbtnAnchura.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAnchura.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAnchura.gridx = 1;
		gbc_rdbtnAnchura.gridy = 1;
		panel.add(rdbtnAnchura, gbc_rdbtnAnchura);

		rdbtnProfundidad = new JRadioButton("Profundidad Acotada");
		rdbtnProfundidad.setActionCommand("Profundidad Acotada");
		gbc_rdbtnProfundidad = new GridBagConstraints();
		gbc_rdbtnProfundidad.anchor = GridBagConstraints.WEST;
		gbc_rdbtnProfundidad.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnProfundidad.gridx = 1;
		gbc_rdbtnProfundidad.gridy = 2;
		panel.add(rdbtnProfundidad, gbc_rdbtnProfundidad);

		rdbtnCosteUniforme = new JRadioButton("Coste uniforme");
		rdbtnCosteUniforme.setActionCommand("Coste uniforme");
		gbc_rdbtnCosteUniforme = new GridBagConstraints();
		gbc_rdbtnCosteUniforme.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCosteUniforme.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCosteUniforme.gridx = 1;
		gbc_rdbtnCosteUniforme.gridy = 3;
		panel.add(rdbtnCosteUniforme, gbc_rdbtnCosteUniforme);

		rdbtnVoraz = new JRadioButton("Voraz");
		rdbtnVoraz.setActionCommand("Voraz");
		gbc_rdbtnVoraz = new GridBagConstraints();
		gbc_rdbtnVoraz.anchor = GridBagConstraints.WEST;
		gbc_rdbtnVoraz.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnVoraz.gridx = 1;
		gbc_rdbtnVoraz.gridy = 4;
		panel.add(rdbtnVoraz, gbc_rdbtnVoraz);

		rdbtnA = new JRadioButton("A*");
		rdbtnA.setActionCommand("A*");

		gbc_rdbtnA = new GridBagConstraints();
		gbc_rdbtnA.anchor = GridBagConstraints.WEST;
		gbc_rdbtnA.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnA.gridx = 1;
		gbc_rdbtnA.gridy = 5;
		panel.add(rdbtnA, gbc_rdbtnA);

		btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new BtnResolverActionListener());
		gbc_btnResolver = new GridBagConstraints();
		gbc_btnResolver.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnResolver.gridx = 2;
		gbc_btnResolver.gridy = 6;
		panel.add(btnResolver, gbc_btnResolver);

		bg.add(rdbtnA);
		bg.add(rdbtnAnchura);
		bg.add(rdbtnCosteUniforme);
		bg.add(rdbtnProfundidad);
		bg.add(rdbtnVoraz);
	}

	private class BtnResolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Celda inicio = laberinto[0][0];
			Celda objetivo = laberinto[laberinto.length - 1][laberinto[0].length - 1];

			String estrategia = escogerEstrategia();
			System.out.println(estrategia);
			ProblemaSalirLaberinto.SalirLaberinto(laberinto, inicio, objetivo);
			Formas formaFinal = new Formas(laberinto);
			formaFinal.setVisible(true);
			self.dispose();

		}

		private String escogerEstrategia() {
			String eleccion = "";
			switch (bg.getSelection().getActionCommand()) {
			case "Anchura":
				return "BREADTH";

			case "Profundidad Acotada":
				return "DEPTH";

			case "Coste uniforme":
				return "UNIFORM";

			case "Voraz":
				return "GREEDY";

			case "A*":
				return "A";

			}
			return eleccion;

		}
	}

}
