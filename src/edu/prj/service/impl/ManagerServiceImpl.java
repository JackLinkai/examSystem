package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.ManagerBean;
import edu.prj.dao.ManagerDao;
import edu.prj.dao.impl.ManagerDaoImpl;
import edu.prj.service.ManagerService;


public class ManagerServiceImpl implements ManagerService {
	private ManagerDao managerDao = new ManagerDaoImpl();

	@Override
	public Long insert(ManagerBean bean) {
		// TODO Auto-generated method stub
		return managerDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return managerDao.delete(id);
	}

	@Override
	public Long update(ManagerBean bean) {
		// TODO Auto-generated method stub
		return managerDao.update(bean);
	}

	@Override
	public List<ManagerBean> list() {
		// TODO Auto-generated method stub
		return managerDao.list();
	}

	@Override
	public ManagerBean load(Long id) {
		// TODO Auto-generated method stub
		return managerDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return managerDao.count();
	}

	@Override
	public ManagerBean loadByName(String name) {
		// TODO Auto-generated method stub
		return managerDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return managerDao.countByName(name);
	}

	@Override
	public List<ManagerBean> listByName(String name) {
		// TODO Auto-generated method stub
		return managerDao.listByName(name);
	}


}
