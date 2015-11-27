/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_POC;

import com.sun.javafx.css.CalculatedValue;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.security.auth.login.LoginException;

/**
 *
 * @author Anis
 */
public class Login extends UnicastRemoteObject implements ILogin {

    protected Login() throws RemoteException {
        super();
    }

    @Override
    public ISession connexion(String login, String password) throws RemoteException, LoginException {
         if(login.equals("scott") && password.length() == 6)
             return new Calculateur();
        
         throw  new LoginException();   
    }

}
