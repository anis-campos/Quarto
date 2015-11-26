/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.POC;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javax.security.auth.login.LoginException;

/**
 * Cette interface donne un accces aux fonctionnalités uniquement
 *  à un client <b>Identifié</b>
 * @author Anis
 */
public interface ILogin extends Remote {
    
    ISession connexion(String login, String password) throws LoginException, RemoteException;
    
    
    
}
