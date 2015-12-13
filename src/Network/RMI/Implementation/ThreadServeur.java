/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Implementation;

import static Network.RMI.Implementation.Constantes.CONNEXION;
import static Network.RMI.Implementation.Constantes.HOTE;
import static Network.RMI.Implementation.Constantes.PORT_RMI;
import static Network.RMI.Implementation.Constantes.PORT_RMI_OBJ;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import org.apache.log4j.Logger;

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

    private static final Logger logger = Logger.getLogger(ThreadServeur.class);

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
            System.setProperty("java.net.preferIPv4Stack" , "true");
            System.setProperty("java.rmi.server.hostname", HOTE );
            RMISocketFactory.setSocketFactory(new MyRMISocketFactory(PORT_RMI_OBJ));
            registre = LocateRegistry.createRegistry(PORT_RMI);

            serviceImp = new Login();

            url = "rmi://"+HOTE+":"+PORT_RMI + CONNEXION;
            System.out.println("Enregistrement de l'objet avec l'url : " + url);
            Naming.rebind(url, serviceImp);

            System.out.println("Serveur lancé");

        } catch (RemoteException ex) {
            logger.error(ex);
            System.exit(-1);
        } catch (IOException ex) {
            logger.error(ex);
            System.exit(-2);
        }
        
        started = true;
    }

    public void stop() throws RemoteException, NotBoundException, MalformedURLException {

        String[] listEndpoints = Naming.list(url);
        for (String endpoint : listEndpoints) {
            System.out.println("Arret de : " + endpoint);
            Naming.unbind(endpoint);
        }
        started = false;
    }

}
