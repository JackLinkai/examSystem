package edu.prj.bean;

public class StudentBean {

	public StudentBean() {
	}

	private Long StudentID;// 学生ID
	private String LoginName;// 账号
	private String LoginPwd; // 密码
	private String name;// 姓名
	private Long IsDisabled;// 是否禁用
	private Long RoomID;// 班级ID

	public Long getStudentID() {
		return StudentID;
	}

	public void setStudentID(Long studentID) {
		StudentID = studentID;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public String getLoginPwd() {
		return LoginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		LoginPwd = loginPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIsDisabled() {
		return IsDisabled;
	}

	public void setIsDisabled(Long isDisabled) {
		IsDisabled = isDisabled;
	}

	public Long getRoomID() {
		return RoomID;
	}

	public void setRoomID(Long roomID) {
		RoomID = roomID;
	}

}
