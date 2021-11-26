package http.server;

import java.util.Hashtable;

/**
 * Représente une requête HTTP.
 */
public class HttpRequest {
	/**
	 * Méthode de la requête HTTP.
	 */
	private HttpMethod method;
	/**
	 * Resource de la requête HTTP.
	 */
	private String resource;
	/**
	 * Version de la requête HTTP.
	 */
	private String version;
	/**
	 * Header de la requête HTTP.
	 */
	private  Hashtable<String, String> header;
	/**
	 * Paramètres de la requête HTTP.
	 */
	private Hashtable<String, String> parameters;
	/**
	 * Corps de la requête HTTP.
	 */
	private String request;

	/**
	 * Met à jour la méthode de la requête HTTP.
	 * @param method Méthode de la requête HTTP.
	 */
	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	/**
	 * Met à jour la resource de la requête HTTP.
	 * @param resource Resource de la requête HTTP.
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * Met à jour la version de la requête HTTP.
	 * @param version Version de la requête HTTP.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Met à jour le header de la requête HTTP.
	 * @param header Header de la requête HTTP.
	 */
	public void setHeader(Hashtable<String, String> header) {
		this.header = header;
	}

	/**
	 * Met à jour les paramètres de la requête HTTP.
	 * @param parameters Paramètres de la requête HTTP.
	 */
	public void setParameters(Hashtable<String, String> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Met à jour le corps de la requête HTTP.
	 * @param request Corps de la requête HTTP.
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * Retourne la méthode de la requête HTTP.
	 * @return Méthode de la requête HTTP.
	 */
	public HttpMethod getMethod() {
		return this.method;
	}

	/**
	 * Retourne la resource de la requête HTTP.
	 * @return Resource de la requête HTTP.
	 */
	public String getResource() {
		return this.resource;
	}

	/**
	 * Retourne la version de la requête HTTP.
	 * @return Version de la requête HTTP.
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * Retourne le header de la requête HTTP.
	 * @return Header de la requête HTTP.
	 */
	public Hashtable<String, String> getHeader() {
		return this.header;
	}

	/**
	 * Retourne les paramètres de la requête HTTP.
	 * @return Paramètres de la requête HTTP.
	 */
	public Hashtable<String, String> getParameters() {
		return this.parameters;
	}

	/**
	 * Retourne le corps de la requête HTTP.
	 * @return Corps de la Retourne la requête HTTP.
	 */
	public String getRequest() {
		return this.request;
	}
}
