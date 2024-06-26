package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import gameObjects.BeweglichesRechteck;
import gui.Classic;
import gui.Draw;

public class GameLogic {

	public Timer gameTimer;
	private static int screenwidth = Classic.getScreenwidth() ;
	private static int screenheight = Classic.getScreenheight();
	public ArrayList<GameObject> spielObjekte;

	public boolean keyLeftarrowpressed;
	public boolean keyRightarrowpressed;
	public boolean keyUparrowpressed;
	public boolean keyDownarrowpressed;
	public boolean keyDownSarrowpressed;
	public boolean keyUPWarrowpressed;
	public static boolean keypausearrowpressed ;
	private static BeweglichesRechteck rechteckSpieler;
	private static BeweglichesRechteck ball;
	private static BeweglichesRechteck rechteckgegner;
	private static BeweglichesRechteck sicherheitSpieler;
	private static BeweglichesRechteck sicherheitGegner;
	private static BeweglichesRechteck Stein1;
	private static BeweglichesRechteck Stein2;
	private static BeweglichesRechteck Stein3;
	private static BeweglichesRechteck Stein4;
	private static BeweglichesRechteck Stein5;
	private static BeweglichesRechteck Stein6;
	private static BeweglichesRechteck Stein7;
	private static BeweglichesRechteck Stein8;
	private static BeweglichesRechteck Ilusion1;
	private static BeweglichesRechteck Ilusion2;
	private static BeweglichesRechteck Ilusion3;
	private static BeweglichesRechteck Bruch1;

	
	
	
	private int breite = 10; 
	private int hoehe = 100;
	public static GameLogic instance;
	private static int Spiel = -1;
	private static int counter = 0;
	private static int GeschwindigkeitSpieler = 1;
	private static int GeschwindigkeitGegner = 1;
	private static int GeschwindigkeitBall = 1;


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
		sicherheitSpieler = new BeweglichesRechteck(75, 298, breite - 1, hoehe + 2);
		sicherheitGegner = new BeweglichesRechteck(705, 300, breite - 1, hoehe + 2);

		Stein1 = new BeweglichesRechteck(0, 0, 0, 0); //Stein1
		spielObjekte.add(Stein1);
		Stein2=new BeweglichesRechteck(0, 0, 0, 0);//Stein2
		spielObjekte.add(Stein2);
		Stein3=new BeweglichesRechteck(0, 0, 0, 0);//Stein3
		spielObjekte.add(Stein3);
		Stein4 = new BeweglichesRechteck(0, 0, 0, 0);//Stein4
		spielObjekte.add(Stein4);
		Stein5=new BeweglichesRechteck(0, 0, 0, 0);//Stein5
		spielObjekte.add(Stein5);
		Stein6=new BeweglichesRechteck(0, 0, 0, 0);//Stein6
		spielObjekte.add(Stein6);
		Stein7 = new BeweglichesRechteck(0, 0, 0, 0);//Stein7
		spielObjekte.add(Stein7);
		Stein8=new BeweglichesRechteck(0, 0, 0, 0);//Stein8
		spielObjekte.add(Stein8);
		Ilusion1=new BeweglichesRechteck(0, 0, 0, 0);//Ilusion1
		spielObjekte.add(Ilusion1);
		Ilusion2 = new BeweglichesRechteck(0, 0, 0, 0);//ilusion2
		spielObjekte.add(Ilusion2);
		Ilusion3=new BeweglichesRechteck(0, 0, 0, 0);//Ilusion3
		spielObjekte.add(Ilusion3);
		Bruch1=new BeweglichesRechteck(0, 0, 0, 0);//Bruch 1
		spielObjekte.add(Bruch1);
		
		
		

