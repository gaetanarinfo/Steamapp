package steam;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FrameHome extends JFrame {

	private static final long serialVersionUID = 1L;
	public static MouseAdapter iconClose;
	public static JLabel first_label, second_label, three_label, for_label, five_label, six_label;
	public static JButton validateId = new JButton("Valider");
	public static JButton validateAccount = new JButton("Valider");
	public static JFrame window = new JFrame("Steam App");
	public static JButton btnGetIdSteam = new JButton("Récupérer l'id steam");

	public static void Frame() {

		window.setUndecorated(true);
		window.setSize(705, 440); // Taille de la fenêtre
		window.setVisible(true); // Permet de rendre la fenêtre visible
		window.setBackground(Color.black);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme tout les scripts java
		window.setResizable(false); // Supprime le redimensionnement de la fenêtre
		window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Changement de curseur

		Container contentPane = window.getContentPane();
		contentPane.setBackground(Color.decode("#0e0f10"));

		// Groupe des boutons
		GroupLayout groupLayout = new GroupLayout(contentPane);
		contentPane.setLayout(groupLayout);

		ImageIcon loader;
		loader = new ImageIcon(App.class.getResource("/images/steam-neon.gif"));
		first_label = new JLabel(loader, SwingConstants.CENTER);

		first_label.setLocation(705 / 2 - 220 / 2, 440 / 2 - 220 / 2);
		first_label.setSize(220, 220);
		contentPane.add(first_label);

		ImageIcon background = new ImageIcon(App.class.getResource("/images/background2.jpg"));
		second_label = new JLabel(background, SwingConstants.CENTER);
		second_label.setLocation(0, 0);
		second_label.setSize(705, 440);
		contentPane.add(second_label);
		second_label.setVisible(false);

		three_label = new JLabel("CHARGEMENT EN COURS...", SwingConstants.CENTER);
		three_label.setFont(Fonts.FontPerso.font3);
		three_label.setForeground(Color.white);
		three_label.setLocation(705 / 2 - 290 / 2, 440 / 2 - 200 / 2 + 140);
		three_label.setSize(290, 200);
		contentPane.add(three_label);

		TimeOut.setTimeout(() -> first_label.setVisible(false), 3500);
		TimeOut.setTimeout(() -> three_label.setVisible(false), 3500);
		TimeOut.setTimeout(() -> second_label.setVisible(true), 3501);

		// Button Close
		ImageIcon imageIcon2;
		imageIcon2 = new ImageIcon(App.class.getResource("/images/close.png"));
		Image Images2 = imageIcon2.getImage();
		Image newimg2 = Images2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
																						// way
		imageIcon2 = new ImageIcon(newimg2);

		JLabel Close = new JLabel(imageIcon2);
		second_label.add(Close);
		Close.setIcon(imageIcon2);
		Close.setLocation(680, 10);
		Close.setSize(15, 15);
		Close.setToolTipText("Fermer");
		Close.setVisible(false);

		TimeOut.setTimeout(() -> Close.setVisible(true), 3501);

		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				window.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				ImageIcon imageIcon4;
				imageIcon4 = new ImageIcon(getClass().getResource("/images/close-transparent.png"));
				Image Images4 = imageIcon4.getImage();
				Image newimg4 = Images4.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale it the
																								// smooth
				imageIcon4 = new ImageIcon(newimg4);
				Close.setIcon(imageIcon4);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				ImageIcon imageIcon4;
				imageIcon4 = new ImageIcon(getClass().getResource("/images/close.png"));
				Image Images4 = imageIcon4.getImage();
				Image newimg4 = Images4.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale it the
																								// smooth
				imageIcon4 = new ImageIcon(newimg4);
				Close.setIcon(imageIcon4);
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

				System.out.print(Logs.datefl.format(Logs.DateDuJour) + " -- Vous avez fermé Steamapp\n");

				// Class permettant de sauvegarder le logs
				Logs.SaveTextLogs(
						Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- Vous avez fermé Steamapp\n");

				System.exit(0);

			}
		};

		// Impératif pour que le bouton fonctionne
		Close.addMouseListener(iconClose);

	}

}
