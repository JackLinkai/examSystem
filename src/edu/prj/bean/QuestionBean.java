package edu.prj.bean;

public class QuestionBean {
	private Integer QuestionID;//题库ID
	private Long QType;//题目类型
	private String Question;//题目
	private String ItemA;//选项A
	private String ItemB;//选项B
	private String ItemC;//选项C
	private String ItemD;//选项D
	private String ItemE;//选项E
	private String ItemF;//选项F
	private String Answer;//答案
	private Long SubjectID;//所属科目
	private String Tag;//标签
	private String SubjectName;//科目名称

	public Integer getQuestionID() {
		return QuestionID;
	}

	public void setQuestionID(Integer questionID) {
		QuestionID = questionID;
	}

	public Long getQType() {
		return QType;
	}

	public void setQType(Long qType) {
		QType = qType;
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

	public Long getSubjectID() {
		return SubjectID;
	}

	public void setSubjectID(Long subjectID) {
		SubjectID = subjectID;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

}
