package client;
import java.io.*;
class clientFileTransferDelegate extends Thread
{
	clientFileTransfer c=null;
	public clientFileTransferDelegate(String host,int port)
	{
		 c=new clientFileTransfer(host,port);
	}
	public void  run() 
	{
		Thread t=new Thread(c);
		t.start();
	}
}
