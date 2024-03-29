package http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Représente un client web qui accède au serveur web.
 */
public class WebPing {
	/**
	 * Socket du client web.
	 */
	private Socket socket;
	/**
	 * Hote auquel se connecte le client web.
	 */
	private final String httpServerHost;
	/**
	 * Port sur lequel se connecte le client web au serveur web.
	 */
	private final Integer httpServerPort;
	/**
	 * Buffer de lecture du socket.
	 */
	private BufferedReader in;
	/**
	 * Buffer d'écriture du socket.
	 */
	private PrintWriter out;

	/**
	 * Constructeur de WebPing.
	 * @param httpServerHost Hote auquel se connecte le client web.
	 * @param httpServerPort Port sur lequel se connecte le client web au serveur web.
	 */
	public WebPing(String httpServerHost, Integer httpServerPort) {
		this.httpServerHost = httpServerHost;
		this.httpServerPort = httpServerPort;

		try {
			this.socket = new Socket(this.httpServerHost, this.httpServerPort);
			InetAddress addr = socket.getInetAddress();
			System.out.println("Connected to " + addr);

			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
		} catch (java.io.IOException e) {
			System.out.println("Can't connect to " + httpServerHost + ":" + httpServerPort);
			System.out.println(e);
		}
	}

	/**
	 * Test d'une requête GET.
	 */
	public void testGET() {
		this.out.println("GET /index.html?parameter1=value1&parameter2=value2 HTTP/1.1\r\n");
		this.out.print("Host: " + this.httpServerHost + "\r\n\r\n");
		this.out.flush();

		this.listen();
	}

	/**
	 * Test d'une requête POST.
	 */
	public void testPOST() {
		this.out.print("POST /index.html HTTP/1.1\r\n");
		this.out.print("Host: " + this.httpServerHost + "\r\n");
		this.out.print("User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20100101 Firefox/15.0.1\r\n");
		this.out.print("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
		this.out.print("Accept-Language: tr-tr,tr;q=0.8,en-us;q=0.5,en;q=0.3\r\n");
		this.out.print("Accept-Encoding: gzip, deflate\r\n");
		this.out.print("Connection: keep-alive\r\n");
		this.out.print("Referer: http://atasoyweb.net/\r\n");
		this.out.print("Content-Type: application/x-www-form-urlencoded\r\n");
		this.out.print("Content-Length: 35\r\n\r\n");
		this.out.print("variable1=value1&variable2=value2\r\n");
		this.out.flush();

		this.listen();
	}

	/**
	 * Test d'une requête HEAD.
	 */
	public void testHEAD() {
		this.out.print("HEAD /index.html HTTP/1.1\r\n");
		this.out.print("Host: " + this.httpServerHost + "\r\n");
		this.out.print("User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20100101 Firefox/15.0.1\r\n");
		this.out.print("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
		this.out.print("Accept-Language: tr-tr,tr;q=0.8,en-us;q=0.5,en;q=0.3\r\n");
		this.out.print("Accept-Encoding: gzip, deflate\r\n");
		this.out.print("Connection: keep-alive\r\n");
		this.out.print("Referer: http://atasoyweb.net/\r\n");
		this.out.print("Content-Type: application/x-www-form-urlencoded\r\n");
		this.out.print("Content-Length: 0\r\n\r\n");
		this.out.flush();

		this.listen();
	}

	/**
	 * Test d'une requête DELETE.
	 */
	public void testDELETE() {
		this.out.print("DELETE /image.png HTTP/1.1\r\n");
		this.out.print("Host: " + this.httpServerHost + "\r\n");
		this.out.print("User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20100101 Firefox/15.0.1\r\n");
		this.out.print("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
		this.out.print("Accept-Language: tr-tr,tr;q=0.8,en-us;q=0.5,en;q=0.3\r\n");
		this.out.print("Accept-Encoding: gzip, deflate\r\n");
		this.out.print("Connection: keep-alive\r\n");
		this.out.print("Referer: http://atasoyweb.net/\r\n");
		this.out.print("Content-Type: application/x-www-form-urlencoded\r\n");
		this.out.print("Content-Length: 0\r\n\r\n");
		this.out.flush();

		this.listen();
	}

	/**
	 * Test d'une requête PUT
	 */
	public void testPUT() {
		this.out.print("PUT /index98.html HTTP/1.1\r\n");
		this.out.print("Host: " + this.httpServerHost + "\r\n");
		this.out.print("User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20100101 Firefox/15.0.1\r\n");
		this.out.print("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
		this.out.print("Accept-Language: tr-tr,tr;q=0.8,en-us;q=0.5,en;q=0.3\r\n");
		this.out.print("Accept-Encoding: gzip, deflate\r\n");
		this.out.print("Connection: keep-alive\r\n");
		this.out.print("Referer: http://atasoyweb.net/\r\n");
		this.out.print("Content-Type: application/x-www-form-urlencoded\r\n");
		this.out.print("Content-Length: 24\r\n\r\n");
		this.out.println("noooooooooooooooooooon\r\n");
		this.out.flush();

		this.listen();
	}

	/**
	 * Test d'une requête erronée.
	 */
	public  void testError() {
		this.out.println("sfkjsd fksdbfksjdfsc skvjb");
		this.out.print("sjh khsdbbc xsdj");
		this.out.print("sjh khsdbbc xsdj");
		this.out.print("sjh khsdbbc xsdj");
		this.out.print("sjh khsdbbc xsdj");
		this.out.flush();

		this.listen();
	}

	/**
	 * Test d'une requête GET accèdant à une resource binaire.
	 */
	public  void testImage() {
		this.out.println("GET /image.png HTTP/1.1\r\n");
		this.out.print("Host: " + this.httpServerHost + "\r\n\r\n");
		this.out.flush();

		this.listen();
	}

	/**
	 * Lance le processus d'écoute du serveur web.
	 */
	private void listen() {
		try {
			String reply;
			while ((reply = this.in.readLine()) != null) {
				System.out.println(reply);
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + this.httpServerHost);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + this.httpServerHost + " on port " + this.httpServerPort);
			System.exit(1);
		}
	}

	/**
	 * Lance le client web et se connecte au serveur web.
	 * @param args Arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage java WebPing <server host name> <server port number>");
			return;
		}

		String httpServerHost = args[0];
		int httpServerPort = Integer.parseInt(args[1]);

		WebPing webPing = new WebPing(httpServerHost, httpServerPort);

//		webPing.testGET();
//		webPing.testPOST();
//		webPing.testHEAD();
		webPing.testDELETE();
//		webPing.testPUT();
//		webPing.testError();
//		webPing.testImage();
	}
}