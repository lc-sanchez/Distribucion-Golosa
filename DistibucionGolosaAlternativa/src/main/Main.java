package main;
import java.awt.EventQueue;
import java.io.IOException;

import view.Ventana_Principal;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
			EventQueue.invokeLater(new Runnable() {
				
				// Se crea la ventana principal
				Ventana_Principal view = new Ventana_Principal();
				
				public void run() {
					try {
						view.initialize();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		});
	}


}


