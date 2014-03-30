package server;
import java.io.*;
import java.net.*;
import java.awt.Robot;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class serverFileTransfer extends Thread 
{

	BufferedReader keyboardBuffer,socketReader;
	PrintWriter socketWriter;
	String st1,st2;
	ServerSocket server;
	Socket connection;
	InputStream in;
	ObjectInputStream ois;
	ObjectOutputStream oos ;

	public serverFileTransfer(int port)
	{
    try 
    	{
        	server=new ServerSocket(port);
       		connection=server.accept();
       		ois= new ObjectInputStream(connection.getInputStream());
    	}

    catch(Exception ee)
    	{
        	System.out.println(ee);
    	}
	}

	void receiveFile() throws Exception
    {
         
    	String fileName = (String) ois.readObject();
        byte[] binaryFileData=(byte[]) ois.readObject();
        writeSmallBinaryFile(binaryFileData,"ReceivedFiles\\"+fileName);

    }   


    void writeSmallBinaryFile(byte[] aBytes, String aFileName) throws IOException 
    {
    	Path path = Paths.get(aFileName);
    	Files.write(path, aBytes);
  	}

	
	

	public void run() 
	{

		while(true)
		{
			
			try 
			{
				receiveFile();
			}
			catch(Exception e)
			{
				System.exit(0);
				e.printStackTrace();
			}
		}
		
	}


}