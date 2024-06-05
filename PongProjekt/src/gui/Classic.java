package gui;

import javax.swing.JFrame;

import actions.KeyHandler;
import game.GameLogic;

public class Classic {
	
	private static int screenwidth;
	private static int screenheight;
	private static JFrame Pongframe;
	
	public Classic(GameLogic spiellogik) { 	// KeyHandler -> infos
		
		screenwidth = 800;
		screenheight = 600;
		spiellogik.screenwidth = screenwidth;
		spiellogik.screenheight = screenheight;
		
		Pongframe = new JFrame();
		Pongframe.setSize(screenwidth, screenheight);
		Pongframe.setTitle("Pong");
		Pongframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Pongframe.setLayout(null);
		Pongframe.setResizable(false);
		Pongframe.setLocationRelativeTo(null);
		Pongframe.addKeyListener(new KeyHandler(spiellogik));
		Pongframe.requestFocus();
		
		Draw lbldraw = new Draw(spiellogik, screenwidth, screenheight);
		lbldraw.setBounds(0,0, screenwidth, screenheight);
		lbldraw.setVisible(true);
		Pongframe.add(lbldraw);
		
		Pongframe.setVisible(true);
	}

	public static void Classicclose() { //Pong Schließen
		if(Pongframe != null) {
			Pongframe.dispose();
		}
	}

	public static int getScreenwidth() {
		return screenwidth;
	}

	public static int getScreenheight() {
		return screenheight;
	}



}
