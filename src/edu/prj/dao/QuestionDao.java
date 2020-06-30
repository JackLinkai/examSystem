package edu.prj.dao;

import java.util.List;

import edu.prj.bean.*;

public interface QuestionDao {
	Long insert(QuestionBean bean);

	Long delete(Long id);

	Long update(QuestionBean bean);

	List<QuestionBean> list();

	QuestionBean load(Long id);

	Long count();

	QuestionBean loadByName(String name);

	Long countByName(String name);

	List<QuestionBean> listByName(String name, String type);

 List<QuestionBean> listByQType(Integer type);

}
