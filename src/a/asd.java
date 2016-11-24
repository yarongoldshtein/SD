/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yaron
 */
public class asd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
<<<<<<< HEAD
        
        String path="C:\\Users\\asus\\Desktop\\לימודים\\שנה ג\\מבנה תוכנה\\Ex1\\G0.txt";
        try { 
            Graph g= new Graph(path);
            g.display();
            System.out.println("Diameter: "+g.getDiameter());
            System.out.println("Radius: "+g.getRadius());
            
        } catch (IOException ex) {
            Logger.getLogger(asd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
=======
        System.out.println("hello wolrd");
        int y= 0; 
        System.out.println(y);
>>>>>>> 89beaee23b773184bdce6b6ace1cb0ecd91b318c
    }
    
}
