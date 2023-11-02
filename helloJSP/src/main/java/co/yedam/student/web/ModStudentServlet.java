package co.yedam.student.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;

@WebServlet("/editStudent.do")
public class ModStudentServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 수정 -> 바뀐정보 OK/NG 알려주기

		req.setCharacterEncoding("utf-8");

		String sid = req.getParameter("id");
		String sname = req.getParameter("name");
		String spass = req.getParameter("password");
		String sdept = req.getParameter("dept");
		String birth = req.getParameter("birthday");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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

		// 반환할 값: map으로 생성.
		Map<String, Object> map = new HashMap<>();
		StudentService svc = new StudentServiceImpl();
		if (svc.editStudent(vo)) {
			map.put("retCode", "OK");
			map.put("vo", vo);
		} else {
			map.put("retCode", "NG");
			map.put("vo", vo);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 한글처리
		resp.setContentType("text/json;charset=utf-8");
		resp.getWriter().print(gson.toJson(map));
		
	}
}
