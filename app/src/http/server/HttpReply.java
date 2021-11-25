package http.server;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

/**
 * Représente une réponse HTTP.
 */
public class HttpReply {
	/**
	 * Version du protocol HTTP.
	 */
	private final String version;
	/**
	 * Code retour de la réponse HTTP.
	 */
	private Integer code;
	/**
	 * Statut de la réponse HTTP.
	 */
	private  String status;
	/**
	 * Contient les différentes information du header de la réponse HTTP.
	 */
	private Hashtable<String, String> header;
	/**
	 * Corps de la réponse HTTP.
	 */
	private ByteArrayOutputStream reply;

	/**
	 * Constructeur de la réponse HTTP.
	 */
	public HttpReply() {
		this.version = "HTTP/1.1";
	}

	/**
	 * Met à jour le code retour de la réponse HTTP.
	 * @param code Code retour de la réponse HTTP.
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * Met à jour le statut de la réponse HTTP.
	 * @param status Statut de la réponse HTTP.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Met à jour le header de la réponse HTTP.
	 * @param header Header de la réponse HTTP.
	 */
	public void setHeader(Hashtable<String, String> header) {
		this.header = header;
	}

	/**
	 * Met à jour le corps de la réponse HTTP.
	 * @param reply Corps de la réponse HTTP.
	 */
	public void setReply(ByteArrayOutputStream reply) {
		this.reply = reply;
	}

	/**
	 * Retourne le header de la réponse HTTP.
	 * @return Header de la réponse HTTP.
	 */
	public String getHeader() {
		String str;
		str =  this.version + " " +
				this.code + " " +
				this.status + "\r\n";
		if (this.header != null) {
			for (Map.Entry<String, String> entry : this.header.entrySet()) {
				str += entry.getKey() + ": " + entry.getValue() + "\r\n";
			}
		}
		return str;
	}

	/**
	 * Retourne le corps de la réponse HTTP.
	 * @return Corps de la réponse HTTP.
	 */
	public byte[] getReply() {
		return this.reply.toByteArray();
	}
}