		if(true) {
			// Alles inerhalb der "if(Spiel==0) {}" ist das level design

			switch(13){

			case 5: {
				Stein1 = new BeweglichesRechteck(370, 250, 70, 70);
				spielObjekte.add(Stein1);
				
			} break;


			case 6: {
				Stein1 = new BeweglichesRechteck(400, 150, 15, 250);
				spielObjekte.add(Stein1);
				
			}break;
			
			case 7: {
				Stein1 = new BeweglichesRechteck(270, 260, 250, 15);
				spielObjekte.add(Stein1);
				Stein2=new BeweglichesRechteck(270, 360, 250, 15);
				spielObjekte.add(Stein2);
				
			}break;
			
			case 8: {
				Stein1 = new BeweglichesRechteck(370, 0, 15, 130);
				spielObjekte.add(Stein1);
				Stein2=new BeweglichesRechteck(370, 450, 15, 130);
				spielObjekte.add(Stein2);
				Stein3=new BeweglichesRechteck(340, 240, 80, 80);
				spielObjekte.add(Stein3);
				
			}break;
			
			case 9: {
				Stein1 = new BeweglichesRechteck(185, 215, 20, 20); //Ecke1
				spielObjekte.add(Stein1);
				Stein2=new BeweglichesRechteck(385, 115, 20, 20);//Stein2
				spielObjekte.add(Stein2);
				Stein3=new BeweglichesRechteck(585, 215, 20, 20);//Ecke2
				spielObjekte.add(Stein3);
				Stein4 = new BeweglichesRechteck(85, 265, 20, 20);//Stein4
				spielObjekte.add(Stein4);
				Stein5=new BeweglichesRechteck(585, 365, 20, 20);//Ecke3
				spielObjekte.add(Stein5);
				Stein6=new BeweglichesRechteck(685, 265, 20, 20);//Stein6
				spielObjekte.add(Stein6);
				Stein7 = new BeweglichesRechteck(185, 365, 20, 20);//ecke4
				spielObjekte.add(Stein7);
				Stein8=new BeweglichesRechteck(385, 415, 20, 20);//Stein8
				spielObjekte.add(Stein8);
				
			}break;
			
			case 10: {
				Stein1 = new BeweglichesRechteck(370, 0, 15, 130);
				spielObjekte.add(Stein1);
				Stein2=new BeweglichesRechteck(370, 450, 15, 130);
				spielObjekte.add(Stein2);
				Ilusion1=new BeweglichesRechteck(370, 130, 15, 320);
				spielObjekte.add(Ilusion1);
				
			}break;
			
			
			case 11: {
				
				Ilusion1=new BeweglichesRechteck(320, 250, 175, 350);
				spielObjekte.add(Ilusion1);
				Ilusion2=new BeweglichesRechteck(545, 0, 100, 320);
				spielObjekte.add(Ilusion2);
				Ilusion3=new BeweglichesRechteck(170, 0, 100, 320);
				spielObjekte.add(Ilusion3);
				
				
			
			}break;

			
			
                 case 12: {
                	Stein1 = new BeweglichesRechteck(400, 150, 20, 20); //Ecke1
     				spielObjekte.add(Stein1);
     				Stein2=new BeweglichesRechteck(450, 320, 20, 20);//Stein2
     				spielObjekte.add(Stein2);
     				Stein3=new BeweglichesRechteck(350, 230, 20, 20);//Ecke2
     				spielObjekte.add(Stein3);
     				Stein4 = new BeweglichesRechteck(400, 400, 20, 20);//Stein4
     				spielObjekte.add(Stein4);
				  Ilusion1=new BeweglichesRechteck(320, 120, 150, 300);
				  spielObjekte.add(Ilusion1);
				
				
				
			
			}break;
			
			
                 case 13:{	
                Bruch1=new BeweglichesRechteck(540, 0, 15, 700);//Ilusion3
         		spielObjekte.add(Bruch1);}break;
			
			
			default : {
				

			}
			}

		}







