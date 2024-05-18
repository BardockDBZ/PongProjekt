package gui;

import javax.swing.JFrame;

import actions.KeyHandler;
import game.GameLogic;

public class EndlessRunner {
									//Selbe wie beim Pong
	private int screenwidth = 800;
	private int screenheight = 600;
	private static JFrame Pongframe;//Name des Frames zum erstellem
	
	public EndlessRunner(GameLogic spiellogik) {     //GameLogic spiellogik WICHTIG!!! glaub ich zumindest (würde halt nix fumktionieren) Findez man in der Classe KeyHandler
		
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

	public static void EndlessRunnerclose() {	//Zum schließen
		if(Pongframe != null) {
			Pongframe.dispose();
		}
	}

}
