package gameObjects;

import java.awt.event.ActionEvent;
import java.security.PublicKey;

import javax.swing.JLabel;


import game.GameLogic;
import game.GameObject;
import Shop.Inventar;
import gui.Classic;
import gui.Shop;
import gui.StartScreen;

public class BeweglichesRechteck extends GameObject {

	public byte richtung;
	public int schritteInGleicherRichtung;
	private static int  diameter = 27;
	private static int level = 0;
	private static int PunkteGewonen = -1;
	private final int maxgeschwindigkeit = 3;
	private static int GegenerPunkte = 0;
	public static int SpielerPunkte = 0;
	private  int xGeschwindigkeit = 1,
			     yGeschwindigkeit = 1;
	final int minYGeschwindigkeit = 1;

	private static int BruchPunkte1=0;
	private  static int BruchPunkte2=0;

	public BeweglichesRechteck(int posX, int posY, int breite, int hoehe) {
		super(posX, posY, breite, hoehe);
		schritteInGleicherRichtung = 0;
	}

	public void actionPerformed(ActionEvent e) {

		if(GameLogic.keypausearrowpressed) {

		}else {
			start();
			if(GameLogic.getSpiel() == 0) {
				Levelsystem();
				Punktesystem();
				Gewonnen();
			}else if(GameLogic.getSpiel() == 1) {
				EndlessRunnerSchwierigkeit();
				EndlessPunkte();
				EndlessVerloren();
			}else if(GameLogic.getSpiel() == 2) {
				Klassisch();
				Punktesystem();
				Gewonnen();
			}else if(GameLogic.getSpiel() == 3) {
				Klassisch();
				Punktesystem();
				Gewonnen();
			}
			if(GameLogic.getCounter() > 750) {
				move();
				collisionwithwall();
				collisionWithPaddle();
			}

		}
	}

	public void move() {

		positionX += xGeschwindigkeit;
		positionY += yGeschwindigkeit;

	}
	public void start() {

		if(GameLogic.getCounter() ==1) {
			Classic.RefreshRate();
		}else if(GameLogic.getCounter() ==250 ) {
			Classic.RefreshRate();
		}else if(GameLogic.getCounter() ==500) {
			Classic.RefreshRate();
		}else if(GameLogic.getCounter()== 750 ) {
			Classic.RefreshRate();
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
		BeweglichesRechteck sicherheitSpiele = GameLogic.getSicherheitSpieler();
		BeweglichesRechteck Stein = GameLogic.getStein();
		BeweglichesRechteck Stein2 = GameLogic.getStein2();
		BeweglichesRechteck Stein3 = GameLogic.getStein3();
		BeweglichesRechteck Stein4 = GameLogic.getStein4();
		BeweglichesRechteck Stein5 = GameLogic.getStein5();
		BeweglichesRechteck Stein6 = GameLogic.getStein6();
		BeweglichesRechteck Stein7 = GameLogic.getStein7();
		BeweglichesRechteck Stein8 = GameLogic.getStein8();
		BeweglichesRechteck Bruch1 = GameLogic.getBruch1();
		BeweglichesRechteck Bruch2 = GameLogic.getBruch2();
        BeweglichesRechteck ExtraGegener1= GameLogic.getExtraGegner1();
  
	
		

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

			if (this.positionX <= Stein.positionX + Stein.groesseX &&
					this.positionX + this.groesseX >= Stein.positionX &&
					this.positionY <= Stein.positionY + Stein.groesseY &&
					this.positionY + this.groesseY >= Stein.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein.positionY + Stein.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}

			}

			if (this.positionX <= Stein2.positionX + Stein2.groesseX &&
					this.positionX + this.groesseX >= Stein2.positionX &&
					this.positionY <= Stein2.positionY + Stein2.groesseY &&
					this.positionY + this.groesseY >= Stein2.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein2.positionY + Stein2.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
			}

			if (this.positionX <= Stein3.positionX + Stein3.groesseX &&
					this.positionX + this.groesseX >= Stein3.positionX &&
					this.positionY <= Stein3.positionY + Stein3.groesseY &&
					this.positionY + this.groesseY >= Stein3.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein3.positionY + Stein3.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				
			}
			
			if (this.positionX <= Stein4.positionX + Stein4.groesseX &&
					this.positionX + this.groesseX >= Stein4.positionX &&
					this.positionY <= Stein4.positionY + Stein4.groesseY &&
					this.positionY + this.groesseY >= Stein4.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein4.positionY + Stein4.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				
			}
			
			
			if (this.positionX <= Stein5.positionX + Stein5.groesseX &&
					this.positionX + this.groesseX >= Stein5.positionX &&
					this.positionY <= Stein5.positionY + Stein5.groesseY &&
					this.positionY + this.groesseY >= Stein5.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein5.positionY + Stein5.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				
			}
			
			if (this.positionX <= Stein6.positionX + Stein6.groesseX &&
					this.positionX + this.groesseX >= Stein6.positionX &&
					this.positionY <= Stein6.positionY + Stein6.groesseY &&
					this.positionY + this.groesseY >= Stein6.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein6.positionY + Stein6.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				
			}
			
			if (this.positionX <= Stein7.positionX + Stein7.groesseX &&
					this.positionX + this.groesseX >= Stein7.positionX &&
					this.positionY <= Stein7.positionY + Stein7.groesseY &&
					this.positionY + this.groesseY >= Stein7.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein7.positionY + Stein7.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				
			}
			
			
			
			
			if (this.positionX <= Stein8.positionX + Stein8.groesseX &&
					this.positionX + this.groesseX >= Stein8.positionX &&
					this.positionY <= Stein8.positionY + Stein8.groesseY &&
					this.positionY + this.groesseY >= Stein8.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Stein8.positionY + Stein8.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				
			}
			
			
			if (this.positionX <= ExtraGegener1.positionX + ExtraGegener1.groesseX &&
					this.positionX + this.groesseX >= ExtraGegener1.positionX &&
					this.positionY <= ExtraGegener1.positionY + ExtraGegener1.groesseY &&
					this.positionY + this.groesseY >= ExtraGegener1.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (ExtraGegener1.positionY + ExtraGegener1.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));

				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				
			}
			
