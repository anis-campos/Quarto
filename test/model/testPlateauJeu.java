/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.Before;

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
        p = new Parametre(true, true, true, true, true,true,true,true,true,2);
        j1 = new Joueur("Joueur1", false, NumeroJoueur.J1);
        j2 = new Joueur("Joueur2", false, NumeroJoueur.J2);
        partie = new Partie(p, j1, j2);
    }

    @After
    public void tearDown() {
    }

    
}
