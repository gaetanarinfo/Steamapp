package steam;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class App {

	public static int idLogin;
	public static String usernameLogin;
	public static String passwordLogin;
	public static String saltLogin;

	public static String[] listProfilPseudo = {};
	public static String[] listProfilAvatar = {};
	public static String[] listProfilAvatarFull = {};
	public static String[] listProfilId = {};
	public static String[] listProfilFullName = {};
	public static String[] listProfilDrapeau = {};
	public static String[] listProfilLocCityId = {};
	public static String[] listProfilLocStateCode = {};

	public static JLabel username = new JLabel("Nom d'utilisateur :");
	public static JLabel password = new JLabel("Mot de passe :");
	public static JLabel createAccount = new JLabel("Vous n'avez pas de compte ?");
	public static JLabel userid = new JLabel("Votre id steam :");
	public static JLabel useridError = new JLabel();
	public static JLabel error = new JLabel();
	public static JTextField usernameJ = new JTextField();
	public static JTextField userIdLogin = new JTextField();
	public static JPasswordField passwordJ = new JPasswordField();
	public static JLabel title = new JLabel("SteamApp V1", SwingConstants.LEFT);
	public static JLabel Validate = new JLabel();
	public static JLabel tabLeftBtn1 = new JLabel("Site officiel", SwingConstants.CENTER);
	public static JLabel tabLeftBtn2 = new JLabel("Télécharger le client", SwingConstants.CENTER);
	public static JLabel tabLeftBtn3 = new JLabel("Supprimer les profils", SwingConstants.CENTER);
	public static JLabel tabLeftBtn4 = new JLabel("Fermer", SwingConstants.CENTER);
	public static JFrame windowAddAccount = new JFrame("Steam App - Création d'un compte");

	public static String newPseudoProfil;
	public static String newAvatarProfil;
	public static String newAvatarSmallProfil;
	public static int loccityid;
	public static String locstatecode;
	public static String steamidProfil;
	public static String loccountrycodeProfil;
	public static String nameProfil;

	public static JLabel jl = new JLabel();

	public static JLabel titleAddAccount = new JLabel("SteamApp V1 - Création d'un compte", SwingConstants.LEFT);
	public static JLabel contentAddAccount = new JLabel(
			"<html>Steam est l'endroit idéal pour jouer,<br/> créer et parler jeu vidéo.</html>", SwingConstants.LEFT);
	public static JLabel content2AddAccount = new JLabel(
			"<html>Faites des rencontres, rejoignez des<br/> groupes, formez des clans, participez à<br/> des discussions en jeu et bien plus<br/> encore ! Avec plus de 100 millions<br/> d'ami(e)s (ou d'adversaires) en<br/> puissance, vous n'avez pas fini de vous<br/> amuser.</html>",
			SwingConstants.LEFT);

	public static JLabel usernameRegister = new JLabel("Nom d'utilisateur :");
	public static JLabel passwordRegister = new JLabel("Mot de passe :");
	public static JLabel messageRegister = new JLabel("");
	public static JTextField usernameR = new JTextField();
	public static JPasswordField passwordR = new JPasswordField();
	public static String passwordEnc;
	public static String passwordSalt;

	public static String[] getListLastGameName = {};
	public static String[] getListLastGameImage = {};

	// Login user
	public static void login(Connection conn) {

		try {
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM users LIMIT 1");

			while (res.next()) {
				idLogin = res.getInt("id");
				usernameLogin = res.getString(2);
				passwordLogin = res.getString(3);
				saltLogin = res.getString(4);
			}

		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	// Get list profil user
	public static void profils(Connection conn) {

		try (Statement stmt = conn.createStatement()) {
			String SQL = "SELECT * FROM profils WHERE user_id = " + idLogin + " LIMIT 3";

			boolean results = stmt.execute(SQL);

			// Loop through the available result sets.
			do {
				if (results) {
					ResultSet rs = stmt.getResultSet();

					// Show data from the result set.
					while (rs.next()) {

						String pseudo = rs.getString("pseudo");
						String avatar_small = rs.getString("avatar_small");
						String avatar = rs.getString("avatar");
						String id = rs.getString("steamid");
						String fullName = rs.getString("name");
						String country = rs.getString("country");
						String loccityid = rs.getString("loccityid");
						String locstatecode = rs.getString("locstatecode");

						ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(listProfilPseudo));

						arrayList.add(pseudo);

						listProfilPseudo = arrayList.toArray(listProfilPseudo);

						ArrayList<String> arrayList2 = new ArrayList<String>(Arrays.asList(listProfilAvatar));

						arrayList2.add(avatar_small);

						listProfilAvatar = arrayList2.toArray(listProfilAvatar);

						ArrayList<String> arrayList3 = new ArrayList<String>(Arrays.asList(listProfilId));

						arrayList3.add(id);

						listProfilId = arrayList3.toArray(listProfilId);

						ArrayList<String> arrayList4 = new ArrayList<String>(Arrays.asList(listProfilAvatarFull));

						arrayList4.add(avatar);

						listProfilAvatarFull = arrayList4.toArray(listProfilAvatarFull);

						ArrayList<String> arrayList5 = new ArrayList<String>(Arrays.asList(listProfilFullName));

						arrayList5.add(fullName);

						listProfilFullName = arrayList5.toArray(listProfilFullName);

						ArrayList<String> arrayList6 = new ArrayList<String>(Arrays.asList(listProfilDrapeau));

						arrayList6.add("https://community.cloudflare.steamstatic.com/public/images/countryflags/"
								+ country.toLowerCase() + ".gif");

						listProfilDrapeau = arrayList6.toArray(listProfilDrapeau);

						ArrayList<String> arrayList7 = new ArrayList<String>(Arrays.asList(listProfilLocCityId));

						arrayList7.add(loccityid);

						listProfilLocCityId = arrayList7.toArray(listProfilLocCityId);

						ArrayList<String> arrayList8 = new ArrayList<String>(Arrays.asList(listProfilLocStateCode));

						arrayList8.add(locstatecode);

						listProfilLocStateCode = arrayList8.toArray(listProfilLocStateCode);

					}
				}

				results = stmt.getMoreResults();

			} while (results);
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	// Add Profil
	@SuppressWarnings("null")
	public static void addProfil(Connection conn) throws URISyntaxException, IOException {

		try (Statement stmt = conn.createStatement()) {

			SteamProfil.getProfil(userIdLogin.getText());

			if (newPseudoProfil != null) {

				stmt.executeUpdate("INSERT INTO profils " + "VALUES (NULL, '" + idLogin + "', '" + newPseudoProfil
						+ "', '" + newAvatarProfil + "', '" + newAvatarSmallProfil + "', '" + steamidProfil + "', '"
						+ loccountrycodeProfil + "', '" + nameProfil + "', '" + loccityid + "', '" + locstatecode
						+ "')");

				userIdLogin.setBorder(new BorderForms(Color.decode("#5BCD55")));
				userIdLogin.setBorder(BorderFactory.createCompoundBorder(userIdLogin.getBorder(),
						BorderFactory.createEmptyBorder(7, 7, 7, 7)));

				ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(listProfilPseudo));

				arrayList.add(newPseudoProfil);

				listProfilPseudo = arrayList.toArray(listProfilPseudo);

				ArrayList<String> arrayList2 = new ArrayList<String>(Arrays.asList(listProfilAvatar));

				arrayList2.add(newAvatarProfil);

				listProfilAvatar = arrayList2.toArray(listProfilAvatar);

				TimeOut.setTimeout(() -> {
					ValidateHome.windowAddProfil.setVisible(false);
					ValidateHome.btnPlusProfil.setVisible(false);
				}, 2500);

				TimeOut.setTimeout(() -> {
					try {

						ArrayList<String> commands = new ArrayList<String>();
						List<String> jvmArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();

						// Java
						commands.add(
								System.getProperty("java.home") + File.separator + "bin" + File.separator + "java");

						// Jvm arguments
						for (String jvmArg : jvmArgs) {
							commands.add(jvmArg);
						}

						// Classpath
						commands.add("-cp");
						commands.add(ManagementFactory.getRuntimeMXBean().getClassPath());

						// Class to be executed
						commands.add(App.class.getName());

						File workingDir = null; // Null working dir means that the child uses the same working directory

						String[] env = null; // Null env means that the child uses the same environment

						String[] commandArray = new String[commands.size()];
						commandArray = commands.toArray(commandArray);

						Runtime.getRuntime().exec(commandArray, env, workingDir);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.exit(0);
				}, 3500);

				conn.close();

			} else {

				userIdLogin.setBorder(new BorderForms(Color.decode("#EC0F0F")));
				userIdLogin.setBorder(BorderFactory.createCompoundBorder(userIdLogin.getBorder(),
						BorderFactory.createEmptyBorder(7, 7, 7, 7)));

			}

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	// Remove Profil
	public static void removeProfil(Connection conn) {

		try (Statement stmt = conn.createStatement()) {

			stmt.executeUpdate("DELETE FROM profils WHERE user_id = " + idLogin);

			conn.close();

		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

		tabLeftBtn3.setEnabled(false);
		tabLeftBtn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tabLeftBtn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				tabLeftBtn3.setBackground(Color.decode("#32353c"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				tabLeftBtn3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				tabLeftBtn3.setBackground(Color.decode("#32353c"));
			}
		});

		jl.setVisible(false);

		ValidateHome.addProfilTitle.setText("Vos profils (0)");
	}

	// Create account
	@SuppressWarnings("deprecation")
	public static void createAccount(Connection conn) {

		try (Statement stmt = conn.createStatement()) {

			String SQL = "SELECT username FROM users WHERE username = '" + usernameR.getText().trim() + "'";

			boolean results = stmt.execute(SQL);

			// Loop through the available result sets.

			if (results) {
				ResultSet rs = stmt.getResultSet();

				if (rs.next()) {

					messageRegister.setVisible(true);
					messageRegister.setForeground(Color.red);
					messageRegister.setText(usernameR.getText().trim() + " existe déjà sur steamapp !");

				} else {

					Encrypt.encrypt(passwordR.getText().trim());

					stmt.executeUpdate("INSERT INTO users " + "VALUES (NULL, '" + usernameR.getText().trim() + "', '"
							+ passwordEnc + "', '" + passwordSalt + "')");

					messageRegister.setVisible(true);
					messageRegister.setForeground(Color.decode("#68D17C"));
					messageRegister.setText("Votre compte a bien été créer !");

					usernameR.setBorder(new BorderForms(Color.decode("#68D17C")));
					usernameR.setBorder(BorderFactory.createCompoundBorder(usernameR.getBorder(),
							BorderFactory.createEmptyBorder(7, 7, 7, 7)));

					passwordR.setBorder(new BorderForms(Color.decode("#68D17C")));
					passwordR.setBorder(BorderFactory.createCompoundBorder(passwordR.getBorder(),
							BorderFactory.createEmptyBorder(7, 7, 7, 7)));

					conn.close();

				}

			}

		}
		// Handle any errors that may have occurred.
		catch (

		SQLException e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}

	}

	public static void getProfilById(String profilId) {

		SteamProfil.getProfilLastGameById(profilId);
		SteamProfilById.load();

	}

	// Class primary main
	public static void main(String[] args) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				try {
					Logs.LogsDebug("");
					Logs.ReadTextLogs();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				try {
					try {
						Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
								+ " -- Démarrage de Steamapp\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Logs.ReadTextLogs();
				} catch (IOException e1) {
					try {
						Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
								+ " -- Une erreur est survenu\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					e1.printStackTrace();
				}

				// Création d'une instance Window
				FrameHome.Frame();

				// Title JPanel
				title.setFont(Fonts.FontPerso.font1);
				title.setForeground(Color.white);
				title.setLocation(70, 20);
				title.setSize(600, 45);
				FrameHome.second_label.add(title);

				// Logo Jpanel
				ImageIcon imageIcon4;
				imageIcon4 = new ImageIcon(getClass().getResource("/images/logo.png"));
				Image Images4 = imageIcon4.getImage();
				Image newimg4 = Images4.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH); // scale it the smooth

				imageIcon4 = new ImageIcon(newimg4);

				JLabel LogoJ = new JLabel(imageIcon4);

				LogoJ.setIcon(imageIcon4);
				LogoJ.setLocation(20, 20);
				LogoJ.setSize(45, 45);
				FrameHome.second_label.add(LogoJ);

				// Forms login
				windowAddAccount.setUndecorated(true);
				windowAddAccount.setSize(1200, 794); // Taille de la fenêtre
				windowAddAccount.setVisible(false); // Permet de rendre la fenêtre visible
				windowAddAccount.setBackground(Color.black);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				windowAddAccount.setLocation(dim.width / 2 - windowAddAccount.getSize().width / 2,
						dim.height / 2 - windowAddAccount.getSize().height / 2);
				windowAddAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme tout
																					// les scripts
																					// java
				windowAddAccount.setResizable(false); // Supprime le redimensionnement de la
														// fenêtre
				windowAddAccount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Changement
																								// de
																								// curseur

				Container contentPane = windowAddAccount.getContentPane();
				contentPane.setBackground(Color.decode("#1d1e23"));

				// Groupe des boutons
				GroupLayout groupLayout = new GroupLayout(contentPane);
				contentPane.setLayout(groupLayout);

				createAccount.setLocation(300, 360);
				createAccount.setSize(210, 20);
				createAccount.setForeground(Color.lightGray);
				createAccount.setFont(Fonts.FontPerso.font2);

				createAccount.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						FrameHome.window.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						createAccount.setForeground(Color.darkGray);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						FrameHome.window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						createAccount.setForeground(Color.lightGray);
					}
				});

				ValidateHome.createAccountMouse = new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent me) {
						super.mouseClicked(me);
						try {
							createAccountMouse();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}

					public void createAccountMouse() throws IOException {

						System.out.print(Logs.datefl.format(Logs.DateDuJour)
								+ " -- Vous avez ouvert Steamapp création d'un compte\n");

						// Class permettant de sauvegarder le logs
						Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
								+ " -- Vous avez ouvert Steamapp création d'un compte\n");

						windowAddAccount.setVisible(true);
						FrameHome.five_label = new JLabel("", SwingConstants.CENTER);
						FrameHome.five_label.setLocation(0, 0);
						FrameHome.five_label.setSize(1200, 794);
						contentPane.add(FrameHome.five_label);
						FrameHome.five_label.setVisible(true);

						// Title JPanel
						titleAddAccount.setFont(Fonts.FontPerso.font1);
						titleAddAccount.setForeground(Color.white);
						titleAddAccount.setLocation(70, 20);
						titleAddAccount.setSize(600, 45);
						FrameHome.five_label.add(titleAddAccount);

						ImageIcon imageIcon4;
						imageIcon4 = new ImageIcon(getClass().getResource("/images/logo.png"));
						Image Images4 = imageIcon4.getImage();
						Image newimg4 = Images4.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH); // scale it the
																										// smooth

						imageIcon4 = new ImageIcon(newimg4);

						JLabel LogoJ = new JLabel(imageIcon4);

						LogoJ.setIcon(imageIcon4);
						LogoJ.setLocation(20, 20);
						LogoJ.setSize(45, 45);
						FrameHome.five_label.add(LogoJ);

						contentAddAccount.setFont(Fonts.FontPerso.font1);
						contentAddAccount.setForeground(Color.white);
						contentAddAccount.setLocation(70, 50);
						contentAddAccount.setSize(600, 200);
						FrameHome.five_label.add(contentAddAccount);

						content2AddAccount.setFont(Fonts.FontPerso.font5);
						content2AddAccount.setForeground(Color.white);
						content2AddAccount.setLocation(70, 130);
						content2AddAccount.setSize(600, 300);
						FrameHome.five_label.add(content2AddAccount);

						// Create account
						usernameRegister.setLocation(70, 380);
						usernameRegister.setSize(250, 20);
						usernameRegister.setForeground(Color.decode("#1999ff"));
						usernameRegister.setFont(Fonts.FontPerso.font3);

						passwordRegister.setLocation(70, 480);
						passwordRegister.setSize(250, 15);
						passwordRegister.setForeground(Color.decode("#1999ff"));
						passwordRegister.setFont(Fonts.FontPerso.font3);

						messageRegister.setLocation(70, 640);
						messageRegister.setSize(280, 15);
						messageRegister.setForeground(Color.decode("#68D17C"));
						messageRegister.setFont(Fonts.FontPerso.font3);
						messageRegister.setVisible(false);

						// Ajout des label formulaire au Pane
						FrameHome.five_label.add(usernameRegister);
						FrameHome.five_label.add(passwordRegister);
						FrameHome.five_label.add(messageRegister);

						usernameR.setLocation(70, 410);
						usernameR.setSize(280, 50);
						usernameR.setFont(Fonts.FontPerso.font2);
						usernameR.setForeground(Color.white);
						usernameR.setDocument(new JTextFieldLimit(20));

						usernameR.setBorder(new BorderForms(Color.darkGray));
						usernameR.setBackground(Color.decode("#32353c"));
						usernameR.setBorder(BorderFactory.createCompoundBorder(usernameR.getBorder(),
								BorderFactory.createEmptyBorder(10, 10, 10, 10)));

						passwordR.setLocation(70, 505);
						passwordR.setSize(280, 50);

						passwordR.setForeground(Color.white);
						passwordR.setFont(Fonts.FontPerso.font2);
						passwordR.setBackground(Color.decode("#32353c"));
						passwordR.setBorder(new BorderForms(Color.darkGray));
						passwordR.setBorder(BorderFactory.createCompoundBorder(passwordR.getBorder(),
								BorderFactory.createEmptyBorder(10, 10, 10, 10)));

						usernameR.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								usernameR.setBackground(Color.decode("#3b3f47"));
							}

							@Override
							public void mouseExited(MouseEvent e) {
								usernameR.setBackground(Color.decode("#32353c"));
							}
						});

						passwordR.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								passwordR.setBackground(Color.decode("#3b3f47"));
							}

							@Override
							public void mouseExited(MouseEvent e) {
								passwordR.setBackground(Color.decode("#32353c"));
							}
						});

						FrameHome.five_label.add(usernameR);
						FrameHome.five_label.add(passwordR);

						FrameHome.validateAccount.setLocation(70, 580);
						FrameHome.validateAccount.setSize(210, 40);
						FrameHome.validateAccount.setFont(Fonts.FontPerso.font2);
						FrameHome.validateAccount.setForeground(Color.white);
						FrameHome.validateAccount.setHorizontalAlignment(SwingConstants.CENTER);

						FrameHome.validateAccount.setBorder(new BorderForms(Color.darkGray));
						FrameHome.validateAccount.setBackground(Color.decode("#32353c"));
						FrameHome.validateAccount
								.setBorder(BorderFactory.createCompoundBorder(FrameHome.validateAccount.getBorder(),
										BorderFactory.createEmptyBorder(10, 10, 10, 10)));

						FrameHome.five_label.add(FrameHome.validateAccount);

						FrameHome.validateAccount.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								FrameHome.validateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								FrameHome.validateAccount.setBackground(Color.decode("#3b3f47"));
							}

							@Override
							public void mouseExited(MouseEvent e) {
								FrameHome.validateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								FrameHome.validateAccount.setBackground(Color.decode("#32353c"));

							}
						});

						ValidateHome.validateAccountMouse = new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								super.mouseClicked(me);
								try {
									validateAccountMouse();
								} catch (IOException e) {

									e.printStackTrace();
								}
							}

							@SuppressWarnings("deprecation")
							public void validateAccountMouse() throws IOException {

								if (usernameR.getText().length() <= 0) {
									usernameR.setBorder(new BorderForms(Color.red));
									usernameR.setBorder(BorderFactory.createCompoundBorder(usernameR.getBorder(),
											BorderFactory.createEmptyBorder(7, 7, 7, 7)));
								}

								if (passwordR.getText().length() <= 0) {
									passwordR.setBorder(new BorderForms(Color.red));
									passwordR.setBorder(BorderFactory.createCompoundBorder(passwordR.getBorder(),
											BorderFactory.createEmptyBorder(7, 7, 7, 7)));
								}

								if (usernameR.getText().length() >= 2 && passwordR.getText().length() >= 2) {

									Database.createAccounts();

								}

							}
						};

						// Impératif pour que le bouton fonctionne
						FrameHome.validateAccount.addMouseListener(ValidateHome.validateAccountMouse);

						// Button Close
						ImageIcon imageIcon6;
						imageIcon6 = new ImageIcon(getClass().getResource("/images/close.png"));
						Image Images6 = imageIcon6.getImage();
						Image newimg6 = Images6.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale

						// way
						imageIcon6 = new ImageIcon(newimg6);

						JLabel Close3 = new JLabel(imageIcon6);
						FrameHome.five_label.add(Close3);
						Close3.setIcon(imageIcon6);
						Close3.setLocation(1175, 10);
						Close3.setSize(15, 15);
						Close3.setToolTipText("Fermer");

						Close3.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								windowAddAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								ImageIcon imageIcon4;
								imageIcon4 = new ImageIcon(getClass().getResource("/images/close-transparent.png"));
								Image Images4 = imageIcon4.getImage();
								Image newimg4 = Images4.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale
																												// it
																												// the
																												// smooth
								imageIcon4 = new ImageIcon(newimg4);
								Close3.setIcon(imageIcon4);
							}

							@Override
							public void mouseExited(MouseEvent e) {
								windowAddAccount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								ImageIcon imageIcon4;
								imageIcon4 = new ImageIcon(getClass().getResource("/images/close.png"));
								Image Images4 = imageIcon4.getImage();
								Image newimg4 = Images4.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale
																												// it
																												// the
																												// smooth
								imageIcon4 = new ImageIcon(newimg4);
								Close3.setIcon(imageIcon4);
							}
						});

						ValidateHome.iconClose3 = new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								super.mouseClicked(me);
								try {
									iconClose3();
								} catch (IOException e) {

									e.printStackTrace();
								}
							}

							public void iconClose3() throws IOException {

								System.out.print(Logs.datefl.format(Logs.DateDuJour)
										+ " -- Vous avez fermé Steamap ajout d'un compte\n");

								// Class permettant de sauvegarder le logs
								Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
										+ " -- Vous avez fermé Steamapp ajout d'un compte\n");

								windowAddAccount.setVisible(false);

							}
						};

						// Impératif pour que le bouton fonctionne
						Close3.addMouseListener(ValidateHome.iconClose3);

						// Button Close
						ImageIcon imageIcon7;
						imageIcon7 = new ImageIcon(getClass().getResource("/images/about.gif"));

						JLabel About = new JLabel(imageIcon7);
						FrameHome.five_label.add(About);
						About.setIcon(imageIcon7);
						About.setLocation(315, 0);
						About.setSize(888, 794);

					}

				};

				// Impératif pour que le bouton fonctionne
				createAccount.addMouseListener(ValidateHome.createAccountMouse);

				error.setLocation(300, 380);
				error.setSize(420, 40);
				error.setForeground(Color.red);
				error.setFont(Fonts.FontPerso.font3);
				error.setVisible(false);

				username.setLocation(300, 100);
				username.setSize(250, 20);
				username.setForeground(Color.decode("#1999ff"));
				username.setFont(Fonts.FontPerso.font3);

				password.setLocation(300, 200);
				password.setSize(250, 15);
				password.setForeground(Color.decode("#1999ff"));
				password.setFont(Fonts.FontPerso.font3);

				// Ajout des label formulaire au Pane
				FrameHome.second_label.add(username);
				FrameHome.second_label.add(error);
				FrameHome.second_label.add(createAccount);
				FrameHome.second_label.add(password);

				usernameJ.setLocation(300, 130);
				usernameJ.setSize(280, 50);
				usernameJ.setFont(Fonts.FontPerso.font2);
				usernameJ.setForeground(Color.white);
				usernameJ.setDocument(new JTextFieldLimit(20));

				usernameJ.setBorder(new BorderForms(Color.darkGray));
				usernameJ.setBackground(Color.decode("#32353c"));
				usernameJ.setBorder(BorderFactory.createCompoundBorder(usernameJ.getBorder(),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

				passwordJ.setLocation(300, 230);
				passwordJ.setSize(280, 50);

				passwordJ.setForeground(Color.white);
				passwordJ.setFont(Fonts.FontPerso.font2);
				passwordJ.setBackground(Color.decode("#32353c"));
				passwordJ.setBorder(new BorderForms(Color.darkGray));
				passwordJ.setBorder(BorderFactory.createCompoundBorder(passwordJ.getBorder(),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

				usernameJ.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						usernameJ.setBackground(Color.decode("#3b3f47"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						usernameJ.setBackground(Color.decode("#32353c"));
					}
				});

				passwordJ.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						passwordJ.setBackground(Color.decode("#3b3f47"));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						passwordJ.setBackground(Color.decode("#32353c"));
					}
				});

				FrameHome.second_label.add(usernameJ);
				FrameHome.second_label.add(passwordJ);

				usernameJ.setText("test");
				passwordJ.setText("test");

				// Button Login
				ImageIcon imageIcon3;
				imageIcon3 = new ImageIcon(getClass().getResource("/images/login.png"));
				Image Images3 = imageIcon3.getImage();
				Image newimg3 = Images3.getScaledInstance(196, 44, java.awt.Image.SCALE_SMOOTH); // scale it the smooth

				// way
				imageIcon3 = new ImageIcon(newimg3);

				FrameHome.second_label.add(Validate);
				Validate.setIcon(imageIcon3);
				Validate.setLocation(300, 300);
				Validate.setSize(196, 44);

				Validate.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						FrameHome.window.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						ImageIcon imageIcon4;
						imageIcon4 = new ImageIcon(getClass().getResource("/images/login-transparent.png"));
						Image Images4 = imageIcon4.getImage();
						Image newimg4 = Images4.getScaledInstance(196, 44, java.awt.Image.SCALE_SMOOTH); // scale it the
																											// smooth
						imageIcon4 = new ImageIcon(newimg4);
						Validate.setIcon(imageIcon4);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						FrameHome.window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						ImageIcon imageIcon4;
						imageIcon4 = new ImageIcon(getClass().getResource("/images/login.png"));
						Image Images4 = imageIcon4.getImage();
						Image newimg4 = Images4.getScaledInstance(196, 44, java.awt.Image.SCALE_SMOOTH); // scale it the
																											// smooth
						imageIcon4 = new ImageIcon(newimg4);
						Validate.setIcon(imageIcon4);
					}
				});

				ValidateHome.show();

			}

		});
	}

	// Create jlabel list
	public static JLabel createJLabel(ImageIcon imageIcon3) {
		JLabel jl = new JLabel(imageIcon3);

		return jl;
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

		public BorderForms(Color color) {
			this.top = 1;
			this.left = 1;
			this.bottom = 1;
			this.right = 1;
			this.color = color;
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

	public class RoundedBorder extends AbstractBorder {

		private static final long serialVersionUID = 1L;
		private final Color color;
		private final int gap;

		public RoundedBorder(Color c, int g) {
			color = c;
			gap = g;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(color);
			g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, gap, gap));
			g2d.dispose();
		}

		public Insets getBorderInsets(Component c) {
			return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
		}

		public Insets getBorderInsets(Component c, Insets insets) {
			insets.left = insets.top = insets.right = insets.bottom = gap / 2;
			return insets;
		}

		public boolean isBorderOpaque() {
			return false;
		}
	}

}