/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI_POC;

import static RMI_POC.Constantes.*;
import java.rmi.Naming;

/**
 *
 * @author Anis
 */
public class Client {
    public static void main(String[] args) throws Exception {
       
        ILogin service = (ILogin) Naming.lookup("rmi:"+CONNEXION);
        
        ISession session = service.connexion("scott", "Azerty");
        
        System.out.println("Connexion : REUSSI !");
        
        System.out.println("Calcule 1+2+3 : " +session.sum(1,2,3));
        System.out.println("Calcule 1+2+3-5 : " +session.sum(1,2,3,-5));
        System.out.println("Calcule 6*5*4*3*2*1 : " +session.multiply(1,2,3,4,5,6));
        
        session.logout();
        
        System.out.println("Logout. Programme terminé.");
        
    }
}
