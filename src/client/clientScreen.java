package client;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JLabel;


import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;



public class clientScreen extends Thread
{

 	public Socket client;
  public JLabel label;
  public JFrame frame;
  BufferedInputStream is=null;


  public clientScreen(int port)
  {
    try
    {
      client=new Socket("192.168.2.5",port);
      is =new  BufferedInputStream(client.getInputStream());
    }

    catch(Exception ee)
    {
      System.out.println(ee);
    }
    	
 	
  }

  
   
    
  public void run()
	{
    try 
    {
      getScreen();
      Thread.sleep(200);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  
	}


  void drawGUI() throws IOException
  {
      File input = new File("default.jpg");
      BufferedImage image = ImageIO.read(input);
      frame=new JFrame("Server Screen");
      label=new JLabel(new ImageIcon(image));
      frame.getContentPane().add(label,BorderLayout.CENTER);
      frame.pack();
      frame.setVisible(true);

  }

  void refreshScreen(BufferedImage bufImg)
  {
      label.setIcon(new ImageIcon(bufImg));
      frame.repaint();
      frame.getContentPane().repaint();
     
  }



  void getScreen()
  {
            
      String toggle="1";
      BufferedImage bImageFromConvert =null;
      int count=0;

      try
      {
        drawGUI();
      }
      catch(Exception ex)
      {
        System.err.println(ex);
      }
      while(true)
      {    
        try 
        {

          is.mark(819200);
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          byte buffer[] = new byte[1024];

                    //for(int s; (s=is.read(buffer)) != -1; )
                   //{
                     //   baos.write(buffer, 0, s);
                    //}
          byte[] sam=new byte[8192000];
          if(is.read(sam)<5)
          {

            continue;
          }
            
          is.reset();
          baos.flush();
          baos.close();
                   

          InputStream in = new ByteArrayInputStream(sam);
          bImageFromConvert = ImageIO.read(in);
                   
          if (bImageFromConvert!=null) 
          {
            System.out.println("Image received is -> "+bImageFromConvert.toString());
            refreshScreen(bImageFromConvert);
            bImageFromConvert=null;
          }
          else
          {
            bImageFromConvert=null;
            continue;
          }
                    
      }

          catch (IOException ex) 
          {
            System.err.println(ex); 
          } 
            
    } 
           
  }
}

	
    

 
    
