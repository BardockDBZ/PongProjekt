package actions;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.StartScreen;

public class Main {
public static Font KnightWarriors;

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Windows Look and Feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		InputStream is = Main.class.getResourceAsStream("/actions/resources/KNIGHTWARRIOR-W16N8.OTF");
		try {
			KnightWarriors = Font.createFont(Font.TRUETYPE_FONT, is);
			KnightWarriors = KnightWarriors.deriveFont(15F);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	audioManager.playSound("/actions/resources/twash.wav");
		StartScreen.FrameErstellen();	
		//Beim Starten wird der StartScreen erstellt 
		
		        
	
	}

}
