/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.POC;

import java.rmi.Naming;

/**
 *
 * @author Anis
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost:9999/Connexion";
        IService service = (IService) Naming.lookup(url);
        
        boolean connexion = service.connexion("scott", "Azerty");
        System.out.println("Connexion : "+connexion);
    }
}
