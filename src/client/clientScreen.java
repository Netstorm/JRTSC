package client;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.RescaleOp;


public class clientScreen extends Thread 
{

 	public Socket client;
  public JLabel label;
  public JFrame frame;
  public JPanel panel;
  public Graphics g;
  DataInputStream is=null;

  public clientScreen(String host,int port)
  {
    try
    {
      client=new Socket(host,port);
      is =new  DataInputStream(client.getInputStream());
      try
      {
        drawGUI();
      }
      catch(Exception ex)
      {
        System.err.println(ex);
      }

    }

    catch(Exception ee)
    {
      System.out.println(ee);
    }
    	
 	
  }

  public JFrame getApplicationFrame()
  {
    return frame;
  }
   
    
  public void run()
	{
    try 
    {
      getScreen();

    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  
	}


  public void drawGUI() throws IOException
  {
      File input = new File("default.jpg");
      final BufferedImage image = ImageIO.read(input);

        frame = new JFrame("Server Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        panel = new JPanel(false) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0,frame);
            }
        };
        frame.add(panel);
        frame.validate(); 
        frame.repaint();

  }

  void refreshScreen(final BufferedImage bufImg,JPanel panel)
  {
    g= panel.getGraphics();
    g.drawImage(bufImg, 0, 0, frame.getContentPane().getWidth(),frame.getContentPane().getHeight(),frame);
    g.dispose();  
  }



  void getScreen()
  {
            
      String toggle="1";
      BufferedImage bImageFromConvert =null;
      int count=0;

     
      while(true)
      {    
        try 
        {

       
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          byte buffer[] = new byte[1024];

          int len = is.readInt();
          byte data[] = null;
          data = new byte[len];
          is.readFully(data);
          baos.flush();
          baos.close();
                   

          InputStream in = new ByteArrayInputStream(data);
          bImageFromConvert = ImageIO.read(in);
                   
          if (bImageFromConvert!=null) 
          {
            refreshScreen(bImageFromConvert,panel);
         
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

	
    

 
    
