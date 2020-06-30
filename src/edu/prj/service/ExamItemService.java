package edu.prj.service;

import java.util.List;

import edu.prj.bean.ExamItemBean;

public interface ExamItemService {
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
