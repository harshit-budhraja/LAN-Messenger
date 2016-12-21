/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lan.messenger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author arachnis
 */

public class Server {
    
    public static Socket socket;
    public static ServerMessaging server = new ServerMessaging();
    
    public Server(){
        new Thread(new Receiver()).start();
        System.out.println("Receiver Server started");
    }
    
    public static void main(String[] args){
        new Thread(new Receiver()).start();
        System.out.println("Receiver Server started");
    }

static class Receiver implements Runnable {
    
    public Receiver(){
        
    }

    @Override
    public void run() {
         JFrame frame = new JFrame();
         try
        {
            int port = 5321;
            System.out.println("*******************LOGS********************");
            System.out.println("Attempting to start server on localhost:" + String.valueOf(port));
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("SERVER STARTED");
            JOptionPane.showMessageDialog(frame, "Press OK to start the server on this host");
            System.out.println("Waiting for clients to be connected");
            while(true)
            {
                socket=serverSocket.accept();
                System.out.println("CLIENT CONNECTED");
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader buffer = new BufferedReader(isr);
                System.out.println("Waiting for incoming message");
                String message = buffer.readLine();
                System.out.println("Incoming message from a client...");
                System.out.println("Message received successfully... \"" + message + "\"");
                server.setVisible(true);
                server.serverchat(message);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
                System.exit(0);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }    
    }
}
