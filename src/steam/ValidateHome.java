package steam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import steam.App.BorderForms;
import steam.App.JTextFieldLimit;

public class ValidateHome {

	public static JFrame windowAddProfil = new JFrame("Steam App - Création d'un profil");
	public static JLabel btnPlusProfil = new JLabel();
	public static JLabel addProfilTitle = new JLabel();
	public static MouseAdapter validateAccountMouse;
	public static MouseAdapter createAccountMouse;
	public static MouseAdapter tabLeftBtn1Mouse;
	public static MouseAdapter tabLeftBtn2Mouse;
	public static MouseAdapter tabLeftBtn3Mouse;
	public static MouseAdapter tabLeftBtn4Mouse;
	public static MouseAdapter btnValidate;
	public static MouseAdapter addProfilValidate;
	public static MouseAdapter iconClose2;
	public static MouseAdapter addProfil;
	public static MouseAdapter iconClose3;
	public static MouseAdapter btnGetIdSteamMouse;
	public static JLabel tabTemp = new JLabel();

	public static void show() {

		btnValidate = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				super.mouseClicked(me);
				try {
					btnValidate();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			@SuppressWarnings("deprecation")
			public void btnValidate() throws IOException {

				String getValueUsername = App.usernameJ.getText().trim();
				String getValuePassword = App.passwordJ.getText().trim();

				if (App.usernameJ.getText().isEmpty()) {
					App.usernameJ.setBorder(new BorderForms(Color.red));
					App.usernameJ.setBorder(BorderFactory.createCompoundBorder(App.usernameJ.getBorder(),
							BorderFactory.createEmptyBorder(7, 7, 7, 7)));
				} else {
					App.usernameJ.setBorder(new BorderForms(Color.darkGray));
					App.usernameJ.setBorder(BorderFactory.createCompoundBorder(App.usernameJ.getBorder(),
							BorderFactory.createEmptyBorder(7, 7, 7, 7)));
				}

				if (App.passwordJ.getText().isEmpty()) {
					App.passwordJ.setBorder(new BorderForms(Color.red));
					App.passwordJ.setBorder(BorderFactory.createCompoundBorder(App.passwordJ.getBorder(),
							BorderFactory.createEmptyBorder(7, 7, 7, 7)));
				} else {
					App.passwordJ.setFont(Fonts.FontPerso.font2);
					App.passwordJ.setBorder(BorderFactory.createCompoundBorder(App.passwordJ.getBorder(),
							BorderFactory.createEmptyBorder(7, 7, 7, 7)));
				}

				if (!App.passwordJ.getText().isEmpty() && !App.usernameJ.getText().isEmpty()) {

					Database.login();

					// Permet de vérifier si le mot de passe (True or False)
					Boolean verifPassword = Encrypt.decrypt(getValuePassword, App.passwordLogin, App.saltLogin);

					// Permet de vérifier si le nom d'utilisateur est bon ou le mot de passe (True
					// or False)
					if (App.usernameLogin.equals(getValueUsername) && verifPassword) {

						App.title.setText(App.title.getText() + " - " + App.usernameLogin);

						Database.profils();

						App.error.setVisible(false);

						FrameHome.second_label.setVisible(false);
						FrameHome.first_label.setVisible(true);
						FrameHome.three_label.setText("CONNEXION EN COURS...");
						FrameHome.three_label.setVisible(true);

						try {
							Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
									+ "Chargement en cours..." + " \n");
						} catch (IOException t) {
							// TODO Auto-generated catch block
							t.printStackTrace();
						}

						TimeOut.setTimeout(() -> {
							FrameHome.three_label.setText("CHARGEMENT DES PROFILS...");

							try {
								Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
										+ "Chargement des profils..." + " \n");
							} catch (IOException t) {
								// TODO Auto-generated catch block
								t.printStackTrace();
							}
						}, 2000);

						// Fin du chargement du profil
						TimeOut.setTimeout(() -> {
							FrameHome.first_label.setVisible(false);
							FrameHome.three_label.setVisible(false);
						}, 4000);

						JLabel[] tab = new JLabel[App.listProfilPseudo.length]; // instance du tableau

						// Création et vue des profils
						TimeOut.setTimeout(() -> {
							App.usernameJ.setVisible(false);
							App.passwordJ.setVisible(false);
							App.createAccount.setVisible(false);
							App.username.setVisible(false);
							App.password.setVisible(false);
							App.Validate.setVisible(false);
							App.title.setVisible(true);
							FrameHome.second_label.setVisible(true);

						}, 4001);

						ImageIcon imageIconAddProfil;
						imageIconAddProfil = new ImageIcon(getClass().getResource("/images/plus.png"));
						Image ImagesAddProfil = imageIconAddProfil.getImage();
						Image newimgaddprofil = ImagesAddProfil.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth

						// way
						imageIconAddProfil = new ImageIcon(newimgaddprofil);

						btnPlusProfil = new JLabel(imageIconAddProfil, SwingConstants.LEFT);
						FrameHome.second_label.add(btnPlusProfil);
						btnPlusProfil.setIcon(imageIconAddProfil);
						btnPlusProfil.setLocation(475, 118);
						btnPlusProfil.setSize(35, 35);

						windowAddProfil.setUndecorated(true);
						windowAddProfil.setSize(400, 400); // Taille de la fenêtre
						windowAddProfil.setVisible(false); // Permet de rendre la fenêtre visible
						windowAddProfil.setBackground(Color.black);
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						windowAddProfil.setLocation(dim.width / 2 - windowAddProfil.getSize().width / 2,
								dim.height / 2 - windowAddProfil.getSize().height / 2);
						windowAddProfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme tout
																						// les scripts
																						// java
						windowAddProfil.setResizable(false); // Supprime le redimensionnement de la
																// fenêtre
						windowAddProfil.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Changement
																										// de
																										// curseur

						Container contentPane = windowAddProfil.getContentPane();
						contentPane.setBackground(Color.decode("#0e0f10"));

						// Groupe des boutons
						GroupLayout groupLayout = new GroupLayout(contentPane);
						contentPane.setLayout(groupLayout);

						if (App.listProfilAvatar.length >= 3) {
							btnPlusProfil.setVisible(false);
						}

						btnPlusProfil.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								FrameHome.window.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								ImageIcon imageIconAddProfil;
								imageIconAddProfil = new ImageIcon(getClass().getResource("/images/plus-transparent.png"));
								Image ImagesAddProfil = imageIconAddProfil.getImage();
								Image newimgaddprofil = ImagesAddProfil.getScaledInstance(35, 35,
										java.awt.Image.SCALE_SMOOTH); // scale it the smooth
								imageIconAddProfil = new ImageIcon(newimgaddprofil);
								btnPlusProfil.setIcon(imageIconAddProfil);
							}

							@Override
							public void mouseExited(MouseEvent e) {
								FrameHome.window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								ImageIcon imageIconAddProfil;
								imageIconAddProfil = new ImageIcon(getClass().getResource("/images/plus.png"));
								Image ImagesAddProfil = imageIconAddProfil.getImage();
								Image newimgaddprofil = ImagesAddProfil.getScaledInstance(35, 35,
										java.awt.Image.SCALE_SMOOTH); // scale it the smooth
								imageIconAddProfil = new ImageIcon(newimgaddprofil);
								btnPlusProfil.setIcon(imageIconAddProfil);
							}
						});

