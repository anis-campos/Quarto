/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Implementation;

/**
 *
 * @author Anis
 */
public class Constantes {

    //public static final String HOTE = "quarto.cloudapp.net";
    public static final String HOTE = "localhost";

    public static final int PORT_RMI = 8080;

    public static final int PORT_RMI_OBJ = 10000;

    public static final int PORT_RMI_CLIENT_CALLBACK = 5223;

    public static final String CONNEXION = "/Connexion";

    public static final String URL_CONNEXION = "rmi://" + HOTE + ":" + PORT_RMI + CONNEXION;

}
