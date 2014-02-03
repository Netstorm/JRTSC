package client;
import java.awt.Robot;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


import java.lang.reflect.*;
public class clientMouse extends Thread implements NativeMouseInputListener  
{

	BufferedReader br1,socketReader;
    public PrintWriter pw1;
    

 	Socket client;

    public clientMouse(int port)
    {
    	try
        {
            
            client=new Socket("192.168.2.5",port);
            br1=new BufferedReader(new InputStreamReader(System.in));
            socketReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw1=new PrintWriter(client.getOutputStream(),true);
           
            
            
        }

        catch(Exception ee)
        {
            System.out.println(ee);
        }
    	
 	
    }

    //if key is pressed send the keycode to the server
    //There are two main events key press and key release 



     public void nativeMouseClicked(NativeMouseEvent e) {
                System.out.println("Mosue Clicked: " + e.getClickCount());
                //pw1.println(e.getClickCount()+"MC");
      
        }

        public void nativeMousePressed(NativeMouseEvent e) {
                System.out.println("Mosue Pressed: " + e.getButton());
                pw1.println(e.getButton()+"MP"+" "+"X"+e.getX()+" "+"Y"+e.getY());
  
                
        }

        public void nativeMouseReleased(NativeMouseEvent e) {
                System.out.println("Mosue Released: " + e.getButton());
                pw1.println(e.getButton()+"MR"+" "+"X"+e.getX()+" "+"Y"+e.getY());

        }

        public void nativeMouseMoved(NativeMouseEvent e) {
               System.out.println("Mosue Moved: " + e.getX() + ", " + e.getY());
        }

        public void nativeMouseDragged(NativeMouseEvent e) {
                System.out.println("Mosue Dragged: " + e.getX() + ", " + e.getY());
        }


    
    public void run()
	{

        try 
        {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) 
        {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        
        //Construct the keyboardClient object and initialze native hook.
         //GlobalScreen.getInstance().addNativeKeyListener(this);
         GlobalScreen.getInstance().addNativeMouseListener(this);
         GlobalScreen.getInstance().addNativeMouseMotionListener(this);
       


		
	}
}

	
    

 
    
