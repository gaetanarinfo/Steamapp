package steam;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

public class App {

	static MouseAdapter iconClose;

	public static void setTimeout(Runnable runnable, int delay) {
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				runnable.run();
			} catch (Exception e) {
				System.err.println(e);
			}
		}).start();
	}

	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				System.out.println("-- Démarrage de Steamapp --");

				try {
					try {
						Logs.SaveTextLogs(
								ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- Démarrage de Steamapp\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ReadTextLogs();
				} catch (IOException e1) {
					try {
						Logs.SaveTextLogs(
								ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- Une erreur est survenu\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					e1.printStackTrace();
				}

				// Création d'une instance Window
				JFrame window = new JFrame("Steam App");

				window.setUndecorated(true);
				window.setSize(1280, 720); // Taille de la fenêtre
				window.setVisible(true); // Permet de rendre la fenêtre visibleVous avez ajouté un chat
				window.setBackground(Color.black);
				window.setLocation(280, 200); // Permet de déplacer la fenêtre
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme tout les scripts java
				window.setResizable(false); // Supprime le redimensionnement de la fenêtre
				window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Changement de curseur

				Container contentPane = window.getContentPane();
				contentPane.setBackground(Color.decode("#0e0f10"));

				// Groupe des boutons
				GroupLayout groupLayout = new GroupLayout(contentPane);
				contentPane.setLayout(groupLayout);

				JLabel first_label, second_label, three_label;

				ImageIcon loader = new ImageIcon("res/images/steam-neon.gif");
				first_label = new JLabel(loader, SwingConstants.CENTER);
				first_label.setLocation(530, 220);
				first_label.setSize(220, 220);
				contentPane.add(first_label);

				ImageIcon background = new ImageIcon("res/images/background2.jpg");
				second_label = new JLabel(background, SwingConstants.CENTER);
				second_label.setLocation(0, 0);
				second_label.setSize(1280, 720);
				contentPane.add(second_label);
				second_label.setVisible(false);

				three_label = new JLabel("CHARGEMENT EN COURS...", SwingConstants.CENTER);
				three_label.setFont(FontPerso.font1);
				three_label.setForeground(Color.white);
				three_label.setLocation(450, 420);
				three_label.setSize(380, 200);
				contentPane.add(three_label);

				setTimeout(() -> first_label.setVisible(false), 3500);
				setTimeout(() -> three_label.setVisible(false), 3500);
				setTimeout(() -> second_label.setVisible(true), 3501);

				// Button Close
				ImageIcon imageIcon2;
				imageIcon2 = new ImageIcon("res/images/close.png");
				Image Images2 = imageIcon2.getImage();
				Image newimg2 = Images2.getScaledInstance(26, 26, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
																								// way
				imageIcon2 = new ImageIcon(newimg2);

				JLabel Close = new JLabel(imageIcon2);
				second_label.add(Close);
				Close.setIcon(imageIcon2);
				Close.setLocation(1245, 10);
				Close.setSize(26, 26);
				Close.setToolTipText("Fermer");
				Close.setVisible(false);

				setTimeout(() -> Close.setVisible(true), 3501);

				Close.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						window.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						ImageIcon imageIcon4;
						imageIcon4 = new ImageIcon("res/images/close-transparent.png");
						Image Images4 = imageIcon4.getImage();
						Image newimg4 = Images4.getScaledInstance(26, 26, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
						imageIcon4 = new ImageIcon(newimg4);
						Close.setIcon(imageIcon4);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						ImageIcon imageIcon4;
						imageIcon4 = new ImageIcon("res/images/close.png");
						Image Images4 = imageIcon4.getImage();
						Image newimg4 = Images4.getScaledInstance(26, 26, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
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
						Logs.SaveTextLogs(ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
								+ " -- Vous avez fermé Steamapp\n");

						System.exit(0);

					}
				};

				// Impératif pour que le bouton fonctionne
				Close.addMouseListener(iconClose);
				
				// Title JPanel
				JLabel title = new JLabel("Connexion SteamApp V1", SwingConstants.CENTER);
				title.setFont(FontPerso.font1);
				title.setForeground(Color.white);
				title.setLocation(680, 120);
				title.setSize(380, 200);
				second_label.add(title);

				// Forms login
				JLabel username = new JLabel("Nom d'utilisateur :");
				JLabel password = new JLabel("Mot de passe :");

				username.setLocation(700, 260);
				username.setSize(200, 20);
				username.setForeground(Color.white);
				username.setFont(FontPerso.font3);

				password.setLocation(700, 360);
				password.setSize(200, 20);
				password.setForeground(Color.white);
				password.setFont(FontPerso.font3);

				// Ajout des label formulaire au Pane
				second_label.add(username);
				second_label.add(password);

				JTextField usernameJ = new JTextField("");
				JTextField passwordJ = new JTextField("");

				usernameJ.setLocation(700, 290);
				usernameJ.setSize(260, 50);
				usernameJ.setFont(FontPerso.font2);
				usernameJ.setForeground(Color.white);
				usernameJ.setDocument(new JTextFieldLimit(20));
				usernameJ.setOpaque(false);
				usernameJ.setBorder(new BorderForms());
				usernameJ.setBorder(new BorderForms());
				usernameJ.setBorder(BorderFactory.createCompoundBorder(usernameJ.getBorder(),
						BorderFactory.createEmptyBorder(7, 7, 7, 7)));

				passwordJ.setLocation(700, 390);
				passwordJ.setSize(260, 50);
				passwordJ.setOpaque(false);
				passwordJ.setForeground(Color.white);
				passwordJ.setFont(FontPerso.font2);
				passwordJ.setBorder(new BorderForms());
				passwordJ.setBorder(BorderFactory.createCompoundBorder(passwordJ.getBorder(),
						BorderFactory.createEmptyBorder(7, 7, 7, 7)));

				second_label.add(usernameJ);
				second_label.add(passwordJ);

				// Button Login
				ImageIcon imageIcon3;
				imageIcon3 = new ImageIcon("res/images/login.png");
				Image Images3 = imageIcon3.getImage();
				Image newimg3 = Images3.getScaledInstance(190, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
					
				// way
				imageIcon3 = new ImageIcon(newimg3);

				JLabel Validate = new JLabel(imageIcon3);
				second_label.add(Validate);
				Validate.setIcon(imageIcon3);
				Validate.setLocation(695, 470);
				Validate.setSize(190, 60);
				Validate.setToolTipText("Valider");
				Validate.setOpaque(false);
				
				Validate.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						window.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						ImageIcon imageIcon4;
						imageIcon4 = new ImageIcon("res/images/login-transparent.png");
						Image Images4 = imageIcon4.getImage();
						Image newimg4 = Images4.getScaledInstance(190, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
						imageIcon4 = new ImageIcon(newimg4);
						Validate.setIcon(imageIcon4);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						ImageIcon imageIcon4;
						imageIcon4 = new ImageIcon("res/images/login.png");
						Image Images4 = imageIcon4.getImage();
						Image newimg4 = Images4.getScaledInstance(190, 60, java.awt.Image.SCALE_SMOOTH); // scale it the smooth
						imageIcon4 = new ImageIcon(newimg4);
						Validate.setIcon(imageIcon4);
					}
				});

			}

		});
	}

	// Class pour la personnalisation du texte
	public static class FontPerso {
		static final Font font1 = new Font("Arial", Font.BOLD, 25);
		static final Font font2 = new Font("Arial", Font.BOLD, 12);
		static final Font font3 = new Font("Arial", Font.BOLD, 16);
	}
	//

	public static String ReadTextLogs() throws IOException {

		var fileName = "Logs.txt";
		var filePath = Paths.get(fileName);

		byte[] data;

		data = Files.readAllBytes(filePath);
		var content = new String(data);

		System.out.println(content);

		return content;
	}

	// Class pour la Limitation du texte
	public static class JTextFieldLimit extends PlainDocument {

		private static final long serialVersionUID = 1L;

		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;
			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

	// Bordure des formulaires
	public static class BorderForms implements Border {
		int top, left, bottom, right;
		Color color = null;

		public BorderForms() {
			this.top = 1;
			this.left = 1;
			this.bottom = 1;
			this.right = 1;
			this.color = Color.darkGray;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Insets insets = getBorderInsets(c);
			if (color != null)
				g.setColor(color);

			g.fill3DRect(0, 0, width - insets.right, insets.top, true);

			g.fill3DRect(0, insets.top, insets.left, height - insets.top, true);
			g.fill3DRect(insets.left, height - insets.bottom, width - insets.left, insets.bottom, true);
			g.fill3DRect(width - insets.right, 0, insets.right, height - insets.bottom, true);
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(top, left, bottom, right);
		}

		public boolean isBorderOpaque() {
			return true;
		}
	}
	//

}