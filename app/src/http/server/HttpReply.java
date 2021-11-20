package http.server;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

public class HttpReply {
	private String version;
	private Integer code;
	private  String status;
	private Hashtable<String, String> header;
	private ByteArrayOutputStream reply;

	public HttpReply() {
		this.version = "HTTP/1.1";
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setHeader(Hashtable<String, String> header) {
		this.header = header;
	}

	public void setReply(ByteArrayOutputStream reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		String str = "";
		str =  this.version + " " +
				this.code + " " +
				this.status + "\r\n";
		if (this.header != null) {
			for (Map.Entry<String, String> entry : this.header.entrySet()) {
				str += entry.getKey() + ": " + entry.getValue() + "\r\n";
			}
		}
		str += "\r\n";
		str += this.reply;
		return str;
	}
}
