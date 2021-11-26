package http.stream;

import http.factory.HttpReplyFactory;
import http.factory.HttpRequestFactory;
import http.server.HttpReply;
import http.server.HttpRequest;

import java.io.*;
import java.net.Socket;

/**
 * Représente un fil d'exécution du serveur web. Gère une requête web.
 */
public class ClientThread extends Thread {
	/**
	 * Socket lié au client qui requête le serveur.
	 */
	private final Socket clientSocket;
	/**
	 * Buffer de lecture du socket.
	 */
	private BufferedReader in;
	/**
	 * Buffer d'écriture du socket.
	 */
	private PrintWriter out;

	/**
	 * Constructeur du fil d'exécution du serveur web.
	 * @param remote Socket associé à un client web.
	 */
	public ClientThread(Socket remote) {
		this.clientSocket = remote;
		try {
			this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.out = new PrintWriter(clientSocket.getOutputStream(), true);
		}
		catch (Exception e) {
			System.err.println("Error in ClientThread: " + e);
		}
	}

	/**
	 * Lance le fil d'exécution du serveur web.
	 */
	@Override
	public void run() {
		try {
			// build request
			HttpRequest httpRequest = HttpRequestFactory.buildHttpRequest(in);
			// build reply
			HttpReply httpReply = HttpReplyFactory.buildHttpReply(httpRequest);
			// send reply
			this.out.println(httpReply.getHeader());
			OutputStream os = this.clientSocket.getOutputStream();
			os.write(httpReply.getReply());
			os.flush();
			os.close();
			// close socket
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Failed to parse request");
		}
	}
}
