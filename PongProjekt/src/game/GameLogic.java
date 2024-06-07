package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import gameObjects.BeweglichesRechteck;
import gui.Classic;
import gui.Draw;

public class GameLogic {

	private Timer gameTimer;
	private static int screenwidth = Classic.getScreenwidth() ;
	private static int screenheight = Classic.getScreenheight();
	public ArrayList<GameObject> spielObjekte;

	public boolean keyLeftarrowpressed;
	public boolean keyRightarrowpressed;
	public boolean keyUparrowpressed;
	public boolean keyDownarrowpressed;
	public boolean keyDownSarrowpressed;
	public boolean keyUPWarrowpressed;
	private static BeweglichesRechteck rechteckSpieler;
	private static BeweglichesRechteck ball;
	private static BeweglichesRechteck rechteckgegner;
	private static BeweglichesRechteck sicherheit;
	private int breite = 5;
	private int hoehe = 100;
	public static GameLogic instance;
	private static int Spiel = -1;
	private static int counter = 0;
	private static int GeschwindigkeitSpieler = 3;
	private static int GeschwindigkeitGegner = 3;
	private static int GeschwindigkeitBall = 2;

	public GameLogic() {

		instance = this;
		gameTimer = new Timer();
		spielObjekte = new ArrayList<GameObject>();

		keyLeftarrowpressed = false;
		keyRightarrowpressed = false;
		keyUparrowpressed = false;
		keyDownarrowpressed = false;

		ball = new BeweglichesRechteck(getX(), getY(), 20, 20);
		spielObjekte.add(ball);
		
		ball.richtung = 0;

		rechteckSpieler = new BeweglichesRechteck(75, 250, breite, hoehe);
		spielObjekte.add(rechteckSpieler);
		rechteckgegner = new BeweglichesRechteck(705, 300, breite, hoehe);
		spielObjekte.add(rechteckgegner);
		sicherheit = new BeweglichesRechteck(75, 298, breite - 3, hoehe + 2);
		if(Spiel == 0 || Spiel == 1) {	
			gameTimer.scheduleAtFixedRate(new TimerTask(){
				@Override
				public void run() {
					ball.actionPerformed(null);
					counter++;
					
					if(GameLogic.getCounter() <= 750) {

					}else {
						if (keyUparrowpressed) {
							if (rechteckSpieler.positionY >= 3) {
								rechteckSpieler.positionY -= GeschwindigkeitSpieler;
								sicherheit.positionY  -= GeschwindigkeitSpieler;
							}
						} else if (keyDownarrowpressed) {
							if (rechteckSpieler.positionY <= 465) {
								rechteckSpieler.positionY += GeschwindigkeitSpieler;
								sicherheit.positionY += GeschwindigkeitSpieler;
							}
						} else if (keyUPWarrowpressed) {
							if (rechteckSpieler.positionY >= 3) {
								rechteckSpieler.positionY -= GeschwindigkeitSpieler;
								sicherheit.positionY -= GeschwindigkeitSpieler;
							}
						} else if (keyDownSarrowpressed) {
							if (rechteckSpieler.positionY <= 465) {
								rechteckSpieler.positionY += GeschwindigkeitSpieler;
								sicherheit.positionY += GeschwindigkeitSpieler;
							}
						}

					}
					BeweglichesRechteck.GegnerKI();
				}
			}, 0, 5);
		}else {

		}
	}
	public void Geschwindigkeit(int Ball, int Spieler, int Gegner) {
		GeschwindigkeitBall = Ball;
		GeschwindigkeitGegner = Gegner;
		GeschwindigkeitSpieler = Spieler;
	}
	public void stopGameTimer() {
		gameTimer.cancel();
		gameTimer.purge();

	}

	public static int getX() {
		return screenwidth/2;
	}

	public static int getY() {
		return screenheight/2;
	}

	public static BeweglichesRechteck getRechteckSpieler() {
		return rechteckSpieler;
	}
	public static void setRechteckSpieler(BeweglichesRechteck rechteckSpieler) {
		GameLogic.rechteckSpieler = rechteckSpieler;
	}

	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public static BeweglichesRechteck getRechteckGegner() {
		return rechteckgegner;
	}

	public static void setRechteckGegner(BeweglichesRechteck rechteckGegener) {
		GameLogic.rechteckgegner = rechteckGegener;
	}

	public static BeweglichesRechteck getBall() {
		return ball;
	}

	public static void setBall(BeweglichesRechteck ball) {
		GameLogic.ball = ball;
	}
	public static int getSpiel() {
		return Spiel;
	}
	public static void setSpiel(int spiel) {
		Spiel = spiel;
	}
	public static int getScreenheight() {
		return screenheight;
	}
	public static void setScreenheight(int screenheight) {
		GameLogic.screenheight = screenheight;
	}
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		GameLogic.counter = counter;
	}
	public static BeweglichesRechteck getSicherheit() {
		return sicherheit;
	}
	public static void setSicherheit(BeweglichesRechteck sicherheit) {
		GameLogic.sicherheit = sicherheit;
	}
	public static int getScreenwidth() {
		return screenwidth;
	}
	public static void setScreenwidth(int screenwidth) {
		GameLogic.screenwidth = screenwidth;
	}
	public static int getGeschwindigkeitSpieler() {
		return GeschwindigkeitSpieler;
	}
	public static void setGeschwindigkeitSpieler(int geschwindigkeitSpieler) {
		GeschwindigkeitSpieler = geschwindigkeitSpieler;
	}
	public static int getGeschwindigkeitGegner() {
		return GeschwindigkeitGegner;
	}
	public static void setGeschwindigkeitGegner(int geschwindigkeitGegner) {
		GeschwindigkeitGegner = geschwindigkeitGegner;
	}
	public static int getGeschwindigkeitBall() {
		return GeschwindigkeitBall;
	}
	public static void setGeschwindigkeitBall(int geschwindigkeitBall) {
		GeschwindigkeitBall = geschwindigkeitBall;
	}
}
