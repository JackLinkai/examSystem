package edu.prj.service;

import edu.prj.bean.*;

public interface GradeService {
	Long insert(GradeBean bean);

	Long delete(Long id);

	Long update(GradeBean bean);

	java.util.List<GradeBean> list();

	GradeBean load(Long id);// 获取一行，传入主键，不存在返回null

	Long count();// 统计行数

	GradeBean loadByName(String name);// 获取一行，传入名称，不存在返回null

	Long countByName(String name);// 统计行数，传入名称

	java.util.List<GradeBean> listByName(String name);

}
