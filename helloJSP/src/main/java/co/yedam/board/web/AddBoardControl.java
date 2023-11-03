package co.yedam.board.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;
import co.yedam.common.Command;

public class AddBoardControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// 제목 ,내용, 작성자.
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");

		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);

		BoardService svc = new BoardServiceImpl();
		if (svc.addBoard(vo)) {
			try {
				resp.sendRedirect("boardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				resp.sendRedirect("boardForm.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
