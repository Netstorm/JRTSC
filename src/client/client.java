package client;
import java.awt.*;
import java.io.*;
import java.net.*;



public class client extends Thread
{
    BufferedReader br1,socketReader;
    PrintWriter pw1;
    String st1,st2;
    Socket client;
    
    public client(int port)
    {
        try
        {
            
            client=new Socket("192.168.2.5",port);
            br1=new BufferedReader(new InputStreamReader(System.in));
            socketReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw1=new PrintWriter(client.getOutputStream(),true);
            
        }

        catch(Exception ee)
        {
            System.out.println(ee);
        }
    }

    public void setupClient()
    {
        try 
        {
            
            //see what server has to say
            st2=socketReader.readLine();
            System.out.println("Server:"+st2);
            String password=br1.readLine();
            pw1.println(password);

            st2=socketReader.readLine();
            System.out.println("Server:"+st2);

            //we are getting connected lets start keyboard listener,mouse listener etc

            if(st2.equals("Starting services"))
            {  
                //wait for server to kick in 
               Thread.sleep(3000);


               //start keyboard
               clientKeyboard kb=new clientKeyboard(5000);
               Thread keyboardThread=new Thread(kb);
               keyboardThread.start();

               //start everything else here

               clientMouse km=new clientMouse(5020);
               Thread mouseThread=new Thread(km);
               mouseThread.start();
                
                
            }

            else
            {
               System.out.println("Something went wrong..");
            }




        }

        catch(Exception ee) 
        {
            System.out.println(ee);
        }
    }

    public void run()
    {
    setupClient();
    }

}