package co.yedam.student.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.student.service.StudentService;
import co.yedam.student.serviceImpl.StudentServiceImpl;

@WebServlet({ "/RemStudentServlet.do", "/delStudent.do" })
public class RemStudentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("sid");
		StudentService svc = new StudentServiceImpl();
		if (svc.removeStudent(sid)) {
			//웹페이지 주소에....RemStudentServlet.do?sid=lee 이런식으로 치면 OK가 뜨고 lee 데이터가 삭제됨(db연동)
			// {"retCode : "OK"}
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		} else {
			//{"retCode" : "NG"}
			resp.getWriter().print("{\"retCode\" : \"NG\"}");
		}

	}
}
