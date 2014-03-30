package server;
import java.io.*;
import java.net.*;
public class server extends Thread
{
BufferedReader br1,socketReader;
PrintWriter socketWriter;
String st1,st2;
ServerSocket serverChannel;
Socket connection;
serverKeyboard kb=null;
serverMouse sm=null;
serverScreen ss=null;
serverFileTransfer serverFile=null;

public server(int port)
{
    try 
    {
        
        serverChannel=new ServerSocket(port);
        connection=serverChannel.accept();
        br1=new BufferedReader(new InputStreamReader(System.in));
        socketReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
        socketWriter=new PrintWriter(connection.getOutputStream(),true);
        
        
        kb=new serverKeyboard(port+10);
        sm=new serverMouse(port+20);
        ss=new serverScreen(port+30);
        serverFile=new serverFileTransfer(port+40);



      
    }

    catch(Exception ee)
    {
        System.out.println(ee);
        System.out.println("after socket created");
    }
}
public void setupServer()
{
        try 
        {   
            
            String passwd=null;
            passwd=readPassword();
            

            //check if password is present
            if(passwd==null)
            { 
                //if no password, ask for one and save it
                System.out.println("Not setup!");
                socketWriter.println("Please enter a new password");
                //read password and save
                st2=socketReader.readLine();
                savePassword(st2);
            }
            else
            {
                //if password exists then check if its correct and start services
                
                //read password from user
                socketWriter.println("Enter password");
                st2=null;
                st2=socketReader.readLine();
                
                
                //pass is correct?
                if(st2.equals(passwd.trim()))
                {   
                  System.out.println("Client Authenticated!");
                  socketWriter.println("Starting services");


                  Thread screenThread=new Thread(ss);
                  screenThread.start();

                  Thread keyboardThread=new Thread(kb);
                  keyboardThread.start();

                  Thread mouseThread=new Thread(sm);
                  mouseThread.start();

                  Thread serverFileThread=new Thread(serverFile);
                  serverFileThread.start();



                }
                else
                {

                  socketWriter.println("Authentication Fail");
                }

            }

        }

        catch(Exception ee) 
        {
            System.out.println(ee);
        }
}
 

String readPassword() throws IOException 
    {

      String content = null;
      File file = new File("passwd.txt"); //for ex foo.txt
      if(file.exists())
      {
        try 
      {
       FileReader reader = new FileReader(file);
       char[] chars = new char[(int) file.length()];
       reader.read(chars);
       content = new String(chars);
       reader.close();
      } 
      catch (IOException e) 
      {
       e.printStackTrace();
      }
      return content;
      }
      else
      {
        return content=null;
      }
    }   

void savePassword(String password) throws IOException
{
     PrintWriter out = new PrintWriter("passwd.txt");
     out.println(password);
     out.close();

}

public void run() {
  setupServer();
}


}

