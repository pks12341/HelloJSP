package co.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFormControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		
		
		
				
		String path = "WEB-INF/main/loginForm.jsp";
		try {
		req.getRequestDispatcher(path).forward(req,resp);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
