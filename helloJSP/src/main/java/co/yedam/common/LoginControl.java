package co.yedam.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.MemberVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;

public class LoginControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String pw = req.getParameter("pass");
		
		//session : 서버-클라이언트 
		
		BoardService svc = new BoardServiceImpl();
		MemberVO vo = svc.loginCheck(id, pw);
		if (vo != null) {
			System.out.println(vo);
			HttpSession session = req.getSession();	 // 세션정보 가져옴
			session.setAttribute("logId", id); //인터넷창 닫지않는 이상 유ㅜ지됨
			if(vo.getResponsibility().toUpperCase().equals("ADMIN")) {
				session.setAttribute("admin", "OK");
			}
			
			try {
				resp.sendRedirect("boardList.do");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				resp.sendRedirect("loginForm.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
