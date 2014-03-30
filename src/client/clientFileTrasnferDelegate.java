package client;
import java.io.*;
class clientFileTransferDelegate extends Thread
{
	clientFileTransfer c=null;
	public clientFileTransferDelegate(String host,int port)
	{
		System.out.println("making delegate");
		 c=new clientFileTransfer(host,port);
		 System.out.println("made delegate");
	}
	public void  run() 
	{
		
		Thread t=new Thread(c);
		t.start();
		System.out.println("this is running");
	}
}