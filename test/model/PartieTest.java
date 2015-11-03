/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import static model.QuartoCalculator.thereIsQuarto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anis
 */
public class PartieTest {

    private Joueur j1;
    private Joueur j2;
    private Parametre p;
    private Partie partie;

    public PartieTest() {
    }

    @Before
    public void setUp() {
        p = new Parametre(true, true, true, true, true);
        j1 = new Joueur("Joueur1", false, NumeroJoueur.J1);
        j2 = new Joueur("Joueur2", false, NumeroJoueur.J2);
        partie = new Partie(p, j1, j2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testQuartoCalculator() {
        //ajouter des asserts
        Map<Coord, Piece> plateauTest = new HashMap<>();
        partie.plateauJeu.addPiece(new Coord(0, 0), new Piece(true, false, true, true));
        partie.plateauJeu.addPiece(new Coord(0, 1), new Piece(true, false, true, false));
        partie.plateauJeu.addPiece(new Coord(0, 2), new Piece(true, false, false, true));
        partie.plateauJeu.addPiece(new Coord(0, 3), new Piece(true, false, false, false));
        System.out.println(thereIsQuarto(partie.plateauJeu, p, new Coord(0, 1)).toString());
    }
    @Test
    public void testPlateauJeu() {
        partie.plateauJeu.addPiece(new Coord(0, 0), new Piece(true, false, true, true));
        partie.plateauJeu.addPiece(new Coord(0, 1), new Piece(true, true, true, true));
        partie.plateauJeu.addPiece(new Coord(2, 1), new Piece(true, true, true, false));
        partie.plateauJeu.removePieceFromCoord(new Coord(2, 1));
        System.out.println(partie.plateauJeu);
    }
}
