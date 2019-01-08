package Client;


import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Client {

	private String Nom;
	private String IpAddress;
	private String UserId;
	private String Password;
	private ArrayList<String> fichers;

	
	// constructeur client
	
	
	public void Clientest(String nom, String prenom, String ipAddress, String userId, String password, ArrayList<String> fichers) {

		
		//super();
		Nom = nom;
		IpAddress = ipAddress;
		UserId = userId;
		Password = password;
		this.fichers = fichers;
	}
	
	
	
	
	
	
	
	
	
	public Client() {
		// TODO Auto-generated constructor stub
	}


	
	public String getIpAddressByName(String Name) {
		
		if(this.Nom.equals(Name)){
			
		try {
			this.IpAddress = InetAddress.getLocalHost().getHostAddress();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return this.IpAddress;
		
		}
		
		else return "client n'existe pas";
	}









	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}

	public String getIpAddress() {
		return IpAddress;
	}
	public void setIpAddress(String ipAddress) {
		IpAddress = ipAddress;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}


	public ArrayList<String> getFichers() {
		return fichers;
	}


	public void setFichers(ArrayList<String> fichers) {
		this.fichers = fichers;
	}

	
	

	
	
}
