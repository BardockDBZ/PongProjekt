package gameObjects;

import java.awt.event.ActionEvent;

import game.GameLogic;
import game.GameObject;
import gui.Classic;
import gui.StartScreen;

public class BeweglichesRechteck extends GameObject {
	
	public byte richtung;
	public int schritteInGleicherRichtung;
	private static int  diameter = 27;
	
	private final int maxgeschwindigkeit = 3;
	private int GegenerPunkte = 0;
	private int SpielerPunkte = 0;
	private  int xGeschwindigkeit = 2, yGeschwindigkeit = 2;

	public BeweglichesRechteck(int posX, int posY, int breite, int hoehe) {
		super(posX, posY, breite, hoehe);
		schritteInGleicherRichtung = 0;
	}
	
	public void actionPerformed(ActionEvent e) {
		move();
		
		collisionwithwall();
		collisionWithPaddle();
		Punktesystem();
		Gewonnen();
	}

	public void move() {
		positionX += xGeschwindigkeit;
		positionY += yGeschwindigkeit;
	}

	public void collisionwithwall() {
		if (positionX <= 0 || positionX >= Classic.getScreenwidth() - diameter) {
			xGeschwindigkeit = (-(xGeschwindigkeit));
			positionX = Math.max(0, Math.min(positionX, Classic.getScreenwidth() - diameter));
		}
		if (positionY <= 0 || positionY >= Classic.getScreenheight() - (diameter + 30)) {
			yGeschwindigkeit = (-(yGeschwindigkeit));
			 positionY = Math.max(0, Math.min(positionY, Classic.getScreenheight() - (diameter + 30)));
		}
	}

	public void collisionWithPaddle() {
		BeweglichesRechteck spielerPaddle = GameLogic.getRechteckSpieler();
		BeweglichesRechteck gegnerPaddle = GameLogic.getRechteckGegener();

		//  Kollusion mit Paddle 
		if (this.positionX <= spielerPaddle.positionX + spielerPaddle.groesseX &&
		    this.positionX + this.groesseX >= spielerPaddle.positionX &&
		    this.positionY <= spielerPaddle.positionY + spielerPaddle.groesseY &&
		    this.positionY + this.groesseY >= spielerPaddle.positionY) {
			xGeschwindigkeit =(-xGeschwindigkeit);
			yGeschwindigkeit = (yGeschwindigkeit += (this.positionY - (spielerPaddle.positionY + spielerPaddle.groesseY / 2)) / 10);			
			 yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));
		}

		// Check collision with opponent paddle
		if (this.positionX <= gegnerPaddle.positionX + gegnerPaddle.groesseX &&
		    this.positionX + this.groesseX >= gegnerPaddle.positionX &&
		    this.positionY <= gegnerPaddle.positionY + gegnerPaddle.groesseY &&
		    this.positionY + this.groesseY >= gegnerPaddle.positionY) {
			xGeschwindigkeit =(-(xGeschwindigkeit));
			yGeschwindigkeit += (this.positionY - (gegnerPaddle.positionY + gegnerPaddle.groesseY / 2)) / 10;
			yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));
		}
		
	}
	public static void GegnerKI() {
	    BeweglichesRechteck ball = GameLogic.getBall();
	    BeweglichesRechteck gegnerPaddle = GameLogic.getRechteckGegener();
	    
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
			StartScreen.FrameErstellen();
			Classic.Classikclose();
			GameLogic.instance.stopGameTimer();
		}else if(getSpielerPunkte() == 10) {
			setGegenerPunkte(0);
			setSpielerPunkte(0);
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			xGeschwindigkeit = 0;
			yGeschwindigkeit = 0;
			System.out.println("Spieler hat gewonnen" );
			Classic.Classikclose();
			StartScreen.FrameErstellen();
			GameLogic.instance.stopGameTimer();
		}
		
	}

	public int getGegenerPunkte() {
		return GegenerPunkte;
	}

	public void setGegenerPunkte(int gegenerPunkte) {
		GegenerPunkte = gegenerPunkte;
	}

	public int getSpielerPunkte() {
		return SpielerPunkte;
	}

	public void setSpielerPunkte(int spielerPunkte) {
		SpielerPunkte = spielerPunkte;
	}

	
}
