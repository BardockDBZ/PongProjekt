package actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameLogic;
import gui.StartScreen;

public class KeyHandler implements KeyListener {

	GameLogic gamelogic;

	public  KeyHandler(GameLogic spiellogik) {
		gamelogic = spiellogik;
	}
	
	
	public  void keyTyped(KeyEvent e) {
		
	}

	
	public  void keyPressed(KeyEvent e) {			//key tipp zum bewegen tippen

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamelogic.keyLeftarrowpressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamelogic.keyRightarrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_UP) {
			gamelogic.keyUparrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gamelogic.keyDownarrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gamelogic.keypausearrowpressed =! gamelogic.keypausearrowpressed;
			StartScreen.setupPauseMenu();
		if (gamelogic.keypausearrowpressed) {
			
			StartScreen.showPauseMenu();
		}
		else {
			StartScreen.hidePauseMenu();
		}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			gamelogic.keyUPWarrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_S) {
			gamelogic.keyDownSarrowpressed = true;
		}
	}



	public  void keyReleased(KeyEvent e) {			//key tipp zum bewegen loslassen

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamelogic.keyLeftarrowpressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamelogic.keyRightarrowpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_UP) {
			gamelogic.keyUparrowpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gamelogic.keyDownarrowpressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			gamelogic.keyUPWarrowpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_S) {
			gamelogic.keyDownSarrowpressed = false;
		}
		
	}

}
