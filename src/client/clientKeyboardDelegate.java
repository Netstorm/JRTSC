package client;
import java.io.*;
class clientKeyboardDelegate extends Thread
{
	clientKeyboard c=null;
	public clientKeyboardDelegate(String host,int port)
	{
		c=new clientKeyboard(host,port);
	}
	public void  run() 
	{
		
		Thread t=new Thread(c);
		t.start();
	}
}