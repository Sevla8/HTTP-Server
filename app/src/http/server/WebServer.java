package http.server;

import http.stream.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Représente un serveur web.
 */
public class WebServer {
	/**
	 * Socket du serveur web.
	 */
	private final ServerSocket serverSocket;
	/**
	 * Port sur lequel tourne le service HTTP.
	 */
	private final Integer port;

	/**
	 * Constructeur du serveur web.
	 * @param port Port sur lequel tourne le service HTTP.
	 * @throws IOException
	 */
	public WebServer(Integer port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(this.port);
	}

	/**
	 * Lance le serveur web.
	 */
	protected void start() {
		System.out.println("Webserver starting up on port 3000");
		System.out.println("Waiting for connection");
		while (true) {
			try {
				// wait for a connection
				Socket remote = this.serverSocket.accept();
				// remote is now the connected socket
				ClientThread clientThread = new ClientThread(remote);
				clientThread.start();
			}
			catch (Exception e) {
				System.out.println("Error: " + e);
			}
		}
	}

	/**
	 * Constuit et lance le serveur web.
	 * @param args Arguments de la ligne de commande.
	 */
	public static void main(String args[]) {
		try {
			WebServer ws = new WebServer(3000);
			ws.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
