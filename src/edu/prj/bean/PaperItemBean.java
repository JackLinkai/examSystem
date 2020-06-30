package edu.prj.bean;

public class PaperItemBean {
	private Long ItemID;// 项目ID
	private Long PaperID;// 试卷ID
	private Long QuestionID;// 题目ID
	private String Answer;// 答案
	private Double Score;// 该题分数
	private String Papername;// 试卷名称
	private String Question;// 题目
	private String ItemA;// 选项A
	private String ItemB;// 选项B
	private String ItemC;// 选项C
	private String ItemD;// 选项D
	private String ItemE;// 选项E
	private String ItemF;// 选项F

	public Long getItemID() {
		return ItemID;
	}

	public void setItemID(Long itemID) {
		ItemID = itemID;
	}

	public String getPapername() {
		return Papername;
	}

	public void setPapername(String papername) {
		Papername = papername;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public Long getPaperID() {
		return PaperID;
	}

	public void setPaperID(Long paperID) {
		PaperID = paperID;
	}

	public Long getQuestionID() {
		return QuestionID;
	}

	public void setQuestionID(Long questionID) {
		QuestionID = questionID;
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
}
