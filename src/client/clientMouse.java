package client;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.net.*;
import java.io.*;
import java.awt.event.KeyListener;
import  java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public  class clientMouse extends Thread
{

  BufferedReader br1,socketReader;
  public PrintWriter pw1;
  Socket client;
  JFrame frame;
  double titleBar=0;
  double border=0;
  double serverScreenHeight=0;
  double serverScreenWidth=0;

  public clientMouse(String host,int port)
  {
    try
    {
     
      client=new Socket(host,port);
      br1=new BufferedReader(new InputStreamReader(System.in));
      socketReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
      pw1=new PrintWriter(client.getOutputStream(),true);

      try
      {
        serverScreenHeight=Double.parseDouble(socketReader.readLine());
        serverScreenWidth=Double.parseDouble(socketReader.readLine());
      }
      catch(Exception e)
      {
        System.err.println(e);
      }

    }

    catch(Exception ee)
    {
      System.out.println(ee);
    }
      
  
  }


    MouseListener listener = new MouseListener() {

@Override

public void mouseClicked(MouseEvent arg0) {
 
}

public void mouseExited(MouseEvent arg0) {

}

public void mouseEntered(MouseEvent arg0) {

}

public void mousePressed(MouseEvent arg0) {
  System.out.println("Mouse press");
  System.out.println("X -> "+getRealX(arg0.getX())+" Y -> "+getRealY(arg0.getY())+" Button -> "+arg0.getButton());
  pw1.println(getRealX(arg0.getX())+" "+getRealY(arg0.getY())+" "+arg0.getButton()+" MP");
}

public void mouseReleased(MouseEvent arg0) {

    System.out.println("Mouse release"); 
    System.out.println("X -> "+getRealX(arg0.getX())+" Y -> "+getRealY(arg0.getY())+" Button -> "+arg0.getButton());
    pw1.println(getRealX(arg0.getX())+" "+getRealY(arg0.getY())+" "+arg0.getButton()+" MR");
}


  };

  public int getRealX(int x)
  {
    
    double dX=(double)x;
    double scaleFactor=serverScreenWidth/(frame.getContentPane().getWidth());
    double realX=(double)(dX-border);
    return (int)(scaleFactor*realX);
  }

  public int getRealY(int y)
  {

  
    double dY=(double)y;
    double scaleFactor=serverScreenHeight/(frame.getContentPane().getHeight());
    double realY=(double)(dY-titleBar);
    return (int)(scaleFactor*realY);
  }

  

  public void run()
  {
    
 
    Frame[] frames=Frame.getFrames();
    for(int i=0;i<frames.length;i++)
    {
      System.out.println(frames[i].toString());
    }
    frame=(JFrame) frames[3];
  
    border=((frame.getWidth())-(frame.getContentPane().getWidth()))/2;
    titleBar=((frame.getHeight())-(frame.getContentPane().getHeight()))-border;
    System.out.println("Title bar -> "+titleBar);
    System.out.println("Border -> "+border);
    frames[3].addMouseListener(listener);
  }
}

  
    

 
    
