package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.PaperBean;
import edu.prj.bean.PaperItemBean;
import edu.prj.bean.QuestionBean;
import edu.prj.dao.PaperItemDao;
import edu.prj.dao.impl.PaperItemDaoImpl;
import edu.prj.service.PaperItemService;

public class PaperItemServiceImpl implements PaperItemService {

	private PaperItemDao paperItemDao = new PaperItemDaoImpl();

	@Override
	public Long insert(PaperBean bean, List<QuestionBean> list) {
		// TODO Auto-generated method stub
		return paperItemDao.insert(bean, list);
	}

	@Override
	public Long insert(PaperItemBean bean) {
		// TODO Auto-generated method stub
		return paperItemDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return paperItemDao.delete(id);
	}

	@Override
	public Long update(PaperItemBean bean) {
		// TODO Auto-generated method stub
		return paperItemDao.update(bean);
	}

	@Override
	public List<PaperItemBean> list() {
		// TODO Auto-generated method stub
		return paperItemDao.list();
	}

	@Override
	public PaperItemBean load(Long id) {
		// TODO Auto-generated method stub
		return paperItemDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return paperItemDao.count();
	}

	@Override
	public List<PaperItemBean> listByName(String name) {
		// TODO Auto-generated method stub
		return paperItemDao.listByName(name);
	}

}
