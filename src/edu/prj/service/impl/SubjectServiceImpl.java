package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.SubjectBean;
import edu.prj.dao.SubjectDao;
import edu.prj.dao.impl.SubjectDaoImpl;
import edu.prj.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
	private SubjectDao subjectDao = new SubjectDaoImpl();

	@Override
	public Long insert(SubjectBean bean) {
		// TODO Auto-generated method stub
		return subjectDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return subjectDao.delete(id);
	}

	@Override
	public Long update(SubjectBean bean) {
		// TODO Auto-generated method stub
		return subjectDao.update(bean);
	}

	@Override
	public List<SubjectBean> list() {
		// TODO Auto-generated method stub
		return subjectDao.list();
	}

	@Override
	public SubjectBean load(Long id) {
		// TODO Auto-generated method stub
		return subjectDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return subjectDao.count();
	}

	@Override
	public SubjectBean loadByName(String name) {
		// TODO Auto-generated method stub
		return subjectDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return subjectDao.countByName(name);
	}

	@Override
	public List<SubjectBean> listByName(String name) {
		// TODO Auto-generated method stub
		return subjectDao.listByName(name);
	}

}
