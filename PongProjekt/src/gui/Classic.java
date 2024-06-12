package gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import actions.KeyHandler;
import actions.Main;
import game.GameLogic;
import game.GameObject;
import gameObjects.BeweglichesRechteck;
import java.awt.Font;

public class Classic {
	private static JLabel pointsLabel;
	private static JLabel pointsLabel1;
	private static JLabel Counter1;
	private static JLabel Counter2;
	private static JLabel Counter3;
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

		Pongframe.getContentPane().setLayout(null);
		Pongframe.setResizable(false);
		Pongframe.setLocationRelativeTo(null);
		Pongframe.addKeyListener(new KeyHandler(spiellogik));
		Pongframe.requestFocus();
		Draw lbldraw = new Draw(spiellogik, screenwidth, screenheight);
		lbldraw.setBounds(0, 0, screenwidth, screenheight);
		lbldraw.setVisible(true);
		
		pointsLabel = new JLabel("" + BeweglichesRechteck.getSpielerPunkte()); 
		pointsLabel.setBounds(279, 48, 200, 69);
		pointsLabel.setFont(Main.KnightWarriors.deriveFont(70F));
		pointsLabel.setVisible(true);
		pointsLabel.setForeground(Color.white);
		pointsLabel1 = new JLabel("" + BeweglichesRechteck.getGegnerPunkte());
		pointsLabel1.setBounds(489,48,200,69);;
		pointsLabel1.setFont(Main.KnightWarriors.deriveFont(70F));
		pointsLabel1.setVisible(true);
		pointsLabel1.setForeground(Color.white);
		Counter1 = new JLabel("ACHTUNG");
		Counter1.setBounds(293,433,200,69);
		Counter1.setFont(Main.KnightWarriors.deriveFont(60F));
		Counter1.setVisible(true);
		Counter1.setForeground(Color.white);
		Counter2 = new JLabel("FERTIG");
		Counter2.setBounds(293,433,200,69);
		Counter2.setFont(Main.KnightWarriors.deriveFont(60F));
		Counter2.setVisible(false);
		Counter2.setForeground(Color.white);
		Counter3 = new JLabel("LOS");
		Counter3.setBounds(293,433,200,69);
		Counter3.setFont(Main.KnightWarriors.deriveFont(60F));
		Counter3.setVisible(false);
		Counter3.setForeground(Color.white);
		Pongframe.getContentPane().add(pointsLabel);
		Pongframe.getContentPane().add(pointsLabel1);
		Pongframe.getContentPane().add(Counter1);
		Pongframe.getContentPane().add(Counter2);
		Pongframe.getContentPane().add(Counter3);
		Pongframe.getContentPane().add(lbldraw);
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
	public static void RefreshPoints() {
		pointsLabel.setText("" + BeweglichesRechteck.getSpielerPunkte());
		pointsLabel1.setText("" + BeweglichesRechteck.getGegnerPunkte());
	}
	public static void RefreshRate() {
		
		if(GameLogic.getCounter() ==1) {
			Counter1.setText("ACHTUNG");
		}else if(GameLogic.getCounter() ==250  ) {
			Counter2.setText("FERTIG");
			Counter2.setVisible(true);
			Counter1.setVisible(false);
		}else if(GameLogic.getCounter() ==500) {
			Counter3.setText("LOS");
			Counter2.setVisible(false);
			Counter3.setVisible(true);
		}else if(GameLogic.getCounter()== 750 ) {
			Counter3.setVisible(false);
		}	
		
	}
}