			if(BruchPunkte1<3) {
			if (this.positionX <= Bruch1.positionX + Bruch1.groesseX &&
					this.positionX + this.groesseX >= Bruch1.positionX &&
					this.positionY <= Bruch1.positionY + Bruch1.groesseY &&
					this.positionY + this.groesseY >= Bruch1.positionY) {
				xGeschwindigkeit = -xGeschwindigkeit;
				yGeschwindigkeit += (this.positionY - (Bruch1.positionY + Bruch1.groesseY / 2)) / 10;
				yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));
				BruchPunkte1++; 
				if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
					yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
				}
				}}
			
			if(BruchPunkte2<10) {
				if (this.positionX <= Bruch2.positionX + Bruch2.groesseX &&
						this.positionX + this.groesseX >= Bruch2.positionX &&
						this.positionY <= Bruch2.positionY + Bruch2.groesseY &&
						this.positionY + this.groesseY >= Bruch2.positionY) {
					xGeschwindigkeit = -xGeschwindigkeit;
					yGeschwindigkeit += (this.positionY - (Bruch2.positionY + Bruch2.groesseY / 2)) / 10;
					yGeschwindigkeit = Math.max(-maxgeschwindigkeit, Math.min(yGeschwindigkeit, maxgeschwindigkeit));
					BruchPunkte2++; 
					if (Math.abs(yGeschwindigkeit) < minYGeschwindigkeit) {									//wert wird positiv gemacht	
						yGeschwindigkeit = (int) (minYGeschwindigkeit * Math.signum(yGeschwindigkeit));		//winkel verschiebung nicht mehr nur 45 grad
					}
					}}
			
		
				
			}
			
			
				if(yGeschwindigkeit == 0) {				//wenn alles schief geht und y trotzdem 0 ist 
					yGeschwindigkeit += 1;
				
			}




		}
	
	
	
	public static void GegnerKI() {
		BeweglichesRechteck ball = GameLogic.getBall();
		BeweglichesRechteck extraball1 = GameLogic.getExtraBall1();
		BeweglichesRechteck extraball2 = GameLogic.getExtraBall2();
		
		
		
		BeweglichesRechteck gegnerPaddle = GameLogic.getRechteckGegner();
		BeweglichesRechteck sicherheitGegner = GameLogic.getSicherheitGegner();
		
		BeweglichesRechteck ExtraGegner1 = GameLogic.getExtraGegner1();
		if(GameLogic.keypausearrowpressed) {

		}else {
			if(ball.positionX <= sicherheitGegner.positionX + sicherheitGegner.groesseX &&
					ball.positionX + ball.groesseX >= sicherheitGegner.positionX &&
					ball.positionY <= sicherheitGegner.positionY + sicherheitGegner.groesseY &&
					ball.positionY + ball.groesseY >= sicherheitGegner.positionY) {

			}else {
				if (ball.positionY + ball.groesseY / 2 > gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
					gegnerPaddle.positionY += GameLogic.getGeschwindigkeitGegner(); // Paddle nach unten bewegen
				} else if (ball.positionY + ball.groesseY / 2 < gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
					gegnerPaddle.positionY -= GameLogic.getGeschwindigkeitGegner(); // Paddle nach oben bewegen
				}

				// Sicherstellen, dass das Paddle nicht aus dem Bildschirmbereich bewegt wird
				gegnerPaddle.positionY = Math.max(0, Math.min(gegnerPaddle.positionY, Classic.getScreenheight() - gegnerPaddle.groesseY));
			}
			
			
			
			// KI des Extragegners
			if (ball.positionY + ball.groesseY / 2 > ExtraGegner1.positionY + ExtraGegner1.groesseY / 2) {
				ExtraGegner1.positionY += GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach unten bewegen
			} else if (ball.positionY + ball.groesseY / 2 < ExtraGegner1.positionY + ExtraGegner1.groesseY / 2) {
				ExtraGegner1.positionY -= GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach oben bewegen
			}
			
// Gegenr Ki Für Extraball 1
			if(GameLogic.getBallon1()) {
			if (extraball1.positionY + extraball1.groesseY / 2 > gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
				gegnerPaddle.positionY += GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach unten bewegen
			} else if (extraball1.positionY + extraball1.groesseY / 2 < gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
				gegnerPaddle.positionY -= GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach oben bewegen
			}
			
			
			if (extraball1.positionY + extraball1.groesseY / 2 > ExtraGegner1.positionY + ExtraGegner1.groesseY / 2) {
				ExtraGegner1.positionY += GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach unten bewegen
			} else if (extraball1.positionY + extraball1.groesseY / 2 < ExtraGegner1.positionY + ExtraGegner1.groesseY / 2) {
				ExtraGegner1.positionY -= GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach oben bewegen
			}
			}
    // KI für Extraball 2	
			if(GameLogic.getBallon2()) {
			if (extraball2.positionY + extraball2.groesseY / 2 > gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
				gegnerPaddle.positionY += GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach unten bewegen
			} else if (extraball2.positionY + extraball2.groesseY / 2 < gegnerPaddle.positionY + gegnerPaddle.groesseY / 2) {
				gegnerPaddle.positionY -= GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach oben bewegen
			}
			
			if (extraball2.positionY + extraball2.groesseY / 2 > ExtraGegner1.positionY + ExtraGegner1.groesseY / 2) {
				ExtraGegner1.positionY += GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach unten bewegen
			} else if (extraball2.positionY + extraball2.groesseY / 2 < ExtraGegner1.positionY + ExtraGegner1.groesseY / 2) {
				ExtraGegner1.positionY -= GameLogic.getGeschwindigkeitGegner()/2; // Paddle nach oben bewegen
			}
		}
			
			
		}
	}

	public void Punktesystem() {

		if (positionX == 0) {
			setGegenerPunkte(GegenerPunkte + 1);  
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			yGeschwindigkeit = -yGeschwindigkeit;
			Classic.RefreshPoints();
		}
		if(positionX ==  Classic.getScreenwidth() - diameter ) {
			setSpielerPunkte(SpielerPunkte + 1);   
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			yGeschwindigkeit = -yGeschwindigkeit;
			Classic.RefreshPoints();
		}
	}



	public void Gewonnen() {
		if (getGegnerPunkte() == PunkteGewonen ) {
			setGegenerPunkte(0);
			setSpielerPunkte(0);
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			xGeschwindigkeit = 0;
			yGeschwindigkeit = 0;
			System.out.println("Gegner hat gewonnen" );
			Classic.Classicclose();
			GameLogic.instance.stopGameTimer();
			BruchPunkte1=0;
			BruchPunkte2=0;
		}else if(getSpielerPunkte() == PunkteGewonen) {
			level++;
			Inventar.TalerGewinn(1);
			setGegenerPunkte(0);
			setSpielerPunkte(0);
			positionX = GameLogic.getX();
			positionY = GameLogic.getY();
			xGeschwindigkeit = 0;
			yGeschwindigkeit = 0;
			System.out.println("Spieler hat gewonnen" );
			Classic.Classicclose();
			GameLogic.instance.stopGameTimer();
			BruchPunkte1=0;
			BruchPunkte2=0;
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
				Classic.Classicclose();
				GameLogic.instance.stopGameTimer();


			}
		}
	}
	public void EndlessRunnerSchwierigkeit() {
		GameLogic.setGeschwindigkeitBall(1);
		GameLogic.setGeschwindigkeitSpieler(1);
		GameLogic.setGeschwindigkeitGegner(1);
		if (SpielerPunkte >= 0 && SpielerPunkte <= 5 ) {
			GameLogic.setGeschwindigkeitBall(1);
			GameLogic.setGeschwindigkeitSpieler(1);
			GameLogic.setGeschwindigkeitGegner(1);
		}else if(SpielerPunkte >= 5 && SpielerPunkte <= 10) {
			GameLogic.setGeschwindigkeitSpieler(2);
		}else if(SpielerPunkte >= 10 && SpielerPunkte <= 15) {
			GameLogic.setGeschwindigkeitSpieler(2);
			GameLogic.setGeschwindigkeitBall(2);
			GameLogic.setGeschwindigkeitGegner(2);
		}else if(SpielerPunkte >= 15 && SpielerPunkte <= 20) {
			GameLogic.setGeschwindigkeitSpieler(2);
			GameLogic.setGeschwindigkeitBall(3);
			GameLogic.setGeschwindigkeitGegner(2);
		}else if(SpielerPunkte >= 20) {
			GameLogic.setGeschwindigkeitSpieler(3);
			GameLogic.setGeschwindigkeitBall(4);
			GameLogic.setGeschwindigkeitGegner(2);
		}
	}
	public void Levelsystem() {
		GameLogic.setGeschwindigkeitBall(1);
		GameLogic.setGeschwindigkeitSpieler(1);
		GameLogic.setGeschwindigkeitGegner(1);
		switch (getLevel()) {
		case 0: {	//Level Tutorial
			GameLogic.setGeschwindigkeitBall(1);
			GameLogic.setGeschwindigkeitSpieler(2);
			GameLogic.setGeschwindigkeitGegner(1);
			if (GameLogic.getCounter()==0) {
				System.out.println("Tutorial");
			}
			PunkteGewonen = 10;

		} break;
		case 1: {					//Level 1
			GameLogic.setGeschwindigkeitBall(1);
			GameLogic.setGeschwindigkeitSpieler(2);
			GameLogic.setGeschwindigkeitGegner(1);
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}
		}break;
		case 2: {					//Level 2
			GameLogic.setGeschwindigkeitBall(2);
			GameLogic.setGeschwindigkeitSpieler(2);
			GameLogic.setGeschwindigkeitGegner(2);
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 3: {					//Level 3
			GameLogic.setGeschwindigkeitBall(3);
			GameLogic.setGeschwindigkeitSpieler(2);
			GameLogic.setGeschwindigkeitGegner(2);
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 4: {					//Level 4
			GameLogic.setGeschwindigkeitBall(3);
			GameLogic.setGeschwindigkeitSpieler(3);
			GameLogic.setGeschwindigkeitGegner(2);
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 5: {					//Level 5
			GameLogic.setGeschwindigkeitBall(4);
			GameLogic.setGeschwindigkeitSpieler(3);
			GameLogic.setGeschwindigkeitGegner(2);
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 6: {					//Level 6
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 7: { 					//Level 7
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 8: {					//Level 8
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 9: {					//Level 9
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 10: {					//Level 10
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;	
		case 11: {					//Level 11
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 12: {					//Level 12
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 13: {					//Level 13
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 14: {					//Level 14
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 15: {					//Level 15
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 16: {					//Level 16
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 17: {					//Level 17
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 18: {					//Level 18
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 19: {					//Level 19
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}break;
		case 20: {					//Level 20
			if (GameLogic.getCounter()==0) {
				System.out.println("level : " + getLevel());
			}		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + level);
		}

	}
	public void Klassisch() {
		PunkteGewonen = 10;
		GameLogic.setGeschwindigkeitBall(3);
		GameLogic.setGeschwindigkeitGegner(2);
		GameLogic.setGeschwindigkeitSpieler(3);
	}


	public static int getGegnerPunkte() {
		return GegenerPunkte;
	}

	public static void setGegenerPunkte(int gegenerPunkte) {
		GegenerPunkte = gegenerPunkte;
	}

	public static int getSpielerPunkte() {
		return SpielerPunkte;
	}

	public static void setSpielerPunkte(int spielerPunkte) {
		SpielerPunkte = spielerPunkte;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		BeweglichesRechteck.level = level;
	}

	public static int getPunkteGewonen() {
		return PunkteGewonen;
	}

	public static void setPunkteGewonen(int punkteGewonen) {
		PunkteGewonen = punkteGewonen;
	}


}
