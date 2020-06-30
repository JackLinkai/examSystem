package edu.prj.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.prj.bean.ClassRoomBean;
import edu.prj.dao.ClassRoomDao;
import edu.prj.util.DbPub;

public class ClassRoomDaoImpl implements ClassRoomDao {
	/**
	 * 插入数据
	 */
	@Override
	public Long insert(ClassRoomBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Insert Into ClassRoom(RoomName,GradeID)");
		sb.append(" values(?, ?)");
		paramsList.add(bean.getRoomName());
		paramsList.add(bean.getGradeID());

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		// 创建三大对象
		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		if (num > 0) {

			sql = " select LAST_INSERT_ID()";
			Long result = DbPub.queryScalarLong(conn, sql);

			if (result > 0) {
				bean.setRoomID(result);
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
		sb.append(" delete from ClassRoom ");
		sb.append(" where RoomId=?");
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
	public Long update(ClassRoomBean bean) {
		// TODO Auto-generated method stub
		Long num = 0L;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" update ClassRoom set ");
		sb.append(" RoomName = ? ");
		sb.append(" ,GradeID = ? ");
		sb.append(" where RoomID = ? ");
		paramsList.add(bean.getRoomName());
		paramsList.add(bean.getGradeID());
		paramsList.add(bean.getRoomID());
		

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		conn = DbPub.getConn();
		num = DbPub.update(conn, sql, params);

		DbPub.close(conn);

		return num;

	}

	@Override
	public List<ClassRoomBean> list() {
		// TODO Auto-generated method stub
		// 定义一个泛型接受表的数据
		List<ClassRoomBean> list = new ArrayList<ClassRoomBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" Select A.*,B.gradeName As gradeName ");
		sb.append(" From ClassRoom A ");
		sb.append(" 	Left Join Grade B On A.gradeID=B.gradeID ");
		sb.append("  where 1=1");
		sb.append("  Order by A.RoomID ");

		String sql = sb.toString();
		Object[] params = paramsList.toArray();
		Connection conn = null;
		ResultSet rs = null;

		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);

		try {
			ClassRoomBean bean = null;
			while (rs.next()) {
				bean = toBeanEx(rs);
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbPub.close(conn);

		return list;

	}

	private ClassRoomBean toBean(ResultSet rs) throws SQLException {
		ClassRoomBean bean;
		bean = new ClassRoomBean();
		bean.setRoomID(rs.getLong("RoomID"));
		bean.setRoomName(rs.getString("RoomName"));
		bean.setGradeID(rs.getLong("GradeId"));
		return bean;

	}

	private ClassRoomBean toBeanEx(ResultSet rs) throws SQLException {
		ClassRoomBean bean;
		bean = new ClassRoomBean();
		bean.setRoomID(rs.getLong("RoomID"));
		bean.setRoomName(rs.getString("RoomName"));
		bean.setGradeID(rs.getLong("GradeId"));
		bean.setGradeName(rs.getString("GradeName"));
		return bean;
	}

	@Override
	public ClassRoomBean load(Long id) {
		// TODO Auto-generated method stub
		ClassRoomBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From ClassRoom");
		sb.append(" Where 1=1 ");
		sb.append("  And RoomID=?");
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
		sb.append("Select count(1) From ClassRoom");
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
	public ClassRoomBean loadByName(String name) {
		// TODO Auto-generated method stub
		ClassRoomBean bean = null;

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append("Select * From ClassRoom");
		sb.append(" Where 1=1 ");
		sb.append("  And RoomName=?");
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
		sb.append("Select count(1) From ClassRoom");
		sb.append(" Wherre 1=1 ");
		sb.append("  And RoomName=?");
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
	public List<ClassRoomBean> listByName(String name) {
		// TODO Auto-generated method stub
		List<ClassRoomBean> list = new ArrayList<ClassRoomBean>();

		StringBuffer sb = new StringBuffer();
		List<Object> paramsList = new ArrayList<Object>();
		sb.append(" select * from ClassRoom ");
		sb.append(" where 1=1 ");
		sb.append(" 	and RoomName like ?  ");
		sb.append(" 	order by roomId  ");
		name = "%" + name + "%";
		paramsList.add(name);

		String sql = sb.toString();
		Object[] params = paramsList.toArray();

		Connection conn = null;
		ResultSet rs = null;
		conn = DbPub.getConn();
		rs = DbPub.query(conn, sql, params);
		try {
			ClassRoomBean bean = null;
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
