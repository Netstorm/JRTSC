package server;
import java.io.*;
import java.net.*;
import java.awt.Robot;

import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.ByteBuffer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class serverScreen extends Thread 
{

	BufferedReader keyboardBuffer,socketReader;
  PrintWriter socketWriter;
  String st1,st2;
  ServerSocket server;
  Socket connection;
  //BufferedOutputStream outToClient = null;
  DataOutputStream outToClient=null;
    DataOutputStream dos=null;
  public serverScreen(int port)
  {
    try 
      {
        server=new ServerSocket(port);
        connection=server.accept();
        connection.setKeepAlive(true);
        connection.setSoTimeout(50000);
       // outToClient = (connection.getOutputStream());
        dos= new DataOutputStream(connection.getOutputStream());
        
    
    	}

    catch(Exception ee)
    	{
        System.out.println(ee);
    	}
	}

	
	
	void streamScreen() throws IOException
	{


		try 

		{ 
            
               

              Robot robot = new Robot();
      
              float quality= 0.4f;
              Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
              BufferedImage rawImg=null,prevImg=null;

              BufferedImage image=null;
              ImageOutputStream ios=null;
                
              String toggle="1";
              while(toggle.equals("1"))
              {


                rawImg=null;
                rawImg=robot.createScreenCapture(new Rectangle(size));


                if(bufferedImagesEqual(rawImg,prevImg))
                {
                // System.out.println("Both are same");
                 continue; 
                }

                else
                {
              
                 // System.out.println("its different");
                  
                  Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

                  if (!writers.hasNext())
                      throw new IllegalStateException("No writers found");
               
                  ImageWriter writer = (ImageWriter) writers.next();
                  ImageWriteParam param = writer.getDefaultWriteParam();
                  param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                  param.setCompressionQuality(quality);      

                  ByteArrayOutputStream baos=new ByteArrayOutputStream(819200);
                  ios = ImageIO.createImageOutputStream(baos);
                  


                  writer.setOutput(ios);
                  writer.write(null, new IIOImage(rawImg, null, null), param);
                  
                  byte[] result=baos.toByteArray();
                 // System.out.println("YES"+((result.length)));
                  //outToClient.flush();
                  //outToClient.write(result);
                 // outToClient.flush();

                  //connection.shutdownOutput();
                  
                  dos.writeInt(result.length);
                 // System.out.println(result.length);
                  dos.write(result);
                  dos.flush();


                  baos.flush();
                  baos.close();
                  ios.flush();
                  ios.close();
                  writer.abort();
                  prevImg=null;
                  prevImg=rawImg;
                  writer.dispose();
                  //Thread.sleep(1000);
                  //outToClient.close();
                 // outToClient.flush();
                 // Thread.sleep(200);
                  //toggle="2";
                }

              }
            
    }
       
   
		catch (Exception e) 
		{
        	e.printStackTrace();
          System.out.println("WOW SOME EXCEPTION!");


		}

	}

	public void run() 
	{


			
			try 
			{
				  streamScreen();
				  //Thread.sleep(100);
			}

			catch(Exception e)
			{
				  e.printStackTrace();
			}
		
		
	}



  boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) 
  {
      if (img1==null || img2==null) 
      {
        return false;
      }

      if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) 
      {
        for (int x = 0; x < img1.getWidth(); x++) 
        {
            for (int y = 0; y < img1.getHeight(); y++) 
            {
                if (img1.getRGB(x, y) != img2.getRGB(x, y))
                    return false;
            }
        }
      } 
      else 
      {
        return false;
      }
      return true;
  }


  void readAndWriteScreen()
  {

                  
                  String fileToSend = "myimage_compressed.jpg";
                  File myFile = new File( fileToSend );
                  byte[] mybytearray = new byte[(int) myFile.length()];

                  FileInputStream fis = null;

                  try 
                  {
                    fis = new FileInputStream(myFile);
                  } 
                  catch (FileNotFoundException ex) 
                  {
                    // Do exception handling
                  }
                  BufferedInputStream bis = new BufferedInputStream(fis);

                  try 
                  {
                    bis.read(mybytearray, 0, mybytearray.length);
                    outToClient.write(mybytearray, 0, mybytearray.length);
                    outToClient.flush();
                    outToClient.close();
                  } 
                  
                  catch (IOException ex) 
                  {
                  
                  }
                    
                  
                  
  }


}
