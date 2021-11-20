package http.stream;

import http.factory.HttpReplyFactory;
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
			// build request
			HttpRequest httpRequest = HttpRequestFactory.buildHttpRequest(in);
			// build reply
			HttpReply httpReply = HttpReplyFactory.buildHttpReply(httpRequest);
			// send reply
			this.out.println(httpReply.toString());
			// clode socket
			clientSocket.close();
		} catch (IOException e) {
			System.out.println("Failed to parse request");
		}
	}
}
