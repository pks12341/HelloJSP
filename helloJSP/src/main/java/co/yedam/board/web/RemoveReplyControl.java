package co.yedam.board.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.Command;
import co.yedam.reply.service.ReplyService;
import co.yedam.reply.serviceImpl.ReplyServiceImpl;

public class RemoveReplyControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String rno = req.getParameter("rno");
		
		Map<String, String> map = new HashMap<>();

		
		ReplyService svc = new ReplyServiceImpl();
		if(svc.delReply(Integer.parseInt(rno))) { //정상 삭제가 되면
			map.put("retCode", "OK");
		}else {
			map.put("retCode", "NG");
		}
		Gson gson = new GsonBuilder().create();  //json변환
		try {
			resp.getWriter().print(gson.toJson(map));  //문자열 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
