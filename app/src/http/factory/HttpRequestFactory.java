package http.factory;

import http.server.HttpRequest;

import java.io.BufferedReader;

public class HttpRequestFactory {
	public static HttpRequest buildHttpRequest(BufferedReader in) {
		HttpRequest httpRequest = new HttpRequest();

//   System.out.println("Connection, sending data.");
//	BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
//	PrintWriter out = new PrintWriter(remote.getOutputStream());
//
//	// Send the response
//	// Send the headers
//				out.println("HTTP/1.0 200 OK");
//				out.println("Content-Type: text/html");
//				out.println("Server: Bot");
//	// this blank line signals the end of the headers
//				out.println("");
//
//
//	// read the data sent. We basically ignore it,
//	// stop reading once a blank line is hit. This
//	// blank line signals the end of the client HTTP
//	// headers.
//	String str = ".";
//				while (str != null && !str.equals("")) {
//		str = in.readLine();
//		String first = str.split(" ")[0];
//		if (first.equals("GET")) {
//			System.out.println(str);
//			String second = str.split(" ")[1];
//			second = second.substring(1, second.length());
//			System.out.println(second);
//			String content = Files.readString(Paths.get("/home/sevla/Desktop/NET-lab2/TP-HTTP-Code/TP-HTTP-Code/src/web/" + second));
//			out.println(content);
//		}
//		else if (first.equals("POST")) {
//
//		}
//	}
//				System.out.println("**************************************************************");
//
//				out.flush();
//				remote.close();

		return httpRequest;
	}
}
