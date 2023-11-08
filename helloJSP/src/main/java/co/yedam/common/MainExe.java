package co.yedam.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import co.yedam.reply.mapper.ReplyMapper;

public class MainExe {
	public static void main(String[] args) {
		SqlSession session = //
				DataSourceMybatis.getInstance().openSession(true);
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		
		List<Map<String, Object>> map = mapper.getReplyCountByWriter();
		System.out.println(map);
		
		
		
		//mapper.replyList(1, 3).forEach(rep -> System.out.println(rep));
	
		
		
		
		
//		
//		ReplyVO vo1 = new ReplyVO();
//		List<ReplyVO> list = mapper.replyList(1);
//		list.forEach(vo -> System.out.println(vo));
		
		
		
//		ReplyVO s = mapper.selectReply(5);
//		System.out.println(s);
//		
//		vo1.setReply("안녕하세요");
//		vo1.setReplyer("박경석");
//		vo1.
//		
//		int a = mapper.insertReply(vo1);
//		System.out.println(a);
//		
//		int b = mapper.deleteReply(vo1);
//		System.out.println(b);
//		
//		int b = mapper.updateReply(vo1) ;
//		System.out.println(b);
		
		
		
		
	
		
		
		// 목록 / boardList.do / boardList.jsp
		// 조회 / getBoard.do / getBoard.jsp
		// 등록 / boardForm.do / boardForm.jsp
		// 처리 / addBoard.do / boardList.jsp
//
//		BoardService svc = new BoardServiceImpl();
//		BoardDAO dao = new BoardDAO();
//		BoardVO vo = new BoardVO();
//		vo.setTitle("test");
//		vo.setWriter("user032");
//		vo.setContent("content");
//		vo.setBoardNo(5);
		
		
		
		// System.out.println(svc.update(vo));

		// 학생아이디, 비밀번호, 이름 ,학과, 생일
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		// 2012-03-05(한국) Nov-23-2012(국제)이기 떄문에..
//		// Date ->> String : sdf.format().
//		// String ->> Date : sdf.parse().
//
//		StudentVO vo = new StudentVO();
//		vo.setStudentId("newbie");
//		vo.setStudentName("뉴비");
//		vo.setStudentPassword("1234");
//		vo.setStudentDept("영문학과");
//
//		try {
//			vo.setStudentBirthday(sdf.parse("2001-01-01"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		// list
//		StudentService svc = new StudentServiceImpl();
//		// svc.listStudent().forEach(student -> System.out.println(student));
//
//		StudentVO vo2 = svc.getStudent("hong");
//		if (vo2 != null) {
//			System.out.println(vo2);
//		} else {
//			System.out.println("에러발생");
//		}
	}

}
