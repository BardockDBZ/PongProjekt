package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.sound.sampled.FloatControl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import gameObjects.BeweglichesRechteck;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class StartScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static StartScreen startframe;
	private static int breite = 640, laenge = 468;
	private CardLayout cardLayout;
	private JPanel pStartScreen;
	private JPanel pEinstellungen;
	private JPanel pLevelauswahl;
	private JButton btnSpeichern;
	private JButton btnBack;
	private JButton btnVerlassen;
	int option;
	static JSlider slider;
	public static JPanel pPause;
	private static JButton btnPause_1;

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
		setBounds(100, 100, 640, 468);
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
		
		JButton btnStory = new JButton("Geschichte");
		btnStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tausch(pStartScreen, pLevelauswahl);
			/*	GameLogic.setSpiel(0);		
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();		//Pong wird ausgeführt	
				new Classic(spiellogik);							//Frame wird erstellt zum Pong
				Startclose();	//StartScreen Schließen -> soll Nach Gewinnen oder Verlieren der Runde/Level als Abfrage wieder Erstellt werden
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
			*/
			}
		});
		
		JLabel lbEndless = new JLabel("Endlos:");
		lbEndless.setForeground(new Color(255, 255, 255));
		lbEndless.setFont(Main.KnightWarriors);
		
		JButton btnEndless = new JButton("Endlos");
		btnEndless.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameLogic.setSpiel(1);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				//EndlessRunner wird ausgeführt
				Startclose();	//StartScreen Schließen -> soll Nach Verlieren der Runde als Abfrage wieder Erstellt werden
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				
			}
		});
		
		btnVerlassen = new JButton("Verlassen");
		btnVerlassen.setMnemonic('q');
		btnVerlassen.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			option = JOptionPane.showConfirmDialog(null, "Bist du dir sicher, das Spiel zu beenden?", "Beenden", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.CANCEL_OPTION) {
				System.exit(0);	
				}
			}
		});
		
		
		JButton btnClassic = new JButton("Klassisch");
		btnClassic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameLogic.setSpiel(3);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/EnemyApproaching.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		
		JLabel lblClassic = new JLabel("Klassisch:");
		lblClassic.setForeground(new Color(255, 255, 255));
		lblClassic.setFont(Main.KnightWarriors);
		
		JButton btnMultiplayer = new JButton("Mehrspieler");
		btnMultiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameLogic.setSpiel(2);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
			}
		});
		
		JLabel lblMultiplayer = new JLabel("Mehrspieler:");
		lblMultiplayer.setForeground(new Color(255, 255, 255));
		lblMultiplayer.setFont(Main.KnightWarriors);	
		
		JLabel lblShop = new JLabel("");
		lblShop.setAutoscrolls(true);
		lblShop.setBackground(Color.WHITE);
		lblShop.setForeground(Color.WHITE);
		lblShop.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				Shop.ShopErstellen();
				Startclose();
				audioManager.stopSound(getName());
			}
		});
		lblShop.setIcon(new ImageIcon(StartScreen.class.getResource("/actions/resources/image (1).png")));
		
		JLabel lblSettings = new JLabel("");
		lblSettings.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tausch(pStartScreen, pEinstellungen);
			}
		});
		lblSettings.setIcon(new ImageIcon(StartScreen.class.getResource("/actions/resources/setting (2) (1).png")));
		GroupLayout gl_pStartScreen = new GroupLayout(pStartScreen);
		gl_pStartScreen.setHorizontalGroup(
			gl_pStartScreen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pStartScreen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addComponent(lblShop, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pStartScreen.createSequentialGroup()
									.addGap(18)
									.addComponent(lbStartScreenTitel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblSettings, GroupLayout.PREFERRED_SIZE, 52, Short.MAX_VALUE))
								.addGroup(gl_pStartScreen.createSequentialGroup()
									.addGap(28)
									.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING)
										.addComponent(lblClassic)
										.addComponent(lbStory, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMultiplayer)
										.addComponent(lbEndless, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
									.addGap(14)
									.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnClassic, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnMultiplayer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnEndless, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnStory, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_pStartScreen.createSequentialGroup()
							.addComponent(btnVerlassen, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(242))))
		);
		gl_pStartScreen.setVerticalGroup(
			gl_pStartScreen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pStartScreen.createSequentialGroup()
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbStartScreenTitel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSettings))
					.addGap(125)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStory)
						.addComponent(lbStory, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClassic)
						.addComponent(lblClassic))
					.addGap(7)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEndless)
						.addComponent(lbEndless, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMultiplayer)
						.addComponent(lblMultiplayer))
					.addGap(18)
					.addComponent(btnVerlassen, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(98))
				.addGroup(gl_pStartScreen.createSequentialGroup()
					.addComponent(lblShop)
					.addGap(379))
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
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		slider = new JSlider();
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
		
		pLevelauswahl = new JPanel();
		pLevelauswahl.setBackground(Color.BLACK);
		contentPane.add(pLevelauswahl, "name_764749132583500");
		pLevelauswahl.setLayout(null);
		
		JLabel lblLevelwaehlen = new JLabel("Waehle das Level aus!");
		lblLevelwaehlen.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevelwaehlen.setForeground(Color.WHITE);
		lblLevelwaehlen.setBounds(0, 36, 485, 37);
		pLevelauswahl.add(lblLevelwaehlen);
		lblLevelwaehlen.setFont(Main.KnightWarriors);
		
		pPause = new JPanel();
		pPause.setBackground(Color.DARK_GRAY);
		contentPane.add(pPause, "name_24353100108700");
		pPause.setLayout(null);
		
		btnPause_1 = new JButton("Pause");
		btnPause_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPauseMenu();
			}
		});
		btnPause_1.setBounds(29, 354, 0, 0);
		btnPause_1.setMnemonic('P');
		btnPause_1.setBackground(Color.BLACK);
		pPause.add(btnPause_1);
		pPause.setVisible(true);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = slider.getValue();
				lblPercent.setText((int)((value + 80) * 1.25) + "%");
			}
		
		});
		  setVisible(true);
		  audioManager.stopSound(getName());
		  audioManager.playSound("/actions/resources/twash.wav");
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
	private void setupPauseMenu() {
        // Create a modal dialog for the pause menu
      
        pPause.setSize(200, 150);
        pPause.setLayout(new BorderLayout());
        // Add a label to indicate the game is paused
        JLabel pausedLabel = new JLabel("Game Paused", SwingConstants.CENTER);
        pPause.add(pausedLabel, BorderLayout.CENTER);

        // Create a button to resume
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePauseMenu();
            }
        }); }
	 private void showPauseMenu() {
	       
	        pPause.setVisible(true);
	    }
	  private void hidePauseMenu() {
	        pPause.setVisible(false);
	  }
}
