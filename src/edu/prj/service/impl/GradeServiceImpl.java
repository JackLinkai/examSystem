package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.GradeBean;
import edu.prj.dao.GradeDao;
import edu.prj.dao.impl.GradeDaoImpl;
import edu.prj.service.GradeService;

public class GradeServiceImpl implements GradeService {

	private GradeDao gradeDao = new GradeDaoImpl();

	@Override
	public Long insert(GradeBean bean) {
		// TODO Auto-generated method stub
		return gradeDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return gradeDao.delete(id);
	}

	@Override
	public Long update(GradeBean bean) {
		// TODO Auto-generated method stub
		return gradeDao.update(bean);
	}

	@Override
	public List<GradeBean> list() {
		// TODO Auto-generated method stub
		return gradeDao.list();
	}

	@Override
	public GradeBean load(Long id) {
		// TODO Auto-generated method stub
		return gradeDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return gradeDao.count();
	}

	@Override
	public GradeBean loadByName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.countByName(name);
	}

	@Override
	public List<GradeBean> listByName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.listByName(name);
	}

}
