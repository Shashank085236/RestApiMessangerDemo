package org.example.models;

/**
 * 
 * No need to annotate this as @XmlRootElement, as it will NOT serve as root element rather will be a part of XML/JSON generated.
 *
 */

public class Link {
	
	private String URI;
	private String rel;
	
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	
}
