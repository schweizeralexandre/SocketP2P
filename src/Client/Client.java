package Client;

import java.util.List;

public class Client {

	private String Nom;
	private String Prenom;
	private String IpAddress;
	private String UserId;
	private String Password;
	private List<String> fichers;
	
	// constructeur client
	
	
	public Client(String nom, String prenom, String ipAddress, String userId, String password, List<String> fichers) {

		
		super();
		Nom = nom;
		Prenom = prenom;
		IpAddress = ipAddress;
		UserId = userId;
		Password = password;
		this.fichers = fichers;
	}
	
	
	
	
	
	
	
	
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
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
	public List<String> getFichers() {
		return fichers;
	}
	public void setFichers(List<String> fichers) {
		this.fichers = fichers;
	}
	
	
	
	
	
}