package http.factory;

import http.server.HttpMethod;
import http.server.HttpReply;
import http.server.HttpRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class HttpReplyFactory {
	public static HttpReply buildHttpReply(HttpRequest httpRequest) throws IOException {
		HttpReply httpReply = new HttpReply();

		if (httpRequest == null) {
			httpReply.setCode(400);

			httpReply.setStatus("Bad Request");

			String reply = "";
			httpReply.setReply(new ByteArrayOutputStream());

			Hashtable<String, String> header = new Hashtable<>();
			header.put("Server", "Bot");
			header.put("Content-Type", "text/plain");
			header.put("Content-Length", String.valueOf(reply.length()));
			httpReply.setHeader(header);
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

					byte[] reply = Files.readAllBytes(path);
					ByteArrayOutputStream b = new ByteArrayOutputStream();
					b.write(reply);
					System.out.println("reply: " + b.toString());
					httpReply.setReply(b);

					String extension = "";
					int i = resource.lastIndexOf('.');
					if (i > 0) {
						extension = resource.substring(i+1);
					}
					System.out.println("extension: " + extension);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					switch (extension) {
						case "html": {
							header.put("Content-Type", "text/html");
							break;
						}
						case "png": {
							header.put("Content-Type", "image/png");
							break;
						}
						case "jpg", "jpeg": {
							header.put("Content-Type", "image/jpeg");
							break;
						}
						case "mp3" : {
							header.put("Content-Type", "audio/mp3");
							break;
						}
						case "mp4" : {
							header.put("Content-Type", "video/mp4");
							break;
						}
						default:
							header.put("Content-Type", "text/plain");
					}
					header.put("Content-Length", String.valueOf(b.toByteArray().length));
					httpReply.setHeader(header);
				}
				else {
					httpReply.setCode(404);

					httpReply.setStatus("Not Found");

					String reply = "Not Found";
					ByteArrayOutputStream b = new ByteArrayOutputStream();
					b.write(reply.getBytes());
					httpReply.setReply(b);

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
						String str = entry.getKey() + "=" + entry.getValue();
						fileWriter.write(str);
					}
					fileWriter.close();

					httpReply.setCode(200);

					httpReply.setStatus("OK");

					byte[] reply = Files.readAllBytes(path);
					ByteArrayOutputStream b = new ByteArrayOutputStream();
					b.write(reply);
					httpReply.setReply(b);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/html");
					header.put("Content-Length", String.valueOf(reply.length));
					httpReply.setHeader(header);
				}
				else {
					httpReply.setCode(404);

					httpReply.setStatus("Not Found");

					String reply = "Not Found";
					ByteArrayOutputStream b = new ByteArrayOutputStream();
					b.write(reply.getBytes());
					httpReply.setReply(b);

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/plain");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				}
				break;
			}
			case HEAD: {
				if (path != null) {
					httpReply.setCode(200);

					httpReply.setStatus("OK");

					String reply = Files.readString(path);
					httpReply.setReply(new ByteArrayOutputStream());

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
					httpReply.setReply(new ByteArrayOutputStream());

					Hashtable<String, String> header = new Hashtable<>();
					header.put("Server", "Bot");
					header.put("Content-Type", "text/plain");
					header.put("Content-Length", String.valueOf(reply.length()));
					httpReply.setHeader(header);
				}
				break;
			}
			case DELETE: {
				if (path != null) {
					String reply;
					if (file.delete()) {
						httpReply.setCode(200);

						httpReply.setStatus("OK");

						reply = "Deleted";
						ByteArrayOutputStream b = new ByteArrayOutputStream();
						b.write(reply.getBytes());
						httpReply.setReply(b);
					}
					else {
						httpReply.setCode(403);

						httpReply.setStatus("Forbidden");

						reply = "Forbidden";
						ByteArrayOutputStream b = new ByteArrayOutputStream();
						b.write(reply.getBytes());
						httpReply.setReply(b);
					}

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
					ByteArrayOutputStream b = new ByteArrayOutputStream();
					b.write(reply.getBytes());
					httpReply.setReply(b);

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
				}
				else {
					httpReply.setCode(201);
					httpReply.setStatus("Created");
				}

				String reply = "Put";
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				b.write(reply.getBytes());
				httpReply.setReply(b);

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
				ByteArrayOutputStream b = new ByteArrayOutputStream();
				b.write(reply.getBytes());
				httpReply.setReply(b);

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
