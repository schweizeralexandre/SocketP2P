package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import Client.Client;

public class Server {
	public static void main(String[] args) {
		
		List<Client> clients = new ArrayList<Client>() ;
		
		for (int i = 0; i < clients.size(); i++) {
			clients.get(i).getNom() ;
			clients.get(i).getPrenom() ;
			clients.get(i).getIpAddress() ;
			clients.get(i).getUserId() ;
			clients.get(i).getPassword() ;
			clients.get(i).getFichers() ;
		}

		ServerSocket srvSocket = null ;
		try {
			srvSocket = new ServerSocket(45000, 5) ;
			Socket client = srvSocket.accept();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		while (true) {

			try {
				srvSocket.accept() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
