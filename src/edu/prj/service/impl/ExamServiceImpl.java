package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.ExamBean;
import edu.prj.dao.ExamDao;
import edu.prj.dao.impl.ExamDaoImpl;
import edu.prj.service.ExamService;

public class ExamServiceImpl implements ExamService {

	private ExamDao examDao = new ExamDaoImpl();

	@Override
	public Long insert(ExamBean bean) {
		// TODO Auto-generated method stub
		return examDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return examDao.delete(id);
	}

	@Override
	public Long update(ExamBean bean) {
		// TODO Auto-generated method stub
		return examDao.update(bean);
	}

	@Override
	public List<ExamBean> list() {
		// TODO Auto-generated method stub
		return examDao.list();
	}

	@Override
	public ExamBean load(Long id) {
		// TODO Auto-generated method stub
		return examDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return examDao.count();
	}

	@Override
	public List<ExamBean> listByName(String name) {
		// TODO Auto-generated method stub
		return examDao.listByName(name);
	}

	@Override
	public List<ExamBean> listByID(Long id) {
		// TODO Auto-generated method stub
		return examDao.listByID(id);
	}

	@Override
	public List<ExamBean> listScore(String stuName, String papName) {
		// TODO Auto-generated method stub
		return examDao.listScore(stuName, papName);
	}

	@Override
	public List<ExamBean> listStuName(String name) {
		// TODO Auto-generated method stub
		return examDao.listStuName(name);
	}

}
