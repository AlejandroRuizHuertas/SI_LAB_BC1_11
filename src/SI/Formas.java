package SI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Formas extends JFrame {

	private JPanel contentPane;
	private Celda[][] laberinto;

	/**
	 * Pintar pinta los cuadrados pero no los pinta bien, tengo que ver por qué. Estoy
	 * metiendo un laberinto pintado a mano a ver si lo pinta. Si lo pongo todo en false, 
	 * me pinta la cuadrícula.
	 * 
	 *  _ _ _ _
	 * | |    _|
	 * | | |   |
	 * |_ _|_|_|
	 */
	public static void main(String[] args) {
		Celda[][] laberinto = new Celda[3][5];

		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				
				Celda c = new Celda(i, j);

				laberinto[i][j] = c;
			}
		}
		
		Formas frame = new Formas(laberinto);
		frame.setVisible(true);

	}

	/*laberinto[0][0].setVecinos(new boolean[]{true, true, false, false});
	laberinto[0][1].setVecinos(new boolean[]{false, true, true, false});
	laberinto[0][2].setVecinos(new boolean[]{false, false, true, false});
	laberinto[1][0].setVecinos(new boolean[]{false, true, false, false});
	laberinto[1][1].setVecinos(new boolean[]{true, false, true, false});
	laberinto[1][2].setVecinos(new boolean[]{true, false, false, true});
	laberinto[2][0].setVecinos(new boolean[]{false, true, true, true});
	laberinto[2][1].setVecinos(new boolean[]{true, true, true, false});
	laberinto[2][2].setVecinos(new boolean[]{true, false, false, false});
	laberinto[3][0].setVecinos(new boolean[]{false, false, false, true});
	laberinto[3][1].setVecinos(new boolean[]{false, false, true, true});
	laberinto[3][2].setVecinos(new boolean[]{true, false, false, false});*/

	/**
	 * Create the frame.
	 */
	public Formas(Celda[][] lab) {
		this.laberinto = lab;
		this.setBounds(0, 0, 600, 400);
		this.setLocationRelativeTo(null);

	}

	public void paint(Graphics g) {

		int grosor = 40;
		int inicio = 50;

		super.paint(g);
		g.setColor(Color.black);
		int x1;
		int y1;
		int x2;
		int y2;

		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				Celda c = laberinto[i][j];
				// Si tiene pared al norte
				if (!c.getVecinos()[0]) {
					x1 = inicio + c.getColumna() * grosor;
					y1 = inicio + c.getFila() * grosor;
					x2 = x1 + grosor;
					y2 = y1;

					g.drawLine(x1, y1, x2, y2);
				}
				// Si tiene pared al este
				if (!c.getVecinos()[1]) {
					x1 = inicio + grosor + c.getColumna() * grosor;
					y1 = inicio + c.getFila() * grosor;
					x2 = x1;
					y2 = y1 + grosor;
					g.drawLine(x1, y1, x2, y2);
				}
				// Si tiene pared al sur
				if (!c.getVecinos()[2]) {
					x1 = inicio + c.getColumna() * grosor;
					y1 = inicio + grosor + c.getFila() * grosor;
					x2 = x1 + grosor;
					y2 = y1;
					
					g.drawLine(x1, y1, x2, y2);
				}
				// Si tiene pared al oeste
				if (!c.getVecinos()[3]) {
					x1 = inicio + c.getColumna() * grosor;
					y1 = inicio + c.getFila() * grosor;
					x2 = x1;
					y2 = y1 + grosor;
					g.drawLine(x1, y1, x2, y2);
				}
			}
		}

	}

}
