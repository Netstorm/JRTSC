package client;
import java.io.*;
class clientMouseDelegate extends Thread
{
	clientMouse c=null;
	public clientMouseDelegate(String host,int port)
	{
		 c=new clientMouse(host,port);
	}
	public void  run() 
	{
		
		Thread t=new Thread(c);
		t.start();
	}
}