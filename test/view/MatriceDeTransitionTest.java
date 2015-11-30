/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.EntreeGUI;
import model.EtatGUI;
import model.MatriceDeTransition;
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
public class MatriceDeTransitionTest {
    
    public MatriceDeTransitionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        EtatGUI etatSuivant = MatriceDeTransition.getInstance().getEtatSuivant(EtatGUI.J1DoitDonner, EntreeGUI.DonnerJ1);
        System.out.println(MatriceDeTransition.getInstance().printMatice());
        assertTrue(etatSuivant==EtatGUI.J2DoitPlacer);
    }
    
}
