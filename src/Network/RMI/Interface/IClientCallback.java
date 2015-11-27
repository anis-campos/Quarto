/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Interface;

import controlleur.observables.Notification;
import java.rmi.Remote;
import javafx.beans.Observable;

/**
 *
 * @author Anis
 */
public interface IClientCallback extends Remote {
    
    void update(Notification notif);
}
