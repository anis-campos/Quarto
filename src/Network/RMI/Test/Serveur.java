/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anis
 */
public class Serveur {

    public static void main(String[] args) {
        try {
            ThreadServeur.getInstance().start();
            Scanner sc = new Scanner(System.in);
            System.out.println("Pour arreter le serveur appuyer sur Entrée ");
            sc.nextLine();
            ThreadServeur.getInstance().stop();
            System.exit(0);
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
