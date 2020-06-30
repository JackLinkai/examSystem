package edu.prj.dao.impl;

import java.sql.*;
import java.util.*;

import edu.prj.bean.TeacherBean;
import edu.prj.dao.TeacherDao;
import edu.prj.util.DbPub;

public class TeacherDaoImpl implements TeacherDao {

	@Override
	public Long insert(TeacherBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into Teacher(loginName,loginPwd,name,isDisabled) ");
		sb.append(" values(?, ?, ?, ?)");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getName());
		paramsList.add(bean.getIsDisabled());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {

			sql = " select LAST_INSERT_ID()";
			Long result = DbPub.queryScalarLong(conn, sql);

			if (result > 0) {
				bean.setTeacherId(result);
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
		sb.append(" delete from Teacher ");
		sb.append(" where teacherId = ?");
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
	public Long update(TeacherBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update Teacher set ");
		sb.append(" LoginName = ? ");
		sb.append(" ,LoginPwd = ?  ");
		sb.append(" ,name = ? ");
		sb.append(" ,isDisabled = ? ");
		sb.append(" where teacherId = ? ");
		paramsList.add(bean.getLoginName());
		paramsList.add(bean.getLoginPwd());
		paramsList.add(bean.getName());
		paramsList.add(bean.getIsDisabled());
		paramsList.add(bean.getTeacherId());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	@Override
	public List<TeacherBean> list() {
		// TODO Auto-generated method stub
		List<TeacherBean> list = new ArrayList<TeacherBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" order by teacherId  ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			TeacherBean bean = null;
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
	public List<TeacherBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<TeacherBean> list = new ArrayList<TeacherBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" 	and name like ?  ");
		sb.append(" 	order by teacherId  ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			TeacherBean bean = null;
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
	public TeacherBean load(Long id) {
		// TODO Auto-generated method stub
		TeacherBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" 	and teacherId = ?  ");
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
		sb.append(" select count(1) from Teacher");
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
	public TeacherBean loadByName(String name) {
		// TODO Auto-generated method stub
		TeacherBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" 	and loginName = ?  ");
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
		sb.append(" select count(1) from Teacher ");
		sb.append(" where 1=1 ");
		sb.append(" 	and loginName like ?  ");
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.queryScalarLong(conn, sql, params);

		DbPub.close(conn);

		return num;
	}

	private TeacherBean toBean(ResultSet rs) throws SQLException {
		TeacherBean bean;
		bean = new TeacherBean();
		bean.setTeacherId(rs.getLong("TeacherId"));
		bean.setLoginName(rs.getString("loginName"));
		bean.setLoginPwd(rs.getString("loginPwd"));
		bean.setName(rs.getString("name"));
		bean.setIsDisabled(rs.getLong("isDisabled"));
		return bean;
	}

}
