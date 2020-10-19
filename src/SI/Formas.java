package SI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Formas extends JFrame {

	private JPanel contentPane;
	private Celda[][] laberinto;


	/*public static void main(String[] args) {
		Celda[][] laberinto = new Celda[8][8];

		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {

				Celda c = new Celda(i, j);

				laberinto[i][j] = c;
			}
		}

		Formas frame = new Formas(laberinto);
		frame.setVisible(true);

	}*/


	/**
	 * Create the frame.
	 */
	public Formas(Celda[][] lab) {
		setResizable(false);
		this.laberinto = lab;
		this.setBounds(0, 0, 400, 400);
		this.setLocationRelativeTo(null);
		
		JButton btnGuardar = new JButton("Guardar");
		getContentPane().add(btnGuardar, BorderLayout.SOUTH);

	}

	public void paint(Graphics g) {
		super.paint(g);
		int grosor = elegirGrosorMax();
		int inicio = 40;
		
		

		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				Celda c = laberinto[i][j];

				rellenarCelda(c, inicio, grosor, g, Color.white);

				dibujarParedes(c, inicio, grosor, g);

			}
		}

	}

	private int elegirGrosorMax() {
		if (laberinto.length > laberinto[0].length) return 320/laberinto.length;
		else return 320/laberinto[0].length;
	}

	private void dibujarParedes(Celda c, int inicio, int grosor, Graphics g) {
		int x1;
		int y1;
		int x2;
		int y2;
		g.setColor(Color.black);
		// Si tiene pared al norte
		if (!c.getVecinos()[0]) {
			x1 = inicio + c.getColumna() * grosor;
			y1 = inicio + c.getFila() * grosor;
			x2 = x1 + grosor;
			y2 = y1;
			g.setColor(Color.black);
			g.drawLine(x1, y1, x2, y2);
		}
		// Si tiene pared al este
		if (!c.getVecinos()[1]) {
			x1 = inicio + grosor + c.getColumna() * grosor;
			y1 = inicio + c.getFila() * grosor;
			x2 = x1;
			y2 = y1 + grosor;
			g.setColor(Color.black);
			g.drawLine(x1, y1, x2, y2);
		}
		// Si tiene pared al sur
		if (!c.getVecinos()[2]) {
			x1 = inicio + c.getColumna() * grosor;
			y1 = inicio + grosor + c.getFila() * grosor;
			x2 = x1 + grosor;
			y2 = y1;
			g.setColor(Color.black);

			g.drawLine(x1, y1, x2, y2);
		}
		// Si tiene pared al oeste
		if (!c.getVecinos()[3]) {
			x1 = inicio + c.getColumna() * grosor;
			y1 = inicio + c.getFila() * grosor;
			x2 = x1;
			y2 = y1 + grosor;
			g.setColor(Color.black);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private void rellenarCelda(Celda c, int inicio, int grosor, Graphics g, Color color) {
		int x1;
		int y1;

		g.setColor(color);
		x1 = inicio + c.getColumna() * grosor;
		y1 = inicio + c.getFila() * grosor;
		g.fillRect(x1, y1, grosor, grosor);
	}

}
