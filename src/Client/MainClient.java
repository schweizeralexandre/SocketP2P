package Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainClient {

	public static void main(String[] args) throws UnknownHostException {
	
		
		Socket socket;
	    PrintWriter out = null;
		BufferedReader in = null;
		String message = null;
		Client client = new Client();
	    client.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		 
	
		 
		
		try {
		socket = new Socket(client.getIpAddress(),4085);
		
		
		socket.close();
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
