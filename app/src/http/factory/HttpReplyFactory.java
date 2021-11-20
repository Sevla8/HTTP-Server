package http.factory;

import http.server.HttpMethod;
import http.server.HttpReply;
import http.server.HttpRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

public class HttpReplyFactory {
	public static HttpReply buildHttpReply(HttpRequest httpRequest) throws IOException {
		HttpReply httpReply = new HttpReply();

		if (httpRequest == null) {
			return httpReply;
		}

		String resource =  httpRequest.getResource();
		if (resource.equals("/")) {
			resource += "index.html";
		}
		System.out.println("resource: " + resource);

		Path path = null;
		File file = new File("app/src/web" + resource);
		if (file.exists()) {
			path = Paths.get(file.getCanonicalPath());
		}
		System.out.println("path: " + path);

		HttpMethod method = httpRequest.getMethod();
		switch (method) {
			case GET: {
				if (path != null) {
					httpReply.setCode(200);

					httpReply.setStatus("OK");

					String reply = Files.readString(path);
					httpReply.setReply(reply);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/html");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				}
				else {
					httpReply.setCode(404);

					httpReply.setStatus("Not Found");

					String reply = "Not Found";
					httpReply.setReply(reply);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/plain");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				}
				break;
			}
			case POST: {
				if (path != null && Files.probeContentType(path).equals("text/html")) {
					FileWriter fileWriter = new FileWriter(file, true);
					Map<String, String> parameters = httpRequest.getParameters();
					for(Map.Entry<String, String> entry : parameters.entrySet()) {
						String str = "<p>" + entry.getKey() + "=" + entry.getValue() +  "</p>";
						fileWriter.write(str);
					}
					fileWriter.close();

					httpReply.setCode(200);

					httpReply.setStatus("OK");

					String reply = Files.readString(path);
					httpReply.setReply(reply);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/html");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				} else {
					httpReply.setCode(404);

					httpReply.setStatus("Not Found");

					String reply = "Not Found";
					httpReply.setReply(reply);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/plain");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				}
				break;
			}
			case HEAD: {
				httpReply.setCode(200);

				httpReply.setStatus("OK");

				httpReply.setReply("");

				httpReply.setHeader(httpRequest.getHeader());
				break;
			}
			case DELETE: {
				if (path != null) {
					FileWriter fileWriter = new FileWriter(file, false);
					fileWriter.write("");
					fileWriter.close();

					httpReply.setCode(200);

					httpReply.setStatus("OK");

					String reply = "Deleted";
					httpReply.setReply(reply);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/html");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				} else {
					httpReply.setCode(404);

					httpReply.setStatus("Not Found");

					String reply = "Not Found";
					httpReply.setReply(reply);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/plain");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				}
				break;
			}
			case PUT: {
				FileWriter fileWriter = new FileWriter(file, false);
				fileWriter.write(httpRequest.getRequest());
				fileWriter.close();

				if (path != null) {
					httpReply.setCode(200);
					httpReply.setStatus("OK");
				} else {
					httpReply.setCode(201);
					httpReply.setStatus("Created");
				}

				String reply = "Created";
				httpReply.setReply(reply);

				Hashtable<String, String> header = new Hashtable<>();
				header.put("Server", "Bot");
				header.put("Content-Type", "text/html");
				header.put("Content-Length", String.valueOf(reply.length()));
				header.put("Content-Location", resource);
				httpReply.setHeader(header);
				break;
			}
			default: {
				httpReply.setCode(400);

				httpReply.setStatus("Bad Request");

				String reply = "";
				httpReply.setReply(reply);

				Hashtable<String, String> header = new Hashtable<>();
				header.put("Server", "Bot");
				header.put("Content-Type", "text/plain");
				header.put("Content-Length", String.valueOf(reply.length()));
				httpReply.setHeader(header);
			}
		}

		return httpReply;
	}
}
