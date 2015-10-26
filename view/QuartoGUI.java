/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import model.Coord;

/**
 * @author Anis
 */
public class QuartoGUI extends JFrame implements Observer {


    private Map<JPanel, Coord> cases;

    private Map<JLabel, String> pieces;

    private JPanel jEntete;

    private JPanel jPlateau;

    private JPanel jPieces;

    private JPanel jPieceJ1;
    private JPanel jPieceJ2;

    private JButton bDonnerJ1;
    private JButton bDonnerJ2;

    private Joueur courant = Joueur.J1;

    enum Joueur{
        J1,J2
    }


    private JPanel layeredPane;

    public QuartoGUI() {
        super();
        initComponents();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println(e);
            }


        });



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
            jPanel.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    JPanel toto = (JPanel) e.getComponent();
                    Coord c = cases.get(toto);
                    if (c.x == 0 && c.y == 0)
                        System.out.println("Taille de la case (0,0) : " + toto.getWidth() + "x" + toto.getHeight());
                }
            });
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

        for (int i = 0; i < 16; i++) {
            JLabel jLabel = new JLabel();
            jLabel.setText("PIECE_" + i);

            pieces.put(jLabel,"PIECE_" + i);

            jLabel.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    JLabel toto = (JLabel) e.getComponent();
                    if (toto.getText().matches("PIECE_1"))
                        System.out.println("Taille de la piece 1 : " + toto.getWidth() + "x" + toto.getHeight());
                }
            });

            jLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel lab = (JLabel)e.getSource();
                    lab.setVisible(false);
                    placerPiece(new JLabel(lab.getText()));

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
            });
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

    void placerPiece(JLabel lab){
        if(courant == Joueur.J1){
            jPieceJ2.add(lab);
            courant = Joueur.J2;
        }else{
            jPieceJ1.add(lab);
            courant = Joueur.J1;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
