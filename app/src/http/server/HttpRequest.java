package http.server;

import java.util.Hashtable;

public class HttpRequest {
	private HttpMethod method;
	private String resource;
	private String version;
	private  Hashtable<String, String> header;
	private Hashtable<String, String> parameters;
	private String request;

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setHeader(Hashtable<String, String> header) {
		this.header = header;
	}

	public void setParameters(Hashtable<String, String> parameters) {
		this.parameters = parameters;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public HttpMethod getMethod() {
		return this.method;
	}

	public String getResource() {
		return this.resource;
	}

	public String getVersion() {
		return this.version;
	}

	public Hashtable<String, String> getHeader() {
		return this.header;
	}

	public Hashtable<String, String> getParameters() {
		return this.parameters;
	}

	public String getRequest() {
		return this.request;
	}
}
