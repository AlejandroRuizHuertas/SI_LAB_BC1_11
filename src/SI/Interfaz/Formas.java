package SI.Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SI.ElegirEstrategia;
import SI.JSON.ProblemaJSON;
import SI.Problema.Nodo;
import SI.Problema.Busqueda;
import SI.Wilson.Celda;
import SI.Wilson.Wilson;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;

public class Formas extends JFrame {

	private JPanel contentPane;
	private Celda[][] laberinto;
	private Graphics2D g2d;
	private BufferedImage bufferedImage;
	private Formas self;
	private boolean resultado;
	private String estrategia;

	/**
	 * Create the frame.
	 */
	public Formas(Celda[][] lab, boolean resultado, String estrategia) {
		self = this;
		this.resultado = resultado;
		this.estrategia = estrategia;
		setResizable(false);
		this.laberinto = lab;
		this.setBounds(0, 0, 400, 400);
		this.setLocationRelativeTo(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		getContentPane().add(splitPane, BorderLayout.SOUTH);

		JButton btnGuardar = new JButton("Guardar laberinto");
		btnGuardar.addActionListener(new BtnGuardarActionListener());
		splitPane.setLeftComponent(btnGuardar);

		JButton btnResolver = new JButton("Resolver");

		if (resultado) {
			btnResolver.setEnabled(false);
		}
		btnResolver.addActionListener(new BtnResolverActionListener());
		splitPane.setRightComponent(btnResolver);

	}

	public void paint(Graphics g) {
		super.paint(g);
		int grosor = elegirGrosorMax();
		int inicio = 40;

		// Constructs a BufferedImage of one of the predefined image types.
		bufferedImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);

		// Create a graphics which can be used to draw into the buffered image
		g2d = bufferedImage.createGraphics();

		for (int i = 0; i < laberinto.length; i++) {
			for (int j = 0; j < laberinto[0].length; j++) {
				Celda c = laberinto[i][j];

				switch (c.getvalue()) {
				case 0:
					rellenarCelda(c, inicio, grosor, g, Color.white, g2d);
					break;
				case 1:
					rellenarCelda(c, inicio, grosor, g, new Color(223, 199, 167), g2d);
					break;
				case 2:
					rellenarCelda(c, inicio, grosor, g, new Color(202, 231, 193), g2d);
					break;
				case 3:
					rellenarCelda(c, inicio, grosor, g, new Color(128, 206, 225), g2d);
					break;
				case 4: // Si est� en el camino soluci�n
					rellenarCelda(c, inicio, grosor, g, Color.red, g2d);
					break;
				case 5: // Si est� en la frontera
					rellenarCelda(c, inicio, grosor, g, Color.blue, g2d);
					break;
				case 6: // Si est� visitado
					rellenarCelda(c, inicio, grosor, g, Color.green, g2d);
					break;
				}

				dibujarParedes(c, inicio, grosor, g, g2d);

			}
		}

	}

	private int elegirGrosorMax() {
		if (laberinto.length > laberinto[0].length)
			return 320 / laberinto.length;
		else
			return 320 / laberinto[0].length;
	}

	private void dibujarParedes(Celda c, int inicio, int grosor, Graphics g, Graphics2D g2d) {
		int x1;
		int y1;
		int x2;
		int y2;
		g.setColor(Color.black);
		g2d.setColor(Color.black);
		// Si tiene pared al norte
		if (!c.getneighbors()[0]) {
			x1 = inicio + c.getColumna() * grosor;
			y1 = inicio + c.getFila() * grosor;
			x2 = x1 + grosor;
			y2 = y1;

			g.drawLine(x1, y1, x2, y2);

			g2d.drawLine(x1, y1, x2, y2);

		}
		// Si tiene pared al este
		if (!c.getneighbors()[1]) {
			x1 = inicio + grosor + c.getColumna() * grosor;
			y1 = inicio + c.getFila() * grosor;
			x2 = x1;
			y2 = y1 + grosor;

			g.drawLine(x1, y1, x2, y2);
			g2d.drawLine(x1, y1, x2, y2);
		}
		// Si tiene pared al sur
		if (!c.getneighbors()[2]) {
			x1 = inicio + c.getColumna() * grosor;
			y1 = inicio + grosor + c.getFila() * grosor;
			x2 = x1 + grosor;
			y2 = y1;

			g.drawLine(x1, y1, x2, y2);

			g2d.drawLine(x1, y1, x2, y2);
		}
		// Si tiene pared al oeste
		if (!c.getneighbors()[3]) {
			x1 = inicio + c.getColumna() * grosor;
			y1 = inicio + c.getFila() * grosor;
			x2 = x1;
			y2 = y1 + grosor;
			// Dibujo la pared para el archivo
			g2d.drawLine(x1, y1, x2, y2);
			// Dibujo la pared para mostrarlo
			g.drawLine(x1, y1, x2, y2);
		}
	}

	private void rellenarCelda(Celda c, int inicio, int grosor, Graphics g, Color color, Graphics2D g2d) {
		int x1;
		int y1;

		x1 = inicio + c.getColumna() * grosor;
		y1 = inicio + c.getFila() * grosor;
		// Relleno la celda para la interfaz
		g.setColor(color);
		g.fillRect(x1, y1, grosor, grosor);
		// Relleno la celda para imprimir el archivo
		g2d.setColor(color);
		g2d.fillRect(x1, y1, grosor, grosor);
	}

	private class BtnGuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!resultado)
				guardarLaberinto();
			else
				guardarSolucion();

		}

		private void guardarSolucion() {
			File file;
			g2d.dispose();

			file = new File("solution_" + laberinto.length + "x" + laberinto[0].length + "_" + estrategia + "_20.png");

			try {
				ImageIO.write(bufferedImage, "png", file);
				Wilson.generarJson(laberinto);
				ProblemaJSON.generarJSONProblema(laberinto.length, laberinto[0].length);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		private void guardarLaberinto() {
			File file;
			g2d.dispose();
			file = new File("puzzle_loop_" + laberinto.length + "x" + laberinto[0].length + ".png");
			try {
				ImageIO.write(bufferedImage, "png", file);
				Wilson.generarJson(laberinto);
				ProblemaJSON.generarJSONProblema(laberinto.length, laberinto[0].length);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private class BtnResolverActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ElegirEstrategia frameElegirEstrategia = new ElegirEstrategia(laberinto);
			frameElegirEstrategia.setVisible(true);
			self.dispose();

		}
	}
}