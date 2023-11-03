package co.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("FirstControl.....");
		

	}

}
