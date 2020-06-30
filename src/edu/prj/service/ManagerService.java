package edu.prj.service;

import edu.prj.bean.ManagerBean;

public interface ManagerService {
	Long insert(ManagerBean bean);
	
	Long delete(Long id);
	
	Long update(ManagerBean bean);
	
	java.util.List<ManagerBean> list();
	
    ManagerBean load(Long id); //获取一行，传入主键，不存在返回null
	
	Long count();          //统计行数
	
	ManagerBean loadByName(String name); // 获取一行，传入名称，不存在返回null
	
	Long countByName(String name);    //统计行数，传入名称
	
	java.util.List<ManagerBean> listByName(String name);
	
}
