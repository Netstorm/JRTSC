package server;
import java.io.*;
import java.net.*;
import java.awt.Robot;

import java.awt.event.MouseEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;

public class serverMouse extends Thread 
{

	BufferedReader keyboardBuffer,socketReader;
        

  PrintWriter socketWriter;
  String st1,st2;
  ServerSocket server;
  Socket connection;

  public serverMouse(int port)
  {
    try 
      {
        server=new ServerSocket(port);
        connection=server.accept();
        keyboardBuffer=new BufferedReader(new InputStreamReader(System.in));
        socketReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        socketWriter=new PrintWriter(connection.getOutputStream(),true);
      
    	}

    catch(Exception ee)
    	{
        System.out.println(ee);
    	}
	}

	
	
	void controlMouse() throws IOException
	{

		try 
		{
        
		while(true)
    {
        Thread.sleep(1000);
         Robot robot = new Robot();
        // Simulate a key press
        
        String key=socketReader.readLine();
        String[] splited = key.split("\\s+");
       

        String keyCode=splited[0].replaceAll("\\D","");
        String Xpos=splited[1].replaceAll("\\D","");
        String Ypos=splited[2].replaceAll("\\D","");


        if(splited[0].contains("MP"))
          {
            

            robot.mouseMove(Integer.parseInt(Xpos),Integer.parseInt(Ypos));

            //int mask = InputEvent.BUTTON1_DOWN_MASK;
              //robot.mousePress(mask);



          }
        if(splited[0].contains("MR"))
          {

              System.out.println("Mouse X position: "+Xpos);
              System.out.println("Mouse Y position: "+Ypos);
              System.out.println("Mouse key : "+keyCode);
            
             robot.mouseMove(Integer.parseInt(Xpos),Integer.parseInt(Ypos));
              //robot.mouseRelease(Integer.parseInt(keyCode));
            
          }

    }         
        
    }

        

		 
		catch (Exception e) 
		{
        	e.printStackTrace();
		}

	}

	public void run() 
	{

		//while(true)
		{
			
			try 
			{
				controlMouse();
				Thread.sleep(100);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


}