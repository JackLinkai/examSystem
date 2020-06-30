package edu.prj.dao;

import java.util.List;

import edu.prj.bean.*;

public interface PaperItemDao {
	Long insert(PaperBean bean, List<QuestionBean> list);

	Long insert(PaperItemBean bean);

	Long delete(Long id);

	Long update(PaperItemBean bean);

	List<PaperItemBean> list();

	PaperItemBean load(Long id);

	Long count();

	List<PaperItemBean> listByName(String name);

}
