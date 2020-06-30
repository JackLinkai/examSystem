package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.StudentBean;
import edu.prj.dao.StudentDao;
import edu.prj.dao.impl.StudentDaoImpl;
import edu.prj.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public Long insert(StudentBean bean) {
		// TODO Auto-generated method stub
		return studentDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return studentDao.delete(id);
	}

	@Override
	public Long update(StudentBean bean) {
		// TODO Auto-generated method stub
		return studentDao.update(bean);
	}

	@Override
	public List<StudentBean> list() {
		// TODO Auto-generated method stub
		return studentDao.list();
	}

	@Override
	public StudentBean load(Long id) {
		// TODO Auto-generated method stub
		return studentDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return studentDao.count();
	}

	@Override
	public StudentBean loadByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.countByName(name);
	}

	@Override
	public List<StudentBean> listByName(String name) {
		return studentDao.listByName(name);
	}

}
