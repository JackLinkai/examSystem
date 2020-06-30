package edu.prj.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.SysFun;

import edu.prj.bean.ExamBean;
import edu.prj.dao.ExamDao;
import edu.prj.util.DbPub;

public class ExamDaoImpl implements ExamDao {

	@Override
	public Long insert(ExamBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into Exam(StudentID,PaperID,StartOn,EndOn,IsMark,TotalScore) ");
		sb.append(" values(?,?,?,?,?,?)");

		paramsList.add(bean.getStudentID());
		paramsList.add(bean.getPaperID());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getIsMark());
		paramsList.add(bean.getTotalScore());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {

			sql = " select LAST_INSERT_ID()";
			Long result = DbPub.queryScalarLong(conn, sql);

			if (result > 0) {
				bean.setExamID(result);

				num = result;
			}
		}
		DbPub.close(conn);

		return num;
	}

	@Override
	public Long delete(Long id) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" delete from Exam ");
		sb.append(" where ExamID = ?");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public Long update(ExamBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Exam set ");
		sb.append(" StudentID = ? ");
		sb.append(" ,PaperID = ?  ");
		sb.append(" ,StartOn = ? ");
		sb.append(" ,EndOn = ? ");
		sb.append(" ,IsMark = ? ");
		sb.append(" ,TotalScore = ? ");
		sb.append(" where ExamID = ? ");
		paramsList.add(bean.getStudentID());
		paramsList.add(bean.getPaperID());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getIsMark());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getExamID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<ExamBean> list() {
		// TODO Auto-generated method stub
		List<ExamBean> list = new ArrayList<ExamBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*, S.name As studentName, B.PaperName AS PaperName ");
		sb.append(" from Exam A ");
		sb.append(" 	Left Join Student S On A.StudentID=S.StudentID ");
		sb.append(" 	Left Join Paper B On A.PaperID=B.PaperID ");
		sb.append(" Where 1=1 ");
		sb.append("  Order by A.ExamID ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			ExamBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return list;
	}

	private ExamBean toBean(ResultSet rs) throws SQLException {
		ExamBean bean;
		bean = new ExamBean();
		bean.setExamID(rs.getLong("ExamID"));
		bean.setStudentID(rs.getLong("StudentID"));
		bean.setPaperID(rs.getLong("PaperID"));
		bean.setStartOn(rs.getTimestamp("StartOn"));
		bean.setEndOn(rs.getTimestamp("EndOn"));
		bean.setIsMark(rs.getLong("IsMark"));
		bean.setTotalScore(rs.getDouble("TotalScore"));
		bean.setName(rs.getString("studentName"));
		bean.setPaperName(rs.getString("PaperName"));
		return bean;
	}

	@Override
	public ExamBean load(Long id) {
		// TODO Auto-generated method stub
		ExamBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Exam ");
		sb.append(" where 1=1 ");
		sb.append(" 	and ExamID = ?  ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			if (rs.next()) {
				bean = toBean1(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return bean;
	}

	private ExamBean toBean1(ResultSet rs) throws SQLException {
		ExamBean bean;
		bean = new ExamBean();
		bean.setExamID(rs.getLong("ExamID"));
		bean.setStudentID(rs.getLong("StudentID"));
		bean.setPaperID(rs.getLong("PaperID"));
		bean.setStartOn(rs.getTimestamp("StartOn"));
		bean.setEndOn(rs.getTimestamp("EndOn"));
		bean.setIsMark(rs.getLong("IsMark"));
		bean.setTotalScore(rs.getDouble("TotalScore"));
		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select count(1) from Exam ");
		sb.append(" where 1=1 ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<ExamBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<ExamBean> list = new ArrayList<ExamBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Select A.*, S.name As StudentName, B.PaperName AS PaperName ");
		sb.append(" from Exam A ");
		sb.append(" 	Left Join Student S On A.StudentID=S.StudentID ");
		sb.append(" 	Left Join Paper B On A.PaperID=B.PaperID ");
		sb.append(" Where 1=1 ");
		if (!SysFun.isNullOrEmpty(name)) {
			sb.append(" 	and S.name = ? ");
			paramsList.add(name);
		}
		sb.append("	and A.isMark");
		sb.append("  Order by A.ExamID ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			ExamBean bean = null;
			while (rs.next()) {
				bean = toBean3(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return list;
	}

	private ExamBean toBean3(ResultSet rs) throws SQLException {
		ExamBean bean;
		bean = new ExamBean();
		bean.setExamID(rs.getLong("ExamID"));
		bean.setStudentID(rs.getLong("StudentID"));
		bean.setPaperID(rs.getLong("PaperID"));
		bean.setTotalScore(rs.getDouble("TotalScore"));
		bean.setName(rs.getString("StudentName"));
		bean.setPaperName(rs.getString("PaperName"));
		return bean;
	}

	@Override
	public List<ExamBean> listByID(Long id) {
		// TODO Auto-generated method stub
		List<ExamBean> list = new ArrayList<>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" SELECT A.*,e.ExamID ");
		sb.append(
				" FROM (SELECT q.Question,q.ItemA,q.ItemB,q.ItemC,q.ItemD,q.ItemE,q.ItemF, p.PaperID,p.Score,q.Answer,q.QuestionID,p.ItemID,q.Qtype ");
		sb.append(" FROM Question q");
		sb.append("		 RIGHT JOIN  Paperitem p on q.QuestionID =p.QuestionID)AS A ");
		sb.append("		 RIGHT JOIN Exam e ON A.PaperID =e.PaperID ");
		sb.append(" WHERE e.ExamID=? ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			ExamBean bean = null;
			while (rs.next()) {
				bean = toBean4(rs);

				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return list;
	}

	private ExamBean toBean4(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		ExamBean bean;
		bean = new ExamBean();
		bean.setQuestion(rs.getString("Question"));
		bean.setItemA(rs.getString("ItemA"));
		bean.setItemB(rs.getString("ItemB"));
		bean.setItemC(rs.getString("ItemC"));
		bean.setItemD(rs.getString("ItemD"));
		bean.setItemE(rs.getString("ItemE"));
		bean.setItemF(rs.getString("ItemF"));
		bean.setPaperID(rs.getLong("PaperID"));
		bean.setExamID(rs.getLong("ExamID"));
		bean.setScore(rs.getDouble("Score"));
		bean.setAnswer(rs.getString("Answer"));
		bean.setQuestionId(rs.getLong("QuestionID"));
		bean.setQtype(rs.getLong("Qtype"));
		return bean;
	}

	@Override
	public List<ExamBean> listScore(String stuName, String papName) {
		// TODO Auto-generated method stub
		List<ExamBean> list = new ArrayList<ExamBean>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" SELECT A.ExamID,A.StudentID,S.name,P.PaperID,P.PaperName,A.TotalScore ");
		sb.append(" from Exam  A  ");
		sb.append(" LEFT JOIN Student S on A.StudentID=S.StudentID ");
		sb.append(" LEFT JOIN Paper P on A.PaperID=P.PaperID");
		sb.append(" where 1=1 ");

		if (!SysFun.isNullOrEmpty(stuName)) {
			sb.append(" 	and S.name like ? ");
			stuName = "%" + stuName + "%";
			paramsList.add(stuName);
		}
		if (!SysFun.isNullOrEmpty(papName)) {
			sb.append(" 	and P.PaperName like ? ");
			papName = "%" + papName + "%";
			paramsList.add(papName);
		}
		sb.append("	and A.IsMark");
		sb.append(" order by A.ExamID ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			ExamBean bean = null;
			while (rs.next()) {
				bean = toBean5(rs);
				list.add(bean);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	private ExamBean toBean5(ResultSet rs) throws SQLException {
		ExamBean bean;
		bean = new ExamBean();
		bean.setExamID(rs.getLong("ExamID"));
		bean.setStudentID(rs.getLong("StudentID"));
		bean.setPaperID(rs.getLong("PaperID"));
		bean.setTotalScore(rs.getDouble("TotalScore"));
		bean.setName(rs.getString("name"));
		bean.setPaperName(rs.getString("PaperName"));
		return bean;
	}

	@Override
	public List<ExamBean> listStuName(String name) {
		// TODO Auto-generated method stub
		List<ExamBean> list = new ArrayList<ExamBean>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*, S.name As StudentName, B.PaperName AS PaperName ");
		sb.append(" from Exam A ");
		sb.append(" 	Left Join Student S On A.StudentID=S.StudentID ");
		sb.append(" 	Left Join Paper B On A.PaperID=B.PaperID ");
		sb.append(" Where 1=1 ");
		if (!SysFun.isNullOrEmpty(name)) {
			sb.append(" 	and S.LoginName = ? ");
			paramsList.add(name);
		}
		sb.append("  Order by A.ExamID ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();// 将泛型集合变为数组，因为在Dbpub中接收的是Object数组
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			ExamBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}
}