						addProfil = new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								super.mouseClicked(me);
								try {
									addProfil();
								} catch (IOException e) {

									e.printStackTrace();
								}
							}

							public void addProfil() throws IOException {

								System.out.print(Logs.datefl.format(Logs.DateDuJour)
										+ " -- Vous avez ouvert Steamapp ajout d'un profil\n");

								// Class permettant de sauvegarder le logs
								Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
										+ " -- Vous avez ouvert Steamapp ajout d'un \n");

								windowAddProfil.setVisible(true);

								FrameHome.for_label = new JLabel("", SwingConstants.CENTER);
								FrameHome.for_label.setLocation(0, 0);
								FrameHome.for_label.setSize(400, 400);
								contentPane.add(FrameHome.for_label);
								FrameHome.for_label.setVisible(true);

								// Button Close
								ImageIcon imageIcon6;
								imageIcon6 = new ImageIcon(getClass().getResource("/images/close.png"));
								Image Images6 = imageIcon6.getImage();
								Image newimg6 = Images6.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale

								// way
								imageIcon6 = new ImageIcon(newimg6);

								JLabel Close2 = new JLabel(imageIcon6);
								FrameHome.for_label.add(Close2);
								Close2.setIcon(imageIcon6);
								Close2.setLocation(375, 10);
								Close2.setSize(15, 15);
								Close2.setToolTipText("Fermer");

								Close2.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										windowAddProfil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										ImageIcon imageIcon4;
										imageIcon4 = new ImageIcon(getClass().getResource("/images/close-transparent.png"));
										Image Images4 = imageIcon4.getImage();
										Image newimg4 = Images4.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth
										imageIcon4 = new ImageIcon(newimg4);
										Close2.setIcon(imageIcon4);
									}

									@Override
									public void mouseExited(MouseEvent e) {
										windowAddProfil.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										ImageIcon imageIcon4;
										imageIcon4 = new ImageIcon(getClass().getResource("/images/close.png"));
										Image Images4 = imageIcon4.getImage();
										Image newimg4 = Images4.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth
										imageIcon4 = new ImageIcon(newimg4);
										Close2.setIcon(imageIcon4);
									}
								});

								iconClose2 = new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent me) {
										super.mouseClicked(me);
										try {
											iconClose2();
										} catch (IOException e) {

											e.printStackTrace();
										}
									}

									public void iconClose2() throws IOException {

										System.out.print(Logs.datefl.format(Logs.DateDuJour)
												+ " -- Vous avez fermé Steamap ajout d'un profil\n");

										// Class permettant de sauvegarder le logs
										Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
												+ " -- Vous avez fermé Steamapp ajout d'un profil\n");

										windowAddProfil.setVisible(false);

									}
								};

								// Impératif pour que le bouton fonctionne
								Close2.addMouseListener(iconClose2);

								ImageIcon iconAddProfil;
								iconAddProfil = new ImageIcon(getClass().getResource("/images/plus.png"));
								Image addProfil = iconAddProfil.getImage();
								Image newaddprofil = addProfil.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH); // scale
																														// it
																														// the
																														// smooth

								// way
								iconAddProfil = new ImageIcon(newaddprofil);

								JLabel btnPlusProfil = new JLabel(iconAddProfil, SwingConstants.LEFT);
								FrameHome.for_label.add(btnPlusProfil);
								btnPlusProfil.setIcon(iconAddProfil);
								btnPlusProfil.setLocation(400 / 2 - 75 / 2, 20);
								btnPlusProfil.setSize(75, 75);

								JLabel titleAddProfil = new JLabel("Ajouter un profil", SwingConstants.LEFT);
								titleAddProfil.setFont(Fonts.FontPerso.font1);
								titleAddProfil.setForeground(Color.white);
								titleAddProfil.setLocation(400 / 2 - 210 / 2, 110);
								titleAddProfil.setSize(210, 30);
								FrameHome.for_label.add(titleAddProfil);

								App.userid.setLocation(95, 170);
								App.userid.setSize(170, 20);
								App.userid.setForeground(Color.decode("#1999ff"));
								App.userid.setFont(Fonts.FontPerso.font3);
								FrameHome.for_label.add(App.userid);

								App.userIdLogin.setLocation(95, 200);
								App.userIdLogin.setSize(210, 40);
								App.userIdLogin.setFont(Fonts.FontPerso.font2);
								App.userIdLogin.setForeground(Color.white);
								App.userIdLogin.setDocument(new JTextFieldLimit(17));

								App.useridError.setLocation(95, 320);
								App.useridError.setSize(210, 50);
								App.useridError.setForeground(Color.orange);
								App.useridError.setFont(Fonts.FontPerso.font3);
								FrameHome.for_label.add(App.useridError);
								App.useridError.setVisible(false);

								App.userIdLogin.setBorder(new BorderForms(Color.darkGray));
								App.userIdLogin.setBackground(Color.decode("#32353c"));
								App.userIdLogin.setBorder(BorderFactory.createCompoundBorder(
										App.userIdLogin.getBorder(), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

								FrameHome.for_label.add(App.userIdLogin);

								App.userIdLogin.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										App.userIdLogin.setBackground(Color.decode("#3b3f47"));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										App.userIdLogin.setBackground(Color.decode("#32353c"));
									}
								});

								FrameHome.validateId.setLocation(95, 260);
								FrameHome.validateId.setSize(210, 40);
								FrameHome.validateId.setFont(Fonts.FontPerso.font2);
								FrameHome.validateId.setForeground(Color.white);
								FrameHome.validateId.setHorizontalAlignment(SwingConstants.CENTER);

								FrameHome.validateId.setBorder(new BorderForms(Color.darkGray));
								FrameHome.validateId.setBackground(Color.decode("#32353c"));
								FrameHome.validateId
										.setBorder(BorderFactory.createCompoundBorder(FrameHome.validateId.getBorder(),
												BorderFactory.createEmptyBorder(10, 10, 10, 10)));

								FrameHome.for_label.add(FrameHome.validateId);

								FrameHome.validateId.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										FrameHome.validateId.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										FrameHome.validateId.setBackground(Color.decode("#3b3f47"));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										FrameHome.validateId
												.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										FrameHome.validateId.setBackground(Color.decode("#32353c"));

									}
								});

								addProfilValidate = new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent me) {
										super.mouseClicked(me);
										try {
											addProfilValidate();
										} catch (IOException e) {

											e.printStackTrace();
										}
									}

									public void addProfilValidate() throws IOException {

										if (App.userIdLogin.getText().length() <= 2) {
											App.userIdLogin.setBorder(new BorderForms(Color.red));
											App.userIdLogin.setBorder(
													BorderFactory.createCompoundBorder(App.userIdLogin.getBorder(),
															BorderFactory.createEmptyBorder(7, 7, 7, 7)));
										} else {
											App.userIdLogin.setBorder(new BorderForms(Color.darkGray));
											App.userIdLogin.setBackground(Color.decode("#32353c"));
											App.userIdLogin.setBorder(
													BorderFactory.createCompoundBorder(App.userIdLogin.getBorder(),
															BorderFactory.createEmptyBorder(10, 10, 10, 10)));

											System.out.print(Logs.datefl.format(Logs.DateDuJour)
													+ " -- Vous avez ajouter un profil\n");

											// Class permettant de sauvegarder le logs
											Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
													+ " -- Vous avez ajouter un profil \n");

											Database.addProfils();

										}

									}

								};

								// Impératif pour que le bouton fonctionne
								FrameHome.validateId.addMouseListener(addProfilValidate);

								FrameHome.btnGetIdSteam.setLocation(95, 330);
								FrameHome.btnGetIdSteam.setSize(210, 40);
								FrameHome.btnGetIdSteam.setFont(Fonts.FontPerso.font2);
								FrameHome.btnGetIdSteam.setForeground(Color.white);
								FrameHome.btnGetIdSteam.setHorizontalAlignment(SwingConstants.CENTER);

								FrameHome.btnGetIdSteam.setBorder(new BorderForms(Color.darkGray));
								FrameHome.btnGetIdSteam.setBackground(Color.decode("#32353c"));
								FrameHome.btnGetIdSteam.setBorder(
										BorderFactory.createCompoundBorder(FrameHome.btnGetIdSteam.getBorder(),
												BorderFactory.createEmptyBorder(10, 10, 10, 10)));

								FrameHome.for_label.add(FrameHome.btnGetIdSteam);

								FrameHome.btnGetIdSteam.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										FrameHome.btnGetIdSteam
												.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										FrameHome.btnGetIdSteam.setBackground(Color.decode("#3b3f47"));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										FrameHome.btnGetIdSteam
												.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										FrameHome.btnGetIdSteam.setBackground(Color.decode("#32353c"));

									}
								});

								btnGetIdSteamMouse = new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent me) {
										super.mouseClicked(me);
										try {
											btnGetIdSteamMouse();
										} catch (IOException e) {

											e.printStackTrace();
										}
									}

									public void btnGetIdSteamMouse() throws IOException {

										openWebpage("https://profile.tf/");

									}

								};

								// Impératif pour que le bouton fonctionne
								FrameHome.btnGetIdSteam.addMouseListener(btnGetIdSteamMouse);

							}
						};

						// Impératif pour que le bouton fonctionne
						btnPlusProfil.addMouseListener(addProfil);

						// Ajout d'un profil
						addProfilTitle.setText("Vos profils (" + App.listProfilPseudo.length + ")");
						addProfilTitle.setLocation(320, 120);
						addProfilTitle.setSize(200, 30);
						addProfilTitle.setForeground(Color.white);
						addProfilTitle.setFont(Fonts.FontPerso.font4);

						FrameHome.second_label.add(addProfilTitle);

						for (int i = 0; i < App.listProfilPseudo.length; i++) {

							tab[i] = App.jl;// on crée les JLabel et on met dans tab
							tab[i].setName("profil");
							tab[i].setText(App.listProfilPseudo[i]);
							tab[i].setLocation(352, (175 + i * 60));
							tab[i].setSize(new Dimension(200, 33));
							tab[i].setForeground(Color.white);
							tab[i].setFont(Fonts.FontPerso.font2);
							tab[i].setHorizontalAlignment(SwingConstants.LEFT);
							tab[i].setBackground(Color.decode("#32353c"));
							tab[i].setBorder(new BorderForms(Color.darkGray));
							tab[i].setBorder(BorderFactory.createCompoundBorder(tab[i].getBorder(),
									BorderFactory.createEmptyBorder(10, 10, 10, 10)));
							tab[i].setOpaque(true);
							FrameHome.second_label.add(tab[i]);

							JLabel tabout = tab[i];

							tab[i].addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent e) {
									tabout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
									tabout.setBackground(Color.decode("#3b3f47"));
								}

								@Override
								public void mouseExited(MouseEvent e) {
									tabout.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
									tabout.setBackground(Color.decode("#32353c"));
								}
							});

						}

						for (int i = 0; i < App.listProfilAvatar.length; i++) {

							Image image = null;

							try {
								URL url = new URL(App.listProfilAvatar[i]);
								URLConnection conn = url.openConnection();
								conn.setRequestProperty("User-Agent", "Mozilla/5.0");

								conn.connect();
								InputStream urlStream = conn.getInputStream();
								image = ImageIO.read(urlStream);

								JLabel lblimage = new JLabel(new ImageIcon(image));
								lblimage.setBounds(320, (175 + i * 60), 32, 32);
								lblimage.setBackground(Color.decode("#32353c"));
								lblimage.setBorder(new BorderForms(Color.darkGray));
								lblimage.setBorder(BorderFactory.createCompoundBorder(lblimage.getBorder(),
										BorderFactory.createEmptyBorder(10, 10, 10, 10)));
								lblimage.setOpaque(true);
								FrameHome.second_label.add(lblimage, BorderLayout.CENTER);

								lblimage.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										lblimage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										lblimage.setBackground(Color.decode("#3b3f47"));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										lblimage.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										lblimage.setBackground(Color.decode("#32353c"));
									}
								});

							} catch (IOException e) {
								try {
									Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- "
											+ e + " \n");
								} catch (IOException t) {
									// TODO Auto-generated catch block
									t.printStackTrace();
								}
							}

						}

						// Liste des bouton left
						App.tabLeftBtn1.setBackground(Color.decode("#32353c"));
						App.tabLeftBtn1.setBorder(new BorderForms(Color.darkGray));
						App.tabLeftBtn1.setBorder(BorderFactory.createCompoundBorder(App.tabLeftBtn1.getBorder(),
								BorderFactory.createEmptyBorder(10, 10, 10, 10)));

						App.tabLeftBtn2.setBackground(Color.decode("#32353c"));
						App.tabLeftBtn2.setBorder(new BorderForms(Color.darkGray));
						App.tabLeftBtn2.setBorder(BorderFactory.createCompoundBorder(App.tabLeftBtn2.getBorder(),
								BorderFactory.createEmptyBorder(10, 10, 10, 10)));

						App.tabLeftBtn3.setBackground(Color.decode("#32353c"));
						App.tabLeftBtn3.setBorder(new BorderForms(Color.darkGray));
						App.tabLeftBtn3.setBorder(BorderFactory.createCompoundBorder(App.tabLeftBtn3.getBorder(),
								BorderFactory.createEmptyBorder(10, 10, 10, 10)));

						App.tabLeftBtn4.setBackground(Color.decode("#32353c"));
						App.tabLeftBtn4.setBorder(new BorderForms(Color.darkGray));
						App.tabLeftBtn4.setBorder(BorderFactory.createCompoundBorder(App.tabLeftBtn4.getBorder(),
								BorderFactory.createEmptyBorder(10, 10, 10, 10)));

						App.tabLeftBtn1.setFont(Fonts.FontPerso.font2);
						App.tabLeftBtn2.setFont(Fonts.FontPerso.font2);
						App.tabLeftBtn3.setFont(Fonts.FontPerso.font2);
						App.tabLeftBtn4.setFont(Fonts.FontPerso.font2);

						App.tabLeftBtn1.setForeground(Color.white);
						App.tabLeftBtn2.setForeground(Color.white);
						App.tabLeftBtn3.setForeground(Color.white);
						App.tabLeftBtn4.setForeground(Color.white);

						App.tabLeftBtn1.setLocation(20, 120);
						App.tabLeftBtn2.setLocation(20, 180);
						App.tabLeftBtn3.setLocation(20, 240);
						App.tabLeftBtn4.setLocation(20, 300);

						App.tabLeftBtn1.setOpaque(true);
						App.tabLeftBtn2.setOpaque(true);
						App.tabLeftBtn3.setOpaque(true);
						App.tabLeftBtn4.setOpaque(true);

						App.tabLeftBtn1.setSize(196, 44);
						App.tabLeftBtn2.setSize(196, 44);
						App.tabLeftBtn3.setSize(196, 44);
						App.tabLeftBtn4.setSize(196, 44);

						FrameHome.second_label.add(App.tabLeftBtn1);
						FrameHome.second_label.add(App.tabLeftBtn2);
						FrameHome.second_label.add(App.tabLeftBtn3);
						FrameHome.second_label.add(App.tabLeftBtn4);

						if (App.listProfilAvatar.length == 0) {

							TimeOut.setTimeout(() -> {
								App.tabLeftBtn3.setEnabled(false);
								App.tabLeftBtn3.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										App.tabLeftBtn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
										App.tabLeftBtn3.setBackground(Color.decode("#32353c"));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										App.tabLeftBtn3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
										App.tabLeftBtn3.setBackground(Color.decode("#32353c"));
									}
								});
							}, 1200);

						}

						App.tabLeftBtn1.addMouseListener(new MouseAdapter() {

							@Override
							public void mouseEntered(MouseEvent e) {
								App.tabLeftBtn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								App.tabLeftBtn1.setBackground(Color.decode("#3b3f47"));
							}

							@Override
							public void mouseExited(MouseEvent e) {
								App.tabLeftBtn1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								App.tabLeftBtn1.setBackground(Color.decode("#32353c"));
							}
						});

						tabLeftBtn1Mouse = new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								super.mouseClicked(me);
								try {
									tabLeftBtn1Mouse();
								} catch (IOException e) {

									e.printStackTrace();
								}
							}

							public void tabLeftBtn1Mouse() throws IOException {
								openWebpage("https://store.steampowered.com/?l=french");
							}

						};

						// Impératif pour que le bouton fonctionne
						App.tabLeftBtn1.addMouseListener(tabLeftBtn1Mouse);

						App.tabLeftBtn2.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								App.tabLeftBtn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								App.tabLeftBtn2.setBackground(Color.decode("#3b3f47"));
							}

							@Override
							public void mouseExited(MouseEvent e) {
								App.tabLeftBtn2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								App.tabLeftBtn2.setBackground(Color.decode("#32353c"));
							}
						});

						tabLeftBtn2Mouse = new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								super.mouseClicked(me);
								try {
									tabLeftBtn2Mouse();
								} catch (IOException e) {

									e.printStackTrace();
								}
							}

							public void tabLeftBtn2Mouse() throws IOException {
								openWebpage("https://store.steampowered.com/about/");
							}

						};

						// Impératif pour que le bouton fonctionne
						App.tabLeftBtn2.addMouseListener(tabLeftBtn2Mouse);

						App.tabLeftBtn3.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								App.tabLeftBtn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								App.tabLeftBtn3.setBackground(Color.decode("#3b3f47"));
							}

							@Override
							public void mouseExited(MouseEvent e) {
								App.tabLeftBtn3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								App.tabLeftBtn3.setBackground(Color.decode("#32353c"));
							}
						});

						tabLeftBtn3Mouse = new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								super.mouseClicked(me);
								try {
									if (App.listProfilAvatar.length != 0) {
										tabLeftBtn3Mouse();
									}
								} catch (IOException e) {

									e.printStackTrace();
								}
							}

							public void tabLeftBtn3Mouse() throws IOException {

								System.out.print(
										Logs.datefl.format(Logs.DateDuJour) + " -- Vous avez supprimer vos profil\n");

								// Class permettant de sauvegarder le logs
								Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
										+ " -- Vous avez supprimer vos profil\n");

								Database.removeProfils();

							}

						};

						// Impératif pour que le bouton fonctionne
						App.tabLeftBtn3.addMouseListener(tabLeftBtn3Mouse);

						App.tabLeftBtn4.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								App.tabLeftBtn4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								App.tabLeftBtn4.setBackground(Color.decode("#3b3f47"));
							}

							@Override
							public void mouseExited(MouseEvent e) {
								App.tabLeftBtn4.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
								App.tabLeftBtn4.setBackground(Color.decode("#32353c"));
							}
						});

						tabLeftBtn4Mouse = new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								super.mouseClicked(me);
								try {
									tabLeftBtn4Mouse();
								} catch (IOException e) {

									e.printStackTrace();
								}
							}

							public void tabLeftBtn4Mouse() throws IOException {

								System.out
										.print(Logs.datefl.format(Logs.DateDuJour) + " -- Vous avez fermé Steamapp\n");

								// Class permettant de sauvegarder le logs
								Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour)
										+ " -- Vous avez fermé Steamapp\n");

								System.exit(0);

							}

						};

						// Impératif pour que le bouton fonctionne
						App.tabLeftBtn4.addMouseListener(tabLeftBtn4Mouse);

					} else

					{
						App.usernameJ.setBorder(new BorderForms(Color.red));
						App.usernameJ.setBorder(BorderFactory.createCompoundBorder(App.usernameJ.getBorder(),
								BorderFactory.createEmptyBorder(7, 7, 7, 7)));

						App.passwordJ.setBorder(new BorderForms(Color.red));
						App.passwordJ.setBorder(BorderFactory.createCompoundBorder(App.passwordJ.getBorder(),
								BorderFactory.createEmptyBorder(7, 7, 7, 7)));

						App.error.setText("Une erreur est survenue !");
						App.error.setVisible(true);

					}

				}

			}
		};

		// Impératif pour que le bouton fonctionne
		App.Validate.addMouseListener(btnValidate);

	}

	public static void openWebpage(String urlString) {

		try {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				desktop.browse(new URI(urlString));
			}
			throw new NullPointerException();
		} catch (Exception e) {
			try {
				Logs.SaveTextLogs(Logs.ReadTextLogs() + Logs.datefl.format(Logs.DateDuJour) + " -- " + e + " \n");
			} catch (IOException t) {
				// TODO Auto-generated catch block
				t.printStackTrace();
			}
		}
	}

}
