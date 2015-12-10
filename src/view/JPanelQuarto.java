/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controlleur.IControlleur;
import controlleur.observables.Notification;
import controlleur.observables.NotificationDernierTour;
import controlleur.observables.NotificationMatchNullAnnonce;
import controlleur.observables.NotificationMatchNullConfirme;
import controlleur.observables.NotificationPieceDonnee;
import controlleur.observables.NotificationPiecePlacee;
import controlleur.observables.NotificationPieceSelectionnee;
import controlleur.observables.NotificationQuartoAnnonce;
import controlleur.observables.NotificationQuartoDetecte;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import model.Coord;
import model.EtatGUI;
import model.NumeroJoueur;

/**
 * @author Anis
 */
public class JPanelQuarto extends JPanel implements Observer {

    protected final HashMap<JPanelCase, Coord> mapCoordByCase;
    protected final HashMap<Coord, JPanelCase> mapCaseByCoord;

    //Map string=name  label
    protected final HashMap<Integer, JLabelPiece> listeDePiecesDisponibles;

    protected JPanel jPlateau;

    protected JPanel jPanelListePieces;

    protected JPanelCase jPieceJ1;
    protected JPanelCase jPieceJ2;

    protected JButton bDonnerJ1;
    protected JButton bDonnerJ2;
    protected JButton bAfficherMenu;

    protected final IControlleur controleur;

    protected JPanel layeredPane;

    protected JButton bAnnoncerQuartoJ1;
    protected JButton bAnnoncerQuartoJ2;

    protected JButton bAnnoncerMatchNullJ1;
    protected JButton bAnnoncerMatchNullJ2;

    protected JLabel jLabelJ2;
    protected JLabel jLabelJ1;
    protected final int initFontSize = 12;
    protected final int fontSizeToUse = 24;// TODO = A changer et faire mieux pour gérer les FONTs

    JTextArea jTextArea1;
    protected final Dimension dimensionCase;
    protected final Image backgroundImage;
    protected JLabel Titre;

    public JPanelQuarto(IControlleur controleur, Dimension dimensionCase) {

        super();

        this.controleur = controleur;

        this.dimensionCase = dimensionCase;

        listeDePiecesDisponibles = new HashMap<>();
        mapCoordByCase = new HashMap<>();
        mapCaseByCoord = new HashMap<>();

        initComponents();
        initFromData();

        jTextArea1.setVisible(false);

        updateScreen(controleur.getEtatCourant());
        this.backgroundImage = GUIImageTool.getImage("/images/wood_texture.jpg");

    }

    public void bDonnerJ2NotVisible() {
        bDonnerJ2.setVisible(false);
    }

    public void bAnnoncerQuartoJ2NotVisible() {
        bAnnoncerQuartoJ2.setVisible(false);
    }

    public void bAnnoncerMatchNullJ2NotVisible() {
        bAnnoncerMatchNullJ2.setVisible(false);
    }

    public void testQuarto() throws AWTException {

        Robot bot = new Robot();
        ArrayList<Point> listLocationPiece = new ArrayList<>();
        ArrayList<Point> listLocationCase = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Component component = jPanelListePieces.getComponent(i);
            Point piece = component.getLocationOnScreen();
            piece.translate(component.getWidth() / 2, component.getHeight() / 2);
            listLocationPiece.add(piece);
        }
        for (int i = 0; i < 10; i++) {
            Component component = jPlateau.getComponent(i);
            Point casePlateau = component.getLocationOnScreen();
            casePlateau.translate(component.getWidth() / 2, component.getHeight() / 2);
            listLocationCase.add(casePlateau);
        }

