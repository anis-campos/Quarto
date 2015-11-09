/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;

/**
 *
 * @author Anis
 */
public class testPlateauJeu {

    private Joueur j1;
    private Joueur j2;
    private Parametre p;
    private Partie partie;

    public testPlateauJeu() {
    }

    @Before
    public void setUp() {
        p = new Parametre(true, true, true, true, true,true,true);
        j1 = new Joueur("Joueur1", false, NumeroJoueur.J1);
        j2 = new Joueur("Joueur2", false, NumeroJoueur.J2);
        partie = new Partie(p, j1, j2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPlateauJeu() {
        try {
            partie.getPlateauJeu().addPiece(new Coord(0, 0), partie.popPieceAvailableByName("CARRE_GRAND_CLAIR_CREUX"));
            partie.getPlateauJeu().addPiece(new Coord(0, 1), partie.popPieceAvailableByName("CARRE_GRAND_FONCE_CREUX"));
            partie.getPlateauJeu().addPiece(new Coord(2, 1), partie.popPieceAvailableByName("CARRE_PETIT_FONCE_CREUX"));
            partie.getPlateauJeu().removePieceFromCoord(new Coord(2, 1));
            System.out.println(partie.getPlateauJeu());
        } catch (Exception ex) {
            Logger.getLogger(testPlateauJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
