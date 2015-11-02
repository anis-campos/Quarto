/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controlleur.IControlleur;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import model.Coord;
import model.NumeroJoueur;

/**
 * @author Anis
 */
public class QuartoGUI extends JFrame implements Observer {

    private Map<JPanel, Coord> cases;

    private Map<String, JLabel> pieces;

    private JPanel jEntete;

    private JPanel jPlateau;

    private JPanel jPieces;

    private JPanel jPieceJ1;
    private JPanel jPieceJ2;

    private JButton bDonnerJ1;
    private JButton bDonnerJ2;

    private NumeroJoueur courant;
    private final IControlleur controleur;

    private JPanel layeredPane;

    public QuartoGUI(IControlleur controleur) {

        super();

        this.controleur = controleur;

        
        initComponents();
        
        courant = controleur.getJoueurCourant();
        
        if(courant==NumeroJoueur.J1)
            bDonnerJ2.setEnabled(false);
        else
            bDonnerJ1.setEnabled(false);

    }

    void initComponents() {

        //CONSTRUCTION DU PLATEAU
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jPlateau = new JPanel();

        GridLayout grid = new GridLayout(4, 4);
        grid.setHgap(5);
        grid.setVgap(5);
        jPlateau.setLayout(grid);

        cases = new HashMap<>();

        for (int i = 0; i < 16; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(Color.WHITE);
            jPanel.setPreferredSize(new Dimension(100, 100));
            jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            cases.put(jPanel, new Coord((i - 1) / 4, (i - 1) % 4));
            jPlateau.add(jPanel);
        }
        /////////////////////////////////////////////////////////////////

        //CONSTRUCTION DE L'ENTETE
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jEntete = new JPanel();
        jEntete.setLayout(new GridBagLayout());
        JLabel lab = new JLabel("QUARTO");
        lab.setFont(new Font("Arial", Font.BOLD, 48));
        jEntete.add(lab);
        /////////////////////////////////////////////////////////////////

        //CONSTRUCTION DES PIECES
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        jPieces = new JPanel();

        pieces = new HashMap<>();

        grid = new GridLayout(2, 8);
        grid.setHgap(5);
        grid.setVgap(5);
        jPieces.setLayout(grid);
        jPieces.setBorder(BorderFactory.createTitledBorder("Liste des Pièces"));

        for (String piece : controleur.getListPieceDisponible()) {

            JLabel jLabel = new JLabel(getImageFile(piece));
            jLabel.setName(piece);
            //jLabel.setText(piece);

            pieces.put(piece, jLabel);

            jLabel.addMouseListener(new PieceClickListener());
            jLabel.setBackground(Color.WHITE);
            jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            jLabel.setPreferredSize(new Dimension(100, 100));
            jPieces.add(jLabel);
        }

        /////////////////////////////////////////////////////////////////
        //CONSTRUCTION DE LA ZONE JOUEUR 1
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        Box jZoneJ1 = Box.createVerticalBox();
        jZoneJ1.setBackground(Color.red);
        jZoneJ1.setPreferredSize(new Dimension(210, 210));

        jPieceJ1 = new JPanel();
        jPieceJ1.setPreferredSize(new Dimension(100, 100));
        jPieceJ1.setBackground(Color.white);
        jPieceJ1.setMaximumSize(new Dimension(100, 100));

        bDonnerJ1 = new JButton("Donner à J2");
        bDonnerJ1.addActionListener(new ButtonDonnerClickListener());

        JLabel j1 = new JLabel("JOUEUR 1");
        jZoneJ1.add(j1);
        jZoneJ1.add(jPieceJ1);
        jZoneJ1.add(bDonnerJ1);
        j1.setAlignmentX(Component.CENTER_ALIGNMENT);
        bDonnerJ1.setAlignmentX(Component.CENTER_ALIGNMENT);
        /////////////////////////////////////////////////////////////////

        //CONSTRUCTION DE LA ZONE JOUEUR 2
        /////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////
        Box jZoneJ2 = Box.createVerticalBox();
        jZoneJ2.setBackground(Color.blue);
        jZoneJ2.setPreferredSize(new Dimension(210, 210));

        jPieceJ2 = new JPanel();
        jPieceJ2.setPreferredSize(new Dimension(100, 100));
        jPieceJ2.setBackground(Color.white);
        jPieceJ2.setMaximumSize(new Dimension(100, 100));

        JLabel j2 = new JLabel("JOUEUR 2");
        bDonnerJ2 = new JButton("Donner à J1");
        bDonnerJ2.setAlignmentX(Component.CENTER_ALIGNMENT);

        bDonnerJ2.addActionListener(new ButtonDonnerClickListener() );

        j2.setAlignmentX(Component.CENTER_ALIGNMENT);
        jZoneJ2.add(j2);
        jZoneJ2.add(jPieceJ2);
        jZoneJ2.add(bDonnerJ2);

        /////////////////////////////////////////////////////////////////
        Box Centre = Box.createHorizontalBox();
        Centre.add(jZoneJ1);
        Centre.add(jPlateau);
        Centre.add(jZoneJ2);

        this.layeredPane = new JPanel();
        this.setContentPane(layeredPane);
        layeredPane.setLayout(new BorderLayout(20, 20));

        layeredPane.add(jEntete, BorderLayout.NORTH);
        layeredPane.add(Centre, BorderLayout.CENTER);
        layeredPane.add(jPieces, BorderLayout.SOUTH);

    }

    public static ImageIcon getImageFile(String piece) {

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(QuartoGUI.class.getResourceAsStream("/images/" + piece + ".png")));
            Image image = imageIcon.getImage();
            Image scaledInstance = image.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);
           
            return new ImageIcon(scaledInstance);
        } catch (IOException ex) {
            Logger.getLogger(QuartoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

           
  

    @Override
    public void update(Observable o, Object arg) {
        //TODO : Deplacer les pieces
    }

    public JLabel cloneLabel(JLabel label){
        JLabel lab = new JLabel(label.getIcon());
        lab.setName(label.getName());
        return lab;
    }
    
    public class ButtonDonnerClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton button = (JButton) e.getSource();
            String nomPiece;
            if(button==bDonnerJ1){
                nomPiece = jPieceJ1.getComponent(0).getName();
            }else
            {
                nomPiece = jPieceJ2.getComponent(0).getName();
            }
            controleur.donnerPiece(nomPiece);
        }
        
    }
    
    public class PieceClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel lab = (JLabel) e.getSource();
            JPanel panel;
            if(controleur.getJoueurCourant()==NumeroJoueur.J1){
                panel = jPieceJ1;
            }else
                panel = jPieceJ2;
            
            if(panel.getComponentCount()==1){
                JLabel labPresent = (JLabel) panel.getComponent(0);
                JLabel piece = pieces.get(labPresent.getName());
                piece.setVisible(true);
                panel.removeAll();
            }
            lab.setVisible(false);
            panel.add(cloneLabel(lab));
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
