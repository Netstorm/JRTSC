package server;
import java.io.*;
import java.net.*;
import java.awt.Robot;
import java.awt.*;
import java.awt.event.InputEvent;

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
        //keyboardBuffer=new BufferedReader(new InputStreamReader(System.in));
        socketReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        socketWriter=new PrintWriter(connection.getOutputStream(),true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		socketWriter.println(screenSize.getHeight());
		socketWriter.println(screenSize.getWidth());
		
      
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
        int mask=0;
		Robot robot = new Robot();
        // Simulate a key press
        String mouseAction=socketReader.readLine();
        String[] mouseActionSplit=mouseAction.split(" ");
		

		if(mouseActionSplit[2].equals("1"))
		{
			mask=InputEvent.BUTTON1_MASK;
		}
		else
			if(mouseActionSplit[2].equals("2"))
			{
				mask=InputEvent.BUTTON2_MASK;
			}
			else
				if(mouseActionSplit[2].equals("3"))
				{
					mask=InputEvent.BUTTON3_MASK;
				}

		if(mouseActionSplit[3].equals("MP"))
		{
			robot.mouseMove(Integer.parseInt(mouseActionSplit[0]),Integer.parseInt(mouseActionSplit[1]));
			robot.delay(100);
			robot.mousePress(mask);

		}
		else
		if(mouseActionSplit[3].equals("MR"))
		{
			robot.mouseMove(Integer.parseInt(mouseActionSplit[0]),Integer.parseInt(mouseActionSplit[1]));
			robot.delay(100);
			robot.mouseRelease(mask);
		}

        

        
        
        }

        

		 
		catch (Exception e) 
		{
        	e.printStackTrace();
		}

	}

	public void run() 
	{

		while(true)
		{
			
			try 
			{
				controlMouse();
				//Thread.sleep(100);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


}