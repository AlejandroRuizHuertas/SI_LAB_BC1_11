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

import SI.Nodo;
import SI.ProblemaSalirLaberinto;
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


	/**
	 * Create the frame.
	 */
	public Formas(Celda[][] lab) {
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

				rellenarCelda(c, inicio, grosor, g, Color.white, g2d);

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
			g2d.dispose();
			File file = new File("milaberinto.png");
			try {
				ImageIO.write(bufferedImage, "png", file);
				Wilson.generarJson(laberinto);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private class BtnResolverActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Ale, resuelto");
			ProblemaSalirLaberinto.SalirLaberinto(laberinto, null, null);
		}
	}
}