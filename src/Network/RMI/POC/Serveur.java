/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.POC;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anis
 */
public class Serveur {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(9999);

            ServiceImp serviceImp = new ServiceImp();

            String url = "rmi://localhost:9999/Connexion";
            System.out.println("Enregistrement de l'objet avec l'url : " + url);
            Naming.rebind(url, serviceImp);

            System.out.println("Serveur lancé");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
