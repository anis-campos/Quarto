/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.controllerLocal.ChessGameControler;
import java.util.Scanner;
import model.Coord;

/**
 *
 * @author Antoine
 */
public class ChessGameCmdLine {
    private ChessGameControler chessGameControler;
    
    public ChessGameCmdLine(ChessGameControler chessGameControler) {
        this.chessGameControler = chessGameControler;
    }
    
    public void afficher(){
        System.out.print(this.chessGameControler.toString());
    }
    
    public void demander(){
        boolean ok = false;
        
        while(!ok){
            System.out.print("\n\n[" + chessGameControler.getColorCurrentPlayer() + "] X Y (pièce à déplacer) : ");
            Scanner sc = new Scanner(System.in);
            int x, y;
            x = sc.nextInt();
            y = sc.nextInt();

            System.out.print("\n[" + chessGameControler.getColorCurrentPlayer() + "] X Y (case choisie) : ");
            Scanner sc2 = new Scanner(System.in);
            int xNew, yNew;
            xNew = sc2.nextInt();
            yNew = sc2.nextInt();

            ok = chessGameControler.move(new Coord(x, y), new Coord(xNew, yNew));
            
            if(!ok)
                System.out.println("\nErreur lors du déplacement, veuillez recommencer");
        }
                               
    }
    
}
