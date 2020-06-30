package edu.prj.bean;

public class ClassRoomBean {

	
	private Long RoomID;// 班级ID
	private String RoomName;// 班级名称
	private Long GradeID;// 年级ID
	private String GradeName;//年级名称
	public String getGradeName() {
		return GradeName;
	}

	public void setGradeName(String gradeName) {
		GradeName = gradeName;
	}

	public Long getRoomID() {
		return RoomID;
	}

	public void setRoomID(Long roomID) {
		RoomID = roomID;
	}

	public String getRoomName() {
		return RoomName;
	}

	public void setRoomName(String roomName) {
		RoomName = roomName;
	}

	public Long getGradeID() {
		return GradeID;
	}

	public void setGradeID(Long gradeID) {
		GradeID = gradeID;
	}

}
