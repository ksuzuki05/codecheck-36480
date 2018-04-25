package codecheck;

import org.apache.commons.lang3.math.NumberUtils;

import codecheck.service.MainService;

/**
 * メインクラス。
 */
public class App {
	public static void main(String[] args) {
		if (args.length != 2 || !NumberUtils.isDigits(args[1])) {
			System.out.println("error! invalid arguments.");
			System.err.println("error! invalid arguments.");
			return;
		}
		
		String seed = args[0];
		String n = args[1];
		
		MainService mainService = new MainService();
		mainService.init(seed);
		
		System.out.println(mainService.calc(Integer.parseInt(n)));
		return;
	}
}
