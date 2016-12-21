/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lan.messenger;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author harshitbudhraja
 */
public class LANMessenger {
    public static String hostIP=null;
    public static InetAddress myIP;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            myIP=InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(LANMessenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Selection().setVisible(true);
    }
    
}
