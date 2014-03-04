package client;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager.*;

public class client extends Thread
{
    BufferedReader br1,socketReader;
    PrintWriter pw1;
    String st1,st2;
    Socket client;
    JFrame f1 = new JFrame("JRTSC");
    JFrame f2 = new JFrame("JRTSC");
   public  clientKeyboard kb=null;
    clientMouse km=null;
    clientScreen cs=null;
    clientKeyboardDelegate obj=null;
     
    public client(String host,int port)
    {
        try
        {
            
            client=new Socket(host,port);
            br1=new BufferedReader(new InputStreamReader(System.in));
            socketReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw1=new PrintWriter(client.getOutputStream(),true);
            cs=new clientScreen(host,port+30);
            obj=new clientKeyboardDelegate(host,port+10);

            
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
            System.out.println("Server: says"+st2);

            if(st2.equals("Please enter a new password"))
            {
                System.out.println("if statment 1");
                JButton setup=new JButton("Setup");

                JLabel instructions=new JLabel();
                instructions.setText("It appears that JRTSC hasn't been setup yet or the configuration has been deleted by the admin.");
                JLabel instructions2=new JLabel();
                instructions2.setText("You can set it up now. Enter a password to do so.");
                JLabel appName=new JLabel();
                appName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                appName.setText("JRTSC");

                JLabel passwordLabel = new javax.swing.JLabel();
                passwordLabel.setText("Enter password:");


                JLabel confirmPasswordLabel = new javax.swing.JLabel();
                confirmPasswordLabel.setText("Re enter password:");

                final JPasswordField passwordField=new JPasswordField();

                final JPasswordField confirmPasswordField=new JPasswordField();

                setup.addActionListener(new ActionListener() 
                { 
                public void actionPerformed(ActionEvent e) { 
                setupPassword(passwordField,confirmPasswordField);
                }});


                
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Container content = f1.getContentPane();
                content.setBackground(Color.white);
                content.setLayout(new BoxLayout(content,1));
                content.add(appName);
                content.add(instructions); 
                content.add(instructions2);
                content.add(passwordLabel);
                content.add(confirmPasswordLabel);
                content.add(passwordField);
                content.add(confirmPasswordField);
                content.add(setup);



                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(content);
                content.setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appName, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(instructions)
                            .addComponent(setup)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(passwordLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(confirmPasswordLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(instructions2))
                        .addContainerGap(38, Short.MAX_VALUE))
                        );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(appName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(instructions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(instructions2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordLabel))
                        .addGap(32, 32, 32)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmPasswordLabel)
                        .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(setup)
                        .addGap(32, 32, 32))
                        );

                f1.pack();
                f1.setVisible(true);

            }
            
            if(st2.equals("Enter password"))
            {
                System.out.println("if statment 2");
                JButton authenticate=new JButton("Authenticate");

                JLabel enterPasswordMessage=new JLabel();
                enterPasswordMessage.setText("Please enter the password previously setup with this server");


                JLabel appName = new javax.swing.JLabel();
                appName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
                appName.setText("JRTSC");


                
                final JPasswordField passwordField=new JPasswordField();

                authenticate.addActionListener(new ActionListener() 
                { 
                public void actionPerformed(ActionEvent e) { 
                authenticatePassword(passwordField);
                }});


                
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Container content = f2.getContentPane();
                content.setBackground(Color.white);
                content.setLayout(new BoxLayout(content,1));
                content.add(appName);
                content.add(enterPasswordMessage); 
                content.add(passwordField);
                content.add(authenticate);
               

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(content);
                content.setLayout(layout);
                layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(appName)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(enterPasswordMessage)
                        .addGap(176, 176, 176))
                    .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(authenticate)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
                        );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(appName)
                        .addGap(18, 18, 18)
                        .addComponent(enterPasswordMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(authenticate)
                        .addContainerGap(139, Short.MAX_VALUE))
                    );
         
                f2.pack();
                f2.setVisible(true);  
            }

        }

        catch(Exception ee) 
        {
            System.out.println(ee);
        }
    }

    public void setupPassword(JPasswordField pass,JPasswordField confirmPass)
    {
        String password=new String(pass.getPassword());
        String confirmPassword=new String(confirmPass.getPassword());
        if(!password.equals(confirmPassword))
        {
            System.out.println("Passwords dont match");
        }
        else
        {
            pw1.println(password);
            f1.setVisible(false);
        }
    }

    public void authenticatePassword(JPasswordField pass) 
    {
        String password=new String(pass.getPassword());
        pw1.println(password);
        f2.setVisible(false);

        try 
        {
            st2=socketReader.readLine();
        }
        catch(Exception e)
        {

        }
        System.out.println("Server:"+st2);

            //we are getting connected lets start keyboard listener,mouse listener etc

            if(st2.equals("Starting services"))
            {  
                //wait for server to kick in 
                try
                {
                    Thread.sleep(3000);
               
                    Thread screenThread=new Thread(cs);
                    screenThread.start();
           
        
                    Thread lol=new Thread(obj);
                    lol.start();

                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                
            }

            else
            {
               System.out.println("Something went wrong..");
            }


    }

    public void run()
    {
    setupClient();
    }

}