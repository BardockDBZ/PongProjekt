package gameObjects;

import java.awt.event.ActionEvent;
import java.security.PublicKey;

import game.GameLogic;
import game.GameObject;
import gui.Classic;
import gui.StartScreen;

public class BeweglichesRechteck extends GameObject {

	public byte richtung;
	public int schritteInGleicherRichtung;
	private static int  diameter = 27;

	private final int maxgeschwindigkeit = 3;
	private static int GegenerPunkte = 0;
	private static int SpielerPunkte = 0;
	private  int xGeschwindigkeit = 2, yGeschwindigkeit = 2;
	final int minYGeschwindigkeit = 1;

	public BeweglichesRechteck(int posX, int posY, int breite, int hoehe) {
		super(posX, posY, breite, hoehe);
		schritteInGleicherRichtung = 0;
	}

	public void actionPerformed(ActionEvent e) {
		start();
		if(GameLogic.getCounter() > 750) {
			move();
			collisionwithwall();
			collisionWithPaddle();
			if(GameLogic.getSpiel() == 0) {
				Punktesystem();
				Gewonnen();
			}else if(GameLogic.getSpiel() == 1) {
				EndlessPunkte();
				EndlessVerloren();
			}
		}

	}

	public void move() {

		positionX += xGeschwindigkeit;
		positionY += yGeschwindigkeit;

	}
	public void start() {
		
		if(GameLogic.getCounter() ==0) {
			System.out.println("ACHTUNG");
		}else if(GameLogic.getCounter() ==250 ) {
			System.out.println("FERTIG");
		}else if(GameLogic.getCounter() ==500) {
			System.out.println("LOS");
		}else if(GameLogic.getCounter()== 750 ) {
			
		}
	
	}
	public void collisionwithwall() {
		if (positionX <= 0 || positionX >= Classic.getScreenwidth() - diameter) {
			xGeschwindigkeit = -xGeschwindigkeit;
			positionX = Math.max(0, Math.min(positionX, Classic.getScreenwidth() - diameter));
		}
		if (positionY <= 0 || positionY >= Classic.getScreenheight() - (diameter + 30)) {
			yGeschwindigkeit = -yGeschwindigkeit;
			positionY = Math.max(0, Math.min(positionY, Classic.getScreenheight() - (diameter + 30)));
		}
	}

	public void collisionWithPaddle() {
		BeweglichesRechteck spielerPaddle = GameLogic.getRechteckSpieler();
		BeweglichesRechteck gegnerPaddle = GameLogic.getRechteckGegner();
		BeweglichesRechteck sicherheitSpiele = GameLogic.getSicherheit();

		if(this.positionX <= sicherheitSpiele.positionX + sicherheitSpiele.groesseX &&
				this.positionX + this.groesseX >= sicherheitSpiele.positionX &&
				this.positionY <= sicherheitSpiele.positionY + sicherheitSpiele.groesseY &&
				this.positionY + this.groesseY >= sicherheitSpiele.positionY) {

		}else {
			if (this.positionX <= spielerPaddle.positionX + spielerPaddle.groesseX &&
					this.positionX + this.groesseX >= spielerPaddle.positionX &&
					this.positionY <= spielerPaddle.positionY + spielerPaddle.groesseY &&
					this.positionY + this.groesseY >= spielerPaddle.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (spielerPaddle.positionY + spielerPaddle.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));
				}

			}

			if (this.positionX <= gegnerPaddle.positionX + gegnerPaddle.groesseX &&
					this.positionX + this.groesseX >= gegnerPaddle.positionX &&
					this.positionY <= gegnerPaddle.positionY + gegnerPaddle.groesseY &&
					this.positionY + this.groesseY >= gegnerPaddle.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (gegnerPaddle.positionY + gegnerPaddle.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}

			}
			if(yGeschwindigkeit == 0) {				//wenn alles schief geht und y trotzdem 0 ist 
				yGeschwindigkeit += 1;
			}
		}
	}
	public static void GegnerKI() {
		BeweglichesRechteck ball = GameLogic.getBall();
		BeweglichesRechteck gegnerPaddle = GameLogic.getRechteckGegner();

		if (ball.positionY + ball.groesseY / 2 > gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
			gegnerPaddle.positionY += 2; // Paddle nach unten bewegen
		} else if (ball.positionY + ball.groesseY / 2 < gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
			gegnerPaddle.positionY -= 2; // Paddle nach oben bewegen
		}

		// Sicherstellen, dass das Paddle nicht aus dem Bildschirmbereich bewegt wird
		gegnerPaddle.positionY = Math.max(0, Math.min(gegnerPaddle.positionY, Classic.getScreenheight() - gegnerPaddle.groesseY));
	}

	public void Punktesystem() {

		if (positionX == 0) {
			setGegenerPunkte(GegenerPunkte + 1);  
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			yGeschwindigkeit = -yGeschwindigkeit;
			System.out.println("Gegner hat : " + getGegenerPunkte());
		}
		if(positionX ==  Classic.getScreenwidth() - diameter ) {
			setSpielerPunkte(SpielerPunkte + 1);   
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			yGeschwindigkeit = -yGeschwindigkeit;
			System.out.println("Spieler hat : " + getSpielerPunkte());
		}
	}
	public void Gewonnen() {
		if (getGegenerPunkte() == 10 ) {
			setGegenerPunkte(0);
			setSpielerPunkte(0);
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			xGeschwindigkeit = 0;
			yGeschwindigkeit = 0;
			System.out.println("Gegner hat gewonnen" );
			GameLogic.instance.stopGameTimer();
		}else if(getSpielerPunkte() == 10) {
			setGegenerPunkte(0);
			setSpielerPunkte(0);
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			xGeschwindigkeit = 0;
			yGeschwindigkeit = 0;
			System.out.println("Spieler hat gewonnen" );
			Classic.Classicclose();
			GameLogic.instance.stopGameTimer();
		}
	}
	public void EndlessPunkte() {

		if(positionX ==  Classic.getScreenwidth() - diameter ) {
			setSpielerPunkte(SpielerPunkte + 1);   
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			yGeschwindigkeit = -yGeschwindigkeit;
			System.out.println("Spieler hat : " + getSpielerPunkte());

		}
	}
	public void EndlessVerloren() {
		if(GameLogic.getCounter() < 1) {

		}else {
			if (positionX == 0) {
				setGegenerPunkte(0);
				setSpielerPunkte(0);
				positionX = GameLogic.getX();
				positionY = GameLogic.getY();
				xGeschwindigkeit = 0;
				yGeschwindigkeit = 0;
				System.out.println("Verloren" );
				GameLogic.instance.stopGameTimer();
				

			}
		}
	}


	public int getGegenerPunkte() {
		return GegenerPunkte;
	}

	public static void setGegenerPunkte(int gegenerPunkte) {
		GegenerPunkte = gegenerPunkte;
	}

	public int getSpielerPunkte() {
		return SpielerPunkte;
	}

	public static void setSpielerPunkte(int spielerPunkte) {
		SpielerPunkte = spielerPunkte;
	}
}
