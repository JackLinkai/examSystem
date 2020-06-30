package edu.prj.service.impl;

import java.util.List;

import edu.prj.bean.ClassRoomBean;
import edu.prj.dao.ClassRoomDao;
import edu.prj.dao.impl.ClassRoomDaoImpl;
import edu.prj.service.ClassRoomService;

public class ClassRoomServiceImpl implements ClassRoomService {

	private ClassRoomDao classRoomDao = new ClassRoomDaoImpl();

	@Override
	public Long insert(ClassRoomBean bean) {
		// TODO Auto-generated method stub
		return classRoomDao.insert(bean);
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		return classRoomDao.delete(id);
	}

	@Override
	public Long update(ClassRoomBean bean) {
		// TODO Auto-generated method stub
		return classRoomDao.update(bean);
	}

	@Override
	public List<ClassRoomBean> list() {
		// TODO Auto-generated method stub
		return classRoomDao.list();
	}

	@Override
	public ClassRoomBean load(Long id) {
		// TODO Auto-generated method stub
		return classRoomDao.load(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return classRoomDao.count();
	}

	@Override
	public ClassRoomBean loadByName(String name) {
		// TODO Auto-generated method stub
		return classRoomDao.loadByName(name);
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		return classRoomDao.countByName(name);
	}

	@Override
	public List<ClassRoomBean> listByName(String name) {
		// TODO Auto-generated method stub
		return classRoomDao.listByName(name);
	}

}
