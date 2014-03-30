package client;
import java.io.*;
class clientClipboardDelegate extends Thread
{
	clientClipboard c=null;
	public clientClipboardDelegate(String host,int port)
	{
		 c=new clientClipboard(host,port);
	}
	public void  run() 
	{
		Thread t=new Thread(c);
		t.start();
	}
}