package edu.prj.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.PaperBean;
import edu.prj.dao.PaperDao;
import edu.prj.util.DbPub;

public class PaperDaoImpl implements PaperDao {

	@Override
	public Long insert(PaperBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(
				" Insert Into Paper(PaperName,TotalScore,PerScore,QuestionNum,ExamMinute,StartOn,EndOn,HasGenerate) ");
		sb.append(" values(?,?,?,?,?,?,?,?)");

		paramsList.add(bean.getPaperName());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getPerScore());
		paramsList.add(bean.getQuestionNum());
		paramsList.add(bean.getExamMinute());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getHasGenerate());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {

			sql = " select LAST_INSERT_ID()";
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
		sb.append(" delete from Paper ");
		sb.append(" where PaperID = ?");
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
	public Long update(PaperBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Paper set ");
		sb.append(" PaperName = ? ");
		sb.append(" ,TotalScore = ?  ");
		sb.append(" ,PerScore = ? ");
		sb.append(" ,QuestionNum = ? ");
		sb.append(" ,ExamMinute = ? ");
		sb.append(" ,StartOn = ? ");
		sb.append(" ,EndOn = ? ");
		sb.append(" ,HasGenerate = ? ");
		sb.append(" where PaperId = ? ");
		paramsList.add(bean.getPaperName());
		paramsList.add(bean.getTotalScore());
		paramsList.add(bean.getPerScore());
		paramsList.add(bean.getQuestionNum());
		paramsList.add(bean.getExamMinute());
		paramsList.add(bean.getStartOn());
		paramsList.add(bean.getEndOn());
		paramsList.add(bean.getHasGenerate());
		paramsList.add(bean.getPaperID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<PaperBean> list() {
		// TODO Auto-generated method stub
		List<PaperBean> list = new ArrayList<PaperBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Paper ");
		sb.append(" where 1=1 ");
		sb.append(" order by PaperID  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			PaperBean bean = null;
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

	private PaperBean toBean(ResultSet rs) throws SQLException {
		PaperBean bean;
		bean = new PaperBean();
		bean.setPaperID(rs.getLong("PaperID"));
		bean.setPaperName(rs.getString("PaperName"));
		bean.setTotalScore(rs.getDouble("TotalScore"));
		bean.setPerScore(rs.getDouble("PerScore"));
		bean.setQuestionNum(rs.getLong("QuestionNum"));
		bean.setExamMinute(rs.getLong("ExamMinute"));
		bean.setStartOn(rs.getTimestamp("Starton"));
		bean.setEndOn(rs.getTimestamp("EndOn"));
		bean.setHasGenerate(rs.getLong("HasGenerate"));
		return bean;
	}

	@Override
	public PaperBean load(Long id) {
		// TODO Auto-generated method stub
		PaperBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Paper ");
		sb.append(" where 1=1 ");
		sb.append(" 	and PaperID = ?  ");
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
		sb.append(" select count(1) from Paper ");
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
	public PaperBean loadByName(String name) {
		// TODO Auto-generated method stub
		PaperBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Paper ");
		sb.append(" where 1=1 ");
		sb.append(" 	and PaperName = ?  ");
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
			// TODO Auto-generated catch block
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
		sb.append(" select count(1) from Paper ");
		sb.append(" where PaperName = ?  ");
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
	public List<PaperBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<PaperBean> list = new ArrayList<PaperBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Paper ");
		sb.append(" where 1=1 ");
		sb.append(" 	and PaperName like ?  ");
		name = "%" + name + "%";
		paramsList.add(name);
		sb.append(" 	order by PaperID  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			PaperBean bean = null;
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