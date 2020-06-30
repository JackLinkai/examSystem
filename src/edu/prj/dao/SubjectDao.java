package edu.prj.dao;

import edu.prj.bean.*;

public interface SubjectDao {
	Long insert(SubjectBean bean);

	Long delete(Long id);

	Long update(SubjectBean bean);

	java.util.List<SubjectBean> list();

	SubjectBean load(Long id);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	SubjectBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称
 
	java.util.List<SubjectBean> listByName(String name);

}
