package edu.prj.bean;

import java.util.Date;

public class ExamBean {
	private Long ExamID;// 考试ID
	private Long StudentID;// 学生ID
	private Long PaperID;// 试卷ID
	private Date StartOn;// 有效开始时间
	private Date EndOn;// 有效结束时间
	private Long IsMark;// 是否阅卷
	private Double TotalScore;// 总分
	private String name;// 学生姓名
	private String PaperName;// 试卷名称
	private String Question;// 题目
	private String ItemA;// 选项A
	private String ItemB;// 选项B
	private String ItemC;// 选项C
	private String ItemD;// 选项D
	private String ItemE;// 选项E
	private String ItemF;// 选项F
	private String Answer;// 答案
	private Double Score;// 该题分数
	private Long QuestionId;// 题库ID
	private Long Qtype;// 题目类型

	public Long getExamID() {
		return ExamID;
	}

	public void setExamID(Long examID) {
		ExamID = examID;
	}

	public Long getStudentID() {
		return StudentID;
	}

	public void setStudentID(Long studentID) {
		StudentID = studentID;
	}

	public Long getPaperID() {
		return PaperID;
	}

	public void setPaperID(Long paperID) {
		PaperID = paperID;
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

	public Long getIsMark() {
		return IsMark;
	}

	public void setIsMark(Long isMark) {
		IsMark = isMark;
	}

	public Double getTotalScore() {
		return TotalScore;
	}

	public void setTotalScore(Double totalScore) {
		TotalScore = totalScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaperName() {
		return PaperName;
	}

	public void setPaperName(String paperName) {
		PaperName = paperName;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getItemA() {
		return ItemA;
	}

	public void setItemA(String itemA) {
		ItemA = itemA;
	}

	public String getItemB() {
		return ItemB;
	}

	public void setItemB(String itemB) {
		ItemB = itemB;
	}

	public String getItemC() {
		return ItemC;
	}

	public void setItemC(String itemC) {
		ItemC = itemC;
	}

	public String getItemD() {
		return ItemD;
	}

	public void setItemD(String itemD) {
		ItemD = itemD;
	}

	public String getItemE() {
		return ItemE;
	}

	public void setItemE(String itemE) {
		ItemE = itemE;
	}

	public String getItemF() {
		return ItemF;
	}

	public void setItemF(String itemF) {
		ItemF = itemF;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public Double getScore() {
		return Score;
	}

	public void setScore(Double score) {
		Score = score;
	}

	public Long getQuestionId() {
		return QuestionId;
	}

	public void setQuestionId(Long questionId) {
		QuestionId = questionId;
	}

	public Long getQtype() {
		return Qtype;
	}

	public void setQtype(Long qtype) {
		Qtype = qtype;
	}

}
