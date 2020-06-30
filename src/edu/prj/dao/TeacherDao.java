package edu.prj.dao;

import edu.prj.bean.TeacherBean;

public interface TeacherDao {
	Long insert(TeacherBean bean);

	Long delete(Long id);

	Long update(TeacherBean bean);

	java.util.List<TeacherBean> list();

	TeacherBean load(Long id); // 获取一行，传入主键，不存在返回null

	Long count(); // 统计行数

	TeacherBean loadByName(String name); // 获取一行，传入名称，不存在返回null

	Long countByName(String name); // 统计行数，传入名称

	java.util.List<TeacherBean> listByName(String name);
}
