/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testwebserver;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


/**
 *
 * @author leppa
 */
public class TestWebServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            // TODO code application logic here
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/test", new  MyHttpHandler());
            server.start();
            System.out.println(" Server started on port 8001");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
