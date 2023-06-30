package steam;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class SteamProfilById {

	public static JFrame windowProfilById = new JFrame();
	public static MouseAdapter iconClose;
	public static JLabel labelLoader = new JLabel();
	public static JLabel loaderImg = new JLabel();
	public static Container contentPane = new Container();
	public static JLabel Close = new JLabel();
	public static JLabel title = new JLabel();
	public static JLabel LogoJ = new JLabel();
	public static JLabel titleLastGame = new JLabel();
	public static JLayeredPane AvatarUser = new JLayeredPane();
	public static JLabel username = new JLabel();
	public static JLabel firstLastname = new JLabel();
	public static JLabel location = new JLabel();
	public static JLabel lblimage = new JLabel();
	public static JLabel lblimage2 = new JLabel();

	public static void view() {

		windowProfilById.setUndecorated(true);
		windowProfilById.setSize(1200, 794); // Taille de la fenêtre
		windowProfilById.setVisible(false); // Permet de rendre la fenêtre visible
		windowProfilById.setBackground(Color.black);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		windowProfilById.setLocation(dim.width / 2 - windowProfilById.getSize().width / 2,
				dim.height / 2 - windowProfilById.getSize().height / 2);
		windowProfilById.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme tout

		windowProfilById.setResizable(false); // Supprime le redimensionnement de la
												// fenêtre
		windowProfilById.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Changement
																						// de
																						// curseur

		contentPane = windowProfilById.getContentPane();
		contentPane.setBackground(Color.decode("#000"));

		// Groupe des boutons
		GroupLayout groupLayout = new GroupLayout(contentPane);
		contentPane.setLayout(groupLayout);

		ImageIcon loader;
		loader = new ImageIcon(App.class.getResource("/images/steam-neon.gif"));
		loaderImg = new JLabel(loader, SwingConstants.CENTER);

		loaderImg.setLocation(1200 / 2 - 220 / 2, 794 / 2 - 220 / 2);
		loaderImg.setSize(220, 220);
		contentPane.add(loaderImg);

		labelLoader.setText("Récupération du profil...");
		labelLoader.setFont(Fonts.FontPerso.font1);
		labelLoader.setForeground(Color.white);
		labelLoader.setLocation(1200 / 2 - 310 / 2, 794 / 2 - 200 / 3 + 140);
		labelLoader.setSize(310, 200);
		contentPane.add(labelLoader);

		// Button Close
		ImageIcon imageIcon;
		imageIcon = new ImageIcon(App.class.getResource("/images/close.png"));
		Image Images = imageIcon.getImage();
		Image newimg = Images.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale

		// way
		imageIcon = new ImageIcon(newimg);

		contentPane.add(Close);
		Close.setIcon(imageIcon);
		Close.setLocation(1175, 10);
		Close.setSize(15, 15);
		Close.setToolTipText("Fermer");
		Close.setVisible(false);

		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				windowProfilById.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				ImageIcon imageIcon2;
				imageIcon2 = new ImageIcon(getClass().getResource("/images/close-transparent.png"));
				Image Images2 = imageIcon2.getImage();
				Image newimg2 = Images2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale
																								// it
																								// the
																								// smooth
				imageIcon2 = new ImageIcon(newimg2);
				Close.setIcon(imageIcon2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				windowProfilById.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				ImageIcon imageIcon2;
				imageIcon2 = new ImageIcon(getClass().getResource("/images/close.png"));
				Image Images2 = imageIcon2.getImage();
				Image newimg2 = Images2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale
																								// it
																								// the
																								// smooth
				imageIcon2 = new ImageIcon(newimg2);
				Close.setIcon(imageIcon2);
			}
		});

		iconClose = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);
				try {
					iconClose();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			public void iconClose() throws IOException {

				System.out.print(Logs.datefl.format(Logs.DateDuJour) + " -- Vous avez fermé Steamapp voir le profil\n");

				// Class permettant de sauvegarder le logs
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
						+ " -- Vous avez fermé Steamapp voir le profil\n");

				windowProfilById.setVisible(false);

			}
		};

		// Impératif pour que le bouton fonctionne
		Close.addMouseListener(iconClose);

		// Title JPanel
		title.setFont(Fonts.FontPerso.font1);
		title.setForeground(Color.white);
		title.setLocation(70, 20);
		title.setSize(600, 45);
		contentPane.add(title);
		title.setVisible(false);

		// Logo Jpanel
		ImageIcon imageIcon2;
		imageIcon2 = new ImageIcon(App.class.getResource("/images/logo.png"));
		Image Images2 = imageIcon2.getImage();
		Image newimg2 = Images2.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH); // scale it the smooth

		imageIcon2 = new ImageIcon(newimg2);

		LogoJ.setIcon(imageIcon2);
		LogoJ.setLocation(20, 20);
		LogoJ.setSize(45, 45);
		contentPane.add(LogoJ);
		LogoJ.setVisible(false);

		titleLastGame.setFont(Fonts.FontPerso.font4);
		titleLastGame.setForeground(Color.white);
		titleLastGame.setLocation(30, 320);
		titleLastGame.setSize(300, 45);
		contentPane.add(titleLastGame);
		titleLastGame.setVisible(false);
		titleLastGame.setText("Activité récente sur steam");

		AvatarUser.setLocation(30, 130);
		AvatarUser.setSize(168, 168);
		AvatarUser.setBackground(Color.decode("#57cbde"));
		AvatarUser.setOpaque(true);
		contentPane.add(AvatarUser, 2, 0);
		AvatarUser.setVisible(false);

		username.setLocation(230, 130);
		username.setSize(400, 40);
		username.setFont(Fonts.FontPerso.font4);
		username.setForeground(Color.white);
		contentPane.add(username);
		username.setVisible(false);

		firstLastname.setLocation(230, 160);
		firstLastname.setSize(400, 40);
		firstLastname.setFont(Fonts.FontPerso.font2);
		firstLastname.setForeground(Color.white);
		contentPane.add(firstLastname);
		firstLastname.setVisible(false);

		location.setLocation(255, 185);
		location.setSize(400, 40);
		location.setFont(Fonts.FontPerso.font2);
		location.setForeground(Color.white);
		contentPane.add(location);
		location.setVisible(false);

	}

	public static void load() {
		TimeOut.setTimeout(() -> labelLoader.setVisible(false), 2000);
		TimeOut.setTimeout(() -> loaderImg.setVisible(false), 2000);
		TimeOut.setTimeout(() -> Close.setVisible(true), 2001);
		TimeOut.setTimeout(() -> contentPane.setBackground(Color.decode("#1b2838")), 2001);
		TimeOut.setTimeout(() -> title.setVisible(true), 2001);
		TimeOut.setTimeout(() -> LogoJ.setVisible(true), 2001);
		TimeOut.setTimeout(() -> titleLastGame.setVisible(true), 2001);
		TimeOut.setTimeout(() -> AvatarUser.setVisible(true), 2001);
		TimeOut.setTimeout(() -> username.setVisible(true), 2001);
		TimeOut.setTimeout(() -> firstLastname.setVisible(true), 2001);
		TimeOut.setTimeout(() -> location.setVisible(true), 2001);
		TimeOut.setTimeout(() -> lblimage.setVisible(true), 2001);
		TimeOut.setTimeout(() -> lblimage2.setVisible(true), 2001);
	}

	public static void avatarLoad(String GetAvatarProfil, String GetDrapeauProfil) {

		Image image = null;

		try {

			URL url = new URL(GetAvatarProfil);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");

			conn.connect();
			InputStream urlStream = conn.getInputStream();
			image = ImageIO.read(urlStream);

			lblimage.setIcon(new ImageIcon(image));
			lblimage.setBounds(32, 132, 164, 164);
			lblimage.setOpaque(false);
			lblimage.setVisible(false);
			contentPane.add(lblimage, 0, 0);

		} catch (IOException e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

		Image image2 = null;

		try {

			URL url2 = new URL(GetDrapeauProfil);
			URLConnection conn2 = url2.openConnection();
			conn2.setRequestProperty("User-Agent", "Mozilla/5.0");

			conn2.connect();
			InputStream urlStream2 = conn2.getInputStream();
			image2 = ImageIO.read(urlStream2);

			lblimage2.setIcon(new ImageIcon(image2));
			lblimage2.setBounds(230, 200, 16, 11);
			lblimage2.setOpaque(false);
			lblimage2.setVisible(false);
			contentPane.add(lblimage2, 0, 0);

		} catch (IOException e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	public static String find(String theUrl) {
		StringBuilder content = new StringBuilder();
		// Use try and catch to avoid the exceptions
		try {
			URL url = new URL(theUrl); // creating a url object
			URLConnection urlConnection = url.openConnection(); // creating a urlconnection object

			// wrapping the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			// reading from the urlconnection using the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				location.setText(line.trim());
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}
