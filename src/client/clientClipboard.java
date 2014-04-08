package client;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.net.*;
import java.io.*;
//import java.awt.event.KeyListener;
import  java.awt.Frame;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;

public class clientClipboard extends Thread
{

  BufferedReader br1,socketReader;
  public PrintWriter pw1;
  Socket client;
  JFrame frame;
  static String current,data=null;

  public clientClipboard(String host,int port)
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

   public static String getClipboardData()
    {
        String clipboardText;
        Transferable trans = Toolkit.getDefaultToolkit().getSystemClipboard()
                .getContents(null);
 
        try {
            if (trans != null && trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                clipboardText = (String) trans
                        .getTransferData(DataFlavor.stringFlavor);
                return clipboardText;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

     public static void setClipboardData(String string) 
     {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                stringSelection, null);
    }

  

/*  public static void main(String args[])
  {
    data= getClipboardData();
    System.out.println(current);
    if(!data.equals(current))
      System.out.println("Will write to socket");
    else
    {
      System.out.println("Will Sleep");
    }

  }
*/

  public void checkClipboard()
  {

    try
    {
    
    data = getClipboardData();
   // System.out.println(current);
    if(!data.equals(current))
    {
      current = getClipboardData();
      pw1.println(current);
     
    }
    else
      {
        Thread.sleep(5000);
        
      }
  }

  catch(Exception e)
  {

  }
  }
  
  public void run()
  {
    
    while(true)
    {
      checkClipboard();
    }

  }
  
}

  
    

 
  