/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Anis
 */
public class PartieTest {
    
    Partie instance;
    
    public PartieTest() {
        instance = new Partie(
                new Parametre(true, true, true, true, true, true, true,true), 
                new Joueur("J1", false, NumeroJoueur.J1), 
                new Joueur("J2", false, NumeroJoueur.J2));
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
    public void getListPiece(){
        for (Map.Entry<Integer, String> piece : instance.getListPieceNameDisponibles()) {
            System.out.println("Piece n°:"+piece.getKey() + "->" + piece.getValue());
        }

        assertEquals(instance.getListPieceNameDisponibles().size(), 16);
    }
 
    
}
