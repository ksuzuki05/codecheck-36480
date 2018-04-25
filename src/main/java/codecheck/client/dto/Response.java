package codecheck.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * recursive REST API のレスポンスを格納するクラス。
 */
public class Response {
	@JsonProperty("seed")
	String seed;
	
	@JsonProperty("n")
	int n;
	
	@JsonProperty("result")
	int result;

	public String getSeed() {
		return seed;
	}

	public int getN() {
		return n;
	}

	public int getResult() {
		return result;
	}
}
