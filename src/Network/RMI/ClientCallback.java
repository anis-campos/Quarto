/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Network.RMI.Interface.IClientCallback;
import controlleur.observables.Notification;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;

/**
 *
 * @author Anis
 */
public class ClientCallback extends UnicastRemoteObject implements IClientCallback {

    private static final Logger logger = Logger.getLogger(ControlleurDistant.class);
    
    private Transmeteur transfereur;

    public ClientCallback() throws RemoteException {
       transfereur = new Transmeteur();
    }

    /**
     * Fonction appelé depuis le serveur pour notifier le client.
     * @param notif
     * @throws RemoteException 
     */
    @Override
    public void notifyMe(Notification notif) throws RemoteException {
        String message = String.format("Notification Recu : %s", notif.toString());
        logger.info(message);
        transfereur.setNotification(notif);
        transfereur.Transferer();

    }
    


    public void addObserver(Observer observer) {
        transfereur.addObserver(observer);
    }

    public class Transmeteur extends Observable implements Serializable {

        private Notification notification;

        public void setNotification(Notification notification) {
            this.notification = notification;
            setChanged();
        }

        public void Transferer() {
            notifyObservers(notification);
        }

    }

}
