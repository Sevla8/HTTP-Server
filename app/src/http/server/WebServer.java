package http.server;

import http.stream.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	private ServerSocket serverSocket;
	private Integer port;

	public WebServer(Integer port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(this.port);
	}

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

	public static void main(String args[]) {
		WebServer ws = null;
		try {
			ws = new WebServer(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ws.start();
	}
}
