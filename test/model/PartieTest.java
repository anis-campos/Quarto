/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anis
 */
public class PartieTest {

    private Joueur j1;
    private Joueur j2;

    public PartieTest() {
    }

    @Before
    public void setUp() {
        j1 = new Joueur("Joueur1", false);
        j2 = new Joueur("Joueur2", false);
    }

    @After
    public void tearDown() {
    }

    
    @Test
    public void Constructor(){
        Parametre p = new Parametre(true, false, true, true, true);
        Partie partie = new Partie(p, j1, j2);
        assertNotNull(partie);
    }

}
