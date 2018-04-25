package codecheck.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class RestClientTest {
	@Rule
	public WireMockRule wiremock = new WireMockRule(options().port(14200));
	
	RestClient restClient = new RestClient();
	
	@Before
	public void setupBeforeClass() {
		// REST 通信の投げ先を wiremock へ変更
		restClient.setBaseUri("http://localhost:14200");
	}

	@Test
	public void test_responseから正しくresultを取得できる() {
		stubFor(get(urlEqualTo("/api/recursive/ask?seed=abc&n=1"))
				.willReturn(aResponse().withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody("{\"seed\":\"abc\",\"n\":1,\"result\":30}")));
		
		int actual = restClient.askServer("abc", 1);
		assertEquals(actual, 30);
	}

}
