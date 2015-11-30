/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;

import static Network.RMI.Constantes.CONNEXION;
import static Network.RMI.Constantes.PORT_RMI;
import Network.RMI.Login;
import Network.RMI.Test.Serveur;
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
public class LauncherServer {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(PORT_RMI);

            Login serviceImp = new Login();

            String url = "rmi:" + CONNEXION;
            System.out.println("Enregistrement de l'objet avec l'url : " + url);
            Naming.rebind(url, serviceImp);

            System.out.println("Serveur lancé");
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
