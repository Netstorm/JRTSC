package server;
import java.io.*;
import java.net.*;
import java.awt.Robot;


public class serverKeyboard extends Thread {

	BufferedReader keyboardBuffer,socketReader;
	PrintWriter socketWriter;
	String st1,st2;
	ServerSocket server;
	Socket connection;

	public serverKeyboard(int port)
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

	
	
	void controlKeyboard() throws IOException
	{

		try 
		{
        
		 Robot robot = new Robot();
        // Simulate a key press
		 System.out.println("server key started");
        int key=Integer.parseInt(socketReader.readLine());
        
        System.out.println("Key received is: "+key);
        //robot.keyPress(key);
        //robot.keyRelease(key);
        
        }

        

		 
		catch (Exception e) {
        e.printStackTrace();
}

	}

	public void run() 
	{

		while(true)
		{
			
			try 
			{
				controlKeyboard();
				Thread.sleep(100);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


}