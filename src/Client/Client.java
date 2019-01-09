package Client;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;



public class Client {

	private String Nom;
	private String UserId;
	private static ArrayList<String> fichiers = new ArrayList<String>();
	private static ArrayList<String> fichiersabsolute = new ArrayList<String>();
	private static ArrayList<String> fichiersOntheServer = new ArrayList<String>();
	private File file;
	private String IPServer = "192.168.56.1";
	Socket socket;
	private JFileChooser Directory = new JFileChooser();
	Scanner scan = new Scanner(System.in);
    PrintWriter out = null;
	BufferedReader in = null;
	String message = null;
	String name = "";
    String path="";

	
	// constructeur client
	
	
	public void Clientest(String nom, String prenom, String ipAddress, String userId, ArrayList<String> fichers) {

		
		//super();
		Nom = nom;
		UserId = userId;
		this.fichiers = fichiers;
	}
	
	
	
	
	
	
	
	
	
	public Client() {
		
		
		try {
			
		socket = new Socket("192.168.43.89",4850);
		in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		System.out.println(in.readLine());
		name = scan.nextLine();
		out.println(name);
		out.flush();
		//System.out.println(in.readLine());
		
		
		
		
		
		
	
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	  // selection du dossier
		
		JFileChooser chooser = new JFileChooser();
	   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int returnVal = chooser.showOpenDialog(new JFrame());
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	      
	    }
	 
	    File file = new File(chooser.getSelectedFile(),"");
	    listFilesForFolderVanacom(file);
	    int size = fichiers.size();
	    out.println(size);
		out.flush();
	    //System.out.println();
	for (String string : fichiers) {
		out.println(string);
		out.flush();
	}
	    try {
	    	out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  Thread waitforClient = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerSocket svr;
				try {
					svr = new ServerSocket(4554);
				
				Socket socket;
				BufferedReader comin = null;
				PrintWriter comout ;
				String fileresquest = null;
				String path="";
				while(true)
				{
					
						System.out.println("waiting for a new connection");
						socket=svr.accept();
						 comin= new BufferedReader(new InputStreamReader(socket.getInputStream()));
						comout = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
					System.out.println("ooh, some is connected");
					
						 fileresquest=comin.readLine();
					
					System.out.println("he asked for "+fileresquest);
					for (String string : fichiersabsolute) 
					{
						if(string.contains(fileresquest))
						{
							path=string;
							break;
						}
					}
					System.out.println("sending..");
			         File myFile = new File (path);
			          byte [] mybytearray  = new byte [(int)myFile.length()];
			    	  FileInputStream fis = null;
			  	    BufferedInputStream bis = null;
			  	    OutputStream os = null;
			          fis = new FileInputStream(myFile);
			          bis = new BufferedInputStream(fis);
			          bis.read(mybytearray,0,mybytearray.length);
			          os = socket.getOutputStream();
			          System.out.println("Sending " + name + "(" + mybytearray.length + " bytes)");
			          os.write(mybytearray,0,mybytearray.length);
			          os.flush();
			          System.out.println("Done.");
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
  waitforClient.start();
		while(true)
		{
			
			System.out.println("enter a command");
			String command=scan.nextLine();
			switch (Integer.parseInt(command)) {
			case 1:
				getList();
				break;
			case 3:
				out.println("3");
				out.flush();
				System.out.println("Enter the name of the file that you want to download");
				String filename=scan.nextLine();
				out.println(filename);
				out.flush();
				
				try {
					String ip=in.readLine();
					System.out.println("ip : "+ip);
					getFile get = new getFile(ip, filename);
					get.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}

	}

	
 private void getList() 
{
		// TODO Auto-generated method stub
	out.println("1");
	out.flush();
	try {
		int size=Integer.parseInt(in.readLine());
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			fichiersOntheServer.add(in.readLine());
		}
		fichiersOntheServer.removeAll(fichiers);
		for (String string : fichiersOntheServer) {
			System.out.println(fichiersOntheServer);
		}
		
		
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}



	// méthode qui permet de récupérer les fichiers

	 public ArrayList<String> listFilesForFolder(File folder) 
		{
		
		 int filesize = 0;
		 File file = new File(folder.getAbsolutePath());
	     File[] files = file.listFiles();
	   
	    	 for (int i = 0; i < files.length; i++) 
	    	    {
	    		
	    		  try 
	    		     { 
	    	         if( files[i].isFile())
	    	         {
	    	        	 fichiers.add(files[i].getName());
	    	        	 System.out.println("ffff");
	    	        	filesize++;
	    	        	
	    	         }
	    	         else
	    	         {
	    	        	 listFilesForFolder(files[i]);
	    	         }
	    		     } catch (Exception e) {
	    		 		
	    		 	}
	    	     }
	    	 
	    	 
			return fichiers;
		 
		}


	 public void listFilesForFolderVanacom(File folder) 
		{
		
		 File file = new File(folder.getAbsolutePath());
	     File[] files = file.listFiles();
	   
	    	 for (int i = 0; i < files.length; i++) 
	    	    {
	    		  try 
	    		     {
	    	         if( files[i].isFile())
	    	         {
	    	        	fichiers.add(files[i].getName());
	    	        	fichiersabsolute.add(files[i].getAbsolutePath());
	    	        	//System.out.println("MOMO : "+files[i].getPath());
	    	        	//absolutefilespath.add(files[i].getAbsolutePath());
	    	         }
	    	         else
	    	        	 listFilesForFolderVanacom(files[i]);
	    		     } catch (Exception e) {
	    		 		
	    		 	}
	    	     }
		
	    
		 
		}

	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}

	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}

	public ArrayList<String> getFichers() {
		return fichiers;
	}


	public void setFichers(ArrayList<String> fichers) {
		this.fichiers = fichers;
	}

	
	

	
	
}
class getFile extends Thread 
{
	getFile(String ip,String file)
	{
		try {
			int bytesRead;
	 	    int current = 0;
	 	    FileOutputStream fos = null;
	 	    BufferedOutputStream bos = null;
	 	    PrintWriter  out;
	 	   	BufferedReader   in;
	 	   String desti="";
	 	   JFileChooser chooser = new JFileChooser();
		   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    int returnVal = chooser.showOpenDialog(new JFrame());
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("You chose to open this file: " +
		         chooser.getSelectedFile().getName());
		        desti=chooser.getSelectedFile().getAbsolutePath();
		    }
			Socket socket = new Socket(ip,4554) ;
			 final int FILE_SIZE = 999999999; 
			System.out.println("Connected to the client") ;
			BufferedReader comin = null;
			PrintWriter comout ;
			 comin= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			comout = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			comout.println(file);
			comout.flush();
			 byte [] mybytearray  = new byte [FILE_SIZE];
		      fos = new FileOutputStream(desti+"\\"+file);
		 	     System.out.println(file);
		 	    InputStream is = socket.getInputStream();
		 	      bos = new BufferedOutputStream(fos);
		 	      bytesRead = is.read(mybytearray,0,mybytearray.length);
		 	      current = bytesRead;
			
		     
			   do {
		 	         bytesRead=is.read(mybytearray, current, (mybytearray.length-current));
		 	         if(bytesRead >= 0) current += bytesRead;
		 	      } while(bytesRead > -1);
		 	      System.out.println("out of the loop");
		 	      bos.write(mybytearray, 0 , current);
		 	      bos.flush();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}