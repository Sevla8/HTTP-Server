package http.stream;

import http.factory.HttpRequestFactory;
import http.server.HttpReply;
import http.server.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;

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

	@Override
	public void run() {
		try {
			// Construction de la requête
			HttpRequest httpRequest = HttpRequestFactory.buildHttpRequest(in);
			// Construction de la réponse
			HttpReply httpReply = this.reply(httpRequest);
			// Envoi de la réponse
			this.out.println(httpReply.toString());
			// Fermeture du socket
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Failed to parse request");
		}
	}

	private HttpReply reply(HttpRequest httpRequest) {
		HttpReply httpReply = new HttpReply();

		return httpReply;
	}
}
