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
  public clientKeyboard kb;

  //public JPanel cPanel=null;
 // BufferedInputStream is=null;
    DataInputStream is=null;

 // public Graphics graphics = null;
   

  public clientScreen(String host,int port)
  {
    try
    {
      client=new Socket(host,port);
     // is =new  BufferedInputStream(client.getInputStream());
      is =new  DataInputStream(client.getInputStream());
      kb=new clientKeyboard(host,port+10,frame);
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
     // Thread.sleep(200);
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
      //frame=new JFrame("Server Screen");
      //JPanel frame=new JPanel();
      //Graphics graphics = frame.getGraphics();
     // if(graphics==null)
       // System.out.println(":null");
        //graphics.drawImage(image, 0, 0, 1920,1080,frame);
      //label=new JLabel(new ImageIcon(image));
      //frame.getContentPane().add(label,BorderLayout.CENTER);
      //frame.pack();
      //frame.setVisible(true);
      //JPanel cPanel = new JPanel();
      //ImageIcon imageIcon= new ImageIcon(image);
      //Image image = imageIcon.getImage();
      //image = image.getScaledInstance(cPanel.getWidth(),cPanel.getHeight(),Image.SCALE_FAST);
   //Graphics graphics = cPanel.getGraphics();
    //graphics.drawImage(image, 0, 0, 1920,1080,cPanel);


//ImageIcon image = new ImageIcon(ima);
//JLabel label = new JLabel("", imageIcon, JLabel.CENTER);
//JPanel panel = new JPanel(new BorderLayout());
//panel.add( label, BorderLayout.CENTER );

        frame = new JFrame("Server Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1920, 1080);

        panel = new JPanel(false) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, image.getWidth(),image.getHeight(),frame);
            }
        };
        frame.add(panel);
        frame.validate(); // because you added panel after setVisible was called
        frame.repaint(); // because you added panel after setVisible was called

        Thread keyboardThread=new Thread(kb);
        keyboardThread.start();
      


  }

  void refreshScreen(final BufferedImage bufImg,JPanel panel)
  {
    g= panel.getGraphics();
  // g.clearRect(0,0,bufImg.getWidth(),bufImg.getHeight());
    g.drawImage(bufImg, 0, 0, bufImg.getWidth(),bufImg.getHeight(),frame);
    g.dispose();

  
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

         // is.mark(819200);
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          byte buffer[] = new byte[1024];

                    //for(int s; (s=is.read(buffer)) != -1; )
                   //{
                     //   baos.write(buffer, 0, s);
                    //}
         // byte[] sam=new byte[8192000];
         //if(is.read(sam)<50)
         // {

       //     continue;
      //    }

          int len = is.readInt();
          System.out.println(len);
          byte data[] = null;
          data = new byte[len];
          is.readFully(data);
         // is.reset();
          baos.flush();
          baos.close();
                   

          InputStream in = new ByteArrayInputStream(data);
          bImageFromConvert = ImageIO.read(in);
                   
          if (bImageFromConvert!=null) 
          {
            System.out.println("Image received is -> "+bImageFromConvert.toString());
            refreshScreen(bImageFromConvert,panel);
         //     g.drawImage(bImageFromConvert, 0, 0, bImageFromConvert.getWidth(),bImageFromConvert.getHeight(),frame);
         



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

	
    

 
    
