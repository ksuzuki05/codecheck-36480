package codecheck.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codecheck.client.RestClient;

/**
 * メインのロジックを提供するクラス。
 */
@Service
public class MainService {
	@Autowired
	RestClient restClient;
	
	private Map<Integer, Integer> resultsCache = new HashMap<>();
	private String seed = null;
	
	/**
	 * seed をセットします。
	 * @param seed
	 */
	public void init(String seed) {
		this.seed = seed;
	}
	
	/**
	 * 以下の要件を満たす関数 f を提供します。
	 * f(0) = 1
	 * f(2) = 2
	 * f(n) = {
	 *   if n mod 2 = 0 then
	 *     f(n-1) + f(n-2) + f(n-3) + f(n-4)
	 *   else
	 *     askServer(n)
	 * }
	 * 
	 * @param n
	 * @return
	 */
	public int calc(int n) {
		if (n == 0) return 1;
		if (n == 2) return 2;
		
		if (n % 2 == 0) {
			return calc(n-1) + calc(n-2) + calc(n-3) + calc(n-4);
		}
		
		if (resultsCache.containsKey(new Integer(n))) {
			return resultsCache.get(n).intValue();
		} else {
			int result = restClient.askServer(seed, n);
			resultsCache.put(new Integer(n), new Integer(result));
			return result;
		}
	}
}
