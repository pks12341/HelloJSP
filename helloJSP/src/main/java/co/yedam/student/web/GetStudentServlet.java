package co.yedam.student.web;

import java.io.IOException;
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

@WebServlet("/getStudent.do")
public class GetStudentServlet extends HttpServlet {
// service : parameter(??)

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("sid");
		StudentService svc = new StudentServiceImpl();
		StudentVO vo = svc.getStudent(id);

		// json반환
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 응답정보의 한글처리와 content타입 지정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		
		Map<String,Object> map = new HashMap<>();
		
		if(vo != null ) {
			map.put("retCode", "OK");
			map.put("vo", vo);
		}else {
			map.put("retCode", "NG");
		}
		resp.getWriter().print(gson.toJson(map));
		
	
	}
}
