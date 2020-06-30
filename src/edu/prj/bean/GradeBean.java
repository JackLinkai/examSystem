package edu.prj.bean;

import java.util.Date;

public class GradeBean{
	private Long GradeId;//年级ID
	private String GradeName;//年级名称
	private Date CreateOn;//创建时间
	private Long CreateBy;//创建人员
	private Date UpdateOn;//修改时间
	private Long UpdateBy;//修改人员

	public GradeBean() {
	}

	public Long getGradeId() {
		return GradeId;
	}

	public void setGradeId(Long gradeId) {
		GradeId = gradeId;
	}

	public String getGradeName() {
		return GradeName;
	}

	public void setGradeName(String gradeName) {
		GradeName = gradeName;
	}

	public Date getCreateOn() {
		return CreateOn;
	}

	public void setCreateOn(Date createOn) {
		CreateOn = createOn;
	}

	public Long getCreateBy() {
		return CreateBy;
	}

	public void setCreateBy(Long createBy) {
		CreateBy = createBy;
	}

	public Date getUpdateOn() {
		return UpdateOn;
	}

	public void setUpdateOn(Date updateOn) {
		UpdateOn = updateOn;
	}

	public Long getUpdateBy() {
		return UpdateBy;
	}

	public void setUpdateBy(Long updateBy) {
		UpdateBy = updateBy;
	}

}
