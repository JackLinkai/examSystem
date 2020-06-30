package edu.prj.service;

import edu.prj.bean.*;

public interface ClassRoomService {
	Long insert(ClassRoomBean bean);

	Long delete(Long id);

	Long update(ClassRoomBean bean);

	java.util.List<ClassRoomBean> list();

	ClassRoomBean load(Long id);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	ClassRoomBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<ClassRoomBean> listByName(String name);

}
