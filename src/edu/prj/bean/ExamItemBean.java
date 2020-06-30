package edu.prj.bean;

public class ExamItemBean {
	private Long ItemID;// 项目ID
	private Long ExamID;// 考试ID
	private Long QuestionID;// 题目ID
	private String StuAnswer;// 学生答案
	private String StdAnswer;// 标准答案
	private Double StdScore;// 标准分数
	private Long MarkResult;// 阅卷结果
	private Double GainScore;// 该题得分

	public Long getItemID() {
		return ItemID;
	}

	public void setItemID(Long itemID) {
		ItemID = itemID;
	}

	public Long getExamID() {
		return ExamID;
	}

	public void setExamID(Long examID) {
		ExamID = examID;
	}

	public Long getQuestionID() {
		return QuestionID;
	}

	public void setQuestionID(Long questionID) {
		QuestionID = questionID;
	}

	public String getStuAnswer() {
		return StuAnswer;
	}

	public void setStuAnswer(String stuAnswer) {
		StuAnswer = stuAnswer;
	}

	public String getStdAnswer() {
		return StdAnswer;
	}

	public void setStdAnswer(String stdAnswer) {
		StdAnswer = stdAnswer;
	}

	public Double getStdScore() {
		return StdScore;
	}

	public void setStdScore(Double stdScore) {
		StdScore = stdScore;
	}

	public Long getMarkResult() {
		return MarkResult;
	}

	public void setMarkResult(Long markResult) {
		MarkResult = markResult;
	}

	public Double getGainScore() {
		return GainScore;
	}

	public void setGainScore(Double gainScore) {
		GainScore = gainScore;
	}

}
