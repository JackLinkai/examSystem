package edu.prj.dao;

import java.util.List;

import edu.prj.bean.*;

public interface PaperDao {
	Long insert(PaperBean bean);

	Long delete(Long id);

	Long update(PaperBean bean);

	List<PaperBean> list();

	PaperBean load(Long id);

	Long count();

	PaperBean loadByName(String name);

	Long countByName(String name);

	List<PaperBean> listByName(String name);

}
