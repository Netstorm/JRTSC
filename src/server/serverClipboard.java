package server;
import java.io.*;
import java.net.*;
import java.awt.Robot;
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


public class serverClipboard extends Thread
{

	BufferedReader keyboardBuffer,socketReader;
	PrintWriter socketWriter;
	String st1,st2;
	ServerSocket server;
	Socket connection;
	String content;
	String prevContent;
	int i=0;

	public serverClipboard(int port)
	{
    try 
    	{
        
        server=new ServerSocket(port);
       	connection=server.accept();
        //keyboardBuffer=new BufferedReader(new InputStreamReader(System.in));
        socketReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        //socketWriter=new PrintWriter(connection.getOutputStream(),true);
      
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

  

  /*public static void main(String args[])
  {
     
  	content = "hi";
  	System.out.println(prevContent);
     if(prevContent==null)
  	{
  		System.out.println("Was null");
  		prevContent=getClipboardData();
  		System.out.println(prevContent);
  	}
  	//content = socketReader.readLine();
  	 else if(!prevContent.equals(content))
  	 {
  	 	prevContent = content;
  	 	System.out.println(prevContent);
  	 }
  }

*/
  public void checkClipboard()throws IOException
  {

  	
  	content = socketReader.readLine();

  	if(prevContent==null)
  	{
  
  		prevContent=content;
  		//System.out.println(content);
  		//System.out.println(prevContent);
  	}
  	 
     else if(!content.equals(prevContent))
	 {
	 
    prevContent= content;
	 	setClipboardData(content);
	

	 }
	
	 
  }
  public void run()
  {
    
    try
    {
  	while(true)
  	{
  	
      checkClipboard();
  	}

  }

  catch(Exception e)
  {

  }

  }
  
}

	
