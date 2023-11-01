package co.yedam.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;

public class MainExe {

	public static void main(String[] args) {
		// 학생아이디, 비밀번호, 이름 ,학과, 생일
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//2012-03-05(한국) Nov-23-2012(국제)이기 떄문에..
		//Date ->> String : sdf.format().
		//String ->> Date : sdf.parse().
		
		StudentVO vo = new StudentVO();
		vo.setStudentId("newbie");
		vo.setStudentName("뉴비");
		vo.setStudentPassword("1234");
		vo.setStudentDept("영문학과");

		try {
			vo.setStudentBirthday(sdf.parse("2001-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//list
		StudentService svc = new StudentServiceImpl();
	//	svc.listStudent().forEach(student -> System.out.println(student));
		
		StudentVO vo2 = svc.getStudent("hong");
		if (vo2 != null) {
			System.out.println(vo2);
		} else {
			System.out.println("에러발생");
		}
	}


}
