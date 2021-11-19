package http.server;

import java.util.Hashtable;

public class HttpRequest {
	private HttpMethod method;
	private String resource;
	private String version;
	private String host;
	private String contentLength;
	private Hashtable variables;

		public HttpRequest() {
			this.variables = new Hashtable<String, String>();
		}
}
