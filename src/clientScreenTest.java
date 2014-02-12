import client.*;

class clientScreenTest extends Thread
{
	public static void main(String args[]) 
	{
		clientScreen c=new clientScreen(5000);
		Thread t=new Thread(c);
		t.start();
	}
}