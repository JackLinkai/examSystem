package edu.prj.service;

import edu.prj.bean.StudentBean;

public interface StudentService {

	Long insert(StudentBean bean);

	Long delete(Long id);

	Long update(StudentBean bean);

	java.util.List<StudentBean> list();

	StudentBean load(Long id);

	Long count();

	StudentBean loadByName(String name);

	Long countByName(String name);

	java.util.List<StudentBean> listByName(String name);

}
