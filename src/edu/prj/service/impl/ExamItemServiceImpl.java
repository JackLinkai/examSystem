package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.ExamItemBean;
import edu.prj.dao.ExamItemDao;
import edu.prj.dao.impl.ExamItemDaoImpl;
import edu.prj.service.ExamItemService;

public class ExamItemServiceImpl implements ExamItemService {

	private ExamItemDao examItemDao = new ExamItemDaoImpl();

	@Override
	public Long insert(ExamItemBean bean) {
		// TODO Auto-generated method stub
		return examItemDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return examItemDao.delete(id);
	}

	@Override
	public Long update(ExamItemBean bean) {
		// TODO Auto-generated method stub
		return examItemDao.update(bean);
	}

	@Override
	public List<ExamItemBean> list() {
		// TODO Auto-generated method stub
		return examItemDao.list();
	}

	@Override
	public ExamItemBean load(Long id) {
		// TODO Auto-generated method stub
		return examItemDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return examItemDao.count();
	}

	@Override
	public Long sum(Long id) {
		// TODO Auto-generated method stub
		return examItemDao.sum(id);
	}

	@Override
	public List<ExamItemBean> listByName(Long id) {
		// TODO Auto-generated method stub
		return examItemDao.listByName(id);
	}

	@Override
	public Long Score() {
		// TODO Auto-generated method stub
		return examItemDao.Score();
	}

}
