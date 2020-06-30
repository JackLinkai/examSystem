package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.TeacherBean;
import edu.prj.dao.TeacherDao;
import edu.prj.dao.impl.TeacherDaoImpl;
import edu.prj.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	private TeacherDao teacherDao = new TeacherDaoImpl();

	@Override
	public Long insert(TeacherBean bean) {
		// TODO Auto-generated method stub
		return teacherDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.delete(id);
	}

	@Override
	public Long update(TeacherBean bean) {
		// TODO Auto-generated method stub
		return teacherDao.update(bean);
	}

	@Override
	public List<TeacherBean> list() {
		// TODO Auto-generated method stub
		return teacherDao.list();
	}

	@Override
	public TeacherBean load(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return teacherDao.count();
	}

	@Override
	public TeacherBean loadByName(String name) {
		// TODO Auto-generated method stub
		return teacherDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return teacherDao.countByName(name);
	}

	@Override
	public List<TeacherBean> listByName(String name) {
		// TODO Auto-generated method stub
		return teacherDao.listByName(name);
	}

}
