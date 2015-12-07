/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import static Network.RMI.Constantes.CONNEXION;
import static Network.RMI.Constantes.PORT_RMI;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anis.DASILVACAMPOS
 */
public class ThreadServeur {

    private static ThreadServeur instance;
    private Registry registre;
    private Login serviceImp;
    private String url;
    private boolean started;

    private ThreadServeur() {

    }

    public static ThreadServeur getInstance() {
        if (instance == null) {
            instance = new ThreadServeur();
        }
        return instance;
    }

    public void start() {
        if (started) {
            return;
        }
        try {
            registre = LocateRegistry.createRegistry(PORT_RMI);
        } catch (RemoteException ex) {
            System.out.println("Une instance de serveur est déja en cours ... ");
            System.exit(-1);
        }
        try {
          

            serviceImp = new Login();

            url = "rmi://localhost" + CONNEXION;
            System.out.println("Enregistrement de l'objet avec l'url : " + url);
            Naming.rebind(url, serviceImp);

            System.out.println("Serveur lancé");
        } catch (RemoteException | MalformedURLException ex) {
              System.out.println("Impossible de lancer le serveur ... ");
            Logger.getLogger(ThreadServeur.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-2);
        }
        started = true;
    }

    public void stop() throws RemoteException, NotBoundException, MalformedURLException {

        String[] listEndpoints = Naming.list(url);
        for (String endpoint : listEndpoints) {
            System.out.println("Arret de : "+endpoint);
            Naming.unbind(endpoint);
        }
        started = false;
    }

}
