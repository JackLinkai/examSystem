package edu.prj.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.ExamItemBean;
import edu.prj.dao.ExamItemDao;
import edu.prj.util.DbPub;

public class ExamItemDaoImpl implements ExamItemDao {

	@Override
	public Long insert(ExamItemBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into ExamItem(ExamID,QuestionID,StuAnswer,StdAnswer,StdScore,MarkResult,GainScore) ");
		sb.append(" values(?,?,?,?,?,?,?)");

		paramsList.add(bean.getExamID());
		paramsList.add(bean.getQuestionID());
		paramsList.add(bean.getStuAnswer());
		paramsList.add(bean.getStdAnswer());
		paramsList.add(bean.getStdScore());
		paramsList.add(bean.getMarkResult());
		paramsList.add(bean.getGainScore());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {

			sql = " select LAST_INSERT_ID()";
			Long result = DbPub.queryScalarLong(conn, sql);

			if (result > 0) {
				bean.setItemID(result);
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
		sb.append(" delete from ExamItem ");
		sb.append(" where ItemID = ?");
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
	public Long update(ExamItemBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Exam set ");
		sb.append(" ExamID = ? ");
		sb.append(" ,QuestionID = ?  ");
		sb.append(" ,StuAnswer = ? ");
		sb.append(" ,StdAnswer = ? ");
		sb.append(" ,StdScore = ? ");
		sb.append(" ,MarkResult = ? ");
		sb.append(" ,GainScore = ? ");
		sb.append(" where ItemID = ? ");

		paramsList.add(bean.getExamID());
		paramsList.add(bean.getQuestionID());
		paramsList.add(bean.getStuAnswer());
		paramsList.add(bean.getStdAnswer());
		paramsList.add(bean.getStdScore());
		paramsList.add(bean.getMarkResult());
		paramsList.add(bean.getGainScore());
		paramsList.add(bean.getItemID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<ExamItemBean> list() {
		// TODO Auto-generated method stub
		List<ExamItemBean> list = new ArrayList<ExamItemBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from ExamItem ");
		sb.append(" where 1=1 ");
		sb.append(" order by ItemID  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			ExamItemBean bean = null;
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

	private ExamItemBean toBean(ResultSet rs) throws SQLException {
		ExamItemBean bean;
		bean = new ExamItemBean();
		bean.setItemID(rs.getLong("ItemID"));
		bean.setExamID(rs.getLong("ExamID"));
		bean.setQuestionID(rs.getLong("QuestionID"));
		bean.setStuAnswer(rs.getString("StuAnswer"));
		bean.setStdAnswer(rs.getString("StdAnswer"));
		bean.setStdScore(rs.getDouble("StdScore"));
		bean.setMarkResult(rs.getLong("MarkResult"));
		bean.setGainScore(rs.getDouble("GainScore"));
		return bean;
	}

	@Override
	public ExamItemBean load(Long id) {
		// TODO Auto-generated method stub
		ExamItemBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from ExamItem ");
		sb.append(" where 1=1 ");
		sb.append(" 	and ItemID = ?  ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			if (rs.next()) {
				bean = toBean(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbPub.close(conn);

		return bean;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select count(1) from ExamItem ");
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
	public List<ExamItemBean> listByName(Long id) {
		// TODO Auto-generated method stub
		List<ExamItemBean> list = new ArrayList<ExamItemBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" SELECT *FROM ExamItem ");
		sb.append(" where ExamID=? ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			ExamItemBean bean = null;
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

	@Override
	public Long sum(Long id) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" SELECT GainScore AS sum FROM ExamItem ");
		sb.append(" where ExamID=? ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long Score() {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" UPDATE  ExamItem SET MarkResult=1 ,GainScore=StdScore ");
		sb.append(" WHERE StuAnswer=StdAnswer; ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;

	}

}