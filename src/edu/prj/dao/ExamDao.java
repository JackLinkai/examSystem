package edu.prj.dao;

import java.util.List;

import edu.prj.bean.*;

public interface ExamDao {
	Long insert(ExamBean bean);

	Long delete(Long id);

	Long update(ExamBean bean);

	List<ExamBean> list();

	ExamBean load(Long id);

	Long count();


	List<ExamBean> listByName(String name);

	List<ExamBean> listByID(Long id);

	List<ExamBean> listScore(String stuName, String papName);

	List<ExamBean> listStuName(String name);
}
