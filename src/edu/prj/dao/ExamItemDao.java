package edu.prj.dao;

import java.util.List;

import edu.prj.bean.*;

public interface ExamItemDao {
	Long insert(ExamItemBean bean);

	Long delete(Long id);

	Long update(ExamItemBean bean);

	List<ExamItemBean> list();

	ExamItemBean load(Long id);

	Long count();

	Long sum(Long id);

	List<ExamItemBean> listByName(Long id);

	Long Score();

}
