/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lan.messenger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author arachnis
 */

public class Client {
    
    public Client(){
    }
    
    public static Socket socket;
    InputStream is;
    
    public void startClient(String host) {
        try
        {
            int port = 5321;
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
            is = socket.getInputStream();
            new Thread(new Receiver(is)).start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Could not establish a connection to " + host);
            System.exit(0);
        }
    }
    
    public void sendMessage(String message){
            OutputStream os = null;
        try {
            os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter buffer = new BufferedWriter(osw);
            buffer.write(message);
            buffer.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Receiver implements Runnable{
    
    InputStream is;
    public Receiver(InputStream is){
        this.is = is;
    }

    @Override
    public void run() {
        while(true){
            System.out.println("Inside client receiver thread");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = null;
            try {
                System.out.println("Inside client receiver thread before readline");
                message = br.readLine();
                System.out.println("Recieved message is :- " + message);
            } catch (IOException ex) {
                Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Message received from the server : " +message);
            if(message!=null)
                Selection.m.serverchat(message + "\n");
        }
    }
    
}
