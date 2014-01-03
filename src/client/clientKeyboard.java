package client;
import java.awt.Robot;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;




import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class clientKeyboard extends Thread implements NativeKeyListener {

	BufferedReader br1,socketReader;
    public PrintWriter pw1;
 	Socket client;

    public clientKeyboard(int port)
    {
    	try
        {
            
            client=new Socket("localhost",port);
            br1=new BufferedReader(new InputStreamReader(System.in));
            socketReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw1=new PrintWriter(client.getOutputStream(),true);
            
        }

        catch(Exception ee)
        {
            System.out.println(ee);
        }
    	
 	
    }

    
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + e.getKeyCode());
        pw1.println(e.getKeyCode()+"KP");

        if (e.getKeyCode() == NativeKeyEvent.VK_ESCAPE) {
                GlobalScreen.unregisterNativeHook();
               // pw1.println(e.getKeyCode());
        }
}

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + e.getKeyCode());
       pw1.println(e.getKeyCode()+"KR");
}

public void nativeKeyTyped(NativeKeyEvent e) {
  //      System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
   //     pw1.println(e.getKeyCode());
}
   
    
    public void run()
	{

        try {
                GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());

                System.exit(1);
        }
        final int port=5000;
        //Construct the example object and initialze native hook.
        GlobalScreen.getInstance().addNativeKeyListener(new clientKeyboard(port));


		
	}
}

	
    

 
    
