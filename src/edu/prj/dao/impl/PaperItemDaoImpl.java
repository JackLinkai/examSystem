package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import edu.prj.bean.PaperBean;
import edu.prj.bean.PaperItemBean;
import edu.prj.bean.QuestionBean;
import edu.prj.dao.PaperItemDao;
import edu.prj.util.DbPub;

public class PaperItemDaoImpl implements PaperItemDao {

	@Override
	public Long insert(PaperBean bean, List<QuestionBean> list) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Insert Paperitem(PaperID,QuestionID,Answer,Score ) ");
		sb.append("values(?,?,?,?) ");
		for (Object obj : list) {
			QuestionBean question = (QuestionBean) obj;
			paramsList.add(bean.getPaperID());
			paramsList.add(question.getQuestionID());
			paramsList.add(question.getAnswer());
			paramsList.add(bean.getPerScore());
		}

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {
			sql = "select LAST_INSERT_ID()";// 或者 select @@identity
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				bean.setPaperID(result);
				num = result;
			}
		}
		DbPub.close(conn);
		return num;
	}

	@Override
	public Long insert(PaperItemBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Insert Paperitem(PaperID,QuestionID,Answer,Score ) values(?,?,?,?)");

		paramsList.add(bean.getPaperID());
		paramsList.add(bean.getQuestionID());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getScore());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {
			sql = "select LAST_INSERT_ID()";// 或者 select @@identity
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				bean.setPaperID(result);
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
		sb.append(" delete from Paperitem ");
		sb.append(" where ItemID=? ");
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
	public Long update(PaperItemBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" update Paperitem set ");
		sb.append(" PaperID =? ");
		sb.append(" ,QuestionID=? ");
		sb.append(" ,Answer=? ");
		sb.append(" ,Score=? ");
		sb.append(" where ItemId=? ");

		paramsList.add(bean.getPaperID());
		paramsList.add(bean.getQuestionID());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getScore());
		paramsList.add(bean.getItemID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();// 将泛型集合变为数组，因为在Dbpub中接收的是Object数组

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);
		return num;
	}

	@Override
	public List<PaperItemBean> list() {
		// TODO Auto-generated method stub
		List<PaperItemBean> list = new ArrayList<PaperItemBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A,B.paperName As PaperName,C.question As Question ");
		sb.append(" from Paperitem A ");
		sb.append(" left join paper B on A.paperId=B.paperId ");
		sb.append(" left join question C on A.questionId=C.questionId ");
		sb.append(" where 1=1 ");
		sb.append(" order by A.ItemId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();// 将泛型集合变为数组，因为在Dbpub中接收的是Object数组

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			PaperItemBean bean = null;
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

	private PaperItemBean toBean(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		PaperItemBean bean;
		bean = new PaperItemBean();
		bean.setAnswer(rs.getString("Answer"));
		bean.setScore(rs.getDouble("Score"));
		bean.setItemID(rs.getLong("ItemID"));
		bean.setPapername(rs.getString("paperName"));
		bean.setQuestion(rs.getString("question"));
		return bean;
	}

	@Override
	public PaperItemBean load(Long id) {
		// TODO Auto-generated method stub
		PaperItemBean bean = null;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Paperitem ");
		sb.append(" where 1=1 ");
		sb.append(" and ItemId=? ");
		paramsList.add(id);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();// 将泛型集合变为数组，因为在Dbpub中接收的是Object数组
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
		sb.append(" select count(1) from Paperitem ");
		sb.append(" where 1=1 ");
		String sql = sb.toString();
		Object[] params = paramsList.toArray();// 将泛型集合变为数组，因为在Dbpub中接收的是Object数组

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);
		return num;
	}

	@Override
	public List<PaperItemBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<PaperItemBean> list = new ArrayList<PaperItemBean>();
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(
				" select A.itemId,B.paperName,C.question,C.itemA,C.itemB,C.itemC,C.itemD,C.itemE,C.itemF,A.answer,A.score ");
		sb.append(" from Paperitem A ");
		sb.append(" left join Paper B on A.paperId=B.paperId ");
		sb.append(" left join Question C on A.questionId=C.questionId ");
		sb.append(" where 1=1 ");
		sb.append(" and B.papername = ? ");
		paramsList.add(name);
		sb.append(" order by A.itemId ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			PaperItemBean bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);
				list.add(bean);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;
	}

	private PaperItemBean toBeanEx(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		PaperItemBean bean;
		bean = new PaperItemBean();
		bean.setAnswer(rs.getString("Answer"));
		bean.setScore(rs.getDouble("Score"));
		bean.setItemID(rs.getLong("ItemID"));
		bean.setPapername(rs.getString("paperName"));
		bean.setQuestion(rs.getString("question"));
		bean.setItemA(rs.getString("itemA"));
		bean.setItemB(rs.getString("itemB"));
		bean.setItemC(rs.getString("itemC"));
		bean.setItemD(rs.getString("itemD"));
		bean.setItemE(rs.getString("itemE"));
		bean.setItemF(rs.getString("itemF"));

		return bean;
	}

}