package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import actions.KeyHandler;
import actions.Main;
import game.GameLogic;
import game.GameObject;
import gameObjects.BeweglichesRechteck;

public class Classic {

	private static int screenwidth = 800;
	private static int screenheight = 600;
	private static JFrame Pongframe;

	public Classic(GameLogic spiellogik) { // KeyHandler -> infos

		Pongframe = new JFrame();
		Pongframe.setSize(screenwidth, screenheight);
		Pongframe.setTitle("Pong");
		Pongframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Pongframe.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				beenden();
		
			}
		});

		Pongframe.setLayout(null);
		Pongframe.setResizable(false);
		Pongframe.setLocationRelativeTo(null);
		Pongframe.addKeyListener(new KeyHandler(spiellogik));
		Pongframe.requestFocus();
		Draw lbldraw = new Draw(spiellogik, screenwidth, screenheight);
		lbldraw.setBounds(0, 0, screenwidth, screenheight);
		lbldraw.setVisible(true);
		Pongframe.add(lbldraw);
		JLabel pointsLabel = new JLabel("Points:" + BeweglichesRechteck.SpielerPunkte); 
		pointsLabel.setBounds(215, 200, 200, 69);
		pointsLabel.setFont(Main.KnightWarriors);
		Pongframe.setVisible(true);
	}

	public static void Classicclose() { // Pong Schließen
		if (Pongframe != null) {
			Pongframe.dispose();
		}
	}

	public static int getScreenwidth() {
		return screenwidth;
	}

	public static int getScreenheight() {
		return screenheight;
	}

	public static void beenden() {
		BeweglichesRechteck.setGegenerPunkte(0);
		BeweglichesRechteck.setSpielerPunkte(0);
		StartScreen.FrameErstellen();
		Classic.Classicclose();
		GameLogic.instance.stopGameTimer();
	}

	public static void setScreenwidth(int screenwidth) {
		Classic.screenwidth = screenwidth;
	}

	public static void setScreenheight(int screenheight) {
		Classic.screenheight = screenheight;
	}

}
