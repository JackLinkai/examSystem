package edu.prj.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.GradeBean;
import edu.prj.dao.GradeDao;
import edu.prj.util.DbPub;

public class GradeDaoImpl implements GradeDao {
	/**
	 * 插入数据
	 */
	@Override
	public Long insert(GradeBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into Grade(GradeName,CreateOn,UpdateOn) ");
		sb.append(" values(?,?,?)");
		paramsList.add(bean.getGradeName());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getUpdateOn());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;

		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num > 0) {
			sql = "SELECT LAST_INSERT_id()";
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				bean.setGradeId(result);
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

		sb.append(" delete from Grade ");
		sb.append(" where GradeID = ?");
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
	public Long update(GradeBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Grade set ");
		sb.append(" GradeName = ? ");
		sb.append(" ,CreateOn = ?  ");
		sb.append(" ,CreateBy = ?  ");
		sb.append(" ,UpdateOn = ?  ");
		sb.append(" ,UpdateBy = ?  ");
		sb.append(" where GradeID = ? ");
		paramsList.add(bean.getGradeName());
		paramsList.add(bean.getCreateOn());
		paramsList.add(bean.getCreateBy());
		paramsList.add(bean.getUpdateOn());
		paramsList.add(bean.getUpdateBy());
		paramsList.add(bean.getGradeId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);
		if (num > 0) {
			sql = "SELECT LAST_INSERT_ID()"; // 或者Select @@identity
			Long result = DbPub.queryScalarLong(conn, sql);
			if (result > 0) {
				bean.setGradeId(result);
				num = result;
			}
		}
		DbPub.close(conn);

		return num;

	}

	@Override
	public List<GradeBean> list() {
		// TODO Auto-generated method stub
		List<GradeBean> list = new ArrayList<GradeBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Grade ");
		sb.append(" where 1=1 ");
		sb.append(" order by GradeID  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			GradeBean bean = null;
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

	public GradeBean toBean(ResultSet rs) throws SQLException {
		GradeBean bean;
		bean = new GradeBean();
		bean.setGradeId(rs.getLong("GradeId"));
		bean.setGradeName(rs.getString("GradeName"));
		bean.setCreateOn(rs.getTimestamp("CreateOn"));
		bean.setCreateBy(rs.getLong("CreateBy"));
		bean.setUpdateOn(rs.getTimestamp("UpdateOn"));
		bean.setUpdateBy(rs.getLong("UpdateBy"));
		return bean;

	}

	@Override
	public GradeBean load(Long id) {
		// TODO Auto-generated method stub
		GradeBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Grade ");
		sb.append(" where 1=1 ");
		sb.append(" 	and GradeID = ?  ");
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
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select count(1) from Grade ");
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
	public GradeBean loadByName(String name) {
		// TODO Auto-generated method stub
		GradeBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Grade ");
		sb.append(" where 1=1 ");
		sb.append(" 	and GradeName = ?  ");
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
		sb.append(" select count(1) from Grade ");
		sb.append(" where 1=1 ");
		sb.append(" 	and GradeName like ?  ");
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
	public List<GradeBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<GradeBean> list = new ArrayList<GradeBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Grade ");
		sb.append(" where 1=1 ");
		sb.append(" 	and GradeName like ?  ");
		sb.append(" 	order by gradeID  ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			GradeBean bean = null;
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