        for (int i = 0; i < 4; i++) {
            robotClick(bot, listLocationPiece.get(i));
            Point bouton;
            if (controleur.getJoueurCourant() == NumeroJoueur.J1) {
                bouton = bDonnerJ1.getLocationOnScreen();
                bouton.translate(bDonnerJ1.getWidth() / 2, bDonnerJ1.getHeight() / 2);
            } else {
                bouton = bDonnerJ2.getLocationOnScreen();
                bouton.translate(bDonnerJ2.getWidth() / 2, bDonnerJ2.getHeight() / 2);
            }
            robotClick(bot, bouton);
            robotClick(bot, listLocationCase.get(i));
        }

    }

    public void testDernierTour() throws AWTException {

        Integer[] listPiece = {
            14, 4, 3, 11,
            5, 9, 16, 6,
            12, 8, 2, 13,
            1, 15, 10, 7
        };

        Robot bot = new Robot();

        for (int i = 0; i < 16; i++) {
            Component component = jPlateau.getComponent(i);
            Point casePlateau = component.getLocationOnScreen();
            casePlateau.translate(component.getWidth() / 2, component.getHeight() / 2);

            JLabelPiece piece = listeDePiecesDisponibles.get(listPiece[i]);
            Point locationOnScreen = piece.getLocationOnScreen();
            locationOnScreen.translate(piece.getSize().width / 2, piece.getSize().height / 2);

            robotClick(bot, locationOnScreen);
            Point bouton;
            if (controleur.getJoueurCourant() == NumeroJoueur.J1) {
                bouton = bDonnerJ1.getLocationOnScreen();
                bouton.translate(bDonnerJ1.getWidth() / 2, bDonnerJ1.getHeight() / 2);
            } else {
                bouton = bDonnerJ2.getLocationOnScreen();
                bouton.translate(bDonnerJ2.getWidth() / 2, bDonnerJ2.getHeight() / 2);
            }
            robotClick(bot, bouton);
            robotClick(bot, casePlateau);
        }

    }

    protected void robotClick(Robot bot, Point p) {
        bot.mouseMove(p.x, p.y);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(JPanelQuarto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fonction d'initialisation des composants de la GUI
     */
    protected void initComponents() {
        buildPlateauJeu();
        Box entete = buildEntete();
        buildListePieces();

        Box Centre = Box.createHorizontalBox();
        Centre.add(buildZoneJ1());
        Centre.add(jPlateau);
        Centre.add(buildZoneJ2());

        Box Bottom = Box.createVerticalBox();
        JPanel panel = new JPanel(new GridBagLayout());
        jTextArea1 = new JTextArea("Instructions sur le jeu");
        jTextArea1.setRows(2);
        jTextArea1.setFont(new Font("arial", Font.BOLD, 20));
        panel.add(jTextArea1);
        Bottom.add(panel);
        Bottom.add(Box.createRigidArea(new Dimension(1, 10)));
        Bottom.add(jPanelListePieces);

        placeBlocs(Centre, Bottom, entete);

    }

    protected void buildPlateauJeu() {
        jPlateau = new JPanel();

        GridLayout grid = new GridLayout(4, 4);
        grid.setHgap(5);
        grid.setVgap(5);
        jPlateau.setLayout(grid);
        jPlateau.setOpaque(false);
        jPlateau.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("enabled")) {
                    boolean etat = (boolean) evt.getNewValue();
                    for (Component component : jPlateau.getComponents()) {

                        component.setEnabled(etat);
                    }
                }
            }
        });

        for (int i = 0; i < 16; i++) {
            Coord coord = new Coord(i / 4, i % 4);
            JPanelCase jPanel = new JPanelCase(dimensionCase, new CasePlateauClickAction(coord));
            mapCoordByCase.put(jPanel, coord);
            mapCaseByCoord.put(coord, jPanel);
            jPlateau.add(jPanel);
        }
    }

    protected Box buildEntete() {
        Box jEntete = Box.createVerticalBox();
        jEntete.setOpaque(false);
        Titre = new JLabel("QUARTO");
        Titre.setOpaque(false);
        Titre.setFont(new Font("Arial", Font.BOLD, 48));

        bAfficherMenu = new JButton("Afficher le Menu");
        bAfficherMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) layeredPane.getParent().getParent().getLayout();
                cl.show(layeredPane.getParent().getParent(), "menu");
            }
        });
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(false);
        jPanel.add(Titre);
        JPanel jPanel2 = new JPanel();
        jPanel2.setOpaque(false);
        jPanel2.add(bAfficherMenu);
        Titre.setAlignmentX(SwingConstants.CENTER);
        bAfficherMenu.setAlignmentX(SwingConstants.CENTER);
        jEntete.add(jPanel);
        jEntete.add(jPanel2);
        return jEntete;
    }

    protected void buildListePieces() {
        jPanelListePieces = new JPanel();
        jPanelListePieces.setOpaque(false);
        GridLayout grid = new GridLayout(2, 8);
        grid.setHgap(5);
        grid.setVgap(5);
        jPanelListePieces.setLayout(grid);
        jPanelListePieces.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                if (evt.getPropertyName().equals("enabled")) {
                    for (Component piece : jPanelListePieces.getComponents()) {
                        piece.setEnabled((boolean) evt.getNewValue());
                    }
                }
            }
        });
    }

    protected Box buildZoneJ1() {
        Box jZoneJ1 = Box.createVerticalBox();
        jZoneJ1.setBackground(Color.red);
        jZoneJ1.setPreferredSize(new Dimension(210, 210));

        jPieceJ1 = new JPanelCase(dimensionCase, null);

        bDonnerJ1 = new JButton();
        bDonnerJ1.addActionListener(new ButtonDonnerClickListener());
        bDonnerJ1.setAlignmentX(Component.CENTER_ALIGNMENT);

        bAnnoncerQuartoJ1 = new JButton();
        bAnnoncerQuartoJ1.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerQuartoJ1.addActionListener(new ButtonAnnoncerQuartoClickListener());

        bAnnoncerMatchNullJ1 = new JButton("Annoncer Match Null");
        bAnnoncerMatchNullJ1.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerMatchNullJ1.addActionListener(new ButtonAnnoncerMatchNullClickListener());

        jLabelJ1 = new JLabel("", JLabel.CENTER);//initFromData

        JPanel jLabelJ1Conteneur = new JPanel(new BorderLayout());
        jLabelJ1Conteneur.setOpaque(false);
        jLabelJ1Conteneur.add(jLabelJ1, BorderLayout.PAGE_END);
        jLabelJ1Conteneur.setAlignmentX(CENTER_ALIGNMENT);
        jLabelJ1Conteneur.setMaximumSize(new Dimension((int) (dimensionCase.height * 2f), (int) (dimensionCase.height * 0.7f)));

        jZoneJ1.add(jLabelJ1Conteneur);
        jZoneJ1.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ1.add(jPieceJ1);
        jZoneJ1.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ1.add(bDonnerJ1);
        jZoneJ1.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ1.add(bAnnoncerQuartoJ1);
        jZoneJ1.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ1.add(bAnnoncerMatchNullJ1);
        return jZoneJ1;
    }

    protected Box buildZoneJ2() {
        Box jZoneJ2 = Box.createVerticalBox();
        jZoneJ2.setBackground(Color.blue);
        jZoneJ2.setPreferredSize(new Dimension(210, 210));

        jPieceJ2 = new JPanelCase(dimensionCase, null);

        bDonnerJ2 = new JButton();
        bDonnerJ2.setAlignmentX(Component.CENTER_ALIGNMENT);
        bDonnerJ2.addActionListener(new ButtonDonnerClickListener());

        bAnnoncerQuartoJ2 = new JButton();
        bAnnoncerQuartoJ2.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerQuartoJ2.addActionListener(new ButtonAnnoncerQuartoClickListener());

        bAnnoncerMatchNullJ2 = new JButton("Annoncer Match Null");
        bAnnoncerMatchNullJ2.setAlignmentX(CENTER_ALIGNMENT);
        bAnnoncerMatchNullJ2.addActionListener(new ButtonAnnoncerMatchNullClickListener());

        jLabelJ2 = new JLabel("", JLabel.CENTER);
        JPanel jLabelJ2Conteneur = new JPanel(new BorderLayout());
        jLabelJ2Conteneur.setOpaque(false);
        jLabelJ2Conteneur.add(jLabelJ2, BorderLayout.PAGE_END);
        jLabelJ2Conteneur.setAlignmentX(CENTER_ALIGNMENT);
        jLabelJ2Conteneur.setMaximumSize(new Dimension((int) (dimensionCase.height * 2f), (int) (dimensionCase.height * 0.7f)));

        jZoneJ2.add(jLabelJ2Conteneur);
        jZoneJ2.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ2.add(jPieceJ2);
        jZoneJ2.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ2.add(bDonnerJ2);
        jZoneJ2.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ2.add(bAnnoncerQuartoJ2);
        jZoneJ2.add(Box.createRigidArea(new Dimension(0, 10)));
        jZoneJ2.add(bAnnoncerMatchNullJ2);
        return jZoneJ2;
    }

    public void cacherAfficherMenu() {
        this.bAfficherMenu.setVisible(false);
    }

    protected void placeBlocs(Box Centre, Box Bottom, Box entete) {
        layeredPane = new JPanel();
        layeredPane.setLayout(new BorderLayout(20, 20));
        layeredPane.add(entete, BorderLayout.NORTH);
        layeredPane.add(Centre, BorderLayout.CENTER);
        layeredPane.add(Bottom, BorderLayout.SOUTH);
        layeredPane.setOpaque(false);
        this.add(layeredPane);
    }

    public void initFromData() {

        //CONSTRUCTION DES PIECES DISPONIBLES
        for (Map.Entry<Integer, String> IdEtNomPiece : controleur.getListPieceDisponible()) {
            JLabelPiece jLabelPiece = new JLabelPiece(IdEtNomPiece.getKey(), IdEtNomPiece.getValue(), dimensionCase, new PieceClickAction(IdEtNomPiece.getKey()));
            listeDePiecesDisponibles.put(IdEtNomPiece.getKey(), jLabelPiece);
            jPanelListePieces.add(jLabelPiece);
        }
        //CONSTRUCTION DES PIECES SUR PLATEAU JEU
        for (Map.Entry<Coord, String> CoordEtNomPiece : controleur.getListPiecePlateauJeu()) {
            JLabelPiece jLabelPiece = new JLabelPiece(-1, CoordEtNomPiece.getValue(), dimensionCase, null);
            JPanelCase laCase = mapCaseByCoord.get(CoordEtNomPiece.getKey());
            laCase.setPiece(jLabelPiece);
        }

        //CONSTRUCTION PIECE J1
        String nameJ1 = controleur.getNamePieceJ1();
        if (nameJ1 != null) {
            JLabelPiece labelPieceJ1 = new JLabelPiece(-1, nameJ1, dimensionCase, null);
            jPieceJ1.setPiece(labelPieceJ1);
        }
        //CONSTRUCTION PIECE J2
        String nameJ2 = controleur.getNamePieceJ2();
        if (nameJ2 != null) {
            JLabelPiece labelPieceJ2 = new JLabelPiece(-1, nameJ2, dimensionCase, null);
            jPieceJ2.setPiece(labelPieceJ2);
        }

        //INFOS JOUEURS
        bDonnerJ1.setText("Donner à " + controleur.getNomJoueur(NumeroJoueur.J2));
        bDonnerJ2.setText("Donner à " + controleur.getNomJoueur(NumeroJoueur.J1));
        jLabelJ1.setText(controleur.getNomJoueur(NumeroJoueur.J1));
        jLabelJ2.setText(controleur.getNomJoueur(NumeroJoueur.J2));
        bAnnoncerQuartoJ1.setText("Quarto!");
        bAnnoncerQuartoJ2.setText("Quarto!");
        bAnnoncerMatchNullJ1.setEnabled(false);
        bAnnoncerMatchNullJ2.setEnabled(false);

        //Cacher l'action du Joueur 2 qui est un BOT/JOUEUR RESEAU
        if (controleur.onePlayer()) {
            bAnnoncerQuartoJ2NotVisible();
            bDonnerJ2NotVisible();
            bAnnoncerMatchNullJ2NotVisible();
        }

    }

    /**
     * Recupère le JPanel du joueur ( contenant normalement une pièce )
     *
     * @param num Le NumeroJoueur du joueur
     * @return Le JPanel de ce joueur
     */
    protected JPanelCase getPanelJoueur(NumeroJoueur num) {
        JPanelCase panel;
        if (num == NumeroJoueur.J1) {
            panel = jPieceJ1;
        } else {
            panel = jPieceJ2;
        }

        return panel;
    }

    /**
     * Met à jour la vue du jeu suite à une action du joueur
     *
     * @param o
     * @param arg Notification de l'action réalisée
     */
    @Override
    public void update(Observable o, Object arg) {
        //TODO : Deplacer les pieces
        Notification notif = (Notification) arg;

        if (notif instanceof NotificationPieceDonnee) {
            NotificationPieceDonnee donner = (NotificationPieceDonnee) notif;
            notifDonnerPiece(donner);
        } else if (notif instanceof NotificationPieceSelectionnee) {
            NotificationPieceSelectionnee selectionner = (NotificationPieceSelectionnee) notif;
            notifSelectionnerPiece(selectionner);

        } else if (notif instanceof NotificationPiecePlacee) {
            NotificationPiecePlacee placee = (NotificationPiecePlacee) notif;
            notifPlacerPiece(placee);
        }

        updateScreen(notif.nouvelEtat);

        if (notif instanceof NotificationQuartoDetecte) {

            NotificationQuartoDetecte quartoDetecte = (NotificationQuartoDetecte) notif;

            printWinPopup(quartoDetecte.getQuartos(), controleur.getNomJoueur(notif.joueurSource));

        }

        if (notif instanceof NotificationQuartoAnnonce) {

            NotificationQuartoAnnonce quartoAnnonce = (NotificationQuartoAnnonce) notif;

            if (quartoAnnonce.getQuartos().isEmpty()) {
                printLoosePopup(controleur.getNomJoueur(notif.joueurSource));
            } else {
                printWinPopup(quartoAnnonce.getQuartos(), controleur.getNomJoueur(notif.joueurSource));
            }
        }
        if (notif instanceof NotificationDernierTour) {
            JFrame frame = (JFrame) SwingUtilities.getRoot(this);
            //  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            Icon icon;

            JOptionPane.showMessageDialog(frame, " Attention, c'est le dernier tours !!! Vous avez deux choix : \n"
                    + "=> Annoncer un quarto.\n"
                    + "=> Declarer match nul.", "Fin de Partie", JOptionPane.WARNING_MESSAGE);
        }

        if (notif instanceof NotificationMatchNullAnnonce) {
            JFrame frame = (JFrame) SwingUtilities.getRoot(this);
            //  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            JOptionPane.showMessageDialog(frame, " Attention, Votre adversaire a annoncé match null !!! Vous avez deux choix : \n"
                    + "=> Annoncer un quarto adverse.\n"
                    + "=> Confirmer le match nul.", "Fin de Partie", JOptionPane.WARNING_MESSAGE);
        }

        if (notif instanceof NotificationMatchNullConfirme) {
            JFrame frame = (JFrame) SwingUtilities.getRoot(this);
            //  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            JOptionPane.showMessageDialog(frame, " MATCH NULL ! ", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    protected void printWinPopup(ArrayList<ArrayList<Coord>> quartosTrouves, String nomJoueur) {
        JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        surlignerQuartos(quartosTrouves);

        Icon icon = new ImageIcon(GUIImageTool.getImage("/images/trophy.png"));
        JOptionPane.showMessageDialog(frame, "BRAVO '" + nomJoueur + "' , VOUS AVEZ GAGNE!", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    protected void printLoosePopup(String nomJoueur) {
        JFrame frame = (JFrame) SwingUtilities.getRoot(this);
        Icon icon = new ImageIcon(GUIImageTool.getImage("/images/defeat.png"));
        JOptionPane.showMessageDialog(frame, "DOMMAGE '" + nomJoueur + "' , VOUS AVEZ PERDU!", "Fin de Partie", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public void enableJPlateau() {
        jPlateau.setEnabled(true);
    }

    public void enableJPanelListePieces() {
        jPanelListePieces.setEnabled(true);
    }

    public void disableJPlateau() {
        jPlateau.setEnabled(false);
    }

    public void disableJPanelListePieces() {
        jPanelListePieces.setEnabled(false);
    }

    /**
     * Met à jour l'interface à l'aide de la matrice d'état
     *
     * @param etat Etat du jeu (définit les éléments à afficher
     * activer/désactiver)
     */
    private void updateScreen(EtatGUI etat) {
        ScreenUpdater.updateScreen(this, etat);
    }

    /**
     * Réalise l'action de donner une pièce à l'adversaire en terme d'interface
     *
     * @param donner Notification à l'interface après avoir mis à jour le model
     * (envoyée par le controller pour mettre à jour la vue)
     */
    protected void notifDonnerPiece(NotificationPieceDonnee donner) {
        JPanelCase panelSource = getPanelJoueur(donner.joueurSource);
        JPanelCase panelDestination = getPanelJoueur(donner.joueurAdversaire);
        JLabelPiece lab = panelSource.getPiece();
        panelSource.removePiece();
        panelDestination.setPiece(lab.getClone());
    }

    /**
     * Réalise l'action de selectionner une pièce à partir de la liste de pièce
     *
     * @param selectionnee Notification de Selection de la pièce (envoyée par le
     * controlleur pour mettre à jour la vue)
     */
    protected void notifSelectionnerPiece(NotificationPieceSelectionnee selectionnee) {

        JLabelPiece lab = listeDePiecesDisponibles.get(selectionnee.idPiece);

        JPanelCase panelJoueurCourant;
        panelJoueurCourant = getPanelJoueur(selectionnee.joueurSource);

        if (!panelJoueurCourant.isEmpty()) {
            JLabelPiece labPresent = panelJoueurCourant.getPiece();
            JLabelPiece piece = listeDePiecesDisponibles.get(labPresent.getId());
            piece.setVisible(true);
            panelJoueurCourant.removeAll();
        }
        lab.setVisible(false);
        panelJoueurCourant.setPiece(lab.getClone());
    }

    /**
     * Place la pièce du joueur sur le plateau de jeu
     *
     * @param placee Notification du placement de la pièce sur le plateau
     */
    protected void notifPlacerPiece(NotificationPiecePlacee placee) {
        JPanelCase casePlateau = mapCaseByCoord.get(placee.casePlateau);
        JPanelCase panelJoueur = getPanelJoueur(placee.joueurSource);
        if (!panelJoueur.isEmpty()) {
            JLabelPiece piece = panelJoueur.getPiece();
            casePlateau.setPiece(piece.getClone());
            panelJoueur.removePiece();
        }

    }





    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    /**
     *
     * @param quartos
     */
    private void surlignerQuartos(ArrayList<ArrayList<Coord>> quartos) {

        for (ArrayList<Coord> quarto : quartos) {

            for (Coord coord : quarto) {
                mapCaseByCoord.get(coord).surlignerCase();
            }

        }
    }

    //------------------------------------------------------------------------------------
    //--------------------- Classes Listeners pour les actions ---------------------------
    //------------------------------------------------------------------------------------
    /**
     * Action du click sur un bouton donner
     */
    private class ButtonDonnerClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.donnerPieceAdversaire();
        }

    }

    /**
     * Action du click sur un bouton annoncer quarto
     */
    private class ButtonAnnoncerQuartoClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.annoncerQuarto();
        }
    }

    private class ButtonAnnoncerMatchNullClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controleur.annoncerMatchNul();
        }
    }

    /**
     * Action du click sur un pièce
     */
    private class PieceClickAction implements Runnable {

        private final int idPiece;

        public PieceClickAction(int idPiece) {
            this.idPiece = idPiece;
        }

        @Override
        public void run() {
            controleur.selectionPiece(idPiece);
        }

    }

    /**
     * Action du click sur une case du plateau
     */
    private class CasePlateauClickAction implements Runnable {

        private final Coord coord;

        public CasePlateauClickAction(Coord coord) {
            this.coord = coord;
        }

        @Override
        public void run() {
            controleur.poserPiece(coord);
        }

    }

}
