package codecheck.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import codecheck.client.dto.Response;

/**
 * recursive REST API をコールするクライアントクラス。
 */
@Component
public class RestClient {
	private static String BASE_URI = "http://challenge-server.code-check.io";
	private static final String API_PATH = "/api/recursive/ask";
	private static final String SEED_KEY = "seed";
	private static final String N_KEY = "n";
	private static final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * 与えられた seed と n を使用して、REST API をコールし、
	 * リクエストの result を取得します。
	 * 
	 * @param seed
	 * @param n
	 * @return result
	 */
	public int askServer(String seed, int n) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URI + API_PATH)
				.queryParam(SEED_KEY, seed)
				.queryParam(N_KEY, n);
		
		Response response = restTemplate.getForObject(builder.toUriString(), Response.class);
		
		return response.getResult();
	}
	
	/**
	 * REST 通信先の URI をセットします（テスト用）
	 * @param uri
	 */
	protected void setBaseUri(String uri) {
		BASE_URI = uri;
	}
}
