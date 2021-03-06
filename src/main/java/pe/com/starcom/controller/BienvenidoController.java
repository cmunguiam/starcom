package pe.com.starcom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bienvenidoController")
@Scope("session")
public class BienvenidoController {

	private static final Logger logger = LoggerFactory
			.getLogger(BienvenidoController.class);

	private int count;

	public BienvenidoController() {

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void increment() {
		count++;
	}

}
