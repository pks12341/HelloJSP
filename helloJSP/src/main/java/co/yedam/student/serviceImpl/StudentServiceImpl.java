package co.yedam.student.serviceImpl;

import java.util.List;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;

public class StudentServiceImpl implements StudentService{
	
	StudentDAO dao = new StudentDAO();

	@Override
	public boolean addStudent(StudentVO vo) {
		System.out.println(vo);
		return dao.insert(vo) == 1;
	}

	@Override
	public boolean editStudent(StudentVO vo) {
		return dao.update(vo) ==1;
	}

	@Override
	public boolean removeStudent(String sid) {
		return dao.delete(sid) ==1;
	}

	@Override
	public List<StudentVO> listStudent() {
		return dao.list();
	}

	@Override
	public StudentVO getStudent(String sid) {
		return dao.select(sid);
	}

}
