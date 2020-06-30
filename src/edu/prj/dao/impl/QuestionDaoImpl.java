package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuvei.common.SysFun;


import edu.prj.bean.QuestionBean;
import edu.prj.dao.QuestionDao;
import edu.prj.util.DbPub;

public class QuestionDaoImpl implements QuestionDao {

	@Override
	public Long insert(QuestionBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append("insert into Question(QType,Question,ItemA,ItemB,ItemC,ItemD,ItemE,ItemF,Answer,SubjectID,Tag)");
		sb.append("values (?,?,?,?,?,?,?,?,?,?,?)");

		paramsList.add(bean.getQType());
		paramsList.add(bean.getQuestion());
		paramsList.add(bean.getItemA());
		paramsList.add(bean.getItemB());
		paramsList.add(bean.getItemC());
		paramsList.add(bean.getItemD());
		paramsList.add(bean.getItemE());
		paramsList.add(bean.getItemF());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getSubjectID());
		paramsList.add(bean.getTag());
		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num > 0) {
			// sql = "Select max(id) from Student ";
			sql = "SELECT LAST_INSERT_id()"; // 或者Select @@identity
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				bean.setQuestionID(Integer.parseInt(result + ""));
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
		sb.append(" delete from Question ");
		sb.append(" where QuestionId = ?");
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
	public Long update(QuestionBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append(" Update Question Set ");
		sb.append(" QType = ?");
		sb.append(" ,Question = ?");
		sb.append(" ,ItemA = ? ");
		sb.append(" ,ItemB = ? ");
		sb.append(" ,ItemC = ? ");
		sb.append(" ,ItemD = ? ");
		sb.append(" ,ItemE = ? ");
		sb.append(" ,ItemF = ? ");
		sb.append(" ,Answer = ? ");
		sb.append(" ,SubjectID = ? ");
		sb.append(" ,Tag = ? ");
		sb.append("    Where QuestionID = ?");
		paramsList.add(bean.getQType());
		paramsList.add(bean.getQuestion());
		paramsList.add(bean.getItemA());
		paramsList.add(bean.getItemB());
		paramsList.add(bean.getItemC());
		paramsList.add(bean.getItemD());
		paramsList.add(bean.getItemE());
		paramsList.add(bean.getItemF());
		paramsList.add(bean.getAnswer());
		paramsList.add(bean.getSubjectID());
		paramsList.add(bean.getTag());
		paramsList.add(bean.getQuestionID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		DbPub.close(conn);

		return num;
	}

	@Override
	public List<QuestionBean> list() {
		// TODO Auto-generated method stub
		List<QuestionBean> list = new ArrayList<QuestionBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*,B.SubjectName As SubjectName ");
		sb.append(" From Question A ");
		sb.append(" 	Left Join Subject B On A.SubjectID=B.SubjectID ");
		sb.append(" Where 1=1 ");
		sb.append("  Order by A.QuestionID ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			QuestionBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);

		return list;
	}

	private QuestionBean toBean(ResultSet rs) throws SQLException {
		QuestionBean bean;
		bean = new QuestionBean();
		bean.setQuestionID(rs.getInt("QuestionID"));
		bean.setQType(rs.getLong("QType"));
		bean.setQuestion(rs.getString("Question"));
		bean.setItemA(rs.getString("ItemA"));
		bean.setItemB(rs.getString("ItemB"));
		bean.setItemC(rs.getString("ItemC"));
		bean.setItemD(rs.getString("ItemD"));
		bean.setItemE(rs.getString("ItemE"));
		bean.setItemF(rs.getString("ItemF"));
		bean.setAnswer(rs.getString("Answer"));
		bean.setSubjectID(rs.getLong("SubjectID"));
		bean.setTag(rs.getString("Tag"));
		bean.setSubjectName(rs.getString("SubjectName"));
		return bean;
	}

	@Override
	public QuestionBean load(Long id) {
		// TODO Auto-generated method stub
		QuestionBean bean = null;
		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select A.*,S.SubjectName As SubjectName ");
		sb.append(" from Question A ");
		sb.append(" 	Left join Subject S on A.SubjectID=S.SubjectID ");
		sb.append(" 	Where 1=1 ");
		sb.append("  And A.QuestionID=?");		
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
		sb.append("Select count(1) From Question");
		sb.append(" Wherre 1=1 ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public QuestionBean loadByName(String name) {
		// TODO Auto-generated method stub
		QuestionBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From Question");
		sb.append(" Where 1=1 ");
		sb.append("  And Question=?");
		paramsList.add(name);

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
			e.printStackTrace();
		}

		DbPub.close(conn);
		return bean;
	}

	@Override
	public Long countByName(String name) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select count(1) From Question");
		sb.append(" Wherre 1=1 ");
		sb.append("  And QuestionID=?");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<QuestionBean> listByName(String name, String type) {
		// TODO Auto-generated method stub
		List<QuestionBean> list = new ArrayList<QuestionBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select A.*,S.SubjectName As SubjectName ");
		sb.append(" from Question A ");
		sb.append(" Left join Subject S on A.SubjectID=S.SubjectID ");
		sb.append(" Where 1=1 ");

		if (!SysFun.isNullOrEmpty(name)) {
			sb.append("  and S.SubjectName like ?");
			name = "%" + name + "%";
			paramsList.add(name);
		}
		if (!SysFun.isNullOrEmpty(type)) {
			sb.append("  and QType  = ?");
			paramsList.add(type);
		}
		sb.append("      order by A.QuestionID");

		  String sql = sb.toString();
	        Object[] params = paramsList.toArray();
	        Connection conn = null;
	        ResultSet rs = null;

	        conn = DbPub.getConn();
	        rs = DbPub.query(conn, sql, params);

	        try {
	            QuestionBean bean = null;
	            while (rs.next()) {
	                bean = toBean(rs);
	                list.add(bean);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        DbPub.close(conn);

	        return list;

	}

	@Override
	public List<QuestionBean> listByQType(Integer type) {
		// TODO Auto-generated method stub
		List<QuestionBean> list = new ArrayList<QuestionBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*,B.SubjectName As SubjectName ");
		sb.append(" From Question A ");
		sb.append(" 	Left Join Subject B On A.SubjectID=B.SubjectID ");
		sb.append(" Where 1=1 ");
		sb.append(" 	and QType = ?  ");
		sb.append(" 	order by QuestionID  ");
		paramsList.add(type);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			QuestionBean bean = null;
			while (rs.next()) {
				bean = toBean(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);
		return list;

	}

}