package client;
import java.io.*;
class clientMouseDelegate extends Thread
{
	clientMouse c=null;
	public clientMouseDelegate(String host,int port)
	{
		System.out.println("making delegate");
		 c=new clientMouse(host,port);
	}
	public void  run() 
	{
		
		Thread t=new Thread(c);
		t.start();
	}
}