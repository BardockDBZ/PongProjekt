package gui;

import javax.swing.JFrame;

import actions.KeyHandler;
import game.GameLogic;

public class Classik {
	
	private static int screenwidth;
	private static int screenheight;
	private static JFrame Pongframe;
	
	public Classik(GameLogic spiellogik) { 	// KeyHandler -> infos
		setScreenwidth(800);
		setScreenheight(600);
		
		spiellogik.screenwidth = screenwidth;
		spiellogik.screenheight = getScreenheight();
		
		Pongframe = new JFrame();
		Pongframe.setSize(screenwidth, getScreenheight());
		Pongframe.setTitle("Pong");
		Pongframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Pongframe.setLayout(null);
		Pongframe.setResizable(false);
		Pongframe.setLocationRelativeTo(null);
		Pongframe.addKeyListener(new KeyHandler(spiellogik));
		Pongframe.requestFocus();
		
		Draw lbldraw = new Draw(spiellogik, screenwidth, getScreenheight());
		lbldraw.setBounds(0,0, screenwidth, getScreenheight());
		lbldraw.setVisible(true);
		Pongframe.add(lbldraw);
		
		Pongframe.setVisible(true);
	}

	public static void Classikclose() { //Pong Schließen
		if(Pongframe != null) {
			Pongframe.dispose();
		}
	}

	public static int getScreenwidth() {
		return screenwidth;
	}

	public static void setScreenwidth(int screenwidth) {
		Classik.screenwidth = screenwidth;
	}

	public static int getScreenheight() {
		return screenheight;
	}

	public static void setScreenheight(int screenheight) {
		Classik.screenheight = screenheight;
	}

	
}
