/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Network.RMI.Interface.IClientCallback;
import controlleur.ControlleurDistant;
import controlleur.observables.Notification;
import java.rmi.RemoteException;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author Anis
 */
public class ClientCallback extends Observable implements IClientCallback {

    private static final Logger logger = Logger.getLogger(ControlleurDistant.class);

    
    public ClientCallback() {
    }

    @Override
    public void notifyMe(Notification notif) throws RemoteException {
        logger.info("Notification Recu : " + notif.toString());
        setChanged();
        notifyObservers(notif);
    }

}
