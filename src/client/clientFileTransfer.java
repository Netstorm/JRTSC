package client;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.net.*;
import java.io.*;
import java.awt.event.KeyListener;
import java.awt.Frame;
import javax.swing.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public  class clientFileTransfer extends Thread
{
Socket client;
JFrame frame;
ObjectInputStream ois;
ObjectOutputStream oos ;

  	public clientFileTransfer(String host,int port)
  	{
    	try
    	{
      	client=new Socket(host,port);
    	//ois= new ObjectInputStream(client.getInputStream());
       	oos= new ObjectOutputStream(client.getOutputStream());
    	}

    	catch(Exception ee)
    	{
      	System.out.println(ee);
    	}
      
  	}	


 

  	public void run()
  	{
    	Frame[] frames=Frame.getFrames();
    	final JFrame jframe=(JFrame)frames[3];
    	try
    	{
    		addFileTransferOption(jframe);
    	}
    	catch(Exception ee)
    	{
    		System.err.println(ee);
    	}
   		
  	}

  	public void addFileTransferOption(final JFrame frame)throws IOException
  	{
  		JMenu jMenu5 = new javax.swing.JMenu();
    	JMenuItem jMenuItem2 = new javax.swing.JMenuItem();

    	jMenu5.setText("File Transfer");
    	jMenuItem2.setText("Select Files");
    	jMenuItem2.addActionListener(new java.awt.event.ActionListener() 
    	{
        	public void actionPerformed(java.awt.event.ActionEvent evt) 
        	{
            	try
            	{
            		jMenuItem2ActionPerformed(evt,frame);
            	}
            	catch(Exception ee)
            	{
            		System.err.println(ee);
            	}
                
            }
        });

    	jMenu5.add(jMenuItem2);
    	JMenuBar jmenu=new JMenuBar();
    	frame.setJMenuBar(jmenu);
    	jmenu.add(jMenu5);
    	frame.validate();
    	frame.repaint();
  	}


  	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt,JFrame frame) throws IOException
  	{
        createFileChooser(frame);
    }


  	private void createFileChooser(final JFrame frame) throws IOException
  	{

		String filename = File.separator+"tmp";
		JFileChooser fileChooser = new JFileChooser(new File(filename));
		fileChooser.showOpenDialog(frame);

		try
		{
			SendFile(((fileChooser.getSelectedFile()).getPath()));
		}

		catch(Exception ee)
		{
			System.err.println(ee);
		}

	}

	byte[] readSmallBinaryFile(String aFileName) throws IOException 
	{
    	Path path = Paths.get(aFileName);
    	return Files.readAllBytes(path);
  	}

 	
 	void SendFile(String filename) throws Exception
    {    
        File f=new File(filename);
        oos.writeObject(f.getName());
        oos.flush();
        oos.writeObject(readSmallBinaryFile(filename));
        System.out.println("File length -> "+f.length());
        
    }

}

  
    

 
    
