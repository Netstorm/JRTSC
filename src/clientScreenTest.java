import client.*;
import java.io.*;
class clientScreenTest extends Thread
{
	public static void main(String args[]) throws IOException
	{
		clientScreen c=new clientScreen(5000);
		Thread t=new Thread(c);
		t.start();
		//c.drawGUI();
	}
}