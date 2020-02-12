package com.latte.model;

import java.util.HashMap;
import java.util.List;

public class KaKaoGeoResponse {
	private HashMap<String, Object> meta;
	private List<Documents> documents;

	public KaKaoGeoResponse() {
		super();
	}

	public KaKaoGeoResponse(HashMap<String, Object> meta, List<Documents> documents) {
		super();
		this.meta = meta;
		this.documents = documents;
	}

	public HashMap<String, Object> getMeta() {
		return meta;
	}

	public void setMeta(HashMap<String, Object> meta) {
		this.meta = meta;
	}

	public List<Documents> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Documents> documents) {
		this.documents = documents;
	}

}
