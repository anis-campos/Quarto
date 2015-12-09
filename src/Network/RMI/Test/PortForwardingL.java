/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
/**
 * This program will demonstrate the port forwarding like option -L of ssh
 * command; the given port on the local host will be forwarded to the given
 * remote host and port on the remote side. $ CLASSPATH=.:../build javac
 * PortForwardingL.java $ CLASSPATH=.:../build java PortForwardingL You will be
 * asked username, hostname, port:host:hostport and passwd. If everything works
 * fine, you will get the shell prompt. Try the port on localhost.
 *
 */
import static Network.RMI.Constantes.PORT_RMI_CLIENT_CALLBACK;
import com.jcraft.jsch.*;

public class PortForwardingL {

    public static void main(String[] arg) {

        int lport;
        String rhost;
        int rport;

        try {

            JSch jsch = new JSch();

            String user = "anis";
            String host = "quarto.cloudapp.net";

            jsch.addIdentity("~/.ssh/privatekey.pem", "RaFR2FPg5gXuWJS");

            Session session = jsch.getSession(user, host, 22);

            java.util.Properties config = new java.util.Properties();

            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            int assinged_port;
            rhost = "quarto.cloudapp.net";

//            lport = 1098;
//            rport = 1098;
//            assinged_port = session.setPortForwardingL(lport, rhost, rport);
//            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);

            lport = PORT_RMI_CLIENT_CALLBACK;
            rport = PORT_RMI_CLIENT_CALLBACK;
            session.setPortForwardingR(lport, rhost, rport);
            System.out.println("rhost:" + rport + " -> " + "localhost" + ":" + lport);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
