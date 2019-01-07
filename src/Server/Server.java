package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {

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
