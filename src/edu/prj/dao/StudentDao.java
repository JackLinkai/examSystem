package edu.prj.dao;

import edu.prj.bean.StudentBean;

public interface StudentDao {

	Long insert(StudentBean bean);

	Long delete(Long id);

	Long update(StudentBean bean);

	java.util.List<StudentBean> list();

	StudentBean load(Long id);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	StudentBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<StudentBean> listByName(String name);

}
