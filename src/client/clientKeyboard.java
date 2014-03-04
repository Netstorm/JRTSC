package client;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.net.*;
import java.io.*;


 class clientKeyboard extends KeyAdapter implements Runnable 
{

  BufferedReader br1,socketReader;
  public PrintWriter pw1;
  Socket client;
  JFrame frame;

  public clientKeyboard(String host,int port,JFrame jframe)
  {
    try
    {
      frame=jframe;
      client=new Socket(host,port);
      br1=new BufferedReader(new InputStreamReader(System.in));
      socketReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
      pw1=new PrintWriter(client.getOutputStream(),true);
    }

    catch(Exception ee)
    {
      System.out.println(ee);
    }
      
  
  }


  public void keyPressed(KeyEvent e) 
  {
    System.out.println("Key Pressed: " + e.getKeyCode());
    pw1.println(e.getKeyCode()+"KP");
  }
  

  public void keyReleased(KeyEvent e) 
  {
    System.out.println("Key Released: " + e.getKeyCode());
    pw1.println(e.getKeyCode()+"KR");
  }

  public void run()
  {
    frame.addKeyListener(this);
  }
}

  
    

 
    
