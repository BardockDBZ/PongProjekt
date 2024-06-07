package actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameLogic;

public class KeyHandler implements KeyListener {

	GameLogic gamelogic;

	public  KeyHandler(GameLogic spiellogik) {
		gamelogic = spiellogik;
	}
	
	@Override
	public  void keyTyped(KeyEvent e) {

	}

	@Override
	public  void keyPressed(KeyEvent e) {			//key tipp zum bewegen tippen

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamelogic.keyLeftarrowpressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamelogic.keyRightarrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_UP) {
			gamelogic.keyUparrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gamelogic.keyDownarrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_W) {
			gamelogic.keyUPWarrowpressed = true;
		}else if (e.getKeyCode() == KeyEvent.VK_S) {
			gamelogic.keyDownSarrowpressed = true;
		}
	}

	@Override
	public  void keyReleased(KeyEvent e) {			//key tipp zum bewegen loslassen

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamelogic.keyLeftarrowpressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamelogic.keyRightarrowpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_UP) {
			gamelogic.keyUparrowpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			gamelogic.keyDownarrowpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_W) {
			gamelogic.keyUPWarrowpressed = false;
		}else if (e.getKeyCode() == KeyEvent.VK_S) {
			gamelogic.keyDownSarrowpressed = false;
		}
		
	}

}
