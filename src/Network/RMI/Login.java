/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Databse.Compte;
import Databse.CompteDAL;
import Network.RMI.Interface.ILogin;
import Network.RMI.Interface.ISession;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.security.auth.login.LoginException;

/**
 *
 * @author Anis
 */
public class Login extends UnicastRemoteObject implements ILogin {

    public Login() throws RemoteException {
    }

    @Override
    public ISession connexion(String login, String password) throws LoginException, RemoteException {
        CompteDAL dal = CompteDAL.getDAL();
        
        if (!dal.checkLogin(login, password)) {
            throw new LoginException();
        }
        
        Compte compte = dal.findCompte(login);
        
        Session serveur = new Session(compte);
       
       return serveur;
    }



}
