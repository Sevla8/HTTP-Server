package http.factory;

import http.server.HttpMethod;
import http.server.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

//GET /index.html?ds=evf&g=o&ph=l HTTP/1.1\r\n
//Host: atasoyweb.net\r\n
//User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:14.0) Gecko/20100101 Firefox/14.0.1\r\n
//Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n
//Accept-Language: tr-tr,tr;q=0.8,en-us;q=0.5,en;q=0.3\r\n
//Accept-Encoding: gzip, deflate\r\n
//Connection: keep-alive\r\n\r\n

//POST /index.html HTTP/1.1\r\n
//Host: atasoyweb.net\r\n
//User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20100101 Firefox/15.0.1\r\n
//Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n
//Accept-Language: tr-tr,tr;q=0.8,en-us;q=0.5,en;q=0.3\r\n
//Accept-Encoding: gzip, deflate\r\n
//Connection: keep-alive\r\n
//Referer: http://atasoyweb.net/\r\n
//Content-Type: application/x-www-form-urlencoded\r\n
//Content-Length: 35\r\n\r\n
//variable1=value1&variable2=value2

public class HttpRequestFactory {
	public static HttpRequest buildHttpRequest(BufferedReader in) throws IOException {
		HttpRequest httpRequest = new HttpRequest();

		String line = in.readLine();

		// happens sometimes
		if (line == null) return null;

		// method
		String method = line.split(" ")[0];
		System.out.println("method: " + method);
		switch (method) {
			case "GET":
				httpRequest.setMethod(HttpMethod.GET);
				break;
			case "POST":
				httpRequest.setMethod(HttpMethod.POST);
				break;
			case "HEAD":
				httpRequest.setMethod(HttpMethod.HEAD);
				break;
			case "DELETE":
				httpRequest.setMethod(HttpMethod.DELETE);
				break;
			case "PUT":
				httpRequest.setMethod(HttpMethod.PUT);
				break;
		}

		// resource (brut)
		String resourceBrut = line.split(" ")[1];
		System.out.println("resource (brut): " + resourceBrut);

		// version
		String version = line.split(" ")[2];
		System.out.println("version: " + version);
		httpRequest.setResource(version);

		// settings
		Hashtable<String, String> header = new Hashtable<>();
		while (!(line = in.readLine()).equals("")) {
			String key = line.split(": ")[0];
			String value = line.split(": ")[1];
			header.put(key, value);
		}
		System.out.println("header: " + header);
		httpRequest.setHeader(header);

		// request content
		String requestLength0 = header.get("Content-Length");
		int requestLength = 0;
		if (requestLength0 != null) {
			requestLength = Integer.parseInt(requestLength0);
		}
		char[] request1 = new char[requestLength];
		in.read(request1, 0, requestLength);
		String request = new String(request1);
		System.out.println("request: " + request);
		httpRequest.setRequest(request);

		// resource (refined)
		String resource = resourceBrut.split("\\?")[0];
		httpRequest.setResource(resource);
		System.out.println("resource (refined): " + resource);

		// parameters
		Hashtable<String, String> parameters = new Hashtable<>();
		// parameters in resource
		if (resourceBrut.contains("?")) {
			String[] parameterList = resourceBrut.split("\\?");
			if (parameterList.length > 1) {
				String parameterList1 = parameterList[1];
				String[] params = parameterList1.split("&");
				for (String entry : params) {
					String key = entry.split("=")[0];
					String value = entry.split("=")[1];
					parameters.put(key, value);
				}
			}
		}
		// parameters in request
		String parameterList = request;
		if (parameterList.contains("=")) {
			String[] params = parameterList.split("&");
			for (String entry : params) {
				String key = entry.split("=")[0];
				String value = entry.split("=")[1];
				parameters.put(key, value);
			}
		}
		System.out.println("parameters: " + parameters);
		httpRequest.setParameters(parameters);

		return httpRequest;
	}
}