		//  0 : Story   1 : Endless      2: VS        3: Classic
		if(Spiel == 0 || Spiel == 1 || Spiel == 2 || Spiel == 3) {	
			gameTimer.scheduleAtFixedRate(new TimerTask(){
				@Override
				public void run() {
					ball.actionPerformed(null);
					counter++;
					if(GameLogic.keypausearrowpressed) {

					}else {
						if(GameLogic.getCounter() <= 750) {

						}else {
							if(Spiel == 2) {
								if (keyUPWarrowpressed) {
									if (rechteckSpieler.positionY >= 3) {
										rechteckSpieler.positionY -= GeschwindigkeitSpieler;
										sicherheitSpieler.positionY -= GeschwindigkeitSpieler;
									}
								} else if (keyDownSarrowpressed) {
									if (rechteckSpieler.positionY <= 465) {
										rechteckSpieler.positionY += GeschwindigkeitSpieler;
										sicherheitSpieler.positionY += GeschwindigkeitSpieler;
									}
								}
								if (keyUparrowpressed) {
									if (rechteckgegner.positionY >= 3) {
										rechteckgegner.positionY -= GeschwindigkeitSpieler;
										sicherheitGegner.positionY -= GeschwindigkeitSpieler;
									}
								} else if (keyDownarrowpressed) {
									if (rechteckgegner.positionY <= 465) {
										rechteckgegner.positionY += GeschwindigkeitSpieler;
										sicherheitGegner.positionY -= GeschwindigkeitSpieler;
									}
								} 
							}else {

								if (keyUparrowpressed) {
									if (rechteckSpieler.positionY >= 3) {
										rechteckSpieler.positionY -= GeschwindigkeitSpieler;
										sicherheitSpieler.positionY  -= GeschwindigkeitSpieler;
									}
								} else if (keyDownarrowpressed) {
									if (rechteckSpieler.positionY <= 465) {
										rechteckSpieler.positionY += GeschwindigkeitSpieler;
										sicherheitSpieler.positionY += GeschwindigkeitSpieler;
									}
								} else if (keyUPWarrowpressed) {
									if (rechteckSpieler.positionY >= 3) {
										rechteckSpieler.positionY -= GeschwindigkeitSpieler;
										sicherheitSpieler.positionY -= GeschwindigkeitSpieler;
									}
								} else if (keyDownSarrowpressed) {
									if (rechteckSpieler.positionY <= 465) {
										rechteckSpieler.positionY += GeschwindigkeitSpieler;
										sicherheitSpieler.positionY += GeschwindigkeitSpieler;
									}
								}

							}
						}
					}
					if(Spiel !=2) {
						BeweglichesRechteck.GegnerKI();
					}
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
	public static BeweglichesRechteck getStein() {
		return Stein1;
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
	public static BeweglichesRechteck getSicherheitSpieler() {
		return sicherheitSpieler;
	}
	public static void setSicherheitSpieler(BeweglichesRechteck sicherheit) {
		GameLogic.sicherheitSpieler = sicherheit;
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
	public static  BeweglichesRechteck getSicherheitGegner() {
		return sicherheitGegner;
	}
	public static void setSicherheitGegner(BeweglichesRechteck sicherheitGegner) {
		GameLogic.sicherheitGegner = sicherheitGegner;
	}

	public static BeweglichesRechteck getStein2() {
		return Stein2;
	}
	public static void setStein2(BeweglichesRechteck stein2) {
		Stein2 = stein2;
	}
	
	public static BeweglichesRechteck getStein3() {
		return Stein3;
		}
	
	public static BeweglichesRechteck getStein4() {
		return Stein4;
		}
	
	
	public static BeweglichesRechteck getStein5() {
		return Stein5;
		}
	
	public static BeweglichesRechteck getStein6() {
		return Stein6;
		}
	
	public static BeweglichesRechteck getStein7() {
		return Stein7;
		}
	
	public static BeweglichesRechteck getStein8() {
		return Stein8;
		}
	public static BeweglichesRechteck getBruch1() {
		return Bruch1;
		}
	
	


}
