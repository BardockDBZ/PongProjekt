package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Shop() {
		setTitle("Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		
		JMenuBar mbShop = new JMenuBar();
		mbShop.setBackground(Color.WHITE);
		setJMenuBar(mbShop);
		
		JMenu mnShopDatei = new JMenu("Datei");
		mbShop.add(mnShopDatei);
		
		JMenuItem mntmShopClose = new JMenuItem("Shop schließen");
		mntmShopClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Shopclose();
			}
		});
		mnShopDatei.add(mntmShopClose);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Shop");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(0, 0, 484, 36);
		contentPane.add(lblNewLabel);
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
