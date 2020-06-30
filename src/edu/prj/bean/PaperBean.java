package edu.prj.bean;

import java.util.Date;

public class PaperBean {
	private Long PaperID;// 试卷ID
	private String PaperName;// 试卷名称
	private Double TotalScore;// 总分
	private Double PerScore;// 每题分数
	private Long QuestionNum;// 题目数
	private Long ExamMinute;// 考试分钟
	private Date StartOn;// 有效开始日期
	private Date EndOn;// 有效结束日期
	private Long HasGenerate;// 是否已生成

	public Long getPaperID() {
		return PaperID;
	}

	public void setPaperID(Long paperID) {
		PaperID = paperID;
	}

	public String getPaperName() {
		return PaperName;
	}

	public void setPaperName(String paperName) {
		PaperName = paperName;
	}

	public Double getTotalScore() {
		return TotalScore;
	}

	public void setTotalScore(Double totalScore) {
		TotalScore = totalScore;
	}

	public Double getPerScore() {
		return PerScore;
	}

	public void setPerScore(Double perScore) {
		PerScore = perScore;
	}

	public Long getQuestionNum() {
		return QuestionNum;
	}

	public void setQuestionNum(Long questionNum) {
		QuestionNum = questionNum;
	}

	public Long getExamMinute() {
		return ExamMinute;
	}

	public void setExamMinute(Long examMinute) {
		ExamMinute = examMinute;
	}

	public Date getStartOn() {
		return StartOn;
	}

	public void setStartOn(Date startOn) {
		StartOn = startOn;
	}

	public Date getEndOn() {
		return EndOn;
	}

	public void setEndOn(Date endOn) {
		EndOn = endOn;
	}

	public Long getHasGenerate() {
		return HasGenerate;
	}

	public void setHasGenerate(Long hasGenerate) {
		HasGenerate = hasGenerate;
	}

}
