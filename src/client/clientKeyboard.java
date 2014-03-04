package client;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.net.*;
import java.io.*;
import java.awt.event.KeyListener;
import  java.awt.Frame;

public  class clientKeyboard extends Thread
{

  BufferedReader br1,socketReader;
  public PrintWriter pw1;
  Socket client;
  JFrame frame;

  public clientKeyboard(String host,int port)
  {
    try
    {
     
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


    KeyListener listener = new KeyListener() {

@Override

public void keyPressed(KeyEvent event) {

     System.out.println("Key Pressed "+event.getKeyCode());
     pw1.println(event.getKeyCode()+"KP");

}

@Override

public void keyReleased(KeyEvent event) {

    System.out.println("Key Released "+event.getKeyCode());
    pw1.println(event.getKeyCode()+"KR");

}

@Override

public void keyTyped(KeyEvent event) {

     System.out.println("Key Typed "+event.getKeyCode());

}


  };

  public void run()
  {
    Frame[] frames=Frame.getFrames();
    frames[3].addKeyListener(listener);
  }
}

  
    

 
    
