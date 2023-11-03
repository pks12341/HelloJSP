package co.yedam.board.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/FirstServlet.do")
public class FirstServlet extends HttpServlet {
	// init->service ->destroy

	private static final long serialVersionUID = 1L;

	public FirstServlet() {
		super();

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service실행.");
		doGet(req,resp);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset = utf-8"); // 한글 처리

		String name = "홍길동";
		int age = 20;
		for (int i = 0; i < 5; i++) {
			response.getWriter().print("<p>" + i + "번째 이름은 " + name + ",나이는 " + age + "입니다.</p>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
