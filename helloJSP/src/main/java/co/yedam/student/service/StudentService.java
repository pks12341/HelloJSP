package co.yedam.student.service;

import java.util.List;

public interface StudentService {
	//crud //등록 수정 삭제 목록 단건조회
	public boolean addStudent(StudentVO vo);
	public boolean editStudent(StudentVO vo);
	public boolean removeStudent(String sid);//학생아이디 하나만 알면되기때문에
	public List<StudentVO> listStudent();
	public StudentVO getStudent(String sid);//학생아이디 하나만 알면되기때문에
	
	
}
