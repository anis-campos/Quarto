/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 *
 * @author Anis
 */
public class MyRMISocketFactory extends RMISocketFactory {

    private static int PREFERED_PORT;

    
    
    public MyRMISocketFactory(int port) {
       PREFERED_PORT = port;
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port == 0 ? PREFERED_PORT : port, Integer.MAX_VALUE, InetAddress.getByName("127.0.0.1"));
        //return new ServerSocket(port == 0 ? PREFERED_PORT : port);
    }

    
    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return RMISocketFactory.getDefaultSocketFactory()
                .createSocket(host, port);
    }

    @Override
    public int hashCode() {
        return 57;
    }

    @Override
    public boolean equals(Object o) {
        return this.getClass() == o.getClass();
    }

}
