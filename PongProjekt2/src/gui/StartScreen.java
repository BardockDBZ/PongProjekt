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
import javax.swing.UIManager;

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
	private static JLabel lblpausiert;
	
	//private static String Text[][] =new String[3][4];
	


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
			lbStartScreenTitel.setFont(Main.KnightWarriors.deriveFont(35F));
		
		JButton btnStory = new JButton("Geschichte");
		btnStory.setHorizontalAlignment(SwingConstants.LEFT);
		btnStory.setBorderPainted(false);
		btnStory.setOpaque(false);
		btnStory.setBackground(new Color(0, 0, 0));
		btnStory.setToolTipText("Bringt dich zur Geschichte des Spiels und zur Levelauswahl");
		btnStory.setForeground(new Color(255, 255, 255));
		btnStory.setFont(Main.KnightWarriors.deriveFont(25F));
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
		
		JButton btnEndless = new JButton("Endlos");
		btnEndless.setToolTipText("Kämpfe bis zum Ende, oder gibt es eins?");
		btnEndless.setHorizontalAlignment(SwingConstants.LEFT);
		btnEndless.setForeground(new Color(255, 255, 255));
		btnEndless.setOpaque(false);
		btnEndless.setBorderPainted(false);
		btnEndless.setFont(Main.KnightWarriors.deriveFont(25F));
		btnEndless.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameLogic.setSpiel(1);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				//EndlessRunner wird ausgeführt
				Startclose();	//StartScreen Schließen -> soll Nach Verlieren der Runde als Abfrage wieder Erstellt werden
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/Megalovania.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
				
			}
		});
		
		btnVerlassen = new JButton("Verlassen");
		btnVerlassen.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerlassen.setForeground(new Color(255, 255, 255));
		btnVerlassen.setOpaque(false);
		btnVerlassen.setBorderPainted(false);
		btnVerlassen.setMnemonic('q');
		btnVerlassen.setFont(Main.KnightWarriors.deriveFont(25F));
		btnVerlassen.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			option = JOptionPane.showConfirmDialog(null, "Bist du dir sicher, das Spiel zu beenden?", "Beenden", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.CANCEL_OPTION) {
				System.exit(0);	
				}
			}
		});
		
		
		JButton btnClassic = new JButton("Klassisch");
		btnClassic.setToolTipText("Der Klassische Pong Modus");
		btnClassic.setHorizontalAlignment(SwingConstants.LEFT);
		btnClassic.setForeground(new Color(255, 255, 255));
		btnClassic.setOpaque(false);
		btnClassic.setBorderPainted(false);
		btnClassic.setFont(Main.KnightWarriors.deriveFont(25F));
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
		
		JButton btnMultiplayer = new JButton("Mehrspieler");
		btnMultiplayer.setToolTipText("Kämpfe gegen deinen Freund!");
		btnMultiplayer.setHorizontalAlignment(SwingConstants.LEFT);
		btnMultiplayer.setForeground(new Color(255, 255, 255));
		btnMultiplayer.setBorderPainted(false);
		btnMultiplayer.setOpaque(false);
		btnMultiplayer.setFont(Main.KnightWarriors.deriveFont(25F));
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
				audioManager.playSound("/actions/resources/Bonetrousle.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		
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
							.addGap(18)
							.addComponent(lbStartScreenTitel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSettings, GroupLayout.PREFERRED_SIZE, 52, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addComponent(btnStory, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
							.addGap(392))
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addComponent(btnClassic, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
							.addGap(453))
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addComponent(btnEndless, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
							.addGap(453))
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addComponent(btnMultiplayer, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
							.addGap(453))
						.addGroup(gl_pStartScreen.createSequentialGroup()
							.addComponent(btnVerlassen, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(453, Short.MAX_VALUE))))
		);
		gl_pStartScreen.setVerticalGroup(
			gl_pStartScreen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pStartScreen.createSequentialGroup()
					.addGroup(gl_pStartScreen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pStartScreen.createParallelGroup(Alignment.BASELINE)
							.addComponent(lbStartScreenTitel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSettings))
						.addComponent(lblShop))
					.addPreferredGap(ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
					.addComponent(btnStory)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnClassic)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEndless)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnMultiplayer)
					.addGap(18)
					.addComponent(btnVerlassen, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(79))
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
		btnSpeichern.setForeground(new Color(255, 255, 255));
		btnSpeichern.setOpaque(false);
		btnSpeichern.setBorderPainted(false);
		btnSpeichern.setFont(Main.KnightWarriors.deriveFont(20F));
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSpeichern.setBounds(86, 326, 153, 23);
		pEinstellungen.add(btnSpeichern);
		
		btnBack = new JButton("Einstellungen verlassen");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setOpaque(false);
		btnBack.setBorderPainted(false);
		btnBack.setFont(Main.KnightWarriors.deriveFont(20F));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			tausch(pEinstellungen,pStartScreen);				//Von Einstellungen zum Start Screen
			}
		});
		
		btnBack.setBounds(249, 326, 277, 23);
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
		comboBox.setOpaque(false);
		comboBox.setBorder(UIManager.getBorder("CheckBoxMenuItem.border"));
		comboBox.setBackground(new Color(0, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Deutsch", "English", "Italiano"}));
		comboBox.setFont(Main.KnightWarriors);
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
		lblLevelwaehlen.setBounds(69, 40, 485, 37);
		pLevelauswahl.add(lblLevelwaehlen);
		lblLevelwaehlen.setFont(Main.KnightWarriors.deriveFont(35F));
		
		JLabel lblShop_1 = new JLabel("");
		lblShop_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Shop.ShopErstellen();
				Startclose();
				audioManager.stopSound(getName());
			}
		});
		lblShop_1.setIcon(new ImageIcon(StartScreen.class.getResource("/actions/resources/image (1).png")));
		lblShop_1.setForeground(new Color(255, 255, 255));
		lblShop_1.setBackground(new Color(255, 255, 255));
		lblShop_1.setBounds(10, 0, 52, 48);
		pLevelauswahl.add(lblShop_1);
		
		JLabel lblSettings_1 = new JLabel("");
		lblSettings_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tausch(pLevelauswahl, pEinstellungen);
			}
		});
		lblSettings_1.setIcon(new ImageIcon(StartScreen.class.getResource("/actions/resources/setting (2) (1).png")));
		lblSettings_1.setBounds(564, 0, 52, 48);
		pLevelauswahl.add(lblSettings_1);
		
		JButton btnLevel1 = new JButton("1");
		btnLevel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(1);
				
					GameLogic.setSpiel(0);
					GameLogic.setCounter(0);
					GameLogic spiellogik = new GameLogic();
					new Classic(spiellogik);				
					Startclose();	
					BeweglichesRechteck.setGegenerPunkte(0);
					BeweglichesRechteck.setSpielerPunkte(0);
					audioManager.stopSound(getName());
					audioManager.playSound("/actions/resources/GhostFight.wav");
					float volume = StartScreen.slider.getValue();
			        audioManager.setVolume(volume);
			        StartScreen.slider.getValue();
				}
			
			
		});
		btnLevel1.setForeground(new Color(255, 255, 255));
		btnLevel1.setBorderPainted(false);
		btnLevel1.setOpaque(false);
		btnLevel1.setBackground(new Color(0, 0, 0));
		btnLevel1.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel1.setBounds(40, 148, 46, 29);
		pLevelauswahl.add(btnLevel1);
		
		if(BeweglichesRechteck.getAbgeLevel()>=1) {
		JButton btnLevel2 = new JButton("2");
		btnLevel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(2);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/GhostFight.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel2.setOpaque(false);
		btnLevel2.setForeground(Color.WHITE);
		btnLevel2.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel2.setBorderPainted(false);
		btnLevel2.setBackground(Color.BLACK);
		btnLevel2.setBounds(96, 148, 46, 29);
		pLevelauswahl.add(btnLevel2);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=2) {
		JButton btnLevel3 = new JButton("3");
		btnLevel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(3);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/GhostFight.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel3.setOpaque(false);
		btnLevel3.setForeground(Color.WHITE);
		btnLevel3.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel3.setBorderPainted(false);
		btnLevel3.setBackground(Color.BLACK);
		btnLevel3.setBounds(152, 148, 46, 29);
		pLevelauswahl.add(btnLevel3);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=3) {
		JButton btnLevel4 = new JButton("4");
		btnLevel4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(4);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/DeathByGlamour.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel4.setOpaque(false);
		btnLevel4.setForeground(Color.WHITE);
		btnLevel4.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel4.setBorderPainted(false);
		btnLevel4.setBackground(Color.BLACK);
		btnLevel4.setBounds(208, 148, 46, 29);
		pLevelauswahl.add(btnLevel4);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=4) {
		JButton btnLevel5 = new JButton("5");
		btnLevel5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(5);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/DeathByGlamour.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel5.setOpaque(false);
		btnLevel5.setForeground(Color.WHITE);
		btnLevel5.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel5.setBorderPainted(false);
		btnLevel5.setBackground(Color.BLACK);
		btnLevel5.setBounds(264, 148, 46, 29);
		pLevelauswahl.add(btnLevel5);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=5) {
		JButton btnLevel6 = new JButton("6");
		btnLevel6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(6);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/DeathByGlamour.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel6.setOpaque(false);
		btnLevel6.setForeground(Color.WHITE);
		btnLevel6.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel6.setBorderPainted(false);
		btnLevel6.setBackground(Color.BLACK);
		btnLevel6.setBounds(320, 148, 46, 29);
		pLevelauswahl.add(btnLevel6);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=6) {
		JButton btnLevel7 = new JButton("7");
		btnLevel7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(7);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/DeathByGlamour.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel7.setOpaque(false);
		btnLevel7.setForeground(Color.WHITE);
		btnLevel7.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel7.setBorderPainted(false);
		btnLevel7.setBackground(Color.BLACK);
		btnLevel7.setBounds(376, 148, 46, 29);
		pLevelauswahl.add(btnLevel7);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=7) {
		JButton btnLevel8 = new JButton("8");
		btnLevel8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(8);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/SpiderDance.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel8.setOpaque(false);
		btnLevel8.setForeground(Color.WHITE);
		btnLevel8.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel8.setBorderPainted(false);
		btnLevel8.setBackground(Color.BLACK);
		btnLevel8.setBounds(432, 148, 46, 29);
		pLevelauswahl.add(btnLevel8);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=8) {
		JButton btnLevel9 = new JButton("9");
		btnLevel9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(9);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/SpiderDance.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel9.setOpaque(false);
		btnLevel9.setForeground(Color.WHITE);
		btnLevel9.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel9.setBorderPainted(false);
		btnLevel9.setBackground(Color.BLACK);
		btnLevel9.setBounds(488, 148, 46, 29);
		pLevelauswahl.add(btnLevel9);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=9) {
		JButton btnLevel10 = new JButton("10");
		btnLevel10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(10);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/SpiderDance.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel10.setOpaque(false);
		btnLevel10.setForeground(Color.WHITE);
		btnLevel10.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel10.setBorderPainted(false);
		btnLevel10.setBackground(Color.BLACK);
		btnLevel10.setBounds(544, 148, 57, 29);
		pLevelauswahl.add(btnLevel10);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=10) {
		JButton btnLevel11 = new JButton("11");
		btnLevel11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(11);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/SpiderDance.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel11.setOpaque(false);
		btnLevel11.setForeground(Color.WHITE);
		btnLevel11.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel11.setBorderPainted(false);
		btnLevel11.setBackground(Color.BLACK);
		btnLevel11.setBounds(29, 188, 57, 29);
		pLevelauswahl.add(btnLevel11);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=11) {
		JButton btnLevel12 = new JButton("12");
		btnLevel12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(12);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/Heartache.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel12.setOpaque(false);
		btnLevel12.setForeground(Color.WHITE);
		btnLevel12.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel12.setBorderPainted(false);
		btnLevel12.setBackground(Color.BLACK);
		btnLevel12.setBounds(90, 188, 57, 29);
		pLevelauswahl.add(btnLevel12);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=12) {
		JButton btnLevel13 = new JButton("13");
		btnLevel13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(13);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/Heartache.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel13.setOpaque(false);
		btnLevel13.setForeground(Color.WHITE);
		btnLevel13.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel13.setBorderPainted(false);
		btnLevel13.setBackground(Color.BLACK);
		btnLevel13.setBounds(152, 188, 57, 29);
		pLevelauswahl.add(btnLevel13);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=13) {
		JButton btnLevel14 = new JButton("14");
		btnLevel14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(14);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/SpearOfJustice.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel14.setOpaque(false);
		btnLevel14.setForeground(Color.WHITE);
		btnLevel14.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel14.setBorderPainted(false);
		btnLevel14.setBackground(Color.BLACK);
		btnLevel14.setBounds(208, 188, 57, 29);
		pLevelauswahl.add(btnLevel14);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=14) {
		JButton btnLevel15 = new JButton("15");
		btnLevel15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(15);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/SpearOfJustice.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel15.setOpaque(false);
		btnLevel15.setForeground(Color.WHITE);
		btnLevel15.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel15.setBorderPainted(false);
		btnLevel15.setBackground(Color.BLACK);
		btnLevel15.setBounds(264, 188, 57, 29);
		pLevelauswahl.add(btnLevel15);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=15) {
		JButton btnLevel16 = new JButton("16");
		btnLevel16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(16);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/SpearOfJustice.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel16.setOpaque(false);
		btnLevel16.setForeground(Color.WHITE);
		btnLevel16.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel16.setBorderPainted(false);
		btnLevel16.setBackground(Color.BLACK);
		btnLevel16.setBounds(320, 188, 57, 29);
		pLevelauswahl.add(btnLevel16);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=16) {
		JButton btnLevel17 = new JButton("17");
		btnLevel17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(17);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/BattleAgainstATrueHero.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel17.setOpaque(false);
		btnLevel17.setForeground(Color.WHITE);
		btnLevel17.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel17.setBorderPainted(false);
		btnLevel17.setBackground(Color.BLACK);
		btnLevel17.setBounds(376, 188, 57, 29);
		pLevelauswahl.add(btnLevel17);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=17) {
		JButton btnLevel18 = new JButton("18");
		btnLevel18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(18);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/BattleAgainstATrueHero.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel18.setOpaque(false);
		btnLevel18.setForeground(Color.WHITE);
		btnLevel18.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel18.setBorderPainted(false);
		btnLevel18.setBackground(Color.BLACK);
		btnLevel18.setBounds(432, 188, 57, 29);
		pLevelauswahl.add(btnLevel18);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=18) {
		JButton btnLevel19 = new JButton("19");
		btnLevel19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(19);
				
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/HopesAndDreams.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
			}
		});
		btnLevel19.setOpaque(false);
		btnLevel19.setForeground(Color.WHITE);
		btnLevel19.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevel19.setBorderPainted(false);
		btnLevel19.setBackground(Color.BLACK);
		btnLevel19.setBounds(488, 188, 57, 29);
		pLevelauswahl.add(btnLevel19);
		}
		
		if(BeweglichesRechteck.getAbgeLevel()>=19) {
		JButton btnLevel20 = new JButton("20");
		btnLevel20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BeweglichesRechteck.setLevel(20);
				GameLogic.setSpiel(0);
				GameLogic.setCounter(0);
				GameLogic spiellogik = new GameLogic();
				new Classic(spiellogik);				
				Startclose();	
				BeweglichesRechteck.setGegenerPunkte(0);
				BeweglichesRechteck.setSpielerPunkte(0);
				audioManager.stopSound(getName());
				audioManager.playSound("/actions/resources/HopesAndDreams.wav");
				float volume = StartScreen.slider.getValue();
		        audioManager.setVolume(volume);
		        StartScreen.slider.getValue();
		        
			}
		});
		btnLevel20.setOpaque(false);
		btnLevel20.setForeground(Color.WHITE);
		btnLevel20.setFont(Main.KnightWarriors.deriveFont(24F));
		btnLevel20.setBorderPainted(false);
		btnLevel20.setBackground(Color.BLACK);
		btnLevel20.setBounds(544, 188, 57, 29);
		pLevelauswahl.add(btnLevel20);
	}
		
		
		
		JButton btnLevelBack = new JButton("Zurueck");
		btnLevelBack.setForeground(new Color(255, 255, 255));
		btnLevelBack.setOpaque(false);
		btnLevelBack.setBorderPainted(false);
		btnLevelBack.setFont(Main.KnightWarriors.deriveFont(25F));
		btnLevelBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tausch(pLevelauswahl, pStartScreen);
			}
		});
		btnLevelBack.setBounds(432, 319, 122, 23);
		pLevelauswahl.add(btnLevelBack);
		
		pPause = new JPanel();
		pPause.setOpaque(false);
		pPause.setBackground(Color.DARK_GRAY);
		contentPane.add(pPause, "name_24353100108700");
		pPause.setLayout(null);
		
		
		
		
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
	public static void setupPauseMenu() {
        // Create a modal dialog for the pause menu
        pPause.setSize(640, 468);
        pPause.setLayout(new BorderLayout());
        lblpausiert = new JLabel("Spiel pausiert", SwingConstants.CENTER);
		lblpausiert.setForeground(new Color(255, 255, 255));
		lblpausiert.setBounds(264, 121, 91, 27);
		pPause.add(lblpausiert, BorderLayout.CENTER);
		pPause.setVisible(true);
		lblpausiert.setFont(Main.KnightWarriors);
       // Create a button to resume
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hidePauseMenu();
            }
        }); }
	 public static void showPauseMenu() {
	       
	        pPause.setVisible(true);
	    }
	  public static void hidePauseMenu() {
	        pPause.setVisible(false);
	  }
	
}
