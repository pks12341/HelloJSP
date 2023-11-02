package co.yedam.student.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;

@WebServlet("/addStudent.do")
public class AddStudentServlet extends HttpServlet {

	// init -> service -> destroy

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 한글처리
		req.setCharacterEncoding("utf-8");

		// studentinfo.html에서 let param
		// let param =
		// `id=${sid}&name=${sname}&password=${pass}&dept=${sdept}&birthday=${birth}`;
		// 여기서 ..
		String sid = req.getParameter("id");
		String sname = req.getParameter("name");
		String spass = req.getParameter("password");
		String sdept = req.getParameter("dept");
		String birth = req.getParameter("birthday");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // dd는 소문자로하기

//		System.out.println(sid);
//		System.out.println(sname);
//		System.out.println(spass);
//		System.out.println(sdept);
//		System.out.println(birth);
		StudentVO vo = new StudentVO();
		vo.setStudentId(sid);
		vo.setStudentName(sname);
		vo.setStudentPassword(spass);
		vo.setStudentDept(sdept);
		try {
			vo.setStudentBirthday(sdf.parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		String sid = req.getParameter("id");
//		String sname = req.getParameter("name");
//		String spass = req.getParameter("passwird");
//		String sdept = req.getParameter("dept");
//		String birth = req.getParameter("birthday");

		StudentService svc = new StudentServiceImpl();
		if (svc.addStudent(vo)) {
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\" : \"NG\"}");
		}

	}

}

//@Override
//protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	String sid = req.getParameter("sid");
//	StudentService svc = new StudentServiceImpl();
//	if (svc.removeStudent(sid)) {
//		//웹페이지 주소에....RemStudentServlet.do?sid=lee 이런식으로 치면 OK가 뜨고 lee 데이터가 삭제됨(db연동)
//		// {"retCode : "OK"}
//		resp.getWriter().print("{\"retCode\" : \"OK\"}");
//	} else {
//		//{"retCode" : "NG"}
//		resp.getWriter().print("{\"retCode\" : \"NG\"}");
//	}
//
//}
