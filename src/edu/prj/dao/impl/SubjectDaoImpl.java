package edu.prj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.SubjectBean;
import edu.prj.dao.SubjectDao;
import edu.prj.util.DbPub;

public class SubjectDaoImpl implements SubjectDao {
	/**
	 * 插入数据
	 */
	@Override
	public Long insert(SubjectBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();

		sb.append("insert into Subject(SubjectName,Status)");
		sb.append("values (?,?)");

		paramsList.add(bean.getSubjectName());
		paramsList.add(bean.getStatus());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num > 0) {
			sql = "SELECT LAST_INSERT_ID()"; // 或者Select @@identity
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				bean.setSubjectID(result);
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
		sb.append(" delete from Subject ");
		sb.append(" where SubjectID = ?");
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
	public Long update(SubjectBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Subject set ");
		sb.append(" SubjectName = ? ");
		sb.append(" ,Status = ?  ");
		sb.append(" where SubjectID = ? ");
		paramsList.add(bean.getSubjectName());
		paramsList.add(bean.getStatus());
		paramsList.add(bean.getSubjectID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<SubjectBean> list() {
		// TODO Auto-generated method stub
		List<SubjectBean> list = new ArrayList<SubjectBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Subject ");
		sb.append(" where 1=1 ");
		sb.append(" order by SubjectID  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			SubjectBean bean = null;
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

	public SubjectBean toBean(ResultSet rs) throws SQLException {
		SubjectBean bean;
		bean = new SubjectBean();
		bean.setSubjectID(rs.getLong("SubjectID"));
		bean.setSubjectName(rs.getString("SubjectName"));
		bean.setStatus(rs.getLong("Status"));
		return bean;
	}

	@Override
	public SubjectBean load(Long id) {
		// TODO Auto-generated method stub
		SubjectBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Subject ");
		sb.append(" where 1=1 ");
		sb.append(" 	and SubjectID = ?  ");
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
		sb.append(" select count(1) from Subject ");
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
	public SubjectBean loadByName(String name) {
		// TODO Auto-generated method stub
		SubjectBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Subject ");
		sb.append(" where 1=1 ");
		sb.append(" 	and SubjectName = ?  ");
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
		sb.append(" select count(1) from Subject ");
		sb.append(" where 1=1 ");
		sb.append(" 	and SubjectName like ?  ");
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
	public List<SubjectBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<SubjectBean> list = new ArrayList<SubjectBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Subject ");
		sb.append(" where 1=1 ");
		sb.append(" 	and SubjectName like ?  ");
		sb.append(" 	order by SubjectID  ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			SubjectBean bean = null;
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
