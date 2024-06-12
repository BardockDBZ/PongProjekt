package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actions.Main;
import actions.audioManager;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JButton;

public class Shop extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Shop Shopframe;						   //Zum Frame erstllen + Name wegen verwirrung
	private static int breite, laenge; //Panel massse -> werden in Set geändert
	
	

	public static void ShopErstellen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shopframe = new Shop();
					Shopframe.setVisible(true);
					audioManager.playSound("/actions/resources/Shop.wav");
					float volume = StartScreen.slider.getValue();
			        audioManager.setVolume(volume);
			        StartScreen.slider.getValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Shop() {
		setTitle("Shop");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblShop = new JLabel("Shop");
		lblShop.setForeground(new Color(255, 255, 255));
		lblShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblShop.setFont(Main.KnightWarriors);
		lblShop.setBounds(0, 0, 484, 36);
		contentPane.add(lblShop);
		JButton btnSchliessen = new JButton("Shop schließen");
		btnSchliessen.setBackground(new Color(128, 128, 255));
		btnSchliessen.setBounds(44, 339, 114, 23);
		contentPane.add(btnSchliessen);
		btnSchliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			StartScreen.FrameErstellen();
			Shopclose();
			audioManager.stopSound(getName());
			
			}
		});
	}
	public static void Shopclose() {	//so wird der Shop geschlossen
		if(Shopframe != null) {
			Shopframe.dispose();	
		}
	}
	public static int getBreite() {
		return breite;
	}
	public static void setBreite(int breite) {
		Shop.breite = breite;
	}
	public static int getLaenge() {
		return laenge;
	}
	public static void setLaenge(int laenge) {
		Shop.laenge = laenge;
	}
}
