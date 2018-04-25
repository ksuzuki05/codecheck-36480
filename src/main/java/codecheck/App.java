package codecheck;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import codecheck.service.MainService;

/**
 * メインクラス。
 */
@Component
public class App {
	@Autowired
	MainService mainService;
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/config.xml");
		App p = context.getBean(App.class);
		p.start(args);
	}
	
	private void start(String[] args) {
		if (args.length != 2 || !NumberUtils.isDigits(args[1])) {
			System.out.println("error! invalid arguments.");
			System.err.println("error! invalid arguments.");
			System.exit(1);
		}
		
		String seed = args[0];
		String n = args[1];
		
		mainService.init(seed);
		
		System.out.println(mainService.calc(Integer.parseInt(n)));
		System.exit(0);
	}
}
