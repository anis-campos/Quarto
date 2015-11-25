/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remoteLauncher;

import Network.Serveur;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anis
 */
public class LauncherServer {


    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
        
        System.out.println("Pour lancer le serveur, veuillez appuyer sur entrer ....");
        sc.nextLine();
        System.out.println("Veuillez appuyer sur entrer pour l'arreter...");
       
        
        Thread thread = new Thread(Serveur.getInstance());
        thread.start();
       
        
        sc.nextLine();
        Serveur.getInstance().stop();
        System.out.println("Serveur arrete");
    }

}
