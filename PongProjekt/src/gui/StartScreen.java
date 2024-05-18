package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.FloatControl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import actions.Main;
import actions.audioManager;
import game.GameLogic;

public class StartScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static StartScreen startframe;
	private static int breite = 500, laenge = 450;
	private CardLayout cardLayout;
	private JPanel pStartScreen;
	private JPanel pEinstellungen;
	private JButton btnEinstellungen;
	private JButton btnSpeichern;
	private JButton btnBack;
	private JButton btnVerlassen;
	private static FloatControl volumeControl;

	/**
	 * Launch the application.
	 */
	
	public static void FrameErstellen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startframe = new StartScreen();
					startframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
		   

	/**S
	 * Create the frame.
	 */
	
	public StartScreen() {
		setTitle("Pong - Gruppe 6 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, breite, laenge);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		  cardLayout = new CardLayout(); // Initialize CardLayout
	      contentPane.setLayout(cardLayout);
	       setContentPane(contentPane);
		
	       pStartScreen = new JPanel();
			pStartScreen.setBackground(new Color(0, 0, 0));
			contentPane.add(pStartScreen, "name_1180047954601100");
			
			JLabel lbStartScreenTitel = new JLabel("Pong ");
			lbStartScreenTitel.setForeground(new Color(255, 255, 255));
			lbStartScreenTitel.setHorizontalAlignment(SwingConstants.CENTER);
			lbStartScreenTitel.setFont(Main.KnightWarriors);
			
			JLabel lbStory = new JLabel("Geschichte:");
			lbStory.setForeground(new Color(255, 255, 255));
			lbStory.setFont(Main.KnightWarriors);
		
		JButton btnClassic = new JButton("Geschichte");
		btnClassic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//Pong wird ausgeführt						
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);							//Frame wird erstellt zum Pong
				Startclose();									//StartScreen Schließen -> soll Nach Gewinnen oder Verlieren der Runde/Level als Abfrage wieder Erstellt werden
			}
		});
		
		JLabel lbEndless = new JLabel("Endlos:");
		lbEndless.setForeground(new Color(255, 255, 255));
		lbEndless.setFont(Main.KnightWarriors);
		
		JButton btnEndless = new JButton("Endlos");
		btnEndless.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameLogic spiellogik = new GameLogic();
				new EndlessRunner(spiellogik);				//EndlessRunner wird ausgeführt
				Startclose();								//StartScreen Schließen -> soll Nach Verlieren der Runde als Abfrage wieder Erstellt werden
			}
		});
		
		JLabel lbShop = new JLabel("Laden:");
		lbShop.setForeground(new Color(255, 255, 255));
		lbShop.setFont(Main.KnightWarriors);
		
		JButton btnShop = new JButton("Laden");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shop.setBreite(500); 						//Shop breite wird angegeben
				Shop.setLaenge(450);						//Shop länge wird angegeben
				Shop.ShopErstellen();						//Shop wird erstellt / Start Screen bleibt 
			}
		});
		
		btnEinstellungen = new JButton("Einstellungen");
		btnEinstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			tausch(pStartScreen,pEinstellungen);				//Von StartScreen zu Einstellungen
			}
		});
		
		btnVerlassen = new JButton("Verlassen");
		btnVerlassen.addActionListener(e -> System.exit(0));
		GroupLayout gl_pStartScreen = new GroupLayout(pStartScreen);
		gl_pStartScreen.setHorizontalGroup(
			gl_pStartScreen.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pStartScreen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING)
								.addComponent(lbStartScreenTitel, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pStartScreen.createSequentialGroup()
									.addGap(43)
									.addComponent(btnEinstellungen, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addGap(91)
									.addComponent(btnVerlassen, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pStartScreen.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbStory, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
							.addGap(34))
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addGroup(gl_pStartScreen.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_pStartScreen.createSequentialGroup()
									.addComponent(lbShop, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnShop, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING, gl_pStartScreen.createSequentialGroup()
									.addComponent(lbEndless, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING)
										.addComponent(btnClassic, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEndless, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))))
							.addGap(163))))
		);
		gl_pStartScreen.setVerticalGroup(
			gl_pStartScreen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pStartScreen.createSequentialGroup()
					.addComponent(lbStartScreenTitel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(140)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbStory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClassic))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbEndless, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEndless))
					.addGap(10)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbShop, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnShop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(91)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEinstellungen)
						.addComponent(btnVerlassen, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		pStartScreen.setLayout(gl_pStartScreen);
		
		pEinstellungen = new JPanel();
		pEinstellungen.setBackground(new Color(0, 0, 0));
		contentPane.add(pEinstellungen, "name_1181388485838200");
		pEinstellungen.setLayout(null);
		
		JLabel lbEinstellungen = new JLabel("Einstellungen");
		lbEinstellungen.setHorizontalAlignment(SwingConstants.CENTER);
		lbEinstellungen.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lbEinstellungen.setBounds(0, 0, 484, 36);
		pEinstellungen.add(lbEinstellungen);
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.setBounds(57, 326, 107, 23);
		pEinstellungen.add(btnSpeichern);
		
		btnBack = new JButton("Zurück");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			tausch(pEinstellungen,pStartScreen);				//Von Einstellungen zum Start Screen
			}
		});
		
		btnBack.setBounds(269, 326, 90, 23);
		pEinstellungen.add(btnBack);
		
		JSlider slider = new JSlider();
		slider.setMinimum(-80);
		slider.setMaximum(0);
		slider.addChangeListener(e -> {
		   float volume = slider.getValue();
           audioManager.setVolume(volume);
	});
		
		slider.setValue(100);
		slider.setBounds(247, 89, 200, 26);
		pEinstellungen.add(slider);
		
		JLabel lblSoundVolume = new JLabel("Allgemeiner Ton: ");
		lblSoundVolume.setForeground(new Color(255, 255, 255));
		lblSoundVolume.setBounds(10, 89, 131, 26);
		pEinstellungen.add(lblSoundVolume);
		lblSoundVolume.setFont(Main.KnightWarriors);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Deutsch", "English", "Italiano"}));
		comboBox.setBounds(247, 142, 200, 22);
		pEinstellungen.add(comboBox);
		
		JLabel lblSprache = new JLabel("Sprache:");
		lblSprache.setForeground(Color.WHITE);
		lblSprache.setFont(Main.KnightWarriors);
		lblSprache.setBounds(10, 140, 119, 26);
		pEinstellungen.add(lblSprache);
		
		JLabel lblPercent = new JLabel("100%");
		lblPercent.setForeground(new Color(255, 255, 255));
		lblPercent.setBounds(191, 89, 46, 26);
		lblPercent.setFont(Main.KnightWarriors);
		pEinstellungen.add(lblPercent);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = slider.getValue();
				lblPercent.setText((int)((value + 80) * 1.25) + "%");
			}
		
		});
		  setVisible(true);
	}
	public static void Startclose() {		//Fenster schließen
		if(startframe != null) {
			startframe.dispose();
		}
	}
	private void tausch(Component oldPanel, Component newPanel) {		//Panel tausch
		cardLayout.show(contentPane, newPanel.getName());

		oldPanel.setVisible(false);
		oldPanel.setEnabled(false);

		newPanel.setVisible(true);
		newPanel.setEnabled(true);

		revalidate();
		repaint();
	}
	public static int getBreite() {
		return breite;
	}

	public static void setBreite(int breite) {
		StartScreen.breite = breite;
	}

	public static int getLaenge() {
		return laenge;
	}

	public static void setLaenge(int laenge) {
		StartScreen.laenge = laenge;
	}
}
