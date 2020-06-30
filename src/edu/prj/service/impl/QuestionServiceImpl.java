package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.QuestionBean;
import edu.prj.dao.QuestionDao;
import edu.prj.dao.impl.QuestionDaoImpl;
import edu.prj.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {
	private QuestionDao questionDao = new QuestionDaoImpl();

	@Override
	public Long insert(QuestionBean bean) {
		// TODO Auto-generated method stub
		return questionDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return questionDao.delete(id);
	}

	@Override
	public Long update(QuestionBean bean) {
		// TODO Auto-generated method stub
		return questionDao.update(bean);
	}

	@Override
	public List<QuestionBean> list() {
		// TODO Auto-generated method stub
		return questionDao.list();
	}

	@Override
	public QuestionBean load(Long id) {
		// TODO Auto-generated method stub
		return questionDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return questionDao.count();
	}

	@Override
	public QuestionBean loadByName(String name) {
		// TODO Auto-generated method stub
		return questionDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return questionDao.countByName(name);
	}

	@Override
	public List<QuestionBean> listByName(String name, String type) {
		// TODO Auto-generated method stub
		return questionDao.listByName(name, type);
	}

	@Override
	public List<QuestionBean> listByQType(Integer type) {
		// TODO Auto-generated method stub
		return questionDao.listByQType(type);
	}

	

}
