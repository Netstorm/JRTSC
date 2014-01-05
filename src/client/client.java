package client;
import java.awt.*;
import java.io.*;
import java.net.*;





public class client extends Thread
{
    BufferedReader br1,socketReader;
    PrintWriter pw1;
    Thread t1,t2;
    String st1,st2;
    Socket client;
    
    public client(int port)
    {
        try
        {
            
            client=new Socket("localhost",port);
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
            /*if(Thread.currentThread()==t1)
            {
                do  
                {
                    st2=socketReader.readLine();
                    System.out.println("Server:"+st2);
                }while(!st2.equals("exit"));
            }
            else
            {
                while(true)
                {
                    st1=br1.readLine();
                    pw1.println(st1);
                    Thread.sleep(10);
                }
            } */

            st2=socketReader.readLine();
            System.out.println("Server:"+st2);
            String password=br1.readLine();
            pw1.println(password);

            st2=socketReader.readLine();
            System.out.println("Server:"+st2);


            if(st2.equals("Starting services"))
            {
               Thread.sleep(3000);
               clientKeyboard kk=new clientKeyboard(5000);
               
               System.out.println("YAY");
                Thread td=new Thread(kk);
                td.start();
                //kk.run();
                
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

public void run(){
    setupClient();

}

}