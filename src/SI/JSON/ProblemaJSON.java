package SI.JSON;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import SI.Wilson.Wilson;

public class ProblemaJSON {
	private String Inicial;
	private String Objetivo;
	private String Maze;

	public ProblemaJSON(String inicial, String objetivo, String maze) {
		Inicial = inicial;
		Objetivo = objetivo;
		Maze = maze;
	}
	
	

	public String getInicial() {
		return Inicial;
	}

	public void setInicial(String inicial) {
		Inicial = inicial;
	}

	public String getObjetivo() {
		return Objetivo;
	}

	public void setObjetivo(String objetivo) {
		Objetivo = objetivo;
	}

	public String getMaze() {
		return Maze;
	}

	public void setMaze(String maze) {
		Maze = maze;
	}
	public static void generarJSONProblema(int filas, int columnas) {
		String id_ini = "(0, 0)";
		String id_fin = "(" + (filas-1) + ", " + (columnas-1) + ")";
		
		String txt = "problema_" + filas + "x" + columnas + "_maze.json";
		Gson gson = new Gson();
		ProblemaJSON problemaJSON = new ProblemaJSON(id_ini, id_fin, txt);
		
		String j = gson.toJson(problemaJSON);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("problema_"+filas+"x"+columnas+".json"))) {
			bw.write(j);
			System.out.println("Problema creado");
		} catch (IOException ex) {
			Logger.getLogger(ProblemaJSON.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
